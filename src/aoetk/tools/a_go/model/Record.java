package aoetk.tools.a_go.model;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;

/**
 * 出撃記録.
 */
public class Record {

    public static final int SALLY_GOAL = 36;

    public static final int S_WIN_GOAL = 6;

    public static final int BOSS_ACCESSION_GOAL = 24;

    public static final int BOSS_WIN_GOAL = 12;

    private ReadOnlyIntegerWrapper sally = new ReadOnlyIntegerWrapper(this, "sally", 0);

    private ReadOnlyIntegerWrapper sWin = new ReadOnlyIntegerWrapper(this, "sWin", 0);

    private ReadOnlyIntegerWrapper bossAccession = new ReadOnlyIntegerWrapper(this, "bossAccession", 0);

    private ReadOnlyIntegerWrapper bossWin = new ReadOnlyIntegerWrapper(this, "bossWin", 0);

    private ReadOnlyIntegerWrapper sallyDiff = new ReadOnlyIntegerWrapper(-SALLY_GOAL);

    private ReadOnlyIntegerWrapper sWinDiff = new ReadOnlyIntegerWrapper(-S_WIN_GOAL);

    private ReadOnlyIntegerWrapper bossAccessionDiff = new ReadOnlyIntegerWrapper(-BOSS_ACCESSION_GOAL);

    private ReadOnlyIntegerWrapper bossWinDiff = new ReadOnlyIntegerWrapper(-BOSS_WIN_GOAL);

    public Record() {
        sallyDiff.bind(sally.add(-SALLY_GOAL));
        sWinDiff.bind(sWin.add(-S_WIN_GOAL));
        bossAccessionDiff.bind(bossAccession.add(-BOSS_ACCESSION_GOAL));
        bossWinDiff.bind(bossWin.add(-BOSS_WIN_GOAL));
    }

    public Record(int sally, int sWin, int bossAccession, int bossWin) {
        this();
        this.sally.set(sally);
        this.sWin.set(sWin);
        this.bossAccession.set(bossAccession);
        this.bossWin.set(bossWin);
    }

    public int getSally() {
        return sally.get();
    }

    public ReadOnlyIntegerProperty sallyProperty() {
        return sally.getReadOnlyProperty();
    }

    public int getSWin() {
        return sWin.get();
    }

    public ReadOnlyIntegerProperty sWinProperty() {
        return sWin.getReadOnlyProperty();
    }

    public int getBossAccession() {
        return bossAccession.get();
    }

    public ReadOnlyIntegerProperty bossAccessionProperty() {
        return bossAccession.getReadOnlyProperty();
    }

    public int getBossWin() {
        return bossWin.get();
    }

    public ReadOnlyIntegerProperty bossWinProperty() {
        return bossWin.getReadOnlyProperty();
    }

    public int getSallyDiff() {
        return sallyDiff.get();
    }

    public ReadOnlyIntegerProperty sallyDiffProperty() {
        return sallyDiff.getReadOnlyProperty();
    }

    public int getSWinDiff() {
        return sWinDiff.get();
    }

    public ReadOnlyIntegerProperty sWinDiffProperty() {
        return sWinDiff.getReadOnlyProperty();
    }

    public int getBossAccessionDiff() {
        return bossAccessionDiff.get();
    }

    public ReadOnlyIntegerProperty bossAccessionDiffProperty() {
        return bossAccessionDiff.getReadOnlyProperty();
    }

    public int getBossWinDiff() {
        return bossWinDiff.get();
    }

    public ReadOnlyIntegerProperty bossWinDiffProperty() {
        return bossWinDiff.getReadOnlyProperty();
    }

    public void loose(boolean boss) {
        sally.set(getSally() + 1);
        if (boss) {
            bossAccession.set(getBossAccession() + 1);
        }
    }

    public void win(boolean boss) {
        sally.set(getSally() + 1);
        if (boss) {
            bossAccession.set(getBossAccession() + 1);
            bossWin.set(getBossWin() + 1);
        }
    }

    public void sWin(boolean boss) {
        sally.set(getSally() + 1);
        sWin.set(getSWin() + 1);
        if (boss) {
            bossAccession.set(getBossAccession() + 1);
            bossWin.set(getBossWin() + 1);
        }
    }

    public void reset() {
        sally.set(0);
        sWin.set(0);
        bossAccession.set(0);
        bossWin.set(0);
    }

}
