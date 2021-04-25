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

/**
 * @author Nourin Abd El Hadi
 *
 */

public class OfficerController {

	@FXML
	private Button officerLogin;

	@FXML
	private Button remove;

	@FXML
	private TextField oEmail;

	@FXML
	private PasswordField oPassword;

	@FXML
	private Label oWarning;

	@FXML
	private Label oWarning2;

	@FXML
	private Label nullWarn;

	@FXML
	private Label giveID;

	@FXML
	private Label removed;

	@FXML
	private Button officerSignup;

	@FXML
	private TextField sEmail;

	@FXML
	private TextField sLast;

	@FXML
	private TextField sFirst;

	// officers login

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
				// putting each column into an array
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
		int unique = gen();
		String line = "";
		String delimiter = ",";
		boolean flag = false;
		String[] id = new String[1000];
		BufferedWriter officerRead = new BufferedWriter(new FileWriter("officers.csv", true));
		officerRead.close();
		String[] emails = new String[1000];
		String[] user = new String[1000];

		if (sLast.getText().equals("") || sFirst.getText().equals("") || sEmail.getText().equals("")) {
			System.out.println("null fields");
			nullWarn.setVisible(true);

		} else {
			// read file
			BufferedReader read = new BufferedReader(new FileReader("officers.csv"));
			try {
				int i = 0;
				while ((line = read.readLine()) != null) {
					// putting each column into an array
					user = line.split(delimiter);
					emails[i] = user[2];
					id[i] = user[3];
					i++;
				}
				int j = 0;

				if (unique == Integer.parseInt(id[j])) {
					unique = gen();
				}

				while (emails[j] != null) {
					if (sEmail.getText().equals(emails[j])) {
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
				officerRead = new BufferedWriter(new FileWriter("officers.csv", true));
				officerRead.write(sFirst.getText() + "," + sLast.getText() + "," + sEmail.getText() + "," + unique);
				officerRead.newLine();
				officerRead.flush();
				System.out.println("Successfully wrote to the file.");
			} catch (IOException e) {
				System.out.println("An error occurred.");
			} finally {
				if (officerRead != null)
					try {
						officerRead.close();
					} catch (IOException e2) {

					}
			}
			// error checking
			if (flag == true) {
				System.out.println("email already exists");
				giveID.setVisible(false);
				oWarning2.setVisible(true);
				nullWarn.setVisible(false);
				removed.setVisible(false);
			} else {
				System.out.println("Officer Signed up");
				giveID.setText("Officer Signed Up! Unique ID is: " + unique);
				giveID.setVisible(true);
				oWarning2.setVisible(false);
				nullWarn.setVisible(false);
				removed.setVisible(false);
			}
		}
	}

	@FXML
	void remove(ActionEvent event) throws IOException {

		String line = "";
		String delimiter = ",";
		String[] off = null;
		String[] firstName = new String[1000];
		int[] uniqueID = new int[1000];
		String[] lastName = new String[1000];
		String[] email = new String[1000];
		int index = 0;
		boolean check = false;

		// read file
		BufferedReader read = new BufferedReader(new FileReader("officers.csv"));
		try {
			int i = 0;
			while ((line = read.readLine()) != null) {
				off = line.split(delimiter);
				firstName[i] = off[0];
				lastName[i] = off[1];
				email[i] = off[2];
				uniqueID[i] = Integer.parseInt(off[3]);
				i++;
			}

			// looking for the email in the file and storing the index
			for (int j = 0; email[j] != null; j++) {
				if (sEmail.getText().equals(email[j])) {
					index = j;
				}
			}

			// if the inputed email exists, set all values in that row to null
			if (sEmail.getText().equals(email[index])) {
				check = true;
				firstName[index] = "";
				email[index] = "";
				lastName[index] = "";
				uniqueID[index] = 0;
			}

			read.close();

			System.out.println("file read");

		} catch (IOException e3) {
			e3.printStackTrace();
		}

		if (check == true) {
			// write to file
			BufferedWriter cancelled = null;
			try {
				cancelled = new BufferedWriter(new FileWriter("officers.csv"));

				for (int i = 0; email[i] != null; i++) {
					// if i is equal to index, then skip, else copy values from arrays to overwrite
					// file
					if (i == index)
						continue;
					else {
						cancelled.write(firstName[i] + "," + email[i] + "," + lastName[i] + "," + uniqueID[i]);
						cancelled.newLine();
					}
				}
				cancelled.flush();
				System.out.println("Successfully wrote to the file.");
			} catch (IOException e) {
				System.out.println("An error occurred.");
			} finally {
				if (cancelled != null)
					try {
						cancelled.close();
					} catch (IOException e2) {

					}
			}
			removed.setVisible(true);
			giveID.setVisible(false);
			oWarning2.setVisible(false);
			nullWarn.setVisible(false);
		}

	}

}
