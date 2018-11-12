import java.util.Random;

class B implements Runnable {
    private Thread t = null;
    private int id;

    public B(int id) {
        this.id = id;
    }

//    @Override
//    public void run() {
//        System.out.println("Discuta echipa" + this.id);
//        DiscutaEchipa();
//    }

    public void run() {
        System.out.println("Discuta echipa" + this.id);
        while (!Variables.DEBATE_FINISHED) {
            if (Variables.f2.isAvailable()) {
                Random random = new Random();
                int nr = random.nextInt()%10;
                System.out.println(nr + " " + this.id);
                if (nr % 2 == 0) {
                    Variables.f2.get();
                    Variables.DEBATE_FINISHED = true;
                    System.out.println("Discutie cu success." + this.id);
                    Variables.DEBATE_FINISHED = false;

                    C R3 = new C(this.id);
                    R3.start();
                    Variables.f2.put();
                    try {

                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {

                    Variables.f2.get();
                    Variables.DEBATE_FINISHED = true;
                    System.out.println("Documentatia nu e ok pentru " + this.id);
                    Variables.DEBATE_FINISHED = false;
                    A R4 = new A(this.id);
                    R4.start();
                    Variables.f2.put();
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

//    private boolean isEnabled(){
//        String newFlag = null;
//        Map<String, String> env = System.getenv();
//        for (String envName : env.keySet()) {
//            if(envName.equals("flag")){
//                newFlag = env.get(envName);
//            }
//        }
//        if(newFlag.equals("true")){
//            return true;
//        }
//        return false;
//    }
}
