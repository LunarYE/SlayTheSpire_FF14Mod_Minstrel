package cards;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static pathes.AbstractCardEnum.MINSTREL_COLOR;

public class AbstractExampleCard extends CustomCard implements SpawnModificationCard {
    // useTmpArt表示是否使用测试卡图，当你卡图不够用时可以使用
    public AbstractExampleCard(String id, String name,  String img, int cost, String rawDescription,
                             CardType type, CardColor color, CardRarity rarity, CardTarget target) {

        super(id, name,  img, cost, rawDescription, type, color, rarity, target);
    }
    // 如果按这个方法实现，在cards文件夹下分别放test_attack.png、test_power.png、test_skill.png即可
//    private static String GetTmpImgPath(CardType t) {
//        String type;
//        switch (t) {
//            case ATTACK:
//                type = "attack";
//                break;
//            case POWER:
//                type = "power";
//                break;
//            case STATUS:
//            case CURSE:
//            case SKILL:
//                type = "skill";
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + t);
//        }
//        return String.format("img/cards/test_"+type+".png");
//    }

    // 如果实现这个方法，只要将相应类型的卡牌丢进相应文件夹即可，如攻击牌卡图添加进img/cards/attack/下
//    private static String GetImgPath(CardType t, String name) {
//        String type;
//        switch (t) {
//            case ATTACK:
//                type = "attack";
//                break;
//            case POWER:
//                type = "power";
//                break;
//            case STATUS:
//                type = "status";
//                break;
//            case CURSE:
//                type = "curse";
//                break;
//            case SKILL:
//                type = "skill";
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + t);
//        }
//        return String.format("img/cards/test_"+type+".png");
//    }

    public AbstractExampleCard(String id, String name, int cost, String rawDescription,
                               CardType type, CardColor color, CardRarity rarity, CardTarget target) {

        super(id, name,  "img/minstrel/cards/test.png", cost, rawDescription, type, color, rarity, target);
    }


    // 简化伤害代码
    public void damageToEnemy(AbstractMonster m, AbstractGameAction.AttackEffect effect) {
        this.addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, this.damage), effect));
    }

    // 简化aoe代码
    public void damageToAllEnemies(AbstractGameAction.AttackEffect effect) {
        this.addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage,
                this.damageTypeForTurn, effect));
    }

    // 获得等于这张牌格挡数值的格挡
    public void gainBlock() {
        this.addToBot(new GainBlockAction(AbstractDungeon.player, this.block));
    }

    // 获得一定格挡
    public void gainBlock(int amt) {
        this.addToBot(new GainBlockAction(AbstractDungeon.player, amt));
    }

    // 抽牌
    public void drawCards(int amt) {
        this.addToBot(new DrawCardAction(amt));
    }

    // 直接给予玩家能力
    public void applyToPlayer(AbstractPower power) {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, power));
    }

    // 这里我们重写了upgrade方法，因为有重复代码，大多数时候都能简化（除了灼热攻击）
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            limitedUpgrade();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    // 升级效果写这里即可
    public void limitedUpgrade() {

    }
}
