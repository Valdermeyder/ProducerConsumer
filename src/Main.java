/**
 * Created by JCW478 on 4/25/2017.
 */
public class Main {

    public static void main(String[] args) {
        StoreService storeService = new StoreServiceCas();
        Producer producer = new Producer(storeService);
        Consumer consumer = new Consumer(storeService);
        producer.start();
        consumer.start();
    }


}
