import java.util.Random;

public class D  implements Runnable{
    private Thread t = null;


    private int id;
    public D(int id){
        this.id = id;
    }

    public void run() {
        System.out.println("Incepe publicare internet." + this.id);
        while (!Variables.PUBLISHING_FINISHED) {
            if (Variables.f4.isAvailable()) {
                Random random = new Random();
                if (random.nextInt() % 2 == 0) {
                    Variables.f4.get();
                    Variables.PUBLISHING_FINISHED = true;
                    System.out.println("Documentatie publicata cu success." + this.id);
                    Variables.PUBLISHING_FINISHED = false;
                    Variables.f4.put();
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
