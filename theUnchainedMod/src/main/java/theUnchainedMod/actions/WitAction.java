package theUnchainedMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class WitAction extends AbstractGameAction {

    private boolean upgraded;

    public WitAction(boolean upgraded) {
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        AbstractDungeon.actionManager.addToTop(new WaitAction(0.1F));
        this.tickDuration();
        if (this.isDone) {
            Iterator var1 = DrawCardAction.drawnCards.iterator();
            while (var1.hasNext()) {
                AbstractCard c = (AbstractCard) var1.next();
                if (c.cost == 2 || c.costForTurn == 2) {
                    AbstractDungeon.actionManager.addToTop(new NewQueueCardAction(c, true, false, true));
                    if (upgraded) {
                        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(1));
                    }
                    break;
                }
            }
        }
    }
}
