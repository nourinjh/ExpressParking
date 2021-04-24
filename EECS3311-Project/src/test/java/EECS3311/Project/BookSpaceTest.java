package EECS3311.Project;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookSpaceTest {
	private BookSpaceController test = new BookSpaceController();

	@Test
	public void test_null_input() {

		try {
			test.spaceNum.setText("");
			test.plateNum.setText("L6D3Q1");
			test.bookingTime.setText("120");
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			test.spaceNum.setText("23453");
			test.plateNum.setText("");
			test.bookingTime.setText("12");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Test
	public void test_taken_spot() {
		try {
			test.spaceNum.setText("22222");
			test.plateNum.setText("L6D3Q1");
			test.bookingTime.setText("120");
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			test.spaceNum.setText("33333");
			test.plateNum.setText("L6D3Q1");
			test.bookingTime.setText("120");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Test
	public void test_more_than_3() {
		try {
			test.count = 4;

		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			test.count = 3;
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			test.count = 2;
			assertEquals("Booked! Your Booking ID is: " + test.bookID, test.booked.getText());
		} catch (Exception e) {
			System.out.println("error");
		}

	}

}