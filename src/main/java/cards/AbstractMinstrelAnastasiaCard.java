 package cards;



import com.megacrit.cardcrawl.cards.AbstractCard;
import pathes.AbstractCardTagsEnum;


 public abstract class AbstractMinstrelAnastasiaCard
   extends AbstractMinstrelCard {
   public AbstractMinstrelAnastasiaCard(String id, String img, int cost, AbstractCard.CardType type, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
     super(id, img, cost, type, rarity, target);
     this.tags.add(AbstractCardTagsEnum.MINSTREL_TAGS);
   }
 }

