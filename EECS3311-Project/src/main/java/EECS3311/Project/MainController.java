package EECS3311.Project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

/**
 * @author Nourin Abd El Hadi
 *
 */

public class MainController {
	@FXML
	private Button userLogin;

	@FXML
	private Button signup;

	@FXML
	private Button officer;

	@FXML
	void goOfficer(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("Officer.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void userSignup(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("UserSignup.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void loginPage(ActionEvent event) throws Exception {
		Parent main_parent = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
		Scene main_scene = new Scene(main_parent);
		Stage main_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		main_stage.hide();
		main_stage.setScene(main_scene);
		main_stage.show();
	}

}
