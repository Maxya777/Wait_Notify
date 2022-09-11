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
            e.printStackTrace();
        }
    }

    public void deliverCar() {
        synchronized (shop) {
            try {
                System.out.println(Thread.currentThread().getName() + ": машина в пути");
                Thread.sleep(DELIVER_TIME);
                shop.getCars().add(new Car());
                System.out.println(Thread.currentThread().getName() + ": новая лада уже в салоне");
                System.out.println("Машин в салоне " + shop.getCars().size());
                Thread.sleep(DELIVER_TIME);
                shop.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


