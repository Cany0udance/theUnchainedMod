package theUnchainedMod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theUnchainedMod.DefaultMod;

public class TunnelVisionChainPower extends AbstractChainPower {
    public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID("TunnelVisionChainPower");
    private static final PowerStrings powerstrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerstrings.NAME;
    public static final String[] DESCRIPTIONS = powerstrings.DESCRIPTIONS;
    public static AbstractPower.PowerType POWER_TYPE = PowerType.BUFF;

    private final int tunnelAmount;

    public TunnelVisionChainPower(AbstractCreature owner, int amount, int tunnelAmount, AbstractCard.CardType cardType) {
        super(POWER_ID, owner, amount, new ApplyPowerAction(owner, owner, new TunnelVisionPower(owner, owner, tunnelAmount)), cardType);
        this.name = NAME;
        this.type = POWER_TYPE;
        this.tunnelAmount = tunnelAmount;
        this.updateDescription();
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.tunnelAmount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3] + this.tunnelAmount + DESCRIPTIONS[1];
        }
    }
}
