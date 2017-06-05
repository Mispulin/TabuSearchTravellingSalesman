package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TabuSearchTravellingSalesman extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/TabuSearchTravellingSalesman.fxml"));
        primaryStage.setTitle("Tabu Search and the Travelling Salesman");
        primaryStage.setScene(new Scene(root, 570, 350));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
