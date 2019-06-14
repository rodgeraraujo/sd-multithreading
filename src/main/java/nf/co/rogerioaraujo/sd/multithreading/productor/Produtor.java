package nf.co.rogerioaraujo.sd.multithreading.productor;

import nf.co.rogerioaraujo.sd.multithreading.buffer.*;

import java.util.Random;

public class Produtor implements Runnable {

    private final Buffer localizacaoCompartilhada;
    private final Random gerador = new Random();

    public Produtor(Buffer buffer) {
        this.localizacaoCompartilhada = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 10; i++ ) {
            try {
                Thread.sleep(gerador.nextInt(3000));
                localizacaoCompartilhada.set(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("\n%s\n%s\n", "Produtor produz!", "Fim do Produtor.");
    }
}
