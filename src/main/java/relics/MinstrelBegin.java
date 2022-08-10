package relics;

import actions.SelectCardToHandAction;
import basemod.abstracts.CustomRelic;
import cards.minstrel.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import powers.AbstractMinstrelPower;
import powers.minstrel.ArmyPaeonPower;
import powers.minstrel.PoetSoulPower;

import java.util.ArrayList;
import java.util.Random;

// 继承CustomRelic
public class MinstrelBegin extends CustomRelic {
    /**
     * 遗物id
     */
    public static final String ID = "MinstrelBegin";
    /**
     * 图片路径
     */
    private static final String IMG = "img/minstrel/relics/test.png";
    /**
     * 图片底片
     */
    private static final String IMG_OTL = "img/minstrel/relics/outline/cLanguageProgramBegin.png";
    /**
     * 遗物类型
     */
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    /**
     * 点击音效
     */
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    /**
     * 调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
     */
    public MinstrelBegin() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public void atBattleStart() {
        //在战斗开始时触发
//        this.counter = 0;
        //3张演奏曲选择一张加入到手牌
        addToBot((AbstractGameAction) new SelectCardToHandAction(returnRandomCardByCardTagInCombat(), true, true));
    }
//    @Override
//    public void atTurnStart() {
//        AbstractPower poetSoulPower = AbstractDungeon.player.getPower(PoetSoulPower.POWER_ID);
//        if (poetSoulPower == null){
//            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player,
//                    (AbstractPower)new PoetSoulPower((AbstractCreature)AbstractDungeon.player, 1)));
//        } else if (poetSoulPower.amount < 4) {
//            Random random = new Random();
//            int n5 = random.nextInt(100);
////        if (n5<=30){
//            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player,
//                    (AbstractPower)new PoetSoulPower((AbstractCreature)AbstractDungeon.player, 1)));
////        }
//        }
//    }

    @Override
    public void onPlayerEndTurn() {
        AbstractPower poetSoulPower = AbstractDungeon.player.getPower(PoetSoulPower.POWER_ID);
        if (poetSoulPower == null || poetSoulPower.amount < 4) {
            Random random = new Random();
            int n5 = random.nextInt(100);
            //        if (n5<=30){
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player,
                    (AbstractPower) new PoetSoulPower((AbstractCreature) AbstractDungeon.player, 1)));
//        }
        }
    }

    //    @Override
//    public void onUseCard(AbstractCard card, UseCardAction action) {
//        //在用户使用牌时触发
//        if (card.type == AbstractCard.CardType.SKILL) {
//            this.counter++;
//            if (this.counter % 2 == 0) {
//                //如果是2的倍数，counter=0和获得5点格挡
//                this.counter = 0;
//                flash();
//                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new RelicAboveCreatureAction((AbstractCreature) AbstractDungeon.player, (AbstractRelic) this));
//                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new GainBlockAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, 3));
//            }
//        }
//    }

    @Override
    public void onVictory() {
        //在胜利时触发
        this.counter = -1;
    }

    /**
     * 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
     */
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MinstrelBegin();
    }

    public static ArrayList<AbstractCard> returnRandomCardByCardTagInCombat() {
        ArrayList<AbstractCard> returnCard = new ArrayList<>();
        returnCard.add(new ArmyPaeon());
        returnCard.add(new MageBallad());
        returnCard.add(new WandererMinuet());

        return returnCard;
    }
}
