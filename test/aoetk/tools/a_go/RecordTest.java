package aoetk.tools.a_go;

import aoetk.tools.a_go.model.Record;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RecordTest {

    @Test
    public void testInit() throws Exception {
        Record sut = new Record();
        assertThat(sut.getSally(), is(0));
        assertThat(sut.getSWin(), is(0));
        assertThat(sut.getBossAccession(), is(0));
        assertThat(sut.getBossWin(), is(0));
        assertThat(sut.getSallyDiff(), is(-Record.SALLY_GOAL));
        assertThat(sut.getSWinDiff(), is(-Record.S_WIN_GOAL));
        assertThat(sut.getBossAccessionDiff(), is(-Record.BOSS_ACCESSION_GOAL));
        assertThat(sut.getBossWinDiff(), is(-Record.BOSS_WIN_GOAL));
    }

    @Test
    public void testInitWithParam() throws Exception {
        Record sut = new Record();
        sut.setData(new Record.Data(55, 44, 13, 13));
        assertThat(sut.getSally(), is(55));
        assertThat(sut.getSWin(), is(44));
        assertThat(sut.getBossAccession(), is(13));
        assertThat(sut.getBossWin(), is(13));
        assertThat(sut.getSallyDiff(), is(55 - Record.SALLY_GOAL));
        assertThat(sut.getSWinDiff(), is(44 - Record.S_WIN_GOAL));
        assertThat(sut.getBossAccessionDiff(), is(13 - Record.BOSS_ACCESSION_GOAL));
        assertThat(sut.getBossWinDiff(), is(13 - Record.BOSS_WIN_GOAL));
    }

    @Test
    public void testLooseNormal() throws Exception {
        Record sut = new Record();
        sut.loose(false);
        assertThat(sut.getSally(), is(1));
        assertThat(sut.getSWin(), is(0));
        assertThat(sut.getBossAccession(), is(0));
        assertThat(sut.getBossWin(), is(0));
        assertThat(sut.getSallyDiff(), is(1 - Record.SALLY_GOAL));
        assertThat(sut.getSWinDiff(), is(0 - Record.S_WIN_GOAL));
        assertThat(sut.getBossAccessionDiff(), is(0 - Record.BOSS_ACCESSION_GOAL));
        assertThat(sut.getBossWinDiff(), is(0 - Record.BOSS_WIN_GOAL));
    }

    @Test
    public void testLooseBoss() throws Exception {
        Record sut = new Record();
        sut.loose(true);
        assertThat(sut.getSally(), is(1));
        assertThat(sut.getSWin(), is(0));
        assertThat(sut.getBossAccession(), is(1));
        assertThat(sut.getBossWin(), is(0));
        assertThat(sut.getSallyDiff(), is(1 - Record.SALLY_GOAL));
        assertThat(sut.getSWinDiff(), is(0 - Record.S_WIN_GOAL));
        assertThat(sut.getBossAccessionDiff(), is(1 - Record.BOSS_ACCESSION_GOAL));
        assertThat(sut.getBossWinDiff(), is(0 - Record.BOSS_WIN_GOAL));
    }

    @Test
    public void testWinNormal() throws Exception {
        Record sut = new Record();
        sut.win(false);
        assertThat(sut.getSally(), is(1));
        assertThat(sut.getSWin(), is(0));
        assertThat(sut.getBossAccession(), is(0));
        assertThat(sut.getBossWin(), is(0));
        assertThat(sut.getSallyDiff(), is(1 - Record.SALLY_GOAL));
        assertThat(sut.getSWinDiff(), is(0 - Record.S_WIN_GOAL));
        assertThat(sut.getBossAccessionDiff(), is(0 - Record.BOSS_ACCESSION_GOAL));
        assertThat(sut.getBossWinDiff(), is(0 - Record.BOSS_WIN_GOAL));
    }

    @Test
    public void testWinBoss() throws Exception {
        Record sut = new Record();
        sut.win(true);
        assertThat(sut.getSally(), is(1));
        assertThat(sut.getSWin(), is(0));
        assertThat(sut.getBossAccession(), is(1));
        assertThat(sut.getBossWin(), is(1));
        assertThat(sut.getSallyDiff(), is(1 - Record.SALLY_GOAL));
        assertThat(sut.getSWinDiff(), is(0 - Record.S_WIN_GOAL));
        assertThat(sut.getBossAccessionDiff(), is(1 - Record.BOSS_ACCESSION_GOAL));
        assertThat(sut.getBossWinDiff(), is(1 - Record.BOSS_WIN_GOAL));
    }

    @Test
    public void testSWinNormal() throws Exception {
        Record sut = new Record();
        sut.sWin(false);
        assertThat(sut.getSally(), is(1));
        assertThat(sut.getSWin(), is(1));
        assertThat(sut.getBossAccession(), is(0));
        assertThat(sut.getBossWin(), is(0));
        assertThat(sut.getSallyDiff(), is(1 - Record.SALLY_GOAL));
        assertThat(sut.getSWinDiff(), is(1 - Record.S_WIN_GOAL));
        assertThat(sut.getBossAccessionDiff(), is(0 - Record.BOSS_ACCESSION_GOAL));
        assertThat(sut.getBossWinDiff(), is(0 - Record.BOSS_WIN_GOAL));
    }

    @Test
    public void testSWinBoss() throws Exception {
        Record sut = new Record();
        sut.sWin(true);
        assertThat(sut.getSally(), is(1));
        assertThat(sut.getSWin(), is(1));
        assertThat(sut.getBossAccession(), is(1));
        assertThat(sut.getBossWin(), is(1));
        assertThat(sut.getSallyDiff(), is(1 - Record.SALLY_GOAL));
        assertThat(sut.getSWinDiff(), is(1 - Record.S_WIN_GOAL));
        assertThat(sut.getBossAccessionDiff(), is(1 - Record.BOSS_ACCESSION_GOAL));
        assertThat(sut.getBossWinDiff(), is(1 - Record.BOSS_WIN_GOAL));
    }

}
