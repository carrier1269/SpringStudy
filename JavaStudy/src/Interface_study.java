public class Interface_study {
}

// 인터페이스는 추상 메서드의 집합이다.
// 추상 클래스와의 차이점이 뭐냐? 라고 할 수 있는데, 추상 클래스는 다양한 멤버 변수와 메서드에 추가로 추상 메서드를 포함하고 있어 추상 클래스를 선언하지만,
// 인터페이스는 오로지 추상 메서드만 포함하고 있다.
interface inf {
    public static final val =5; // 인터페이스에서 상수만 선언할 수 있는데, public static final이 무조건 앞에 붙는다. 선언하지 않아도 자동으로 붙는다.
    public abstract solution(int val); // 인터페이스에서는 추상메서드만 선언할 수 있는데, public abstract가 무조건 앞에 붙는다.
}

interface PlayingCard {

    // 변수, 인스턴스 변수, 클래스 변수는 인터페이스에서 선언이 불가능하다.
    public static final int Spade = b4;
    final int Diamond = 3;
    static int Heart = 2;
    int Clover = 1;

    public abstract String getCardNumber();
    String getCardKind(); // public abstract String getCardKind();
}

// 원래의 클래스는 기본적으로 최고 조상이 Object로서 상속을 받고 잇지만,
// 인터페이스는 최고 조상이 Object가 아니며, 인터페이스만 조상으로 가능하다.
// 인터페이스는 단일 상속이 아닌(충돌 문제 때문에), 다중 상속이 가능하다(충돌 문제가 없기 때문에).

interface Fightable extends Movable, Attackable {}

interface Movable {
    void move (int x, int y);
}

interface Attackable {
    void attack(Unit u);
}

// 일반 클래스에서 일반 조상 클래스나 추상 클래스를 상속할 때는 extends를 사용하고, 추상 클래스의 같은 경우 추상 메서드를 구현하지만,
// 일반 클래스에서 인터페이스를 상속 받을때는 extends 대신에 implements를 사용하여 상속받는다. 대신 모든 추상 메서드를 구현해야 한다.

class Fighter implements Fightable{
    public void move(int x, int y) {/**/}
    public void attack(Unit u){/**/}
}

// 인터페이스 조상 큻래스를 상속받을 경우, 그리고 일부 추상 메서드만 구현을 할 경우 클래스 앞에 abstract를 붙여 추상 클래스로 완성해야 한다.
abstract class Fighter implements Fightable {
    public void move(int x, int y){/**/}
}
