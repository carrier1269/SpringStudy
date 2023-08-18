public class Main {
    public static void main(String[] args) {

        Runnable r = new RunnableEx22();
        new Thread(r).start();
        new Thread(r).start();
    }
}

class Account2 {
    private int balance = 1000;

    public int getBalance(){
        return balance;
    }

    // 해당 스레드가 동작이 완료되기 전에 다른 스레드를 동작하는 경우,
    // 다른 스레드에 의해서 해당 스레드 결과가 영향을 받고, if(balance >= money)를 전 스레드가 통과한 상태에서 멈춘경우
    // 다음 스레드가 if를 넘어간 상태에서 balance >= money를 성립하지 않는데, 다음 명령을 수행하는 경우가 발생하게 된다.
    // 그래서 synchronized를 사용하여 다른 스레드가 접근하지 못하도록 설정하여 임계범위를 설정한다.
    public synchronized void withdraw(int money){
        System.out.printf("balance = %d , money = %d%n", balance, money);
//        if (balance >= money) {
//            try {Thread.sleep(1000);}
//            catch(InterruptedException e){}
//
//            balance -= money;
//        }
        while (balance < money) {
            try{
                wait(); // 가진 돈보다 출금하려는 돈이 큰 경우 , 동기화 락을 풀어준다.
                // 다른 메서드에서 notify()를 사용하면 락이 풀린다.
            } catch(InterruptedException e) {}

        }

        // wait(), interrupt()를 사용해서 잠시 대기를 시켰다가 값을 충족하면 동작시키게 하는 것도 있다.
        balance -= money;
    }

    public synchronized void deposit(int money){
        balance += money;
        notify();
    }
}

class RunnableEx22 implements Runnable {
    Account2 acc = new Account2();

    public void run() {
        while(acc.getBalance() > 0) {
            int money = (int)(Math.random() * 3 + 1) * 100;
            acc.withdraw(money);
            System.out.println("balance:" + acc.getBalance());
        }
    }
}