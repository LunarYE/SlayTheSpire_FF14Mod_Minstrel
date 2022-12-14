package powers.minstrel;

import cards.minstrel.Venomous;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import helpers.ModHelper;
import powers.AbstractMinstrelPower;

public class PoetSoulPower extends AbstractMinstrelPower {
    public static final String POWER_ID = ModHelper.MakePath(PoetSoulPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PoetSoulPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        loadRegion("rupture");
        updateDescription();
    }


    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (p instanceof AbstractMinstrelPower) {
                ((AbstractMinstrelPower)p).onStackPower((AbstractPower)this);
            }

        }
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (p instanceof AbstractMinstrelPower) {
                ((AbstractMinstrelPower)p).onStackPower((AbstractPower)this);
            }

        }

    }
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
