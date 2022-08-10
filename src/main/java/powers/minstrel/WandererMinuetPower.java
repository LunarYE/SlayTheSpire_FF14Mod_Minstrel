package powers.minstrel;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import helpers.ModHelper;
import powers.AbstractMinstrelPower;

public class WandererMinuetPower extends AbstractMinstrelPower {
    public static final String POWER_ID = ModHelper.MakePath(WandererMinuetPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public WandererMinuetPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        loadRegion("rupture");
        updateDescription();
    }


    @Override
    public void atStartOfTurn() {
        AbstractPower poetSoulPower = AbstractDungeon.player.getPower("Minstrel:PoetSoulPower");
        if (poetSoulPower!=null) {
            addToTop(new DrawCardAction(poetSoulPower.amount));
        }
    }



    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
