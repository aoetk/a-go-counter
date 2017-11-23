package aoetk.tools.a_go.conf;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.io.File;
import java.net.URI;
import java.util.Optional;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * アプリケーション設定.
 * 設定内容はPreferences APIを用いて保存する.
 */
public class ApplicationContext {

    private static ApplicationContext ourInstance = new ApplicationContext();

    private Preferences preferences;

    private File saveFilePath;

    private DoubleProperty stageX = new SimpleDoubleProperty(this, "stageX", 0.0);

    private DoubleProperty stageY = new SimpleDoubleProperty(this, "stageY", 0.0);

    public static ApplicationContext getInstance() {
        return ourInstance;
    }

    private ApplicationContext() {
        preferences = Preferences.userNodeForPackage(ApplicationContext.class);
        restoreSettings();
    }

    /**
     * 設定を保存する.
     */
    public void saveConf() {
        try {
            preferences.putDouble(stageX.getName(), stageX.get());
            preferences.putDouble(stageY.getName(), stageY.get());
            if (saveFilePath != null) {
                preferences.put("saveFilePath", saveFilePath.toURI().toString());
            }
            preferences.flush();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    private void restoreSettings() {
        stageX.set(preferences.getDouble(stageX.getName(), stageX.get()));
        stageY.set(preferences.getDouble(stageY.getName(), stageY.get()));
        final String pathFromPref = preferences.get("saveFilePath", null);
        if (pathFromPref != null) {
            try {
                saveFilePath = new File(URI.create(pathFromPref));
            } catch (Exception e) {
                // ファイルパスが不正な場合は無し設定扱い
                e.printStackTrace();
            }
        }
    }

    /**
     * 情報を保存しているファイルのパスを返す.
     *
     * @return 情報を保存しているファイルのパス.
     */
    public Optional<File> getSaveFilePath() {
        return Optional.ofNullable(saveFilePath);
    }

    /**
     * 情報を保存するファイルのパスを設定する.
     *
     * @param saveFilePath 情報を保存するファイルのパス
     */
    public void setSaveFilePath(File saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    /**
     * Stageのx座標.
     *
     * @return Stageのx座標
     */
    public DoubleProperty stageXProperty() {
        return stageX;
    }

    /**
     * Stageのy座標.
     *
     * @return Stageのy座標
     */
    public DoubleProperty stageYProperty() {
        return stageY;
    }

}
