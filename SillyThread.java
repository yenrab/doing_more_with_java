public class SillyThread extends Thread{
	public void run(){
		for (int i = 0; i < 3; i++) {
			System.out.println("Thread id: "
                               +Thread.currentThread().getName());
			try {
				Thread.currentThread().sleep(100);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args){
        	for (int threadCnt = 0; threadCnt < 3; threadCnt++) {
           		 SillyThreadExample aSillyThread =  new SillyThreadExample();
           		 aSillyThread.start();
       		 }
	}
}
