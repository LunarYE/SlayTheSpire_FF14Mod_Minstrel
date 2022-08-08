/*     */ package characters;
/*     */

import basemod.abstracts.CustomEnergyOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class EnergyOrbMinstrel extends CustomEnergyOrb {
  private static final float ORB_IMG_SCALE = 1.15F * Settings.scale;

  private float angle4;
  private float angle3;
  private float angle2;
  private float angle1;
  private Color glowColor = Color.WHITE.cpy();
  private float duration = 1.0F;
  private float timer = 0.0F;

  public EnergyOrbMinstrel(String[] orbTexturePaths, String orbVfxPath) {
    super(orbTexturePaths, orbVfxPath, null);
  }


  public void updateOrb(int orbCount) {
    this.timer += Gdx.graphics.getDeltaTime();
    this.duration = (float)Math.sin(this.timer) / 2.0F + 1.0F;
    this.glowColor.a = Interpolation.fade.apply(0.6F, 0.2F, this.duration);


    if (orbCount == 0) {
      this.angle4 += Gdx.graphics.getDeltaTime() * 5.0F;
      this.angle3 += Gdx.graphics.getDeltaTime() * -5.0F;
      this.angle2 += Gdx.graphics.getDeltaTime() * 8.0F;
      this.angle1 += Gdx.graphics.getDeltaTime() * -8.0F;
    } else {
      this.angle4 += Gdx.graphics.getDeltaTime() * 20.0F;
      this.angle3 += Gdx.graphics.getDeltaTime() * -20.0F;
      this.angle2 += Gdx.graphics.getDeltaTime() * 40.0F;
      this.angle1 += Gdx.graphics.getDeltaTime() * -40.0F;
    }
  }




}


/* Location:              G:\Steam game\steamapps\workshop\content\646570\2568224579\VUPShionMod.jar!\VUPShionMod\modules\EnergyOrbShion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */