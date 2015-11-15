package aoetk.tools.a_go;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Controller implements Initializable {

    public static final String KEY_SALLY = "sally";

    public static final String KEY_S_WIN = "sWin";

    public static final String KEY_BOSS_ACCESSION = "bossAccession";

    public static final String KEY_BOSS_WIN = "bossWin";

    private static final String DIFF_FORMAT = "%+d";

    @FXML
    Button saveButton;

    @FXML
    RadioButton normalRadio;

    @FXML
    RadioButton bossRadio;

    @FXML
    Text sallyText;

    @FXML
    Text sallyDiffText;

    @FXML
    Text sWinText;

    @FXML
    Text sWinDiffText;

    @FXML
    Text bossAccessionText;

    @FXML
    Text bossAccessionDiffText;

    @FXML
    Text bossWinText;

    @FXML
    Text bossWinDiffText;

    private Record record;

    private Preferences preferences;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preferences = Preferences.userNodeForPackage(Controller.class);
        record = new Record(preferences.getInt(KEY_SALLY, 0),
                preferences.getInt(KEY_S_WIN, 0),
                preferences.getInt(KEY_BOSS_ACCESSION, 0),
                preferences.getInt(KEY_BOSS_WIN, 0));

        ToggleGroup toggleGroup = new ToggleGroup();
        normalRadio.setToggleGroup(toggleGroup);
        bossRadio.setToggleGroup(toggleGroup);

        // set bindings
        sallyText.textProperty().bind(record.sallyProperty().asString());
        sWinText.textProperty().bind(record.sWinProperty().asString());
        bossAccessionText.textProperty().bind(record.bossAccessionProperty().asString());
        bossWinText.textProperty().bind(record.bossWinProperty().asString());
        sallyDiffText.textProperty().bind(record.sallyDiffProperty().asString(DIFF_FORMAT));
        sWinDiffText.textProperty().bind(record.sWinDiffProperty().asString(DIFF_FORMAT));
        bossAccessionDiffText.textProperty().bind(record.bossAccessionDiffProperty().asString(DIFF_FORMAT));
        bossWinDiffText.textProperty().bind(record.bossWinDiffProperty().asString(DIFF_FORMAT));
        record.sallyProperty().addListener(observable -> saveButton.setDisable(false));
    }

    public void handleLooseButtonAction(ActionEvent event) {
        record.loose(bossRadio.isSelected());
    }

    public void handleWinButtonAction(ActionEvent event) {
        record.win(bossRadio.isSelected());
    }

    public void handleSWinButtonAction(ActionEvent event) {
        record.sWin(bossRadio.isSelected());
    }

    public void handleResetButtonAction(ActionEvent event) {
        record.reset();
    }

    public void handleSaveButtonAction(ActionEvent event) {
        try {
            preferences.putInt(KEY_SALLY, record.getSally());
            preferences.putInt(KEY_S_WIN, record.getSWin());
            preferences.putInt(KEY_BOSS_ACCESSION, record.getBossAccession());
            preferences.putInt(KEY_BOSS_WIN, record.getBossWin());
            preferences.flush();
            saveButton.setDisable(true);
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

}
