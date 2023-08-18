package def_sta;

public class def_sta {
}

// 기존의 인터페이스는 이렇게 추상메서드들의 집합으로 이루어져있다.
// 하지만 아래와 같이 할 경우, 새로운 메서드를 생성할 때 모든 클래스에서 변경을 해야한다는 까다로운 단점이 있다.
interface MyInterface{
    void method();
    void newMethod();
}

// 그것을 해결하기 위해서 default 메서드라는 것이 생겼고, 이는 인터페이스 내에서 구현된 메서드라고 보면 된다.
interface MyInterface_new {
    void method();
    default void newMethod(){}
}

// 하지만 인터페이스를 사용하는 이유는 충돌을 해결하기 위해서인데, default를 사용하면 충돌문제 발생.
// 오버라이딩을 사용하여 충돌을 방지하면 된다.
// 조상과 자손의 메서드 충돌이 발생할 경우 조상 인터페이스의 메서드가 우선시 된다.