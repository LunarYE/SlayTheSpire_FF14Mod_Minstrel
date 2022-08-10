package cards.minstrel;

import actions.MakeLoadedCardAction;
import actions.SelectCardToHandAction;
import actions.XActionAction;
import cards.AbstractExampleCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import helpers.ModHelper;
import pathes.AbstractCardEnum;

import java.util.ArrayList;
import java.util.function.Consumer;

public class DeathRain extends AbstractExampleCard {
    /**
     * 获取类名 死亡箭雨
     */
    public static final String CLASS_NAME = DeathRain.class.getSimpleName();
    /**
     * 获取类名作为卡牌id
     */
    public static final String ID = ModHelper.MakePath(CLASS_NAME);
    /**
     * 卡牌贴图路径
     */
    private static final String IMG_PATH = "img/minstrel/cards/test.png";
    /**
     * 卡牌基础费用
     */
    private static final int COST = 1;
    /**
     * 卡牌基础数值
     */
    private static final int NUMERICAL = 3;
    /**
     * 升级后提高的数值
     */
    private static final int UPGRADE_NUMERICAL = 3;
    /**
     * 从.json文件中提取键名为卡牌id的信息
     */
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    /**
     * 从.json文件中获取类名为键名的卡牌名称
     */
    private static final String NAME = CARD_STRINGS.NAME;
    /**
     * 从.json文件中获取类名为键名的卡牌效果
     */
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    /**
     * 定义卡牌类型
     */
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    /**
     * 定义卡牌颜色
     */
    private static final AbstractCard.CardColor COLOR = AbstractCardEnum.MINSTREL_COLOR;
    /**
     * 定义卡牌稀有度
     */
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.SPECIAL;
    /**
     * 定义卡牌指向对象
     */
    private static final AbstractCard.CardTarget TARGET = CardTarget.ALL_ENEMY;

    public DeathRain() {
        super(ID, NAME, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //消耗
        this.exhaust = true;
        this.baseDamage = NUMERICAL;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        damageToEnemy(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        AbstractDungeon.actionManager.addToBottom
                (new DamageAllEnemiesAction
                        (p, this.multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HEAVY));
//        this.addToBot(new MakeLoadedCardAction(this.upgraded, new DeathRain(), this.magicNumber)); //加入到抽牌堆
//        Consumer<Integer> actionConsumer = effect -> {
//            DeathRain bombardaMagica = new DeathRain();
//            if (this.upgraded) {
//                bombardaMagica.upgrade();
//            }
//            addToBot(new MakeTempCardInHandAction(bombardaMagica, 1));
//        };
//        addToBot(new MakeTempCardInHandAction(new DeathRain(), 1)); //加入到手牌
//        addToBot((AbstractGameAction)new XActionAction(actionConsumer, this.freeToPlayOnce, this.energyOnUse));
//        addToBot((AbstractGameAction) new SelectCardToHandAction(returnRandomCardByCardTagInCombat(), true, true));

    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(UPGRADE_NUMERICAL);
    }

    public static ArrayList<AbstractCard> returnRandomCardByCardTagInCombat() {
        ArrayList<AbstractCard> returnCard = new ArrayList<>();
        returnCard.add(new DeathRain());
        returnCard.add(new Quick());
//        for (AbstractCard c : AbstractDungeon.srcCommonCardPool.group) {
//            if (c.hasTag(tag) && !c.hasTag(AbstractCard.CardTags.HEALING)) {
//                list.add(c);
//            }
//        }
//        for (AbstractCard c : AbstractDungeon.srcUncommonCardPool.group) {
//            if (c.hasTag(tag) && !c.hasTag(AbstractCard.CardTags.HEALING)) {
//                list.add(c);
//            }
//        }
//        for (AbstractCard c : AbstractDungeon.srcRareCardPool.group) {
//            if (c.hasTag(tag) && !c.hasTag(AbstractCard.CardTags.HEALING) && !(c instanceof DeathRain)) {
//                list.add(c);
//            }
//        }
//
//
//        for (int i = 0; i < 3; i++) {
//            int temp = AbstractDungeon.cardRng.random(list.size() - 1);
//            returnCard.add(list.get(temp));
//            list.remove(temp);
//        }

        return returnCard;
    }
}
