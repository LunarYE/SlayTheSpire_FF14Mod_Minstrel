package powers.minstrel;

import cards.minstrel.Venomous;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import helpers.ModHelper;
import powers.AbstractMinstrelPower;

public class ArmyPaeonPower extends AbstractMinstrelPower {
    public static final String POWER_ID = ModHelper.MakePath(ArmyPaeonPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public int poetSoulAmount = 0;

    public ArmyPaeonPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        loadRegion("rupture");
        AbstractPower poetSoulPower = AbstractDungeon.player.getPower(PoetSoulPower.POWER_ID);
        if (poetSoulPower != null && poetSoulPower.amount != 0) {
            poetSoulAmount = poetSoulPower.amount;
        }
        updateDescription();
    }


    @Override
    public void onStackPower(AbstractPower power) {
        if (power.ID.equals(PoetSoulPower.POWER_ID)) {
            flash();
            AbstractPower poetSoulPower = AbstractDungeon.player.getPower(PoetSoulPower.POWER_ID);
            poetSoulAmount = poetSoulPower.amount;
            updateDescription();
        }
    }

    @Override
    public void atStartOfTurn() {
        try {
            wait(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AbstractPower poetSoulPower = AbstractDungeon.player.getPower(PoetSoulPower.POWER_ID);
        if (poetSoulPower != null && poetSoulPower.amount != 0) {
            poetSoulAmount = poetSoulPower.amount;
            addToTop(new DrawCardAction(poetSoulAmount));
        }
        updateDescription();
    }


    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], new Object[]{Integer.valueOf(poetSoulAmount)});
//        this.description = DESCRIPTIONS[0];
    }
}
