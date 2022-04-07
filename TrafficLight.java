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
 * TrafficLight.java
 * This is the TrafficLight class
 */

public class TrafficLight extends JLabel implements Runnable {
	public JLabel greenLabel, yellowLabel, redLabel;
	public volatile String color = "red";
	public static ImageIcon redLight = new ImageIcon("RedLight.png");
	public static ImageIcon greenLight = new ImageIcon("GreenLight.png");
	public static ImageIcon yellowLight = new ImageIcon("YellowLight.png");
	public AtomicBoolean running = new AtomicBoolean(true);
	public AtomicBoolean pause = new AtomicBoolean(false);
	public AtomicBoolean isRed = new AtomicBoolean(true);
	public Dimension lightSize;
	public Insets inset;
	public int i;

	public Thread t;
	// constructor
	public TrafficLight(int i, Insets inset) {
		super(redLight);
		isRed.set(true);
		this.inset = inset;

		lightSize = this.getPreferredSize();
		this.setVisible(true);
		this.setBounds(inset.left - 20 + (i * 500), inset.top + 305,
				lightSize.width, lightSize.height);

		greenLabel = new JLabel(greenLight);
		yellowLabel = new JLabel(yellowLight);
		redLabel = new JLabel(redLight);
		running.set(true);

		t = new Thread(this);

	}

	@Override

	// runnable method
	public void run() {

		// infinite loop
		while (true) {
			// while running and not paused
			while (running.get() && !pause.get()) {
				// if color is red
				if (running.get() && !pause.get() && color == "red") {
					isRed.set(true);
					try {
						t.sleep(12000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
					}
					if (!pause.get() && color == "red") {
						super.setIcon(greenLight);
						color = "green";
						isRed.set(false);
					} else {
						continue;
					}
				}
				// if color is green
				if (running.get() && !pause.get() && color == "green") {
					try {
						t.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
					}
					if (!pause.get() && color == "green") {
						super.setIcon(yellowLight);
						color = "yellow";
						isRed.set(false);
					} else {
						continue;
					}
				}
				// if color is yellow
				if (running.get() && !pause.get() && color == "yellow") {
					try {
						t.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
					}
					if (!pause.get() && color == "yellow") {
						super.setIcon(redLight);
						color = "red";
						isRed.set(true);
					} else {
						continue;
					}
				}

			}
			Thread.yield();
		}
	}

	// turn off method
	public void turnOff() {
		running.set(false);
		color = "green";
		isRed.set(false);
		super.setVisible(false);
	}
	// turn on method
	public void turnOn() {
		color = "red";
		isRed.set(true);
		running.set(true);
		super.setVisible(true);
		super.setIcon(redLight);

	}

	// getter
	public String getColor() {
		return color;
	}

	// pause
	public void pause() {
		running.set(false);
		pause.set(true);
	}

	// play
	public void play() {
		running.set(true);
		pause.set(false);
	}

}
