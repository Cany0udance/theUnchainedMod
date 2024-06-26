package theUnchainedMod.patches;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import theUnchainedMod.util.TextureLoader;

@SpirePatch(clz = AbstractCreature.class, method = "renderHealth")
public class RelayIconRenderPatch {

    private static final Texture relayIcon = TextureLoader.getTexture("theUnchainedModResources/images/ui/Relay_UIIcon.png");

    @SpirePostfixPatch
    public static void renderRelayIconAndValue(AbstractCreature creature, SpriteBatch sb, float ___BLOCK_ICON_X, float ___BLOCK_ICON_Y, float ___blockOffset, Hitbox ___hb, float ___hbYOffset, Color ___blockTextColor, float ___blockScale) {
        if(!Settings.hideCombatElements && RelayHelpers.currentRelay.get(creature) > 0) {
            int relayAmount = RelayHelpers.currentRelay.get(creature);
            float x = ___hb.cX - ___hb.width / 2.0F;
            float y = ___hb.cY - ___hb.height / 2.0F + ___hbYOffset;
            sb.setColor(RelayHelpers.relayColor.get(creature));
            sb.draw(relayIcon, x + RelayHelpers.RELAY_ICON_X - 32.0F, y + RelayHelpers.RELAY_ICON_Y - 32.0F,
                    32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale,
                    RelayHelpers.relayRotation.get(creature), 0, 0, 64, 64, false, false);
            FontHelper.renderFontCentered(sb, FontHelper.blockInfoFont, Integer.toString(relayAmount), x + RelayHelpers.RELAY_ICON_X, y + 30.0F * Settings.scale, RelayHelpers.relayTextColor.get(creature), RelayHelpers.relayScale.get(creature));
        }
    }
}
