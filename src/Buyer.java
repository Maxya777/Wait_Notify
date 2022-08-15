public class Buyer extends Thread {

    private final Shop shop;

    public Buyer(Shop shop) {
        this.shop = shop;
    }

    public void run() {
            buyCar();
    }

    public void buyCar() {
        synchronized (shop) {
            try {
                System.out.println(Thread.currentThread().getName() + " заходит в салон");
                if (shop.getCar() <= 0) {
                    System.out.println("Машин нет");
                    shop.wait();
                }
                if (shop.getCar() > 0) {
                    shop.sellCar();
                    System.out.println(Thread.currentThread().getName() + " - уехал на новой ладе в закат...");
                }
                System.out.println("Maшин в салоне " + shop.getCar());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
