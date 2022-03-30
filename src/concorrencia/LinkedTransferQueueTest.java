package concorrencia;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class LinkedTransferQueueTest {

	public static void main(String[] args) throws InterruptedException {
		
		TransferQueue<String> tq = new LinkedTransferQueue<>();
		System.out.println(tq.add("Marcelo"));
		tq.put("Robot Alive");
		System.out.println(tq.offer("Marcelo"));
		System.out.println(tq.offer("Marcelo",1,TimeUnit.NANOSECONDS));
		//tq.transfer("Robot Alive");
		System.out.println(tq.tryTransfer("Robot Alive"));
		System.out.println(tq.tryTransfer("Robot Alive",1,TimeUnit.SECONDS));
		
		System.out.println(tq.remainingCapacity());
		
		System.out.println(tq.element());
		System.out.println(tq.peek());
		System.out.println(tq.poll());
		System.out.println(tq.poll(10, TimeUnit.MILLISECONDS));
		System.out.println(tq.remove());
		System.out.println(tq.take());
		}
}
