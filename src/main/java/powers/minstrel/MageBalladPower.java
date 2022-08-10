package powers.minstrel;

import actions.SelectCardToHandAction;
import cards.minstrel.ArmyPaeon;
import cards.minstrel.DeathRain;
import cards.minstrel.Quick;
import cards.minstrel.Straight;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import helpers.ModHelper;
import powers.AbstractMinstrelPower;

import java.util.ArrayList;
import java.util.Random;

public class MageBalladPower extends AbstractMinstrelPower {
    public static final String POWER_ID = ModHelper.MakePath(MageBalladPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public int poetSoulAmount = 0;

    public MageBalladPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        loadRegion("rupture");
        AbstractPower poetSoulPower = AbstractDungeon.player.getPower(PoetSoulPower.POWER_ID);
        if (poetSoulPower != null && poetSoulPower.amount != 0) {
            poetSoulAmount = poetSoulPower.amount * 10;
        }
        updateDescription();
    }

    @Override
    public void onStackPower(AbstractPower power) {
        if (power.ID.equals(PoetSoulPower.POWER_ID)) {
            flash();
            AbstractPower poetSoulPower = AbstractDungeon.player.getPower(PoetSoulPower.POWER_ID);
            poetSoulAmount = poetSoulPower.amount * 10;
            updateDescription();
        }
    }



    @Override
    public void atStartOfTurn() {
        Random random = new Random();
        int n5 = random.nextInt(100);
        if (poetSoulAmount!=0 && n5 <= poetSoulAmount) {
            addToBot((AbstractGameAction) new SelectCardToHandAction(returnRandomCardByCardTagInCombat(), true, true));
        }
    }

    public static ArrayList<AbstractCard> returnRandomCardByCardTagInCombat() {
        ArrayList<AbstractCard> returnCard = new ArrayList<>();
        returnCard.add(new DeathRain());
        returnCard.add(new Straight());

        return returnCard;
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], new Object[]{Integer.valueOf(this.poetSoulAmount)});
//        this.description = DESCRIPTIONS[0];
    }
}
