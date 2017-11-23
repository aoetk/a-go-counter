module aoetk.tools.a_go {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.prefs;
    exports aoetk.tools.a_go;
    opens aoetk.tools.a_go.view to javafx.fxml;
}
