package theUnchainedMod.potions;

import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theUnchainedMod.actions.ChainAction;
import theUnchainedMod.cards.Liberation;

public class FinisherPotion extends CustomPotion {

    public static final String POTION_ID = theUnchainedMod.DefaultMod.makeID("FinisherPotion");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public FinisherPotion() {
        // The bottle shape and inside is determined by potion size and color. The actual colors are the main DefaultMod.java
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.M, PotionColor.SMOKE);
        potency = getPotency();
        description = DESCRIPTIONS[0];
        isThrown = false;
        tips.add(new PowerTip(name, description));
    }

    @Override
    public void use(AbstractCreature target) {
        target = AbstractDungeon.player;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            for (int i = 0; i < potency; i++) {
                AbstractCard liberation = new Liberation();
                liberation.setCostForTurn(0);
                this.addToBot(new MakeTempCardInHandAction(liberation.makeStatEquivalentCopy(), this.potency));
            }
        }
    }

    @Override
    public AbstractPotion makeCopy() {
        return new FinisherPotion();
    }

    @Override
    public int getPotency(final int potency) {
        return 1;
    }

    public void upgradePotion()
    {
        potency += 1;
        tips.clear();
        tips.add(new PowerTip(name, description));
    }
}
