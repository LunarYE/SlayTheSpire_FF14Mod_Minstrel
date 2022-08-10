package cards.minstrel;

import cards.AbstractExampleCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import helpers.ModHelper;
import pathes.AbstractCardEnum;
import powers.minstrel.ArmyPaeonPower;
import powers.minstrel.WandererMinuetPower;

public class WandererMinuet extends AbstractExampleCard {
    /**
     * 获取类名
     */
    public static final String CLASS_NAME = WandererMinuet.class.getSimpleName();
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
    private static final CardType TYPE = CardType.POWER;
    /**
     * 定义卡牌颜色
     */
    private static final CardColor COLOR = AbstractCardEnum.MINSTREL_COLOR;
    /**
     * 定义卡牌稀有度
     */
    private static final CardRarity RARITY = CardRarity.RARE;
    /**
     * 定义卡牌指向对象
     */
    private static final CardTarget TARGET = CardTarget.SELF;

    public WandererMinuet() {
        super(ID, NAME, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //消耗
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        AbstractDungeon.actionManager.addToBottom(
//                new ApplyPowerAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        //增加能力层数
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new WandererMinuetPower((AbstractCreature)p, 1)));
        //减少能力层数
//        addToBot((AbstractGameAction)new ReducePowerAction((AbstractCreature)p, (AbstractCreature)p, MagiamObruorPower.POWER_ID, this.secondaryM));

    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(UPGRADE_NUMERICAL);
    }

}
