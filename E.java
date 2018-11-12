import java.util.Random;

public class E implements Runnable{

    private Thread t = null;

    private int id;
    public E(int id){
        this.id = id;
    }

    public void run() {
        System.out.println("Incepere tiparire documentatie." + this.id);
        while (!Variables.PRINTING_FINISHED){
            if (Variables.f5.isAvailable()) {
                Random random = new Random();
                if (random.nextInt() % 2 == 0) {
                    Variables.f5.get();
                    Variables.PRINTING_FINISHED = true;
                    System.out.println("Documentatie tiparita cu success." + this.id);
                    Variables.PRINTING_FINISHED = false;
                    Variables.f5.put();
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
