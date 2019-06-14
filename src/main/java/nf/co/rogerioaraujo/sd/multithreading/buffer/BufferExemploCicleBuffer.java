package nf.co.rogerioaraujo.sd.multithreading.buffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferExemploCicleBuffer implements Buffer {

    private Lock lock = new ReentrantLock(false);
    private Condition canRead = lock.newCondition();
    private Condition canWrite = lock.newCondition();

    private Integer[] buffer = {-1, -1, -1};
    private Integer buffersOcupados = 0;
    private Integer lerIndice = 0;
    private Integer gravarIndice = 0;

    @Override
    public void set(int valor) {

        try {
            lock.lock();

            if (buffersOcupados.equals(buffer.length)) {
                System.out.println("Todos os buffers cheios.Produtor aguarda.\\n");
                canWrite.await();
            }

            buffer[gravarIndice] = valor;

            System.out.println("Productor write " + valor + " position " + gravarIndice);

            if (gravarIndice == (buffer.length - 1)) gravarIndice = -1;

            gravarIndice++;
            buffersOcupados++;

            canRead.signal();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    @Override
    public int get() {
        Integer valorLido = 0;

        lock.lock();

        try {

            if (buffersOcupados.equals(0)) {
                System.out.println("Todos os buffers estão vazios.\nConsumidor aguarda.\n”");
                canRead.await();
            }

            valorLido = buffer[lerIndice];

            System.out.println("Consumidor lê " + valorLido + " posição " + lerIndice);

            if (lerIndice == (buffer.length -1)) lerIndice = -1;

            lerIndice++;
            buffersOcupados--;

            canWrite.signal();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }

        return valorLido;
    }
}
