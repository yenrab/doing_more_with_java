

public class SimpleRunnableStarter{
	
    public static void main(String[] args){
		for (int threadCnt = 0; threadCnt < 3; threadCnt++) {
			SimpleRunnable aSimpleRunnable = 
            new SimpleRunnable();
			Thread aThread = new Thread(aSimpleRunnable);
			aThread.start();
		}
	}
}