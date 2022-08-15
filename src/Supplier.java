public class Supplier extends Thread {

    private final Shop shop;
    private static final int DELIVER_TIME = 1000;

    public Supplier(Shop shop) {
        this.shop = shop;
    }

    public void run() {
        try {
            do {
                deliverCar();
                Thread.sleep(DELIVER_TIME);
            } while (!isInterrupted());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void deliverCar() {
        synchronized (shop) {
            try {
                System.out.println(Thread.currentThread().getName() + ": машина в пути");
                shop.makeCar();
                Thread.sleep(DELIVER_TIME);
                System.out.println(Thread.currentThread().getName() + ": новая лада уже в салоне");
                System.out.println("Машин в салоне " + shop.getCar());
                Thread.sleep(DELIVER_TIME);
                shop.notify();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}


