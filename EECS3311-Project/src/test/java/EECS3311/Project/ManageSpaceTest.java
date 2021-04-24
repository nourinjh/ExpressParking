package EECS3311.Project;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;


public class ManageSpaceTest {

	@FXML
    private Label nullWarn;

    @FXML
    private Label bookedWarn;

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

	private final ObservableList<forTable> manage = FXCollections.observableArrayList();

	@FXML
	void addSpace(ActionEvent event) {

	}

	@FXML
	void approve(ActionEvent event) {

	}

	@FXML
	void back(ActionEvent event) {

	}

	@FXML
	void cancel(ActionEvent event) {

	}

	@FXML
	void removeSpace(ActionEvent event) {

	}

	@FXML
	void readCSV(ActionEvent event) throws IOException {
//		String[] emails = new String[1000];
		String[] user = new String[1000];
//		String[] bookingID = new String[1000];
//		String[] bookingTime = new String[1000];
//		String[] parkingSpace = new String[1000];
		String line = "";
		String delimiter = ",";

		// read file
		BufferedReader read = new BufferedReader(new FileReader("parkingSpaces.csv"));
		try {
//			int i = 0;
			while ((line = read.readLine()) != null) {
				user = line.split(delimiter);

				forTable fortable = new forTable(user[0], user[3], user[2], user[1]);
				manage.add(fortable);
			}

			System.out.println("file read");

		} catch (IOException e3) {
			e3.printStackTrace();
		}
		
		table.setItems(manage);
		
		VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().add(table);


	}

}
