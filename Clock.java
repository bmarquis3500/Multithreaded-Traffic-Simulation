import java.util.Date;

import javax.swing.JTextField;

//Thank you for the clock class!

/*
 * Benjamin Marquis
 * CMSC 335
 * 12/14/2021
 * Project 3
 * Clock.java
 * This builds the clock class
 */
class Clock implements Runnable {
	JTextField jtf;
	public Clock(JTextField jtf) {
		this.jtf = jtf;
	}

	@Override
	public void run() {
		while (true) {

			Date d1 = new Date();
			jtf.setText(d1.toString());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
		}
	}
}