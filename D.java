import java.util.Random;

public class D  implements Runnable{
    private Thread t = null;

    public void run() {
        System.out.println("Incepe publicare internet.");
        while (!Variables.PUBLISHING_FINISHED){
            Random random = new Random();
            if(random.nextInt() % 2 == 0){
                Variables.PUBLISHING_FINISHED = true;
                System.out.println("Documentatie publicata cu success.");
                Variables.PUBLISHING_FINISHED = false;
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
