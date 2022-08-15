import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Shop shop = new Shop();
        List<Thread> list = new ArrayList<>();

        for (int i = 1; i < 5; i++) {
            Thread buyer = new Thread(null, new Buyer(shop), "Покупатель " + i);
            list.add(buyer);
            buyer.start();
        }

        Thread supplier = new Thread(null, new Supplier(shop), "Поставщик ");
        supplier.start();

        for(Thread thread : list) {
            thread.join();
        }
        supplier.interrupt();
    }
}
