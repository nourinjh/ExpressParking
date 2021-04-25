package EECS3311.Project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * @author Nourin Abd El Hadi
 *
 */

public class ViewBookingController {
	@FXML
	private Button back3;


	@FXML
	private ListView parkList;

	@FXML
	private ListView idList;

	@FXML
	private ListView timeList;

	@FXML
	private ListView paid;

	@FXML
	private ListView exp;

	@FXML
	void back3(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setScene(scene);
		stage.show();
	}

	public void initialize() throws FileNotFoundException {
		int[] index = new int[3];
		String[] emails = new String[1000];
		String[] user = new String[1000];
		String[] bookingID = new String[1000];
		String[] bookingTime = new String[1000];
		String[] parkingSpace = new String[1000];
//		String[] expire = new String[1000];
		String line = "";
		String delimiter = ",";

		// read file
		BufferedReader read = new BufferedReader(new FileReader("parkingSpaces.csv"));
		try {
			int i = 0;
			int j = 0;
			while ((line = read.readLine()) != null) {
				user = line.split(delimiter);
				emails[i] = user[1];
				bookingID[i] = user[2];
				bookingTime[i] = user[3];
				parkingSpace[i] = user[0];

				if(Run.email.equals(emails[i])) {
					index[j] = i;
					j++;
				}
				
				i++;
			}
				for (int t = 0; t < j; t++) {
					parkList.getItems().add(parkingSpace[index[t]]);
					idList.getItems().add(bookingID[index[t]]);
					timeList.getItems().add(bookingTime[index[t]]);
//					paid.getItems().add(paid1[k]);
//					exp.getItems().add(index[t]);
				}
			System.out.println("file read");

		} catch (IOException e3) {
			e3.printStackTrace();
		}

	}
}
