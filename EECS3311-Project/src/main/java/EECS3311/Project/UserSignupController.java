package EECS3311.Project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;

/**
 * @author Nourin Abd El Hadi
 *
 */

public class UserSignupController {
	@FXML
	private Button signup;

	@FXML
	private Button back1;

	@FXML
	private TextField lastName;

	@FXML
	private TextField email;

	@FXML
	private PasswordField password;

	@FXML
	private TextField firstName;

	@FXML
	private Label warning2;

	@FXML
	void back1(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void userSignup(ActionEvent event) throws Exception {
		String line = "";
		String delimiter = ",";
		boolean flag = false;
		BufferedWriter signUps = new BufferedWriter(new FileWriter("signups.csv", true));
		signUps.close();
		String[] emails = new String[1000];
		String[] user = new String[1000];

		if (lastName.getText().equals("") || firstName.getText().equals("") || email.getText().equals("")
				|| password.getText().equals("")) {
			System.out.println("null fields");
			warning2.setVisible(true);

		} else {
			// read file
			BufferedReader read = new BufferedReader(new FileReader("signups.csv"));
			try {
				int i = 0;
				while ((line = read.readLine()) != null) {
					user = line.split(delimiter);
					emails[i] = user[2];
					i++;
				}
				int j = 0;
				while (emails[j] != null) {
					if (email.getText().equals(emails[j])) {
						flag = true;
					}
					j++;
				}

				System.out.println("file read");

			} catch (IOException e3) {
				e3.printStackTrace();
			}

			// write to file
			try {
				signUps = new BufferedWriter(new FileWriter("signups.csv", true));
				signUps.write(firstName.getText() + "," + lastName.getText() + "," + email.getText() + ","
						+ password.getText());
				signUps.newLine();
				signUps.flush();
				System.out.println("Successfully wrote to the file.");
			} catch (IOException e) {
				System.out.println("An error occurred.");
			} finally {
				if (signUps != null)
					try {
						signUps.close();
					} catch (IOException e2) {

					}
			}

			if (flag == true)
				System.out.println("email already exists");
			else {
				Run.email = email.getText();
				Run.fName = firstName.getText();
				Run.lName = lastName.getText();
				System.out.println("went to landing page");
				Parent parent = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
				Scene scene = new Scene(parent);
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.hide();
				stage.setScene(scene);
				stage.show();
			}
		}
	}

}
