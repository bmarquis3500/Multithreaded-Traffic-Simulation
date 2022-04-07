import javax.swing.JTextField;

/*
 * Benjamin Marquis
 * CMSC 335
 * 12/14/2021
 * Project 3
 * SpeedField.java
 * This is the SpeedField class
 */
public class SpeedField extends JTextField implements Runnable {
	public Car car;
	public Thread t;

	// constructor
	public SpeedField(Car car, String text) {
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
			super.setText("" + car.speed * 10);

			t.yield();
		}
	}

}
