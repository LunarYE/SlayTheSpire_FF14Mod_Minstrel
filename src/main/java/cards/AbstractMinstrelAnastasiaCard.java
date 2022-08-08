/*    */ package cards;
/*    */ 
/*    */

import com.megacrit.cardcrawl.cards.AbstractCard;
import pathes.AbstractCardTagsEnum;

/*    */
/*    */ public abstract class AbstractMinstrelAnastasiaCard
/*    */   extends AbstractMinstrelCard {
/*    */   public AbstractMinstrelAnastasiaCard(String id, String img, int cost, AbstractCard.CardType type, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
/*  9 */     super(id, img, cost, type, rarity, target);
/* 10 */     this.tags.add(AbstractCardTagsEnum.MINSTREL_TAGS);
/*    */   }
/*    */ }


/* Location:              G:\Steam game\steamapps\workshop\content\646570\2568224579\VUPShionMod.jar!\VUPShionMod\cards\ShionCard\AbstractShionAnastasiaCard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */