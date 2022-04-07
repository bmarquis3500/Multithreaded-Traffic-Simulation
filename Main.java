import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Benjamin Marquis
 * CMSC 335
 * 12/14/2021
 * Project 3
 * Main.java
 * This is the main file of the program
 */
public class Main {
	public static JFrame frame;
	public static JPanel panel;
	public static volatile TrafficLight light1;
	public static volatile TrafficLight light2;
	public static volatile TrafficLight light3;
	public static boolean paused = false;
	public static Car car1, car2, car3;

	public static void main(String[] args) {

		frame = new JFrame("Stop Light Simulator");
		JPanel panel = new JPanel();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1535, 488);

		panel.setLayout(null);
		Insets inset = panel.getInsets();

		// map
		ImageIcon map = new ImageIcon("Map.png");
		JLabel mapLabel = new JLabel(map);
		Dimension mapSize = mapLabel.getPreferredSize();
		mapLabel.setBounds(inset.left, inset.top, mapSize.width,
				mapSize.height);
		// light 1
		light1 = new TrafficLight(1, inset);

		// light 2

		light2 = new TrafficLight(2, inset);
		// light 3

		light3 = new TrafficLight(3, inset);

		// clock
		JTextField clockField = new JTextField(16);
		Dimension clockSize = clockField.getPreferredSize();
		clockField.setBounds(inset.left + 200, inset.top + 100, clockSize.width,
				clockSize.height);
		Clock clock = new Clock(clockField);
		Thread clockThread = new Thread(clock);
		clockField.setVisible(true);

		// car 1
		car1 = new Car(1, inset);
		car1.setVisible(true);

		// car 2
		car2 = new Car(2, inset);
		car2.setVisible(true);

		// car 3
		car3 = new Car(3, inset);
		car3.setVisible(true);

		// frame
		frame.add(clockField);
		panel.add(mapLabel);
		frame.add(light1);
		frame.add(light2);
		frame.add(light3);
		frame.add(car1);
		frame.add(car2);
		frame.add(car3);
		panel.setVisible(true);
		frame.add(panel);
		frame.setVisible(true);

		// threads
		clockThread.start();
		light1.t.start();
		light2.t.start();
		light3.t.start();
		car1.t.start();
		car2.t.start();
		car3.t.start();

		// control frame
		JFrame controlFrame = new JFrame("Controls");
		controlFrame.setSize(165, 445);

		// control panel
		JPanel controlPanel = new JPanel(new FlowLayout());
		controlPanel.setSize(new Dimension(165, 445));
		controlFrame.setResizable(false);

		// light buttons
		JButton lightButton1 = new JButton("Light 1");
		JButton lightButton2 = new JButton("Light 2");
		JButton lightButton3 = new JButton("Light 3");

		// car buttons
		JButton carButton1 = new JButton("  Car 1  ");
		JButton carButton2 = new JButton("  Car 2  ");
		JButton carButton3 = new JButton("  Car 3  ");

		// labels
		JLabel car1Speed = new JLabel("Car 1 Speed m/s: ");
		JLabel car2Speed = new JLabel("Car 2 Speed m/s: ");
		JLabel car3Speed = new JLabel("Car 3 Speed m/s: ");

		// SpeedFields
		SpeedField car1SpeedField = new SpeedField(car1, "" + car1.speed);
		SpeedField car2SpeedField = new SpeedField(car2, "" + car2.speed);
		SpeedField car3SpeedField = new SpeedField(car3, "" + car3.speed);

		// labels
		JLabel car1Pos = new JLabel("Car 1 Position: ");
		JLabel car2Pos = new JLabel("Car 2 Position: ");
		JLabel car3Pos = new JLabel("Car 3 Position: ");

		// position fields
		PositionField car1PosField = new PositionField(car1,
				"" + car1.position);
		PositionField car2PosField = new PositionField(car2,
				"" + car2.position);
		PositionField car3PosField = new PositionField(car3,
				"" + car3.position);

		// thread starts
		car1PosField.t.start();
		car2PosField.t.start();
		car3PosField.t.start();

		car1SpeedField.t.start();
		car2SpeedField.t.start();
		car3SpeedField.t.start();

		// frame and panel adds
		controlFrame.add(controlPanel);
		controlPanel.add(lightButton1);
		controlPanel.add(lightButton2);
		controlPanel.add(lightButton3);
		controlPanel.add(carButton1);
		controlPanel.add(carButton2);
		controlPanel.add(carButton3);
		controlPanel.add(car1Speed);
		controlPanel.add(car1SpeedField);
		controlPanel.add(car2Speed);
		controlPanel.add(car2SpeedField);
		controlPanel.add(car3Speed);
		controlPanel.add(car3SpeedField);
		controlPanel.add(car1Pos);
		controlPanel.add(car1PosField);
		controlPanel.add(car2Pos);
		controlPanel.add(car2PosField);
		controlPanel.add(car3Pos);
		controlPanel.add(car3PosField);

		// pause button
		JButton pauseButton = new JButton("Pause/Play");
		controlPanel.add(pauseButton);

		// reset button
		JButton resetButton = new JButton("Reset");
		controlPanel.add(resetButton);

		// set Visibles
		lightButton1.setVisible(true);
		lightButton2.setVisible(true);
		lightButton3.setVisible(true);
		carButton1.setVisible(true);
		carButton2.setVisible(true);
		carButton3.setVisible(true);
		car1Speed.setVisible(true);
		car1SpeedField.setVisible(true);
		car2Speed.setVisible(true);
		car2SpeedField.setVisible(true);
		car3Speed.setVisible(true);
		car3SpeedField.setVisible(true);
		pauseButton.setVisible(true);
		resetButton.setVisible(true);
		controlPanel.setVisible(true);
		controlFrame.setVisible(true);

		// runnable action listeners
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// light 1 listener
				lightButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (light1.running.get()) {
							light1.turnOff();
						} else {
							light1.turnOn();
						}

					}
				});

				// light 2 listener
				lightButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (light2.running.get()) {
							light2.turnOff();
						} else {
							light2.turnOn();
						}

					}
				});

				// light 3 listener
				lightButton3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (light3.running.get()) {
							light3.turnOff();
						} else {
							light3.turnOn();
						}

					}
				});

				// car button 1 listener
				carButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (car1.isVisible()) {
							car1.setVisible(false);
							car1.speed = 0;
						} else {
							car1.setVisible(true);
							car1.speed = 10;
						}

					}
				});

				// car button 2 listener
				carButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (car2.isVisible()) {
							car2.setVisible(false);
							car2.speed = 0;
						} else {
							car2.setVisible(true);
							car2.speed = 10;
						}

					}
				});

				// car button 3 listener
				carButton3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (car3.isVisible()) {
							car3.setVisible(false);
							car3.speed = 0;
						} else {
							car3.setVisible(true);
							car3.speed = 10;
						}

					}
				});

				// pause button listener
				pauseButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!paused) {
							car1.pause();
							car2.pause();
							car3.pause();
							light1.pause();
							light2.pause();
							light3.pause();
							paused = true;
						} else {
							car1.play();
							car2.play();
							car3.play();
							light1.play();
							light2.play();
							light3.play();
							paused = false;
						}
					}
				});

				// reset button listener
				resetButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						frame.dispose();
						controlFrame.dispose();
						Main.main(args);

					}
				});

				car1PosField.setText("" + car1.position);
				car2PosField.setText("" + car2.position);
				car3PosField.setText("" + car3.position);
				Thread.yield();
			}
		});

	}

}
