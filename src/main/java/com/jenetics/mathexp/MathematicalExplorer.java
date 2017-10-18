package com.jenetics.mathexp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MathematicalExplorer extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = buildView();
		Scene scene = new Scene(root, 300, 250);        
		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	BorderPane buildView() {
		BorderPane root = new BorderPane();
//		tabPanel = new TabPane();
//		root.setCenter(tabPanel);
//		Accordion accordion = new Accordion();
//		Pane pane = null;
//		TitledPane tiledPane;
//		General1Bar general1 = new General1Bar();
//		pane= general1.getView();
//		tiledPane = new TitledPane("General1", pane);
//		accordion.getPanes().add(tiledPane);
//
//		General2Bar general2 = new General2Bar();
//		pane = general2.getView();
//		tiledPane = new TitledPane("General2", pane);
//		accordion.getPanes().add(tiledPane);
//
//		General3Bar general3 = new General3Bar();
//		pane = general3.getView();
//		tiledPane = new TitledPane("General3", pane);
//		accordion.getPanes().add(tiledPane);
//
//		root.setLeft(accordion);
		return root;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
