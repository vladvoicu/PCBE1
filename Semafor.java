public class Semafor {

    private boolean available;

    public Semafor(boolean available){
        this.available = available;
    }

    public synchronized void put(){
        this.available = true;
    }

    public synchronized void get(){
        this.available = false;
    }

    public boolean isAvailable() {
        return available;
    }

}
