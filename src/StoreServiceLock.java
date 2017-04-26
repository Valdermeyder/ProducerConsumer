import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by JCW478 on 4/25/2017.
 */
public class StoreServiceLock implements StoreService {
    private final Lock lock = new ReentrantLock();
    private final Condition isEmptyCondition = lock.newCondition();
    private final Condition isFullCondition = lock.newCondition();
    private Queue<Integer> store = new LinkedList<>();

    @Override
    public void addToStore() throws InterruptedException {
        addToStore(0);
    }

    public boolean addToStore(Integer newValue) throws InterruptedException {
        lock.lock();
        try {
            while (store.size() > 10) {
                System.out.println("Waiting for more space in the store; current size: " + store.size());
                isFullCondition.await();
            }
            boolean isAdded = store.add(newValue);
            isEmptyCondition.signal();
            return isAdded;
        } finally {
            lock.unlock();
        }

    }

    public Integer getFromStore() throws InterruptedException {
        lock.lock();
        try {
            while (store.isEmpty()) {
                isEmptyCondition.await();
            }
            Integer polledValue = store.poll();
            isFullCondition.signal();
            return polledValue;
        } finally {
            lock.unlock();
        }
    }


}
