package com.company;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    int CARS_COUNT = 10;
    long BUYER_WAIT_TIME = 1000;
    long TOYOTA_PRODUCTION_TIME = 2000;

    List<Car> cars = new ArrayList<>(CARS_COUNT);

    public synchronized void buyCar() {

        try {
            System.out.println("\n" + Thread.currentThread().getName() + " зашел в автосалон купить Toyota");
            while (cars.isEmpty()) {
                wait();
                Thread.sleep(BUYER_WAIT_TIME);
            }
            System.out.println(Thread.currentThread().getName() + " уехал на новенькой Toyota ");
            cars.remove(0);
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
    }

    public synchronized void productionCar() {
        System.out.println(" Завод Toyota отгружает автомобиль в салон ");
        try {
            cars.add(new Car());
            System.out.println(" Завод Toyota поставил один автомобиль в автосалон");
            notify();
            Thread.sleep(TOYOTA_PRODUCTION_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



