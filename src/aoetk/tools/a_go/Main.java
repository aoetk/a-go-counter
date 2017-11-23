package aoetk.tools.a_go;

import aoetk.tools.a_go.conf.ApplicationContext;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private ApplicationContext context;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/aoetk/tools/a_go/view/a-go.fxml"));
        primaryStage.setTitle("あ号カウンタ");
        primaryStage.setScene(new Scene(root));

        context = ApplicationContext.getInstance();
        primaryStage.setX(context.stageXProperty().get());
        primaryStage.setY(context.stageYProperty().get());
        context.stageXProperty().bind(primaryStage.xProperty());
        context.stageYProperty().bind(primaryStage.yProperty());

        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        context.saveConf();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
