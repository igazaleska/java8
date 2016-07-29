package pl.com.waitnotfy;

public class ProducerConsumer {

	private static int[] buffer;
	private static int count;
	private static Object lock = new Object();
	
	static class Producer {
		
		void produce() {
			synchronized (lock){
				while (isFull(buffer)){
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				buffer[count++] = 1;
				lock.notify();
			}
			
		}
	}
		
	static class Consumer{
		
		void consume(){
			synchronized (lock){
				while (isEmpty(buffer)){
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				buffer[--count] = 0;
				lock.notify();
			}
		}
	}

	static boolean isFull(int[] buffer) {
		// TODO Auto-generated method stub
		return count == buffer.length;
	}

	static boolean isEmpty(int[] buffer) {
		// TODO Auto-generated method stub
		return count == 0;
	}
	
	public static void main(String[] args) throws InterruptedException{
		
		buffer = new int[10];
		count = 0;
		
		Producer producer = new Producer();
		Consumer consumer = new Consumer();
		
		Runnable produceTask = () -> {
			for (int i = 0; i < 50; i++){
				producer.produce();
			}
			System.out.println("Done producing");
		};
		
		Runnable consumeTask = () -> {
			for (int i = 0; i < 50; i++){
				consumer.consume();
			}
			System.out.println("Done consuming");
		};
		
		Thread consumerThread = new Thread(consumeTask);
		Thread producerThread = new Thread(produceTask);
		
		consumerThread.start();
		producerThread.start();
		
		consumerThread.join();
		producerThread.join();
		
		System.out.println("Data in the buffer: "+count);
	}
	
}
