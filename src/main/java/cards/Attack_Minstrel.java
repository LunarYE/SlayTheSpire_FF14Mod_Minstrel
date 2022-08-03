package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

public class Attack_Minstrel extends CustomCard {
    /**
     * 卡牌贴图路径
     */
    public static final String IMG_PATH = "img/cards_Seles/Strike.png";
    /**
     * 卡牌基础费用
     */
    private static final int COST = 1;
    /**
     * 卡牌基础数值
     */
    private static final int NUMERICAL = 6;
    /**
     * 升级后提高的数值
     */
    private static final int UPGRADE_NUMERICAL = 3;
    /**
     * 获取类名作为卡牌id
     */
    public static final String ID = "Attack_Minstrel";
    /**
     * 从.json文件中提取键名为卡牌id的信息
     */
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    /**
     * 从.json文件中获取类名为键名的卡牌名称
     */
    public static final String NAME = CARD_STRINGS.NAME;
    /**
     * 从.json文件中获取类名为键名的卡牌效果
     */
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    /**
     * 定义卡牌类型
     */
    public static final CardType TYPE = CardType.ATTACK;
    /**
     * 定义卡牌颜色
     */
    private static final CardColor COLOR = AbstractCardEnum.MINSTREL_COLOR;
    /**
     * 定义卡牌稀有度
     */
    private static final CardRarity RARITY = CardRarity.BASIC;
    /**
     * 定义卡牌指向对象
     */
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Attack_Minstrel() {
        //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //添加基础攻击标签
        this.tags.add(CardTags.STRIKE);
        this.tags.add(CardTags.STARTER_STRIKE);
        //添加攻击的伤害为6
        this.baseDamage = NUMERICAL;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //使用卡牌时触发的动作
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new Attack_Minstrel();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点伤害
            upgradeName();
            this.name = "爆发射击";
            upgradeDamage(UPGRADE_NUMERICAL);
        }
    }


}
