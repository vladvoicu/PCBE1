import java.util.Random;

class B implements Runnable {
    private Thread t = null;

    public void run() {
        System.out.println("Discuta echipa");
        DiscutaEchipa();
    }

    public synchronized void DiscutaEchipa() {
        while (!Variables.DEBATE_FINISHED) {
            Random random = new Random();
            if (random.nextInt() % 2 == 0) {
                Variables.DOCUMENTATION_APPROVED = true;
                System.out.println("Discutie cu success.");
                C R3 = new C();
                R3.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                Variables.DOCUMENTATION_APPROVED = false;

                A R4 = new A();
                R4.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
