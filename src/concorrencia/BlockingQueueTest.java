package concorrencia;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueTest {
	   // 
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> bq = new ArrayBlockingQueue<>(1);
		Thread t1 = new Thread(new RemoveFromQueue(bq));
		bq.put("Marcelo");
		System.out.println(bq.peek());
		System.out.println("Tentando colocar outro valor");
		
		t1.start();
		
		bq.put("Robot Alive Inc");
		System.out.println("Inserindo o ultimo valor");
	
	}
	
	static class RemoverFromQueue implements Runnable{
		private BlockingQueue<String> bq;
		
		public RemoverFromQueue(BlockingQueue<String> bq) {
			this.bq = bq;
		}
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " entrada em espera 2s");
			try {
				TimeUnit.SECONDS.sleep(2);
				System.out.println("removendo o valor: " + bq.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
