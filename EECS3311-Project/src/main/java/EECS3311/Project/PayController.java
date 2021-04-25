package EECS3311.Project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Nourin Abd El Hadi
 *
 */

public class PayController {

	@FXML
	private Label bookedWarn;

	@FXML
	private Label nullWarn;

	@FXML
	private Label error;

	@FXML
	private Button back3;

	@FXML
	private Label con;

	@FXML
	private AnchorPane buttonsSet;

	@FXML
	private Label payLabel;

	@FXML
	private Button paypal;

	@FXML
	private Button credit;

	@FXML
	private Button debit;

	@FXML
	private AnchorPane debitSet;

	@FXML
	private TextField cvvD;

	@FXML
	private TextField nameD;

	@FXML
	private TextField debNum;

	@FXML
	private DatePicker expD;

	@FXML
	private AnchorPane paypalSet;

	@FXML
	private TextField paymail;

	@FXML
	private PasswordField pass;

	@FXML
	private AnchorPane creditSet;

	@FXML
	private TextField cvv;

	@FXML
	private TextField name;

	@FXML
	private TextField credNum;

	@FXML
	private DatePicker exp;

	@FXML
	private AnchorPane mainSet;

	@FXML
	private TextField num3;

	@FXML
	private TextField num2;

	@FXML
	private TextField num1;

	@FXML
	private Button done;

	@FXML
	void done(ActionEvent event) throws IOException {
		if (num1.getText() == "" || num2.getText() == "" || num3.getText() == "") {
			System.out.println("at least one field needs to be filled");
			nullWarn.setVisible(true);
			bookedWarn.setVisible(false);
		}

		double bookPrice = 0;
		String[] allPark = new String[1000];
		String[] parkingSpace = new String[1000];
		String[] email = new String[1000];
		String[] spot = new String[1000];
		int[] bookTime = new int[1000];
		String line = "";
		String delimiter = ",";
		boolean flag = false;

		// read file
		BufferedReader read = new BufferedReader(new FileReader("parkingSpaces.csv"));
		try {
			allPark[0] = "no";
			allPark[1] = "no";
			allPark[2] = "no";
			int i = 0;
			while ((line = read.readLine()) != null) {
				spot = line.split(delimiter);
				parkingSpace[i] = spot[0];
				email[i] = spot[1];
				bookTime[i] = Integer.parseInt(spot[2]);
				i++;
			}
			int j = 0;
			int k = 0;
			while (parkingSpace[j] != null) {
				if (Run.email.contentEquals(email[j])) {
					allPark[k] = parkingSpace[j];

				}

				if (num1.getText().contentEquals(allPark[k])) {
					flag = true;
					bookPrice = Math.round((bookPrice + bookTime[j] * 0.05) * 100.0) / 100.0;
					k++;
				} else if (num2.getText().contentEquals(allPark[k])) {
					flag = true;
					bookPrice = Math.round((bookPrice + bookTime[j] * 0.05) * 100.0) / 100.0;
					k++;
				} else if (num3.getText().contentEquals(allPark[k])) {
					flag = true;
					bookPrice = Math.round((bookPrice + bookTime[j] * 0.05) * 100.0) / 100.0;
					k++;
				}

				j++;
			}

			System.out.println("file read");

		} catch (IOException e3) {
			e3.printStackTrace();
		}

		if (flag == true) {
			nullWarn.setVisible(false);
			bookedWarn.setVisible(false);
			done.setVisible(false);
			num1.setVisible(false);
			num2.setVisible(false);
			num3.setVisible(false);
			mainSet.setVisible(false);
			buttonsSet.setVisible(true);
			con.setVisible(false);

			payLabel.setText("Your total is: $" + bookPrice + "\nHow would you like to pay?");
		} else {
			System.out.println("wrong num");
			bookedWarn.setVisible(true);
		}
	}

	@FXML
	void credit(ActionEvent event) {
		debitSet.setVisible(false);
		creditSet.setVisible(true);
		paypalSet.setVisible(false);

	}

	@FXML
	void creditDone(ActionEvent event) {
		BufferedWriter payConfirm = null;
		try {
			payConfirm = new BufferedWriter(new FileWriter("payConfirm.csv", true));
			payConfirm.write(Run.email + "," + name.getText() + "," + credNum.getText() + "," + cvv.getText() + ","
					+ exp.getValue());
			payConfirm.newLine();
			payConfirm.flush();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
		} finally {
			if (payConfirm != null)
				try {
					payConfirm.close();
				} catch (IOException e2) {

				}
		}
		if (credNum.getText() == "" || name.getText() == "" || cvv.getText() == "" || exp.getValue() == null) {
			error.setVisible(true);
		} else {
			error.setVisible(false);
			nullWarn.setVisible(false);
			bookedWarn.setVisible(false);
			con.setVisible(true);
		}

	}

	@FXML
	void debit(ActionEvent event) {
		debitSet.setVisible(true);
		creditSet.setVisible(false);
		paypalSet.setVisible(false);
		error.setVisible(false);

	}

	@FXML
	void debitDone(ActionEvent event) {
		BufferedWriter payConfirm = null;

		try {
			payConfirm = new BufferedWriter(new FileWriter("payConfirm.csv", true));
			payConfirm.write(Run.email + "," + nameD.getText() + "," + debNum.getText() + "," + cvvD.getText() + ","
					+ expD.getValue());
			payConfirm.newLine();
			payConfirm.flush();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
		} finally {
			if (payConfirm != null)
				try {
					payConfirm.close();
				} catch (IOException e2) {

				}
		}

		if (debNum.getText() == "" || nameD.getText() == "" || cvvD.getText() == "" || expD.getValue() == null) {
			error.setVisible(true);

		} else {
			nullWarn.setVisible(false);
			bookedWarn.setVisible(false);
			con.setVisible(true);
			error.setVisible(false);
		}
	}

	@FXML
	void paypal(ActionEvent event) {
		paypalSet.setVisible(true);
		debitSet.setVisible(false);
		creditSet.setVisible(false);
		error.setVisible(false);

	}

	@FXML
	void paypalDone(ActionEvent event) {
		BufferedWriter payConfirm = null;
		try {
			payConfirm = new BufferedWriter(new FileWriter("payConfirm.csv", true));
			payConfirm.write(Run.email + "," + paymail.getText() + "," + pass.getText() + "," + "N/A" + "," + "N/A");
			payConfirm.newLine();
			payConfirm.flush();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
		} finally {
			if (payConfirm != null)
				try {
					payConfirm.close();
				} catch (IOException e2) {

				}
		}

		if (paymail.getText() == "" || pass.getText() == "") {
			error.setVisible(true);
		} else {
			nullWarn.setVisible(false);
			bookedWarn.setVisible(false);
			con.setVisible(true);
			error.setVisible(false);
		}

	}

	// back button
	@FXML
	void back3(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setScene(scene);
		stage.show();
	}

}
