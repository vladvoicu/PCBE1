import java.util.Random;

public class A implements Runnable {
    private Thread t = null;


    private int id;

    public A(int id) {
        this.id = id;
//        this.semafor = semafor;
    }

    public void run() {
        //notifyAll();
        synchronized (t){
            t.notifyAll();
        }
        System.out.println("Incepere scriere documentatie." + this.id);
        while (!Variables.DOCUMENTATION_FINISHED) {
            if (Variables.f1.isAvailable()) {
                Random random = new Random();
                if (random.nextInt() % 2 == 0) {
                    Variables.f1.get();
                    Variables.DOCUMENTATION_FINISHED = true;
                    System.out.println("Documentatie scrisa cu success." + this.id);
                    Variables.DOCUMENTATION_FINISHED = false;

                    B R2 = new B(this.id);
                    R2.start();
                    Variables.f1.put();
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }
}