package aoetk.tools.a_go.view;

import aoetk.tools.a_go.conf.ApplicationContext;
import aoetk.tools.a_go.model.Record;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.ResourceBundle;

import static java.nio.file.StandardOpenOption.*;

public class Controller implements Initializable {

    public static final String KEY_SALLY = "sally";

    public static final String KEY_S_WIN = "sWin";

    public static final String KEY_BOSS_ACCESSION = "bossAccession";

    public static final String KEY_BOSS_WIN = "bossWin";

    private static final String DIFF_FORMAT = "%+d";

    @FXML
    MenuItem saveMenu;

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

    private Properties appProperties = new Properties();

    private ApplicationContext context = ApplicationContext.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadConf();
        record = new Record(
                Integer.valueOf(appProperties.getProperty(KEY_SALLY, "0")),
                Integer.valueOf(appProperties.getProperty(KEY_S_WIN, "0")),
                Integer.valueOf(appProperties.getProperty(KEY_BOSS_ACCESSION, "0")),
                Integer.valueOf(appProperties.getProperty(KEY_BOSS_WIN, "0"))
        );

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
        record.sallyProperty().addListener(observable -> toDisableSave(false));
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
        if (saveConf()) {
            toDisableSave(true);
        } else {
            handleSaveAsButtonAction(event);
        }
    }

    public void handleQuitButtonAction(ActionEvent event) {
        Platform.exit();
    }

    private void loadConf() {
        if (context.getSaveFilePath().isPresent()) {
            Path filePath = context.getSaveFilePath().get().toPath();
            if (Files.exists(filePath)) {
                try (InputStream inputStream = Files.newInputStream(filePath)) {
                    appProperties.load(inputStream);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean saveConf() {
        if (context.getSaveFilePath().isPresent()) {
            Path filePath = context.getSaveFilePath().get().toPath();
            try (BufferedWriter writer = Files.newBufferedWriter(filePath, CREATE, TRUNCATE_EXISTING, WRITE)) {
                appProperties.setProperty(KEY_SALLY, String.valueOf(record.getSally()));
                appProperties.setProperty(KEY_S_WIN, String.valueOf(record.getSWin()));
                appProperties.setProperty(KEY_BOSS_ACCESSION, String.valueOf(record.getBossAccession()));
                appProperties.setProperty(KEY_BOSS_WIN, String.valueOf(record.getBossWin()));
                appProperties.store(writer, "A-go record.");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private void toDisableSave(boolean disableSave) {
        saveButton.setDisable(disableSave);
        saveMenu.setDisable(disableSave);
    }

    public void handleOpenButtonAction(ActionEvent event) {
        openFile();
        loadConf();
    }

    private void openFile() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("データ保存を保存するファイルを選択してください");
        fileChooser.setSelectedExtensionFilter(
                new FileChooser.ExtensionFilter("プロパティファイル","*.properties"));
        final Window parentWindow = sallyText.getScene().getWindow();
        final File openedFile = fileChooser.showOpenDialog(parentWindow);
        if (openedFile != null) {
            context.setSaveFilePath(openedFile);
        }
    }

    public void handleSaveAsButtonAction(ActionEvent event) {
        openFile();
        if (saveConf()) {
            toDisableSave(true);
        }
    }

}
