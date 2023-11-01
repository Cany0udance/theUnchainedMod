package theUnchainedMod.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import org.lwjgl.Sys;
import theUnchainedMod.TheUnchainedMod;
import theUnchainedMod.powers.ArcaneMasteryPower;
import theUnchainedMod.util.TextureLoader;

import java.util.ArrayList;

import static theUnchainedMod.TheUnchainedMod.makeRelicOutlinePath;
import static theUnchainedMod.TheUnchainedMod.makeRelicPath;

public class ArcaneAmplifier extends CustomRelic {

    public static final String ID = TheUnchainedMod.makeID("ArcaneAmplifier");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Arcane_Amplifier_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Arcane_Amplifier_relic.png"));
    public static ArrayList<ArcaneAmplifier> AllAmplifiers = new ArrayList<>();

    public ArcaneAmplifier() {
        super(ID, IMG, OUTLINE, RelicTier.SPECIAL, LandingSound.MAGICAL);
        tips.add(new PowerTip(
                BaseMod.getKeywordTitle(DESCRIPTIONS[1].toLowerCase()),
                BaseMod.getKeywordDescription(DESCRIPTIONS[1].toLowerCase())
        ));
        tips.add(new PowerTip(
                BaseMod.getKeywordTitle(DESCRIPTIONS[2].toLowerCase()),
                BaseMod.getKeywordDescription(DESCRIPTIONS[2].toLowerCase())
        ));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atTurnStart() {
        counter = 0;
    }

    @Override
    public void onVictory() { counter = -1; }
    @Override
    public void onTrigger()
    {
        counter++;
        if(counter >=3) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            counter = 0;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArcaneMasteryPower(AbstractDungeon.player, 1)));
        }
    }
    @Override
    public void onEquip() {
        if(!AllAmplifiers.contains(this)) AllAmplifiers.add(this);
        super.onEquip();
    }
    @Override
    public void onUnequip() {
        if(AllAmplifiers.contains(this)) AllAmplifiers.remove(this);
        super.onUnequip();
    }

    @Override
    public void atBattleStart() {
        if(!AllAmplifiers.contains(this)) AllAmplifiers.add(this);
        super.atBattleStart();
    }
}
