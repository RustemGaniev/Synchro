package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        long SHOP_SERVICE_TIME = 1000;
        Shop shop = new Shop();
        String[] threadName = {"Покупатель 1", "Покупатель 2", "Покупатель 3", "Покупатель 4", "Покупатель 5",
                "Покупатель 6", "Покупатель 7", "Покупатель 8", "Покупатель 9", "Покупатель 10"};
        for (String tN:threadName) {
            new Thread(shop::buyCar, tN).start();
            Thread.sleep(SHOP_SERVICE_TIME);
            new Thread(shop::productionCar, " Автосалон ").start();
        }

    }
}
