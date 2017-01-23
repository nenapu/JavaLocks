package org.nenapu.java.lock;

/**
 * MonitorObject is used by threads to obtain a fair lock
 * 
 * @author Kirana NS
 */
public class MonitorObject {

	private boolean notified = false;

	public synchronized void dowait(){
		while(!notified){
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
	}

	public synchronized void donotify(){
		notified = true;
		notify();
	}
}
