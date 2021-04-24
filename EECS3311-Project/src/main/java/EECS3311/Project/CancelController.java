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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CancelController {

	@FXML
	public Button back;

	@FXML
	public Button done;

	@FXML
	public Label correct;

	@FXML
	public Label wrong;

	@FXML
	public TextField enterID;
	
	public String endText = "Cancelled";

	@FXML
	public void done() throws Exception {
		if(enterID.getText().contains(null))
			throw new Exception("No ID entered");
		
		
		String line = "";
		String delimiter = ",";
		String[] park = null;
		String[] space = new String[1000];
		String[] bookID = new String[1000];
		int[] bookTime = new int[1000];
		String[] email = new String[1000];
		int index = 0;
		boolean flag = false;
		// read file
		BufferedReader read = new BufferedReader(new FileReader("parkingSpaces.csv"));
		try {
			int i = 0;
			while ((line = read.readLine()) != null) {
				park = line.split(delimiter);
				space[i] = park[0];
				email[i] = park[1];
				bookTime[i] = Integer.parseInt(park[2]);
				bookID[i] = park[3];
				i++;
			}

			for (int j = 0; j < space.length; j++) {
				if (enterID.getText().equals(bookID[j])) {
					index = j;
				}
			}

			if (Run.email.equals(email[index]) && bookTime[index] > 0 && bookID[index].equals(enterID.getText())) {
				flag = true;
				space[index] = "";
				email[index] = "";
				bookTime[index] = 0;
				bookID[index] = "";
			}

			read.close();

			System.out.println("file read");

		} catch (IOException e3) {
			e3.printStackTrace();
		}

		if (flag == true) {
			// write to file
			BufferedWriter cancelled = null;
			try {
				cancelled = new BufferedWriter(new FileWriter("parkingSpaces.csv"));
				for (int i = 0; space[i] != null; i++) {
					if (i == index)
						continue;
					else {
						cancelled.write(space[i] + "," + email[i] + "," + bookTime[i] + "," + bookID[i]);
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
			System.out.println(endText);
			correct.setVisible(true);
			wrong.setVisible(false);
		} else {
			correct.setVisible(false);
			wrong.setVisible(true);
			throw new Exception("Incorrect booking id");
		}
	}

	@FXML
	void back3(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
		stage.setScene(scene);
		stage.show();
	}

}
