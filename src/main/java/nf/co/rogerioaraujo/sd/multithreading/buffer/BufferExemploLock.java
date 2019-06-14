package nf.co.rogerioaraujo.sd.multithreading.buffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferExemploLock implements Buffer {

    private Lock lock = new ReentrantLock(false);
    private Condition podeGravar = lock.newCondition();
    private Condition podeLer = lock.newCondition();

    private Integer buffer = -1;
    private Boolean ocupado = true;

    @Override
    public void set(int value) {
        lock.lock();

        try {
            while (!ocupado) {
                System.out.println("O produtor tenta gravar mas o buffer está cheio");
                podeGravar.await();
            }

            buffer = value;
            System.out.println("Produtor grava: " + buffer);
            ocupado = false;
            podeLer.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    @Override
    public int get() {
        lock.lock();

        try {
            while (ocupado) {
                System.out.println("Consumidor tenta ler. Mas o buffer está vazio.");
                podeLer.await();
            }

            ocupado = true;
            System.out.println("Consumidor lê: " + buffer);
            podeGravar.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return buffer;
    }
}
