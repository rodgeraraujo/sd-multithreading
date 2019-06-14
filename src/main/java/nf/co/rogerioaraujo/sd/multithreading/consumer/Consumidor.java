package nf.co.rogerioaraujo.sd.multithreading.consumer;

import nf.co.rogerioaraujo.sd.multithreading.buffer.Buffer;

import java.util.Random;

public class Consumidor implements Runnable {

    private final Buffer localizacaoCompartilhada;
    private final Random gerador = new Random();

    public Consumidor(Buffer buffer) {
        this.localizacaoCompartilhada = buffer;
    }

    @Override
    public void run() {
        int soma = 0;
        for (int i = 0; i <= 10; i++ ) {
            try {
                Thread.sleep(gerador.nextInt(3000));
                soma += localizacaoCompartilhada.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("\n%s%d\n ", "Fim do Consumidor. Valor da soma: ", soma );
    }
}
