import java.util.Random;

public class C implements Runnable{
    private Thread t = null;
    public void run() {
        while (!Variables.SENDING_FINISHED) {
            Random random = new Random();
            if (random.nextInt() % 2 == 0) {
                Variables.SENDING_FINISHED = true;
                System.out.println("Trimitere spre publicare.");
                D R5 = new D();
                R5.start();
                E R6 = new E();
                R6.start();
                Variables.SENDING_FINISHED = false;
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
