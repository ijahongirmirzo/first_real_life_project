package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main extends Application {
    Connection connection= SqliteConnector.Connector();
    @Override
    public void start(Stage primaryStage) throws Exception{
        checkConnection();
        Parent root = FXMLLoader.load(getClass().getResource("Views/MainTable.fxml"));
        primaryStage.setTitle("Patient list");
        Scene scene=new Scene(root);
        scene.getStylesheets().add(getClass().getResource("design.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
public void checkConnection(){
        if(connection==null){
            System.out.println("Database couldn't be attached");
        }
        else {
            System.out.println("Database successfully attached");
        }

}
    public static void main(String[] args) {
        launch(args);
    }
}
