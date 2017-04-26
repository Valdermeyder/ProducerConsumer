/**
 * Created by JCW478 on 4/26/2017.
 */
public interface StoreService {
    void addToStore() throws InterruptedException;

    boolean addToStore(Integer newValue) throws InterruptedException;

    Integer getFromStore() throws InterruptedException;
}
