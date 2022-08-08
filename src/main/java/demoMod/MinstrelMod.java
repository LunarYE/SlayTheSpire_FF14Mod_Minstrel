package demoMod;

import basemod.BaseMod;
import basemod.abstracts.CustomCard;
import basemod.interfaces.*;
import cards.minstrel.*;
import characters.Minstrel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import pathes.AbstractCardEnum;
import pathes.AbstractPlayerEnum;
import relics.MinstrelBegin;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpireInitializer
public class MinstrelMod implements RelicGetSubscriber, PostPowerApplySubscriber, PostExhaustSubscriber, PostBattleSubscriber, PostDungeonInitializeSubscriber, EditCharactersSubscriber, PostInitializeSubscriber, EditRelicsSubscriber, EditCardsSubscriber, EditStringsSubscriber, OnCardUseSubscriber, EditKeywordsSubscriber, OnPowersModifiedSubscriber, PostDrawSubscriber, PostEnergyRechargeSubscriber {

    public static String MOD_ID = "MinstrelMod";

    private static final String MOD_BADGE = "img/minstrel/UI/badge.png";
    //攻击、技能、能力牌的背景图片(512)
    private static final String ATTACK_CC = "img/512/bg_attack_SELES_s.png";
    private static final String SKILL_CC = "img/512/bg_skill_SELES_s.png";
    private static final String POWER_CC = "img/512/bg_power_SELES_s.png";
    private static final String ENERGY_ORB_CC = "img/512/SELESOrb.png";
    //攻击、技能、能力牌的背景图片(1024)
    private static final String ATTACK_CC_PORTRAIT = "img/1024/bg_attack_SELES.png";
    private static final String SKILL_CC_PORTRAIT = "img/1024/bg_skill_SELES.png";
    private static final String POWER_CC_PORTRAIT = "img/1024/bg_power_SELES.png";
    private static final String ENERGY_ORB_CC_PORTRAIT = "img/1024/SELESOrb.png";
    public static final String CARD_ENERGY_ORB = "img/minstrel/UI/energyOrb.png";
    //选英雄界面的角色图标、选英雄时的背景图片
    private static final String MY_CHARACTER_BUTTON = "img/minstrel/char_select/SelesButton.png";
    private static final String MARISA_PORTRAIT = "img/minstrel/char_select/LunarPortrait.png";
    public static final Color SILVER = CardHelper.getColor(200, 200, 200);
    private ArrayList<AbstractCard> cardsToAdd = new ArrayList<>();
    public static ArrayList<AbstractCard> recyclecards = new ArrayList<>();

    public MinstrelMod() {
        //构造方法，初始化各种参数
        BaseMod.subscribe(this);
        BaseMod.addColor(AbstractCardEnum.MINSTREL_COLOR, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER, ATTACK_CC, SKILL_CC, POWER_CC, ENERGY_ORB_CC, ATTACK_CC_PORTRAIT, SKILL_CC_PORTRAIT, POWER_CC_PORTRAIT, ENERGY_ORB_CC_PORTRAIT, CARD_ENERGY_ORB);
    }

    @Override
    public void receiveEditCharacters() {
        //添加角色到MOD中
//        BaseMod.addCharacter((AbstractPlayer) new Minstrel("Minstrel"), MY_CHARACTER_BUTTON, MARISA_PORTRAIT, ThmodClassEnum.MINSTREL_CLASS);
        BaseMod.addCharacter(new Minstrel(Minstrel.charStrings.NAMES[1],AbstractPlayerEnum.MINSTREL),
                MY_CHARACTER_BUTTON,
                MARISA_PORTRAIT, AbstractPlayerEnum.MINSTREL);
    }

    //初始化整个MOD,一定不能删
    public static void initialize() {
        new MinstrelMod();
    }

