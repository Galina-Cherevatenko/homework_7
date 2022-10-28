package homework_7.task_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество выкрикиваний:");
        int n = in.nextInt();
        System.out.println("Введите победителя: 1 - курица, 2 - яйцо");
        int winnerNum = in.nextInt();

        if (winnerNum==1) {
            Looser eggVoice = new Looser ("яйцо!", n);
            eggVoice.start();
            Winner chickenVoice = new Winner ("курица!", n, eggVoice);
            chickenVoice.start();
        }
        if (winnerNum==2) {
            Looser chickenVoice = new Looser ("курица!", n);
            chickenVoice.start();
            Winner eggVoice = new Winner ("яйцо!", n, chickenVoice);
            eggVoice.start();
        }
    }
}

class Looser extends Thread {
    private int n;
    private String name;

    Looser (String name, int n){
        this.name=name;
        this.n=n;
    }

    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {

            }

            System.out.println(name);
        }
    }
}
class Winner extends Thread {
    private int n;
    private String name;
    private Looser looserName;

    Winner (String name, int n, Looser looserName){
        this.name=name;
        this.n=n;
        this.looserName = looserName;
    }

    @Override
    public void run() {
        for (int i = 0; i < (n-1); i++) {
            try {
                Thread.sleep(300); //Приостанавливаем поток на секунду
            } catch (InterruptedException e) {
                // никак не реагируем на исключение
            }

            System.out.println(name);
        }
        if (looserName.isAlive()) { //Если оппонент еще не сказал последнее слово
            try {
                looserName.join(); //Подождать пока оппонент закончит высказываться.
            } catch (InterruptedException e) {
                // никак не реагируем на исключение
            }
            System.out.println(name);
        }
    }
}