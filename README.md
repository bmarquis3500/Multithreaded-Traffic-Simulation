# Multithreaded-Traffic-Simulation
This simulates traffic flow of cars through 3 stop lights. The user has control over cars and lights respectively. This project features a thread pool and a graphical display of the simulation.



Developer’s guide:
This collection of Java files displays a map to the user that includes 3 traffic lights and 3 cars on a roadway.
The roadway is 1500 pixels long. Each pixel corresponds to 2 meters in distance. The cars travel at 100 m/s meaning they take 10 seconds to travel from one light to the next. The traffic signals are programmed to switch from green to yellow to red. 
The cars in this program take up to 3 seconds to realize a light has turned from red to green. I almost fixed this to be instantaneous but I decided it was realistic and left it as is.
This program also includes a control panel for the user to see the speed and position of each vehicle at a given time. The control panel includes buttons to remove and add each car and light.
The program is run by running the Main.java file.
