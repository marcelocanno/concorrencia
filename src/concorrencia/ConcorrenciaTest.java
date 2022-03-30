package concorrencia;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Contador{
	private int count;
	private AtomicInteger atomicInteger = new AtomicInteger();
	private Lock lock = new ReentrantLock();  // antes de add lock, reentrantLock o resultado já era o esperado
	public void ingremente() {                // incrementado para aprendizado
	//public synchronized void ingremente() {  // uma maneira de resolver o erro
	  lock.lock();
		try {
			count++;                         // implementação ReentrantLock
			 atomicInteger.getAndIncrement();
		}finally{
			lock.unlock();
		}
		//count++;
	 //atomicInteger.getAndIncrement();
	}
	public int getCount() {
		return count;
	}
	public int getAtomicInteger() {
		return atomicInteger.intValue();
	}
}

class IngremetadorThread extends Thread{
	private Contador contador;

	public IngremetadorThread(Contador contador) {
		super();
		this.contador = contador;
	}
	public void run() {
		for(int i = 0; i < 10000; i++) {
			contador.ingremente();
		}
	}
}

public class ConcorrenciaTest {
	public static void main(String[] args) throws InterruptedException{
		Contador c = new Contador();
		IngremetadorThread it1 = new IngremetadorThread(c);
		IngremetadorThread it2 = new IngremetadorThread(c);
		it1.start();
		it2.start();
		it1.join();
		it2.join();
		System.out.println(c.getCount());
		System.out.println(c.getAtomicInteger());
	}
	
	
	

}
