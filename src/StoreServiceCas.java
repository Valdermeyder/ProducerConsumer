import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by JCW478 on 4/25/2017.
 */
public class StoreServiceCas implements StoreService {

    private AtomicInteger store = new AtomicInteger(0);


    @Override
    public void addToStore() throws InterruptedException {
        int val = store.get();
        while (val < 10) {
            if (store.compareAndSet(val, val + 1)) {
                System.out.println("adding: " + val + 1);
                break;
            } else {
                val = store.get();
            }
        }
    }

    public boolean addToStore(Integer newValue) throws InterruptedException {
        addToStore();
        return true;
    }

    public Integer getFromStore() throws InterruptedException {
        int val = store.get();
        while (val > 0) {
            if (store.compareAndSet(val, val - 1)) {
                System.out.println("adding: " + (val - 1));
            } else {
                val = store.get();
            }
        }
        return val;
    }
}
