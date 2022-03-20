package theUnchainedMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theUnchainedMod.DefaultMod;
import theUnchainedMod.actions.NonDominantHandAction;
import theUnchainedMod.characters.TheDefault;

import static theUnchainedMod.DefaultMod.makeCardPath;

public class NonDominantHand extends AbstractDynamicCard {
    public static final String ID = DefaultMod.makeID(NonDominantHand.class.getSimpleName());
    public static final String IMG = makeCardPath("NonDominantHand.png");
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 0;
    private static final int MAGIC_NUMBER = 5;
    private static final int SECOND_MAGIC_NUMBER = 5;
    private static final int UPGRADE_PLUS_SECOND_MAGIC_NUMBER = 2;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 2;

    public NonDominantHand() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = MAGIC_NUMBER;
        defaultBaseSecondMagicNumber = SECOND_MAGIC_NUMBER;
    }

    @Override
    public void upgrade() {
        if(!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_SECOND_MAGIC_NUMBER);
        }

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new NonDominantHandAction(p, m, magicNumber, defaultSecondMagicNumber));
    }
}
