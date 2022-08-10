package cards.minstrel;

import basemod.abstracts.CustomCard;
import cards.AbstractExampleCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import helpers.ModHelper;
import pathes.AbstractCardEnum;

public class Quick extends AbstractExampleCard {
    /**
     * 获取类名
     */
    public static final String CLASS_NAME = Quick.class.getSimpleName();
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
    private static final int NUMERICAL = 5;
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
    private static final CardType TYPE = CardType.ATTACK;
    /**
     * 定义卡牌颜色
     */
    private static final CardColor COLOR = AbstractCardEnum.MINSTREL_COLOR;
    /**
     * 定义卡牌稀有度
     */
    private static final CardRarity RARITY = CardRarity.COMMON;
    /**
     * 定义卡牌指向对象
     */
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public Quick() {
        //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
        super(ID, NAME, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
//        this.exhaust = true; //消耗
//        this.isEthereal = true;
        //添加攻击的伤害
        this.baseDamage = NUMERICAL;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //使用卡牌时触发的动作
        AbstractDungeon.actionManager.addToBottom
                (new DamageAllEnemiesAction
                        (p, this.multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HEAVY));
//        AbstractDungeon.actionManager.addToBottom(
//                new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new Quick();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点伤害
            upgradeName();
            this.name = "百首龙牙箭";
            upgradeDamage(UPGRADE_NUMERICAL);
        }
    }
}
