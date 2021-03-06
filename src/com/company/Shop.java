package com.company;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    static final int CARS_COUNT = 10;
    static final long TOYOTA_PRODUCTION_TIME = 2000;
    static final int SHOP_SERVICE_TIME = 500;
    static final int FIRST_CAR_TO_BUY = 0;

    List<Car> cars = new ArrayList<>(CARS_COUNT);

    public void buyCar() {

        try {
            System.out.println("\n" + Thread.currentThread().getName() + " зашел в автосалон купить Toyota");
            Thread.sleep(SHOP_SERVICE_TIME);
            synchronized (this) {
                while (cars.isEmpty()) {
                    wait();
                }
                System.out.println(Thread.currentThread().getName() + " уехал на новенькой Toyota ");
                cars.remove(FIRST_CAR_TO_BUY);
            }
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
    }

    public void productionCar() {

        try {
            for (int i = 0; i < CARS_COUNT; i++) {
                System.out.println(" Завод Toyota отгружает автомобиль в салон ");
                synchronized (this) {
                    cars.add(new Car());
                    System.out.println(" Завод Toyota поставил один автомобиль в автосалон");
                    notify();
                }
                Thread.sleep(TOYOTA_PRODUCTION_TIME);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}




