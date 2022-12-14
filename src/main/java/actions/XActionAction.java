package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.function.Consumer;

public class XActionAction extends AbstractGameAction {
    private int energyOnUse;
    private boolean freeToPlayOnce;
    private Consumer<Integer> actionConsumer;

    public XActionAction(Consumer<Integer> actionConsumer, boolean freeToPlayOnce, int energyOnUse) {
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
        this.actionConsumer = actionConsumer;
    }


    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (AbstractDungeon.player.hasRelic("Chemical X")) {
            effect += 2;
            AbstractDungeon.player.getRelic("Chemical X").flash();
        }

        if (effect >= 0) {
            if (this.actionConsumer != null) {
                this.actionConsumer.accept(Integer.valueOf(effect));
            }
            if (!this.freeToPlayOnce) {
                AbstractDungeon.player.energy.use(EnergyPanel.totalCount);
            }
        }
        this.isDone = true;
    }
}