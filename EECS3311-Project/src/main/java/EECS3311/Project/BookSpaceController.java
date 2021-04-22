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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BookSpaceController {

	@FXML
	private Button back3;

	@FXML
	private TextField spaceNum;

	@FXML
	private PasswordField plateNum;

	@FXML
	private PasswordField bookingTime;

	@FXML
	private Button done;

	@FXML
	private Label bookedWarn;

	@FXML
	private Label nullWarn;

	@FXML
	void back3(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void done(ActionEvent event) throws IOException {
		boolean spaceFlag = false;
		String line = "";
		String delimiter = ",";
		String[] park = null;

		// read file
		BufferedReader read = new BufferedReader(new FileReader("parkingSpaces.csv"));
		try {
			while ((line = read.readLine()) != null) {
				park = line.split(delimiter);
			}
			int j = 0;
			while (park[j] != null) {
				if (spaceNum.getText().equals(park[j])) {
					spaceFlag = true;
				}
				j++;
			}

			System.out.println("file read");

		} catch (IOException e3) {
			e3.printStackTrace();
		}

		if (spaceNum.getText().equals("") || plateNum.getText().equals("") || bookingTime.getText().equals("")) {
			System.out.println("null fields");
			nullWarn.setVisible(true);
			bookedWarn.setVisible(false);
		} else if (spaceFlag = true) {
			System.out.println("parking space booked");
			bookedWarn.setVisible(true);
			nullWarn.setVisible(false);
		} else {
			//FUCK ME
		}
	}

}