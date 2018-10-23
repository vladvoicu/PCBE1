import java.util.Random;

public class E implements Runnable{

    private Thread t = null;

    public void run() {
        System.out.println("Incepere tiparire documentatie.");
        while (!Variables.PRINTING_FINISHED){
            Random random = new Random();
            if(random.nextInt() % 2 == 0){
                Variables.PRINTING_FINISHED = true;
                System.out.println("Documentatie tiparita cu success.");
                Variables.PRINTING_FINISHED = false;
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
