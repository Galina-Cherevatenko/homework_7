package homework_7.task_1;
public class Main {

    public static void main(String[] args) {

        Object tennis=new Object();
        Ping ping = new Ping(tennis);
        Pong pong = new Pong(tennis);
        ping.setPriority(Thread.MAX_PRIORITY);
        pong.setPriority(Thread.MIN_PRIORITY);
        new Thread(ping).start();
        new Thread(pong).start();
    }
}


class Ping extends Thread {

    private Object tennis;
    Ping(Object tennis){
        this.tennis=tennis;
    }
    @Override
    public void run(){
        try {
            synchronized (tennis) {
                for (int i = 0; i < 10; i++) {
                    System.out.print("Пинг-");
                    tennis.notify();
                    tennis.wait();
                }
                tennis.notify();
            }
        } catch (InterruptedException e) {

                }
            }
        }

class Pong extends Thread {

    private Object tennis;
    Pong(Object tennis){
        this.tennis=tennis;
    }
    @Override
    public void run(){
        try {
            synchronized (tennis) {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Понг");
                    Thread.sleep(1000);
                    tennis.notify();
                    tennis.wait();
                }
                tennis.notify();
            }
        } catch (InterruptedException e) {

        }
    }
}



