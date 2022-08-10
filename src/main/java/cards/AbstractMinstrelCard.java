package cards;


import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;


public abstract class AbstractMinstrelCard
        extends CustomCard
        implements SpawnModificationCard {
    protected final CardStrings cardStrings;
    protected final String NAME;
    protected final String DESCRIPTION;
    protected final String UPGRADE_DESCRIPTION;
    protected final String[] EXTENDED_DESCRIPTION;
    public int secondaryM;
    public int baseSecondaryM;
    public boolean upgradeSecondaryM;
    public boolean isSecondaryMModified;
    private static final Color ENERGY_COST_RESTRICTED_COLOR = new Color(1.0F, 0.3F, 0.3F, 1.0F);
    private static final Color ENERGY_COST_MODIFIED_COLOR = new Color(0.4F, 1.0F, 0.4F, 1.0F);

    public CardRarity displayRarity;
    private static final Texture orb_b = ImageMaster.loadImage("VUPShionMod/img/cardui/Shion/512/card_lime_orb_b.png");
    private static final Texture orb_g = ImageMaster.loadImage("VUPShionMod/img/cardui/Shion/512/card_lime_orb_g.png");
    private static final Texture orb_w = ImageMaster.loadImage("VUPShionMod/img/cardui/Shion/512/card_lime_orb_w.png");

    private static final Texture orb_ab = ImageMaster.loadImage("VUPShionMod/img/cardui/Shion/512/card_lime_orb_ab.png");
    private static final Texture orb_ag = ImageMaster.loadImage("VUPShionMod/img/cardui/Shion/512/card_lime_orb_ag.png");
    private static final Texture orb_aw = ImageMaster.loadImage("VUPShionMod/img/cardui/Shion/512/card_lime_orb_aw.png");

    public String betaArtPath;

    public AbstractMinstrelCard(String id, String img, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(id, (CardCrawlGame.languagePack.getCardStrings(id)).NAME, img, cost, (CardCrawlGame.languagePack.getCardStrings(id)).DESCRIPTION, type, AbstractCardEnum.MINSTREL_COLOR, rarity, target);


        this.cardStrings = CardCrawlGame.languagePack.getCardStrings(id);
        this.NAME = this.cardStrings.NAME;
        this.DESCRIPTION = this.cardStrings.DESCRIPTION;
        this.UPGRADE_DESCRIPTION = this.cardStrings.UPGRADE_DESCRIPTION;
        this.EXTENDED_DESCRIPTION = this.cardStrings.EXTENDED_DESCRIPTION;
        initializeTitle();
        initializeDescription();
    }

    public void updateDescription() {
    }


    protected void upgradeSecondM(int amount) {
        this.baseSecondaryM += amount;
        this.secondaryM = this.baseSecondaryM;
        this.upgradeSecondaryM = true;
    }

    public void safeChangeSecondM(int amount) {
        this.baseSecondaryM += amount;
        if (this.baseSecondaryM < 0) this.baseSecondaryM = 0;
        this.secondaryM = this.baseSecondaryM;
        this.upgradeSecondaryM = true;
    }


    public void displayUpgrades() {
        super.displayUpgrades();

        if (this.upgradeSecondaryM) {
            this.secondaryM = this.baseSecondaryM;
            this.isSecondaryMModified = true;
        }
    }


    public AbstractCard makeStatEquivalentCopy() {
        AbstractMinstrelCard card = (AbstractMinstrelCard) super.makeStatEquivalentCopy();
        card.secondaryM = this.secondaryM;
        card.baseSecondaryM = this.baseSecondaryM;
        card.upgradeSecondaryM = this.upgradeSecondaryM;
        card.isSecondaryMModified = this.isSecondaryMModified;
        return (AbstractCard) card;
    }


    protected String getCost() {
        if (this.cost == -1)
            return "X";
        if (freeToPlay()) {
            return "0";
        }
        return Integer.toString(this.costForTurn);
    }

    private BitmapFont getEnergyFont() {
        FontHelper.cardEnergyFont_L.getData().setScale(this.drawScale);
        return FontHelper.cardEnergyFont_L;
    }

    protected void darkOrbRenderHelper(SpriteBatch sb, Texture img, float posX, float posY) {
        sb.setColor((Color) ReflectionHacks.getPrivate(this, AbstractCard.class, "renderColor"));
        float length = (float) Math.sqrt((posX * posX + posY * posY));
        float angleFinal = (float) Math.toRadians((this.angle + 180.0F - (float) Math.toDegrees(Math.sinh((posY / length)))));
        float drawX = this.current_x + length * (float) Math.cos(angleFinal) * this.drawScale * Settings.scale * 1.0F;
        float drawY = this.current_y + length * (float) Math.sin(angleFinal) * this.drawScale * Settings.scale * 1.0F;

        sb.draw(img, drawX - img
                .getWidth() / 2.0F, drawY - img.getHeight() / 2.0F, img
                .getWidth() / 2.0F, img.getHeight() / 2.0F, img
                .getWidth(), img.getHeight(), this.drawScale * Settings.scale * 1.0F, this.drawScale * Settings.scale * 1.0F, this.angle, 0, 0, img


                .getWidth(), img.getHeight(), false, false);
    }


    public void loadJokeCardImage(String img) {
        this.betaArtPath = img;
        Texture cardTexture = ImageMaster.loadImage(img);
        cardTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        TextureAtlas.AtlasRegion cardImg = new TextureAtlas.AtlasRegion(cardTexture, 0, 0, cardTexture.getWidth(), cardTexture.getHeight());
        ReflectionHacks.setPrivate(this, AbstractCard.class, "jokePortrait", cardImg);
    }


    public void postReturnToHand() {
    }


    public void onTriggerLoaded() {
    }


    public void monsterAfterOnAttack(DamageInfo info, AbstractMonster m, int damageAmount) {
    }


    protected void upgradeBaseCost(int newBaseCost) {
        int diff = this.costForTurn - this.cost;
        this.cost = newBaseCost;
        if (this.costForTurn >= 0) {
            this.costForTurn = this.cost + diff;
        }

        if (this.costForTurn < 0) {
            this.costForTurn = 0;
        }

        this.upgradedCost = true;
    }


    public void onDuelSin() {
    }

    public void onApplyCor() {
    }
}
