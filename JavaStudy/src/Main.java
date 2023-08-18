// 같은 소스 파일(여기선 Main.java파일)의 클래스들은 모두 같은 패키지에 속하게 됨.
// package com.sktelecom.www; 이런느낌

// import java.lang.*; 기본 패키지폴더의 모든 클래스들을 불러온다. 자동 추가문으로 생략 가능함.
// 파이썬의 라이브러리 개념과 같다고 보면 댐.
// *은 모두 불러온다는 것인데, 컴파일을 할 때 시간차이는 그렇게 크지 않음.

// import static java.lang.Math.random; , static import문을 사용하게 되면
// System.out.println(Math.random()); 같은 명령문을 out.println(random());와 같이 사용할 수 있다.



public class Main {
    // public class 이름은 소스파일, Main.java의 이름와 동일해야 한다.
    // 하나의 소스파일에는 하나의 클래스만 작성하는 것이 바람직하다.
    public static void main(String[] args) {
        Tv t1 = new Tv();

        Tv[] tvArr = new Tv[3]; // 참조 변수 사용할 때는 tvArr[0] ~ tvArr[2]로 인덱스를 호출하여 사용한다.

        t1.channel = 7;
        t1.channelDown();
        t1.width = 100;
        // t1 참조변수에서 값을 선언하고, t2 참조변수에서 값을 선언하면, 후자에서 선언한 값이 적용된다.
        // 그래서 클래스 변수는 클래스 변수 이름을 사용하여 참조변수를 만드는 것이 좋다.

        Tv t2 = new Tv();

        t2.width = 200;
        Tv.width = 200; // 클래스 변수를 참조변수로 사용할 경우 클래스의 명을 쓰는게 좋음.

        // 이 결과는 둘이 같은 값이 나온다.
        // static 선언, 클래스 변수를 사용했기 때문에, 클래스에 모두 동일하게 적용된다.

        System.out.println(t1.width);
        System.out.println(t2.width);


//        System.out.println("t1의 channel"+t1.channel);
        }
    }


class Tv {
    String color;
    boolean power;
    int channel;

    // Tv 인스턴스의 클래스 속성에서 선언한 내용이 모든 인스턴스 참조 변수에 공통적으로 적용이 되는 것은 static을 쓴다.
    // 참조 변수를 Tv t1 ~ 라고 선언했을때, t1.width을 사용할 수 있지만, 클래스의 변수 이름을 사용하는 것을 권장.
    // TV.width라고 사용하는 것이 좋다.

    // Tv의 크기, 고유 속성이므로 static을 사용해서 공통적인 변수를 선언한다.
    // 이것을 클래스 변수라고 한다.
    static int width = 50;
    static int height = 100;

    void power() {
        power = !power;
    }

    void channelUp() {
        ++channel;
    }

    void channelDown() {
        --channel;
    }

    // 생성자가 하나도 없는경우 default 생성자를 자동으로 생성한다.
}

class Tv1 extends Tv  {

    int x = 0;

    // this.x 해당 클래스 변수 호출
    // super.x 부모의 변수 호출 == Tv x = new Tv(); , x.x


    // 오버라이딩, 부모의 메서드에서 동일한 메서드 이름으로 생성
    void power(){
        power = power;
    }

    // 오버로딩, 부모의 메서드에서 동일한 이름이지만, 매개변수(파라미터)가 다른 형태로 생성
    void power(int i){
        i += 1;
    }


}

// 내가 공부할라했던 것은 접근 제어자임.
class access_order extends Tv{
    private int x; // 같은 클래스
            int y; // 같은 패키지
    protected int z; // 같은 패키지 + 다른 패키지의 자손 클래스
    public int v; // 접근 제한이 없다.
}

// private 같은 클래스 내에서만 접근이 가능하다.
// (default) 같은 패키지 내에서만 접근이 가능하다.
// protected 같은 패키지 + 다른 패키지의 자손 클래스에서 접근이 가능하다.

// 다른 패키지에서 이전의 패키지의 클래스를 import문을 사용하여 불러와서,
// 상속 클래스를 사용하여 자손 클래스를 생성 후 해당 클래스까지 사용 가능하단 뜻.

// public 접근 제한이 전혀 없다.

// class 앞에 붙일 수 있는 접근 제어자는 public, (default) 두개 뿐

// 멤버에는 접근 제어자 네개 다 앞에 붙일 수 있다.

class TvControl{

    // 다형성, Tv 와 Tv1 타입 불일치해도
    // t, 조상 Tv의 t 참조변수를 통해
    // 자손 클래스 Tv1을 제어가 가능하다.
    Tv t = new Tv1();

    // 조상 클래스의 멤버를 이용하여 자손 클래스를 제어하다 보니까,
    // 자손 클래스의 멤버는 사용할 수 없다.

    // 기능에서 장점으로는, 자손 클래스에서 쓰지않는 기능들은 추가하지 않고 사용하다 보니까
    // 메모리 덜 잡아먹을수도...?

    // 하지만
    // Tv1 t = new Tv();
    // 이렇게 반대로 자손 클래스를 가지고 조상 클래스를 다루는 것은 불가능하다.

    // 버튼은 있는데 눌러도 기능, 멤버가 없는 것과 같음, 오류를 반환한다.

}

class Array{

    Tv T[] = new Tv[2];

    T[0] = new Tv1;
    T[1] = new access_order;


}