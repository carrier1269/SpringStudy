package Interface;

abstract class unit2 {
    int x, y;
    abstract void move(int x, int y);
    void stop() { System.out.print("멈춥니다.");}
}

interface fightable { // 인터페이스의 모든 메서드는 public abstract.
    public void move(int x, int y); //  public abstract가 생략됨.
    void attack(fightable f);
}



class fighter extends unit2 implements fightable {
    // 오버라이딩 규칙 : 조상(public)보다 접근제어자가 범위가 좁으면 안된다.
    public void move(int x, int y) {
        System.out.println("["+x+","+y+"]로 이동");
    }

    public void attack(fightable f) {
        System.out.println(f+"를 공격");
    }

    fightable getfightable(){
        fightable f = new fighter();
//        fightable f = (fightable) new fighter(); // 자손이 조상을 가르킬때는 생략이 가능하다.
        return f; // fightable 반환 타입이 일치함, 형변환
    }

}


public class Main {
    public static void main(String[] args) {
//        fighter f = new fighter();
//        f.move(100, 200);
//        f.attack(new fighter());
        fighter f = new fighter();
        fightable f2 = f.getfightable();

    }
}

// 인터페이스를 사용하는 이유는, 동작 구현을 분리하기 위해서이다.
// 일반 클래스로 생성할 경우 변경이 불가능한데,
// 인터페이스를 사용하고, 상속하여 클래스를 생성할 경우, 유저가 주는 값에 따라 출력값이 바뀔 수 있다.
