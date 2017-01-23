package org.nenapu.java.lock;

/**
 * Test class to test the Fair lock implementation
 * 
 * @author Kirana NS
 */
public class TestFairLock {

	private static final int NUMBER_OF_THREADS = 10;
	private static final long JOB_PROCESSING_TIME_MILLISEC = 500L;
	
	private class MyJob implements Runnable{

		private Lock lock = null;
		
		public MyJob(Lock lock){
			this.lock = lock;
		}
		@Override
		public void run() {
			try {
				lock.lock();
				Thread.sleep(JOB_PROCESSING_TIME_MILLISEC);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				lock.unlock();
			}
		}
	}
	
	private class MyRentrantJob implements Runnable{

		private Lock lock = null;
		
		public MyRentrantJob(Lock lock){
			this.lock = lock;
		}
		@Override
		public void run() {
			try {
				lock.lock();
				Thread.sleep(JOB_PROCESSING_TIME_MILLISEC);
				lock.lock();
				Thread.sleep(JOB_PROCESSING_TIME_MILLISEC);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				lock.unlock();
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		Lock lock = new SimpleLock();
		
		for (int i = 1; i <= NUMBER_OF_THREADS; i++) {
			Thread thread = new Thread(new TestFairLock().new MyJob(lock), "SimpleLock-" + i);
			thread.start();
		}
		
		System.out.println("");
		
		lock = new FairLock();
		
		for (int i = 1; i <= NUMBER_OF_THREADS; i++) {
			Thread thread = new Thread(new TestFairLock().new MyJob(lock), "FairLock-" + i);
			thread.start();
		}
		
		System.out.println("");
		
		lock = new SimpleReentrantLock();
		
		for (int i = 1; i <= NUMBER_OF_THREADS; i++) {
			Thread thread = new Thread(new TestFairLock().new MyJob(lock), "SimpleReentrantLock-" + i);
			thread.start();
		}
		
		System.out.println("");
		
		lock = new FairReentrantLock();
		
		for (int i = 1; i <= NUMBER_OF_THREADS; i++) {
			Thread thread = new Thread(new TestFairLock().new MyJob(lock), "FairReentrantLock-" + i);
			thread.start();
		}
	}
}
