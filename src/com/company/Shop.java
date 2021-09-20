package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Shop {

    int quantityOfCars = 10;
    long BUYER_WAIT_TIME = 1000;
    long TOYOTA_PRODUCTION_TIME = 2000;

    Lock seller = new ReentrantLock(true);
    List<Car> cars = new ArrayList<>(quantityOfCars);
    Condition carCondition = seller.newCondition();

    public void buyCar() {
            seller.lock();
            try {
                System.out.println("\n" + Thread.currentThread().getName() + " зашел в автосалон купить Toyota");
                while (cars.isEmpty())
                    try {
                        System.out.println(" Автосалон просит подождать поставку машин");
                        Thread.sleep(BUYER_WAIT_TIME);
                        carCondition.await();
                        System.out.println(" Говорят в автосалоне появились новенькие машины");
                    } catch (InterruptedException err) {
                    }
                System.out.println(Thread.currentThread().getName() + " уехал на новенькой Toyota ");
            } finally {
                seller.unlock();
            }
        }


    public void productionCar() {
        seller.lock();
        System.out.println(" Завод Toyota отгружает автомобиль в салон ");
            try {
                Thread.sleep(TOYOTA_PRODUCTION_TIME);
                cars.add(new Car());
                System.out.println(" Завод Toyota поставил один автомобиль в автосалон");
                carCondition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                seller.unlock();
            }
        }
    }



