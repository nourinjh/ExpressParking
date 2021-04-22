package EECS3311.Project;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LandingPageController {

	@FXML
	private Button pay;

	@FXML
	private Button cancellations;

	@FXML
	private Button viewBokings;

	@FXML
	private Button bookSpace;

	@FXML
	private Button signout;

	@FXML
	void bookSpace(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("BookSpace.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void cancellations(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("Cancel.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void pay(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("Pay.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void viewBookings(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("Booking.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void signOut(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setScene(scene);
		stage.show();
	}

}
