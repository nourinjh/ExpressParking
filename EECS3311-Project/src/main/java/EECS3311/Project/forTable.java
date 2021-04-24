/**
 * 
 */
package EECS3311.Project;

import javafx.beans.property.SimpleStringProperty;

public class forTable {
	private SimpleStringProperty parkingSpace, bookingID, BookingTime, user;

	public String getparkingSpace() {
		return parkingSpace.get();
	}

	public String getbookingID() {
		return bookingID.get();
	}

	public String getBookingTime() {
		return BookingTime.get();
	}

	public String getuser() {
		return user.get();
	}

	forTable(String parkingSpace, String bookingID, String BookingTime, String user) {
		this.parkingSpace = new SimpleStringProperty(parkingSpace);
		this.bookingID = new SimpleStringProperty(bookingID);
		this.BookingTime = new SimpleStringProperty(BookingTime);
		this.user = new SimpleStringProperty(user);

	}
}
