/**
 * Created by JCW478 on 4/25/2017.
 */
public class Producer extends Thread {
    int counter = 0;
    private StoreService storeService;

    public Producer(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public void run() {
        while (true) {
            try {
                storeService.addToStore();
                sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
