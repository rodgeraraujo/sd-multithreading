package nf.co.rogerioaraujo.sd.multithreading;

import nf.co.rogerioaraujo.sd.multithreading.buffer.Buffer;
import nf.co.rogerioaraujo.sd.multithreading.buffer.BufferExemploCicleBuffer;
import nf.co.rogerioaraujo.sd.multithreading.consumer.Consumidor;
import nf.co.rogerioaraujo.sd.multithreading.productor.Produtor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Buffer buffer = new BufferExemploCicleBuffer();

        try {
            executorService.execute(new Produtor(buffer));
            executorService.execute(new Consumidor(buffer));
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
