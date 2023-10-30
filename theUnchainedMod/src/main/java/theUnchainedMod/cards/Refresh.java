package theUnchainedMod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theUnchainedMod.TheUnchainedMod;
import theUnchainedMod.actions.GainRelayAction;
import theUnchainedMod.actions.RefreshAction;
import theUnchainedMod.characters.TheUnchained;

import static theUnchainedMod.TheUnchainedMod.makeCardPath;

public class Refresh extends AbstractDynamicRelayCard {

    public static final String ID = TheUnchainedMod.makeID(Refresh.class.getSimpleName());
    public static final String IMG = makeCardPath("Refresh.png");
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheUnchained.Enums.UNCHAINED_COLOR;


    private static final int COST = 2;
    private static final int MAGIC_NUMBER = 14;
    private static final int SECOND_MAGIC_NUMBER = 14;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 4;
    private static final int UPGRADE_PLUS_SECOND_MAGIC_NUMBER = 4;

    public Refresh() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC_NUMBER;
        defaultBaseSecondMagicNumber = defaultSecondMagicNumber = SECOND_MAGIC_NUMBER;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_SECOND_MAGIC_NUMBER);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainRelayAction(p, magicNumber));
        AbstractDungeon.actionManager.addToBottom(new RefreshAction(defaultSecondMagicNumber, p));
    }
}
