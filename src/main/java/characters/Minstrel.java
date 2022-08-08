package characters;

import basemod.abstracts.CustomPlayer;
import cards.minstrel.Attack;
import cards.minstrel.Defense;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import demoMod.MinstrelMod;
import helpers.ModHelper;
import pathes.AbstractPlayerEnum;
import pathes.AbstractCardEnum;

import java.util.ArrayList;

public class Minstrel extends CustomPlayer {
    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString(ModHelper.makeID(Minstrel.class.getSimpleName()));
    //初始能量
    private static final int ENERGY_PER_TURN = 3;
    //以下图片依次作用为[篝火休息处的角色背影2，篝火休息处的角色背影1，角色死亡后倒下的图片，角色平常站立时的图片]
    private static final String SELES_SHOULDER_2 = "img/minstrel/char/shoulder2.png";
    private static final String SELES_SHOULDER_1 = "img/minstrel/char/shoulder1.png";
    private static final String SELES_CORPSE = "img/minstrel/char/fallen.png";
    private static final String SELES_STAND = "img/minstrel/char/Seles.png";
    //各种素材，不是很懂
    private static final String[] ORB_TEXTURES = new String[]{"img/minstrel/UI/EPanel/layer5.png", "img/minstrel/UI/EPanel/layer4.png", "img/minstrel/UI/EPanel/layer3.png", "img/minstrel/UI/EPanel/layer2.png", "img/minstrel/UI/EPanel/layer1.png", "img/minstrel/UI/EPanel/layer0.png", "img/minstrel/UI/EPanel/layer5d.png", "img/minstrel/UI/EPanel/layer4d.png", "img/minstrel/UI/EPanel/layer3d.png", "img/minstrel/UI/EPanel/layer2d.png", "img/minstrel/UI/EPanel/layer1d.png"};
    private static final String ORB_VFX = "img/minstrel/UI/energyBlueVFX.png";
    private static final float[] LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};
    //初始生命，最大生命，初始金币,初始的充能球栏位（机器人）,最后一个应该是进阶14时的最大生命值下降
    private static final int STARTING_HP = 75;
    private static final int MAX_HP = 75;
    private static final int STARTING_GOLD = 99;
    private static final int HAND_SIZE = 0;
    private static final int ASCENSION_MAX_HP_LOSS = 5;
    //返回一个颜色
    public static final Color SILVER = CardHelper.getColor(200, 200, 200);

    public Minstrel(String name, AbstractPlayer.PlayerClass setClass)  {
        //构造方法，初始化参数
        super(name, setClass,(EnergyOrbInterface)new EnergyOrbMinstrel(ORB_TEXTURES, ORB_VFX), (String) null, (String) null);
        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 220.0F * Settings.scale;
        initializeClass(SELES_STAND, SELES_SHOULDER_2, SELES_SHOULDER_1, SELES_CORPSE,
                getLoadout(),
                0.0F, 5.0F, 240.0F, 300.0F,
                new EnergyManager(ENERGY_PER_TURN));
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        //添加初始卡组
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Attack.ID);
        retVal.add(Attack.ID);
        retVal.add(Attack.ID);
        retVal.add(Attack.ID);
        retVal.add(Defense.ID);
        retVal.add(Defense.ID);
        retVal.add(Defense.ID);
        retVal.add(Defense.ID);

        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        //添加初始遗物
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("MinstrelBegin");
        UnlockTracker.markRelicAsSeen("MinstrelBegin");
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(
                "吟游诗人",
                "从艾欧泽亚穿越来的光之战士， NL 擅长使用各种增益BUff和控制技能。",
                60,
                60,
                0,
                99,
                5,
                this,
                this.getStartingRelics(),
                this.getStartingDeck(),
                false
        );
    }


    @Override
    public String getTitle(PlayerClass playerClass) {
        //应该是进游戏后左上角的角色名
        String title = "";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            title = "吟游诗人";
        } else if (Settings.language == Settings.GameLanguage.ZHT) {
            title = "吟游诗人";
        } else {
            title = "Minstrel";
        }

        return title;
    }

    @Override

    public AbstractCard.CardColor getCardColor() {
        //选择卡牌颜色
        return AbstractCardEnum.MINSTREL_COLOR;
    }

    @Override
    public Color getCardRenderColor() {
        return SILVER;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return null;
    }

    @Override
    public Color getCardTrailColor() {
        return SILVER;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return ASCENSION_MAX_HP_LOSS;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_MAGIC_BEAM_SHORT", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
    }

    public void updateOrb(int orbCount) {
        this.energyOrb.updateOrb(orbCount);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_MAGIC_BEAM_SHORT";
    }

    @Override
    public String getLocalizedCharacterName() {
        String char_name;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            char_name = "吟游诗人";
        } else if (Settings.language == Settings.GameLanguage.ZHT) {
            char_name = "吟游诗人";
        } else {
            char_name = "Minstrel";
        }
        return char_name;
    }

    @Override
    public AbstractPlayer newInstance() {
        return (AbstractPlayer)new Minstrel(this.name,AbstractPlayerEnum.MINSTREL);
    }

    @Override
    public String getSpireHeartText() {
        return "NL 你操纵着浮游炮发起了全力一击……";
    }

    @Override
    public Color getSlashAttackColor() {
        return SILVER;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[] { AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL
        };
    }

    @Override
    public String getVampireText() {

        return null;
    }

    public void applyEndOfTurnTriggers() {
        super.applyEndOfTurnTriggers();
    }
}
