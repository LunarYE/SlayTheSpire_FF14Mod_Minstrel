package actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class MakeLoadedCardAction
        extends AbstractGameAction {
    private AbstractCard card;
    private int amount;

    private boolean inDiscardPile = false;
    private boolean upgrade = false;
    private boolean top = false;

    public MakeLoadedCardAction(AbstractCard card) {
        this.card = card;
        this.amount = 1;
    }

    public MakeLoadedCardAction(AbstractCard card, int amount) {
        this.card = card;
        this.amount = amount;
    }

    public MakeLoadedCardAction(boolean upgrade, AbstractCard card, int amount) {
        this.card = card;
        this.amount = amount;
        this.upgrade = upgrade;
    }

    public MakeLoadedCardAction(AbstractCard card, boolean inDiscardPile) {
        this.card = card;
        this.amount = 1;
        this.inDiscardPile = inDiscardPile;
    }

    public MakeLoadedCardAction(boolean upgrade, AbstractCard card, boolean inDiscardPile) {
        this.card = card;
        this.amount = 1;
        this.inDiscardPile = inDiscardPile;
        this.upgrade = upgrade;
    }


    public MakeLoadedCardAction(AbstractCard card, int amount, boolean inDiscardPile) {
        this.card = card;
        this.amount = amount;
        this.inDiscardPile = inDiscardPile;
    }

    public MakeLoadedCardAction(boolean upgrade, AbstractCard card, int amount, boolean inDiscardPile) {
        this.card = card;
        this.amount = amount;
        this.inDiscardPile = inDiscardPile;
        this.upgrade = upgrade;
    }


    public MakeLoadedCardAction(boolean top, boolean upgrade, AbstractCard card, int amount, boolean inDiscardPile) {
        this.card = card;
        this.amount = amount;
        this.inDiscardPile = inDiscardPile;
        this.upgrade = upgrade;
        this.top = top;
    }


    @Override
    public void update() {
        if (this.upgrade) {
            this.card.upgrade();
        }
        if (!this.inDiscardPile) {
            addToTop( new MakeTempCardInDrawPileAction(this.card, this.amount, this.top, true, false));
        } else {
            addToTop( new MakeTempCardInDiscardAction(this.card, this.amount));
        }

        this.isDone = true;
    }
}
