import javax.swing.JTextField;

/*
 * Benjamin Marquis
 * CMSC 335
 * 12/14/2021
 * Project 3
 * PositionField.java
 * This is the PositionField object
 */
public class PositionField extends JTextField implements Runnable {
	public Car car;
	public Thread t;

	// constructor
	public PositionField(Car car, String text) {
		super(3);
		this.car = car;
		t = new Thread(this);
		this.setText(text);

	}
	@Override
	// runnable method
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			super.setText("" + car.position);

			t.yield();
		}
	}

}
