package pacman.view.info;

import pacman.model.ScoreKeeper;

public class ScoreView implements Observer {
    private final ScoreKeeper scoreKeeper;
    public ScoreView(ScoreKeeper scoreKeeper) {
        this.scoreKeeper = scoreKeeper;
    }

    @Override
    public void update() {

    }
}
