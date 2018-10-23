import java.util.Random;

public class A implements Runnable {
    private Thread t = null;

    public void run() {
        System.out.println("Incepere scriere documentatie.");
        while (!Variables.DOCUMENTATION_FINISHED){
            Random random = new Random();
            if(random.nextInt() % 2 == 0){
                Variables.DOCUMENTATION_FINISHED = true;
                System.out.println("Documentatie scrisa cu success.");
                B R2 = new B();
                R2.start();
                Variables.DOCUMENTATION_FINISHED = false;
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