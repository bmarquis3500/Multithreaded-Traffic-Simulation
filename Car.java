import java.awt.Dimension;
import java.awt.Insets;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * Benjamin Marquis
 * CMSC 335
 * 12/14/2021
 * Project 3
 * Car.java
 * This builds the Car object
 */

public class Car extends JLabel implements Runnable {
	public int position;
	public int speed = 10;
	public JLabel car1, car2, car3;
	static ImageIcon blueCar = new ImageIcon("BlueCar.png");
	static ImageIcon orangeCar = new ImageIcon("OrangeCar.png");
	static ImageIcon purpleCar = new ImageIcon("PurpleCar.png");
	static Dimension size;
	static Insets inset;
	public AtomicBoolean pause = new AtomicBoolean(false);

	Thread t;

	// constructor
	public Car(int i, Insets inset) {
		super(blueCar);
		if (i == 2) {
			super.setIcon(orangeCar);
			this.position = 500;
		}
		if (i == 3) {
			super.setIcon(purpleCar);
			this.position = 1000;
		}

		t = new Thread(this);
		size = super.getPreferredSize();
		this.inset = inset;

	}
	@Override
	// runnable method
	public void run() {
		// infinite loop
		while (true) {
			// light 1
			if ((this.position > 425 && this.position < 450)
					&& Main.light1.isRed.get()) {
				speed = 0;
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {

				}
				speed = 10;

			}

			// light 2
			else if ((this.position > 925 && this.position < 950)
					&& Main.light2.isRed.get()) {
				speed = 0;
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {

				}
				speed = 10;

			}

			// light 3
			else if ((this.position > 1425 && this.position < 1450)
					&& Main.light3.isRed.get()) {
				speed = 0;
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {

				}
				speed = 10;

			}
			// if paused
			else if (pause.get()) {

			}

			//// movement
			else {
				super.setBounds(inset.left + this.position, inset.top + 235,
						size.width, size.height);
				this.position += speed / 10;
				Thread.yield();
			}

			// return to start
			if ((this.position > 1500)) {
				this.position = 0;
			}

			// sleep 1/100th of a second
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {

			}

		}

	}

	// setter
	public void setPosition(int p) {
		position = p;
	}
	// pause
	public void pause() {
		pause.set(true);
	}
	// play
	public void play() {
		pause.set(false);
	}

}
