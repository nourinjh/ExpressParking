
package EECS3311.Project;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Nourin Abd El Hadi
 *
 */

public class forTable {
	private SimpleStringProperty parkingSpace, bookingID, BookingTime, user;

	public String getparkingSpace() {
		return parkingSpace.getValue();
	}

	public String getbookingID() {
		return bookingID.getValue();
	}

	public String getBookingTime() {
		return BookingTime.getValue();
	}

	public String getuser() {
		return user.getValue();
	}

	forTable(String parkingSpace, String bookingID, String BookingTime, String user) {
		this.parkingSpace = new SimpleStringProperty(parkingSpace);
		this.bookingID = new SimpleStringProperty(bookingID);
		this.BookingTime = new SimpleStringProperty(BookingTime);
		this.user = new SimpleStringProperty(user);

	}
}
