package EECS3311.Project;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CancelTest {
	private CancelController test = new CancelController();

	@Test
	public void test_null_input() {

		try {
			test.enterID.setText("");

		} catch (Exception e) {
			System.out.println(e);
		}


	}

	@Test
	public void test_wrong_ID() {
		try {
			test.enterID.setText("12343");

		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			test.enterID.setText("12345");
			assertEquals("Cancelled", test.endText);
		} catch (Exception e) {
			System.out.println(e);
		}

	}


}