    @Override
    public void receiveEditCards() {

        //将卡牌批量添加
        List<CustomCard> cards = new ArrayList<>();
        //将自定义的卡牌添加到这里

        cards.add(new Quick());
        cards.add(new ShadowBite());
        cards.add(new Straight());
        cards.add(new DeathRain());
        cards.add(new Venomous());

        cards.add(new Attack());
        cards.add(new Defense());
        for (CustomCard card : cards) {
            BaseMod.addCard(card);
            UnlockTracker.unlockCard(card.cardID);
        }

//        Iterator<AbstractCard> var1 = this.cardsToAdd.iterator();
//        while (var1.hasNext()) {
//            AbstractCard card = var1.next();
//            BaseMod.addCard(card);
//        }
    }

    @Override
    public void receivePostExhaust(AbstractCard c) {
    }

    @Override
    public void receivePostPowerApplySubscriber(AbstractPower pow, AbstractCreature target, AbstractCreature owner) {

    }


    @Override
    public void receivePowersModified() {
    }


    @Override
    public void receivePostDungeonInitialize() {
    }


    @Override
    public void receivePostDraw(AbstractCard arg0) {
    }

    private static String loadJson(String jsonPath) {
        return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
    }


    @Override
    public void receiveEditKeywords() {

    }

    @Override
    public void receiveEditStrings() {
        //读取遗物，卡牌，能力，药水，事件的JSON文本

        String relic = "", card = "", power = "", charecter = "", event = "";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            card = "localization/zhs/Minstrel_cards.json";
            relic = "localization/zhs/Minstrel_relics.json";
            power = "localization/zhs/Minstrel_powers.json";
            charecter = "localization/zhs/Minstrel_character.json";
            //potion = "localization/ThMod_YM_potions-zh.json";
            //event = "localization/ThMod_YM_events-zh.json";
        } else {
            //其他语言配置的JSON
        }

        String charecterStrings = Gdx.files.internal(charecter).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CharacterStrings.class, charecterStrings);
        String relicStrings = Gdx.files.internal(relic).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        String cardStrings = Gdx.files.internal(card).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        String powerStrings = Gdx.files.internal(power).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
//     String potionStrings = Gdx.files.internal(potion).readString(String.valueOf(StandardCharsets.UTF_8));
//     BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);
//     String eventStrings = Gdx.files.internal(event).readString(String.valueOf(StandardCharsets.UTF_8));
//     BaseMod.loadCustomStrings(EventStrings.class, eventStrings);
    }

    private void loadCardsToAdd() {
        //将自定义的卡牌添加到这里
        this.cardsToAdd.clear();
        this.cardsToAdd.add(new Attack());
        this.cardsToAdd.add(new Defense());
        this.cardsToAdd.add(new Quick());
        this.cardsToAdd.add(new ShadowBite());
        this.cardsToAdd.add(new Straight());
        this.cardsToAdd.add(new DeathRain());
        this.cardsToAdd.add(new Venomous());
    }

    //添加一度
    @Override
    public void receiveEditRelics() {
        //将自定义的遗物添加到这里
        BaseMod.addRelicToCustomPool((AbstractRelic) new MinstrelBegin(), AbstractCardEnum.MINSTREL_COLOR);
    }

    @Override
    public void receiveRelicGet(AbstractRelic relic) {
        //移除遗物,这里移除了小屋子，太垃圾了。 q

        if (AbstractDungeon.player.name == "Minstrel") {
            AbstractDungeon.shopRelicPool.remove("TinyHouse");
        }
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {

    }

    @Override
    public void receivePostBattle(AbstractRoom r) {

    }

    @Override
    public void receivePostInitialize() {

    }

    @Override
    public void receivePostEnergyRecharge() {
        Iterator<AbstractCard> var1 = recyclecards.iterator();

        while (var1.hasNext()) {
            AbstractCard c = var1.next();
            AbstractCard card = c.makeStatEquivalentCopy();
            AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(card, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, false, true, true));
        }
        recyclecards.clear();
    }

    class Keywords {
        Keyword[] keywords;
    }

    public static String assetPath(String path) {
        return MOD_ID + "/" + path;
    }
}
