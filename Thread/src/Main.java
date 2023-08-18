public class Main {
    static long startTime = 0;
    public static void main(String[] args) {
        ThreadEx1 thread = new ThreadEx1();
        ThreadEx2 runnable = new ThreadEx2();

        Thread thread_runnable = new Thread(runnable);

        DaemonThread daemonthread = new DaemonThread();

        Thread daemon_thread = new Thread(daemonthread);

        daemon_thread.setDaemon(true);
        daemon_thread.start();

        // 멀티 스레드 실행 순서는 평소 디버그 방식처럼 위에서 부터 실행하는 것이 아닌, OS 스케쥴러에 의해
        // 뒤에서 부터 실행할 수도 있고 항상 일정하지가 않다.
        thread.start(); // start() 메서드는 스레드를 시작하는 명령어이다.
        thread_runnable.start();

        // 멀티 스레드를 사용하게 되면 OS 스케쥴러에 의해서 스레드를 번갈아가면서 작업을 수행하게 되는데,
        // 스레드를 변경할때마다 시간이 소요되서 단일 스레드에서 동시작업하는거 보다 총 소요시간이 더 걸리는 경우가 있다.
        // 스레드를 변경하는 것에 대해서 context switching이라고 명칭하며,
        // 단일 스레드를 사용하지 않고 멀티 스레드를 사용하는 것이 시간적인 면에서는 불리하지만, 사용하는 이유는
        // 동시에 여러 작업을 수행할 수 있다는 장점이 있다는 것이다.
        // 채팅 어플 예를 들면, 단일 스레드로 구성할 경우, 사진을 보내는 작업을 진행하는 동안에 내가 채팅을 칠 수 없다는 불편함이 있지만,
        // 멀티 스레드의 경우 이를 해결할 수 있다.

        startTime = System.currentTimeMillis();
        try {
            thread.join(); // join()메서드를 사용하면 main 메서드에서 동작하는 main 스레드가 join() 메서드를 사용한 스레드의 동작이 끝날때까지 기다린다.
            // join()메서드를 사용하지 않으면 main 메서드는 해당 스레드가 실행중임과 상관없이 main 스레드는 종료되고, 해당 스레드는 계속 동작하다 종료된다.
            thread_runnable.join();
        } catch(InterruptedException e) {}

        thread.interrupt(); // Thread 클래스에는 기본적으로 intterupted = false로 설정되어있다.

        System.out.println("isInterrupted()" + thread.isInterrupted());

        // isInterrupted()는 클래스 명을 사용해도 된다. interrupted는 static 메서드, 자기 자신을 호출하기 때문에
        // interrupted는 클래스 명이 아닌 Thread.interrupted로 사용해야 한다. 그래야 자기 자신 스레드의 interrupted를 반환.
        // isInterrupted는 한번 intterupt를 수행하고 나면 true로 바뀌는데,
        // interrupted 메서드는 한번 interrupt를 수행하고 나면 true로 바뀌고 다시 false로 초기화된다.

        System.out.print("소요시간" + (System.currentTimeMillis() - startTime));

        // void setPriority(int newPirority) 스레드의 우선순위를 지정한 값으로 변경시켜 준다. 변경 값은 1 ~ 10이 있다.
        // int getPriority() 스레드의 우선순위를 반환시켜준다.

        // 스레드 메서드
        // static void sleep(long mills) 지정된 시간동안 스레드를 일시정지시킴.
        // void join() 다른 스레드가 끝날때까지 기다린다.
        // void interrupt() --> sleep()이나 join()에 의해 일시정지상태인 스레드를 깨워서 실행대기상태(Runnable)로 만든다.
        // void suspend() --> 스레드를 일시정지시키며, resume()메서드를 호출해 실행대기상태를 만들수 있다.
        // void stop() 스레드를 즉시 종료시킨다.
        // void resume() --> suspend()에 의해 일시정지된 스레드를 다시 실행대기상태로 만든다.
        // static void yield() 스레드 자신에게 주어진 실행시간에 다른 스레드가 동작하도록 양보한다.
        // static이 붙은 메서드는 자기 자신을 호출할 때 사용한다.

    }
}

// 이렇게 스레드를 나누어서 짠 경우는 멀티 스레드를 활용한 것인데, 멀티 스레드가 아닌 단일 스레드를 사용할 경우,
// Main 클래스에 스레드에 배분했던 명령들을 한번에 실행하게 되면 단일 스레드로 사용할 수 있는데,
// 컴파일을 하게 되면 순차적으로 진행이 되지만,
// 멀티 스레드를 사용하게 되면, 순차적으로 진행 되는것이 아닌 같이 진행을 하다 보니까
// 로그창에 결과값이 섞여 나오는 것을 확인할 수 있다.
class ThreadEx1 extends Thread {
    // 메서드 이름이 run()이 아닌 다른거면 실행이 안된다.
    // 스레드 클래스에서는 run()으로 명을 설정했는데,
    // Main 클래스에서 스레드 객체.start()를 사용하는 이유는,
    // 스레드 start() 메서드는 추가로 호출을 하는데, run()메서드를 호출하고,
    // start() 메서드는 종료되는 방식으로 이루어져 있다.
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(this.getName()); // Thread를 상속받았으므로, 현재 스레드의 이름을 반환한다.
            // System.out.println(getName());으로 작성해도 된다. Thread를 상속받은 것 이므로.
        }
    }
}

class ThreadEx2 implements Runnable {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()); // Thread를 상속받은것이 아닌, Runnable 인터페이스를 상속받은 것 이므로,
            // Runnable 조상이 인터페이스이기 때문에 Thread에 있는 기능을 써야한다.
        }
    }
}

class DaemonThread implements Runnable {
    public void  run() {
        // DemonThread, 보조 역할을 하는 데몬 스레드는 반복문과 함께 사용을 한다.
        while(true) {
            try {
                Thread.sleep(3 * 1000);
            } catch(InterruptedException e) {}
//            if(autoSave) autoSave();
        }
    }
}