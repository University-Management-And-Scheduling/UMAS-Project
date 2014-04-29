/****************@author Simant Purohit*********************************/

public class WaitListScan implements Runnable{

	@Override
	public void run() {
		System.out.println("Starting a new scan");
		WaitList.scanWaitList();
		System.out.println("Scanning cycle complete");
		try {
			Thread.sleep(10000);
			Thread.yield();
		} catch (InterruptedException e) {
			System.out.println("Scanning Interrupted");
			e.printStackTrace();
		}
	}
	
}
