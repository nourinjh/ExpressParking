

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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * @author Nourin Abd El Hadi
 *
 */

public class PayStatusController {

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private TextField firstName;


    @FXML
    private TextField parkingSpace;

    @FXML
    void back1(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void userSignup(ActionEvent event) throws Exception {
    	if (firstName.getText().equals(null) || lastName.getText().equals(null) || email.getText().equals(null) || parkingSpace.getText().equals(null))
			throw new Exception("No ID entered");

		String line = "";
		String delimiter = ",";
		String[] park = null;
		boolean flag = false;

		// read file
		BufferedReader read = new BufferedReader(new FileReader("parkingSpaces.csv"));
		try {

			while ((line = read.readLine()) != null) {
				park = line.split(delimiter);

				if (parkingSpace.getText().equals(park[0])) {
					flag = true;
				}

			}
			read.close();

			System.out.println("file read");

		} catch (IOException e3) {
			e3.printStackTrace();
		}

		if (flag == false) {

			throw new Exception("Parking Already Exists");
		} else {

			// write to file
			BufferedWriter parkingSpaces = null;
			try {
				parkingSpaces = new BufferedWriter(new FileWriter("status.csv", true));
				parkingSpaces.write(parkingSpace.getText() + "," + firstName.getText() + "," + lastName.getText() + "," + email.getText());
				parkingSpaces.newLine();
				parkingSpaces.flush();
				System.out.println("Successfully wrote to the file.");
			} catch (IOException e) {
				System.out.println("An error occurred.");
			} finally {
				if (parkingSpaces != null)
					try {
						parkingSpaces.close();
					} catch (IOException e2) {

					}
			}
		}
    }

}
