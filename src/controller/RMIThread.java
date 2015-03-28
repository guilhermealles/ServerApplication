package controller;

public class RMIThread extends Thread {

	public RMIThread() { }
	
	public void run() {
		new RequestToDatabaseControllerStarter();
		System.out.println("Server> RMI thread running...");
	}
}
