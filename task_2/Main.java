package homework_7.task_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество выкрикиваний:");
        int n = in.nextInt();
        System.out.println("Введите победителя: 1 - курица, 2 - яйцо");
        int winnerNum = in.nextInt();
        Object victory =new Object();
        Scream eggVoice = new Scream ("яйцо!", n, victory);
        Scream chickenVoice = new Scream ("курица!", n, victory);
        if (winnerNum==1) {
            eggVoice.setPriority(Thread.MAX_PRIORITY);
            chickenVoice.setPriority(Thread.MIN_PRIORITY);
            new Thread(eggVoice).start();
            new Thread(chickenVoice).start();
        }
        if (winnerNum==2) {
            chickenVoice.setPriority(Thread.MAX_PRIORITY);
            eggVoice.setPriority(Thread.MIN_PRIORITY);
            new Thread(chickenVoice).start();
            new Thread(eggVoice).start();
        }
    }
}

class Scream extends Thread {
    private int n;
    private String name;
    private Object victory;
    Scream (String name, int n, Object victory){
        this.name=name;
        this.n=n;
        this.victory=victory;

    }

    @Override
    public void run(){
        try {
            synchronized (victory) {
                for (int i = 0; i < n; i++) {
                    System.out.println(name);
                    victory.notify();
                    victory.wait();
                }
                victory.notify();
            }
        } catch (InterruptedException e) {

        }
    }
}