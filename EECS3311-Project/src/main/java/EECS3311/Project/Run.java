package EECS3311.Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Run extends Application {
	
	public static String email;
	
	public static String fName;
	
	public static String lName;
	
	public static Stage primaryStage;

	@Override
	public void start(Stage stage) throws Exception {
		primaryStage = stage;

		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		primaryStage.setTitle("Express Parking");
		Scene scene = new Scene(root);
		stage.sizeToScene();
		stage.setScene(scene);
		stage.setResizable(true);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
