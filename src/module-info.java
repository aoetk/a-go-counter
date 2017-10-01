module aoetk.tools.a_go {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    exports aoetk.tools.a_go;
    opens aoetk.tools.a_go to javafx.fxml;
}
