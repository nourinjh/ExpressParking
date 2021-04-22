package EECS3311.Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UserLoginController {

	@FXML
	private Button login;

	@FXML
	private Button back;

	@FXML
	private TextField emailx;

	@FXML
	private PasswordField passwordx;

	@FXML
	private Label warning;

	@FXML
	void back(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void loginButton(ActionEvent event) throws Exception {

		String[] emails = new String[1000];
		String[] user = new String[1000];
		String[] passwords = new String[1000];
		String line = "";
		String delimiter = ",";
		boolean flag = false;

		// read file
		BufferedReader read = new BufferedReader(new FileReader("signups.csv"));
		try {
			int i = 0;
			while ((line = read.readLine()) != null) {
				user = line.split(delimiter);
				emails[i] = user[2];
				passwords[i] = user[3];
				i++;
			}
			int j = 0;
			while (emails[j] != null) {
				if (emailx.getText().equals(emails[j])) {
					flag = true;
				}
				j++;
			}

			System.out.println("file read");

		} catch (IOException e3) {
			e3.printStackTrace();
		}

		if (flag == false) {
			System.out.println("Email and/or password are incorrect or don't exist");
			warning.setVisible(true);
		} else {
			Parent parent = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.hide();
			stage.setScene(scene);
			stage.show();
		}
	}
}