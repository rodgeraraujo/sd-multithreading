package nf.co.rogerioaraujo.sd.multithreading.buffer;

public class BufferExemploSimples implements Buffer {

    private Integer buffer = -1;


    @Override
    public void set(int valor) {
        System.out.printf("Produtor grava: \t%2d\n", valor);
        this.buffer = valor;
    }

    @Override
    public int get() {
        System.out.printf("Consumidor lê: \t%2d\n", buffer);
        return buffer;
    }

}
