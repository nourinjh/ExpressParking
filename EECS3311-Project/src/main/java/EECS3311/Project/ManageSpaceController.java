package EECS3311.Project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
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
import javafx.scene.control.ListView;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Nourin Abd El Hadi
 *
 */

public class ManageSpaceController {

	@FXML
	private Label cancel;

	@FXML
	private Label exists;

	@FXML
	private Label removeddd;

	@FXML
	private Label added;

	@FXML
	private Label approvedd;

	@FXML
	private Label wrong;

	@FXML
	private TextField enterID;

	@FXML
	private TableColumn<forTable, String> park;

	@FXML
	private TableColumn<forTable, String> book;

	@FXML
	private TableColumn<forTable, String> bookTime;

	@FXML
	private TableColumn<forTable, String> user;

	@FXML
	private TableView<forTable> table = new TableView<>();

	@FXML
	private ListView parkList = new ListView();
	@FXML
	private ListView idList = new ListView();
	@FXML
	private ListView timeList = new ListView();
	@FXML
	private ListView userList = new ListView();

	public void initialize() throws FileNotFoundException {

		String[] emails = new String[1000];
		String[] user = new String[1000];
		String[] bookingID = new String[1000];
		String[] bookingTime = new String[1000];
		String[] parkingSpace = new String[1000];
		String line = "";
		String delimiter = ",";

		// read file
		BufferedReader read = new BufferedReader(new FileReader("parkingSpaces.csv"));
		try {
			int i = 0;
			while ((line = read.readLine()) != null) {
				user = line.split(delimiter);
				emails[i] = user[1];
				bookingID[i] = user[2];
				bookingTime[i] = user[3];
				parkingSpace[i] = user[0];

				for (int k = 0; parkingSpace[k] != null; k++) {
					parkList.getItems().add(parkingSpace[k]);
					idList.getItems().add(bookingID[k]);
					timeList.getItems().add(bookingTime[k]);
					userList.getItems().add(emails[k]);

				}

			}

			System.out.println("file read");

		} catch (IOException e3) {
			e3.printStackTrace();
		}

	}

	@FXML
	void addSpace(ActionEvent event) throws Exception {
		if (enterID.getText().contains(null))
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

				if (enterID.getText().equals(park[0])) {
					flag = true;
				}

			}
			read.close();

			System.out.println("file read");

		} catch (IOException e3) {
			e3.printStackTrace();
		}

		if (flag == true) {
			exists.setVisible(true);
			throw new Exception("Parking Already Exists");
		} else {
			exists.setVisible(false);
			// write to file
			BufferedWriter parkingSpaces = null;
			try {
				parkingSpaces = new BufferedWriter(new FileWriter("parkingSpaces.csv", true));
				parkingSpaces.write(enterID.getText());
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

	@FXML
	void approve(ActionEvent event) throws Exception {
		if (enterID.getText().contains(null))
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

				if (enterID.getText().equals(park[0])) {
					flag = true;
				}

			}
			read.close();

			System.out.println("file read");

		} catch (IOException e3) {
			e3.printStackTrace();
		}

		if (flag == true) {
			exists.setVisible(true);
			throw new Exception("Parking Already Exists");
		} else {
			exists.setVisible(false);
			// write to file
			BufferedWriter parkingSpaces = null;
			try {
				parkingSpaces = new BufferedWriter(new FileWriter("parkingSpaces.csv", true));
				parkingSpaces.write("approved");
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
	void cancel(ActionEvent event) throws Exception {
		if (enterID.getText().contains(null))
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

			if (bookTime[index] > 0 && bookID[index].equals(enterID.getText())) {
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
			System.out.println("removed");
			cancel.setVisible(true);
			approvedd.setVisible(false);
			removeddd.setVisible(false);
			added.setVisible(false);
			wrong.setVisible(false);
		} else {
			cancel.setVisible(false);
			approvedd.setVisible(false);
			removeddd.setVisible(false);
			added.setVisible(false);
			wrong.setVisible(true);
			throw new Exception("Incorrect booking id");
		}
	}

	@FXML
	void removeSpace(ActionEvent event) throws Exception {
		if (enterID.getText().contains(null))
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

			if (bookTime[index] > 0 && bookID[index].equals(enterID.getText())) {
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
			System.out.println("removed");
			cancel.setVisible(true);
			approvedd.setVisible(false);
			removeddd.setVisible(false);
			added.setVisible(false);
			wrong.setVisible(false);
		} else {
			cancel.setVisible(false);
			approvedd.setVisible(false);
			removeddd.setVisible(false);
			added.setVisible(false);
			wrong.setVisible(true);
			throw new Exception("Incorrect booking id");
		}
	}

}
