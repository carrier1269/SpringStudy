public class abstract_study2 {
    public static void main(String[] args){
        Unit[] group = new Unit[3];
        group[0] = new Marine();
        group[1] = new Tank();
        group[2] = new DropShip();

        for(int i=0; i < group.length; i++)
            group[i].move(100, 200);
    }
}
abstract class AbstractPlayer extends Player{
    void play(int pos){ /* 내용 생략 */ };

    // 이것은 일단 추상 클래스를 상속받은 자손 클래스이다.
    // 그러므로 멤버들이 넘어오면서, 모든 추상 메서드들이 같이 넘어오는데,
    // 추상 메서드중에 하나만 구현을 하면, 그 아래에는 아직 미완성된 추상 메서드가 남아있으므로,
    // 클래스를 선언할 때 abstract를 붙이지 않으면 오류가 발생한다.
    // abstract void stop();이란 메서드도 같이 따라온 것.

    // 자손 클래스에서 abstract를 없애려면,
    // 부모 클래스의 모든 추상 메서드들을 구현하면 된다.
}

// 추상 클래스를 사용하는 이유.
// 공동 작업이나 단일 작업중에, 구현할 기능을 미리정해놓고,
// 아직 구현하지 않은 기능, 그러니까 메서드를 구현하지 않은것에 대해서
// 효과적으로 표현하기 위해서 사용한다고 보면 된다.

// 공동 작업시 다른사람들이 클래스안에 메서드 기능이 구현이 안되어있는지도 모르고
// 인스턴스를 생성하여 호출할 경우, 에러가 발생하고 그 원인을 찾기 어려울 수 있기 때문에,
// 추상 클래스를 사용해서 표기를 하는 것이 가장 이상적인 방법이다.

abstract class Unit {
    int x, y;
    abstract void move(int x, int y);
    void stop() { /* 현재 위치에 정지 */ }
}

class Marine extends Unit {
    void move(int x, int y) { /* 지정된 위치로 이동 */ }
    void stimPack() { /**/}
}

class Tank extends Unit {
    void move(int x, int y) { /* 지정된 위치로 이동 */ }
    void changeMode() { /**/ }
}

class DropShip extends Unit {
    void move(int x, int y) { /* 지정된 위치로 이동 */ }
    void load() { /**/ }
    void unload() { /**/ }
}

