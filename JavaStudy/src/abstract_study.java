import java.lang.*;

public class abstract_study {
    // 이것은 에러를 반환 한다 왜냐?
    // 추상 클래스의 인스턴스는 생성을 할 수 없다.
//    Player p = new Player();

    // 추상 클래스의 자손 클래스를 생성하여
    // 추상 메서드를 구현했으므로 인스턴스 생성이 가능하다.
    AudioPlayer ap = new AudioPlayer();

    // Player 클래스를 가지고 AudioPlayer 클래스를 제어
    // AudioPlayer 자손 클래스에서 기능 구현이 되어있으므로 괜찮음.
    Player ap = new AudioPlayer();

}

// 추상클래스(미완성 클래스)
abstract class Player{

    // 추상 메서드 ({}몸통이 없음)
    abstract void play(int pos);
    abstract void stop();
}

class AudioPlayer extends Player{
    void play(int pos){ /* 내용 생략 */ };
    void stop(){ /* 내용 생략 */ };
}

