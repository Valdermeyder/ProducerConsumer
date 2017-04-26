/**
 * Created by JCW478 on 4/25/2017.
 */
public class Consumer extends Thread {
    private StoreService storeService;

    public Consumer(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Received value: " + storeService.getFromStore());
                sleep(500);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
