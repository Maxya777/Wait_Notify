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
                Thread.sleep(1000);
                while (shop.getCars().size() == 0) {
                    System.out.println("Машин нет");
                    shop.wait();
                }
                shop.getCars().remove(0);
                System.out.println(Thread.currentThread().getName() + " - уехал на новой ладе в закат...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
