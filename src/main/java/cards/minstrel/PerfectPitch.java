package cards.minstrel;

import cards.AbstractExampleCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import helpers.ModHelper;
import pathes.AbstractCardEnum;
import powers.minstrel.PoetSoulPower;


public class PerfectPitch extends AbstractExampleCard {
    /**
     * 获取类名
     */
    public static final String CLASS_NAME = PerfectPitch.class.getSimpleName();
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
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public PerfectPitch() {
        super(ID, NAME, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //消耗
        this.exhaust = true;
        this.isEthereal = true;
        this.baseDamage = NUMERICAL;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot((AbstractGameAction) new DamageAction((AbstractCreature) m, new DamageInfo((AbstractCreature) p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING));

//        addToBot((AbstractGameAction) new LoseCorGladiiAction(true));
//        damageToEnemy(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int trueDamage = this.baseDamage;

        this.baseDamage = 1;
        if (AbstractDungeon.player.hasPower(PoetSoulPower.POWER_ID)) {
            this.baseDamage += (AbstractDungeon.player.getPower(PoetSoulPower.POWER_ID)).amount*5;
        }
        super.calculateCardDamage(mo);

        this.baseDamage = trueDamage;
    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(UPGRADE_NUMERICAL);
    }
}
