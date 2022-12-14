 package powers;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import helpers.ModHelper;
import powers.minstrel.ArmyPaeonPower;


 public class TwoAttackPower extends AbstractMinstrelPower {
   public static final String POWER_ID = ModHelper.MakePath(TwoAttackPower.class.getSimpleName());
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


   public TwoAttackPower(AbstractCreature owner, int amount,int num) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.amount2 = num;
     updateDescription();
     loadRegion("doubleTap");
   }


   @Override
   public void updateDescription() {
//     this.description = String.format((this.amount > 1) ? DESCRIPTIONS[1] : DESCRIPTIONS[0], new Object[] { Integer.valueOf(this.amount) });
     this.description = String.format(DESCRIPTIONS[0], new Object[] { Integer.valueOf(this.amount2) });
   }


   @Override
   public void onUseCard(AbstractCard card, UseCardAction action) {
     if (card.type == AbstractCard.CardType.ATTACK &&
       !card.purgeOnUse && this.amount > 0) {
       flash();
       AbstractMonster m = null;
       if (action.target != null) {
         m = (AbstractMonster)action.target;
       }

       AbstractCard tmp = card.makeSameInstanceOf();
       AbstractDungeon.player.limbo.addToBottom(tmp);
       tmp.current_x = card.current_x;
       tmp.current_y = card.current_y;
       tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
       tmp.target_y = Settings.HEIGHT / 2.0F;
       if (m != null) {
         tmp.calculateCardDamage(m);
       }

       tmp.purgeOnUse = true;
       for (int i = 0; i < amount2-1; i++) {
         AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
       }

       this.amount--;
       if (this.amount == 0)
         addToTop((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
     }
   }
 }

