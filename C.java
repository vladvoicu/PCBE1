import java.util.Random;

public class C implements Runnable{
    private Thread t = null;

    private int id;
    public C(int id){
        this.id = id;
    }

    public void run() {
        synchronized (t){
            t.notifyAll();
        }
        //notifyAll();
        while (!Variables.SENDING_FINISHED) {
            if (Variables.f3.isAvailable()) {
                Random random = new Random();
                if (random.nextInt() % 2 == 0) {
                    Variables.f3.get();
                    Variables.SENDING_FINISHED = true;
                    System.out.println("Trimitere spre publicare." + this.id);
                    Variables.SENDING_FINISHED = false;

                    D R5 = new D(this.id);
                    R5.start();
                    E R6 = new E(this.id);
                    R6.start();
                    Variables.f3.put();
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void start () {
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }
}
