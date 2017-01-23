# JavaLocks

<h2>Introduction</h2>
<p>
This repository provides different lock implementation using the java wait and notify framework.
You can use these implementations for learning purpose and in your application as well.
Locks are used when you want to execute certain part of the code sequentially and need to make sure
intended part is run by single thread at any given point in time.
</p>
<p>
There are 4 different types of implementations are provided here,
<ul>
<li><b><i>Simple lock:</i></b> Simple lock implementation uses the wait and notify mechanism to gaurd your code
and to make sure only one thread executes it at any given point in time. This implementation does not guarantee
fairness. The order of the threads obtaining the lock is not guaranteed. 
</li>
<li><b><i>Simple Reentrant lock:</i></b> Its an extended version of simple lock. The implementation make sure that 
a thread will get a lock any number of times it asks if it holds the lock on the particular object. Hence the name
reentrant. This implementation also does not guarantee fairness. The order of the threads obtaining the lock is
not guaranteed. 
</li>
<li><b><i>Fair lock:</i></b> Fair lock implementation makes sure that locks are given to the thread based on first
come first serve (FCFS) basis. The first thread to request the lock is the first one to get. Hence it avoids the 
"Thread Starvation" to great extent. The performance of the lock might be little slower but your application logic
demands the same then you can use this implementation.
</li>
<li><b><i>Fair Reentrant lock:</i></b> Fair reentrant lock is the extended version of Fair lock and supports the
multiple request from the thread which holds the lock currently. The advantage of reentrant lock is you can use the
same lock in different parts of the code without worrying a dead lock.
</li>
</ul>

<h2>Usage</h2>
Usage of these locks implementations are very simple. They are all bound by <i>Lock</i> interface. Which provides 
two simple methods.
<ul>
<li>lock(): Obtain the lock</li>
<li>unlock(): Release the lock</li>
</ul>
One thing you must make sure is you will release the lock at any condition (both in case of success and failure/exception).
Hence its better to unlock in finally block.
<br/>
Creating these locks are simple, and as shown below,
```java
Lock lock = new SimpleLock();
Lock lock = new SimpleReentrantLock();
Lock lock = new FairLock();
Lock lock = new FairReentrantLock();
```
<br/>
Use the lock and unlock methods whenever needed. For example,
```java
Lock lock = new FairLock();
try{
    lock.lock();
    //TODO: application logic which suppose to be gaurded by a lock.
}catch(Exception e){
  //TODO: Exception handling;
}finally{
  lock.unlock();
}
