package EECS3311.Project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OfficerController {

	@FXML
	private Button officerLogin;

	@FXML
	private TextField oEmail;

	@FXML
	private PasswordField oPassword;

	@FXML
	private Label oWarning;

	@FXML
	private Label oWarning2;

	@FXML
	private Button officerSignup;

	@FXML
	private TextField sEmail;

	@FXML
	private TextField sLast;

	@FXML
	private TextField sFirst;

	@FXML
	void loginButton(ActionEvent event) throws IOException {
		String[] emails = new String[1000];
		String[] user = new String[1000];
		String[] id = new String[1000];
		String line = "";
		String delimiter = ",";
		boolean flag = false;

		// read file
		BufferedReader read = new BufferedReader(new FileReader("officers.csv"));
		try {
			int i = 0;
			while ((line = read.readLine()) != null) {
				user = line.split(delimiter);
				emails[i] = user[2];
				id[i] = user[3];
				i++;
			}
			int j = 0;
			while (emails[j] != null) {
				// i assumed that the unique id was the password
				if (oEmail.getText().equals(emails[j]) && oPassword.getText().equals(id[j])) {
					flag = true;
				}
				j++;
			}

			System.out.println("file read");

		} catch (IOException e3) {
			e3.printStackTrace();
		}

		// checking if login is correct
		if (flag == false) {
			System.out.println("Email and/or password are incorrect or don't exist");
			oWarning.setVisible(true);
		} else {
			Parent parent = FXMLLoader.load(getClass().getResource("OfficerPage.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.hide();
			stage.setScene(scene);
			stage.show();
		}
	}

	public int gen() {
		Random r = new Random(System.currentTimeMillis());
		return 10000 + r.nextInt(20000);
	}

	@FXML
	void signupButton(ActionEvent event) throws IOException {
		String line = "";
		String delimiter = ",";
		boolean flag = false;
		BufferedWriter signUps = new BufferedWriter(new FileWriter("officers.csv", true));
		signUps.close();
		String[] emails = new String[1000];
		String[] user = new String[1000];

		if (sLast.getText().equals("") || sFirst.getText().equals("") || sEmail.getText().equals("")) {
			System.out.println("null fields");
			oWarning2.setVisible(true);

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
					if (oEmail.getText().equals(emails[j])) {
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
				signUps = new BufferedWriter(new FileWriter("officers.csv", true));
				signUps.write(sFirst.getText() + "," + sLast.getText() + "," + sEmail.getText() + "," + gen());
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
				System.out.println("went to landing page");
				Parent parent = FXMLLoader.load(getClass().getResource("OfficerPage.fxml"));
				Scene scene = new Scene(parent);
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.hide();
				stage.setScene(scene);
				stage.show();
			}
		}
	}

}
