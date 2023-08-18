package interface_2;

abstract class unit2 {
    int x, y;
    abstract void move(int x, int y);
    void stop() { System.out.print("멈춥니다.");}
}

interface fightable { // 인터페이스의 모든 메서드는 public abstract.
    public void move(int x, int y); //  public abstract가 생략됨.
    void attack(fightable f);
}

class fighter1 extends unit2 implements fightable {
    // 오버라이딩 규칙 : 조상(public)보다 접근제어자가 범위가 좁으면 안된다.
    public void move(int x, int y) {
        System.out.println("["+x+","+y+"]로 이동");
    }

    public void attack(fightable f) {
        System.out.println(f+"를 공격");
    }

}


public class Interface_study2 {
    public static void main(String[] args) {
        fighter1 f = new fighter1();
        f.move(100, 200);
        f.attack(new fighter1());
    }
}
