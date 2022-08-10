package cards.minstrel;

import cards.AbstractExampleCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import helpers.ModHelper;
import pathes.AbstractCardEnum;
import powers.minstrel.ArmyPaeonPower;
import powers.minstrel.PoetSoulPower;

public class sing extends AbstractExampleCard {
    /**
     * 获取类名
     */
    public static final String CLASS_NAME = sing.class.getSimpleName();
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
    private static final int NUMERICAL = 0;
    /**
     * 升级后提高的数值
     */
    private static final int UPGRADE_NUMERICAL = 0;
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
    private static final CardType TYPE = CardType.SKILL;
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
    private static final CardTarget TARGET = CardTarget.SELF;


    public sing() {
        //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
        super(ID, NAME, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //添加基础防御标签和将格挡设为5
        this.tags.add(CardTags.STARTER_DEFEND);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        AbstractPower poetSoulPower = AbstractDungeon.player.getPower(PoetSoulPower.POWER_ID);
        if (poetSoulPower.amount < 4) {
            return super.canUse(p, m);
        }
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //使用卡牌时触发的动作
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new PoetSoulPower((AbstractCreature) p, 1)));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new sing();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点格挡
            upgradeName();
        }
    }

}