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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BookSpaceController {

	@FXML
	private Button back3;

	@FXML
	private TextField spaceNum;

	@FXML
	private TextField plateNum;

	@FXML
	private TextField bookingTime;

	@FXML
	private Button done;

	@FXML
	private Label bookedWarn;

	@FXML
	private Label nullWarn;
	
	@FXML
	private Label tooMany;
	
	@FXML
	private Label booked;

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
		int count = 0;

		// read file
		BufferedReader read = new BufferedReader(new FileReader("parkingSpaces.csv"));
		try {
			count = 0;
			while ((line = read.readLine()) != null) {
				park = line.split(delimiter);

				if (Run.email.equals(park[1])) {
					count++;
					if (count >= 3)
						break;
				}

				if (spaceNum.getText().equals(park[0])) {
					spaceFlag = true;
				}

			}
			read.close();

			System.out.println("file read");

		} catch (IOException e3) {
			e3.printStackTrace();
		}

		if (spaceNum.getText().equals("") || plateNum.getText().equals("") || bookingTime.getText().equals("")) {
			System.out.println("null fields");
			nullWarn.setVisible(true);
			bookedWarn.setVisible(false);
			tooMany.setVisible(false);
			booked.setVisible(false);
		} else if (spaceFlag == true) {
			System.out.println("parking space is already reserved");
			bookedWarn.setVisible(true);
			nullWarn.setVisible(false);
			tooMany.setVisible(false);
			booked.setVisible(false);
		} else if (count >= 3) {
			System.out.println("too many bookings");
			tooMany.setVisible(true);
			bookedWarn.setVisible(false);
			nullWarn.setVisible(false);
			booked.setVisible(false);
		} else {
			// write to file
			BufferedWriter parkingSpaces = null;
			int bookID = gen();
			try {				
				parkingSpaces = new BufferedWriter(new FileWriter("parkingSpaces.csv", true));
				parkingSpaces.write(spaceNum.getText() + "," + Run.email + "," + bookingTime.getText() + "," + bookID);
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
			
			booked.setText("Booked! Your Booking ID is: " + bookID);
			booked.setVisible(true);
			tooMany.setVisible(false);
			nullWarn.setVisible(false);
			bookedWarn.setVisible(false);
		}
	}

	public int gen() {
		Random r = new Random(System.currentTimeMillis());
		return 10000 + r.nextInt(20000);
	}

}