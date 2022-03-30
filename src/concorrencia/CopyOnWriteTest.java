package concorrencia;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class CopyOnWriteTest implements Runnable{
	
	private List<Integer> list = new CopyOnWriteArrayList<>();
	 // [1,2,3]
	 // [1,2]              // o array é imutável mas seu dados não
	 // [1]                // toda alteração no array implicará na criação de outro Array
	
	public CopyOnWriteTest() {
		for(int i = 0; i < 100; i++) {
			list.add(i);
		}	
	}
	@Override
	public void run() {
		Iterator<Integer> iterator = list.iterator();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
	}
	while(iterator.hasNext()) {
		System.out.println(Thread.currentThread().getName() + " " + iterator.next());
		}
	}
	public List<Integer> getList(){
		return list;
	}
	public static void main(String[] args) {
		CopyOnWriteTest ct = new CopyOnWriteTest();
		Thread t1 = new Thread(ct);
		Thread t2 = new Thread(ct);
		Thread removedor = new Thread(new RemoverMembros(ct.getList()));
		t1.start();
		t2.start();
		removedor.start();
	}
	private static class RemoverMembros implements Runnable {
		private List<Integer> list;
		
		public RemoverMembros(List<Integer> list) {
			this.list = list;
		}
		@Override
		public void run() {
			for(int i = 0; i < 10 ; i++) {
				System.out.println(Thread.currentThread().getName() + " " + "remover" + list.remove(i));			}
		}
	}
}
