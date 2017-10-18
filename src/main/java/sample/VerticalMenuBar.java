package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author reegan
 */
public class VerticalMenuBar extends Application {
    public static TabPane tabPanel;
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = buildView();
        Scene scene = new Scene(root, 300, 250);        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    BorderPane buildView() {
        BorderPane root = new BorderPane();
        //tabPanel = new TabPane();
        root.setCenter(tabPanel);
        Accordion accordion = new Accordion();
        Pane pane = null;
        TitledPane tiledPane;
        General1Bar general1 = new General1Bar();
        pane= general1.getView();
        tiledPane = new TitledPane("General1", pane);
        
        accordion.getPanes().add(tiledPane);

        General2Bar general2 = new General2Bar();
        pane = general2.getView();
         tiledPane = new TitledPane("General2", pane);
        accordion.getPanes().add(tiledPane);

        General3Bar general3 = new General3Bar();
        pane = general3.getView();
        tiledPane = new TitledPane("General3", pane);
        accordion.getPanes().add(tiledPane);

        root.setLeft(accordion);
        return root;
    }



    public static void main(String[] args) {
        launch(args);
    }
}

class General1Bar {

    public Pane getView() {
        Pane p = new Pane();
        Button button = new Button("One");
        Button button1 = new Button("Two");
        VBox vBox = new VBox(0);
        vBox.getChildren().addAll(button,button1);
        p.getChildren().addAll(vBox);
        return p;
    }

}

class General2Bar {
     public Pane getView() {
        Pane p = new Pane();
        Button button = new Button("One");
        Button button1 = new Button("Two");
        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(button,button1);
        p.getChildren().addAll(vBox);
        return p;
    }

}

class General3Bar {
    public Pane getView() {
        Pane p = new Pane();
        Button button = new Button("One");
        Button button1 = new Button("Two");
        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(button,button1);
        p.getChildren().addAll(vBox);
        return p;
    }
}