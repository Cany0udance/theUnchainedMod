package theUnchainedMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theUnchainedMod.DefaultMod;
import theUnchainedMod.actions.SolarPlexusSoundAction;
import theUnchainedMod.characters.TheUnchained;
import theUnchainedMod.patches.CustomTags;
import theUnchainedMod.powers.SolarPlexusPower;
import theUnchainedMod.powers.WhiplashPower;
import theUnchainedMod.relics.Churros;
import theUnchainedMod.vfx.SolarPlexusEffect;

import static theUnchainedMod.DefaultMod.makeCardPath;

public class SolarPlexus extends AbstractDynamicCard {
    public static final String ID = DefaultMod.makeID(SolarPlexus.class.getSimpleName());
    public static final String IMG = makeCardPath("SolarPlexus.png");
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheUnchained.Enums.COLOR_ORANGE;

    private static final int COST = 0;
    private static final int DAMAGE = 5;
    private static final int UPGRADE_PLUS_DAMAGE = 2;
    private static final int MAGIC_NUMBER = 3;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = -1;


    public SolarPlexus() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC_NUMBER;
        tags.add(CustomTags.CHAIN);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new SolarPlexusEffect(m.hb.cX, m.hb.cY), 0.1F));
        AbstractDungeon.actionManager.addToBottom(new WaitAction(0.3f));
        AbstractDungeon.actionManager.addToBottom(new SolarPlexusSoundAction());
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY, false, true));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new SolarPlexusPower(p, magicNumber, m, CardType.ATTACK)));
        if (p.hasRelic(Churros.ID)) {
            Churros churros = (Churros) p.getRelic(Churros.ID);
            if (!churros.isEaten()) {
                churros.eat();
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new SolarPlexusPower(p, magicNumber, m, CardType.ATTACK)));
            }
        }
    }
}
