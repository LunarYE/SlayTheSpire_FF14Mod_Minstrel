package powers.minstrel;

import cards.minstrel.DeathRain;
import cards.minstrel.PerfectPitch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import helpers.ModHelper;
import powers.AbstractMinstrelPower;

public class WandererMinuetPower extends AbstractMinstrelPower {
    public static final String POWER_ID = ModHelper.MakePath(WandererMinuetPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public int poetSoulAmount = 0;

    public WandererMinuetPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        loadRegion("rupture");
        updateDescription();
    }


        /**
         * 获得能力的时候
         * @param power
         */
    @Override
    public void onStackPower(AbstractPower power) {
        if (power.ID.equals(PoetSoulPower.POWER_ID)) {
            flash();
            AbstractPower poetSoulPower = AbstractDungeon.player.getPower(PoetSoulPower.POWER_ID);
            this.poetSoulAmount = poetSoulPower.amount;
            updateDescription();
        }
    }



    @Override
    public void atStartOfTurn() {
        if (poetSoulAmount != 0){
            addToBot(new MakeTempCardInHandAction(new PerfectPitch(), 1));
        }


    }



    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
