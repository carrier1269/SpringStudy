package InterfaceTest;

//class A {
//    public void method(B b) {
//        b.method();
//    }
//}

class A {
    public void method(I i) {
        i.method();
    }
}

interface I {
    public void method(); // {}를 사용하지 않아서 구현하지 않았기 때문에, 상속을 주고나서 method 메서드만 구현하면 된다.

    // 인터페이스에서 멤버중에 선언된 상수는 public
}
class B implements I{
    public void method() {
        System.out.print("B클래스의 메서드");
    }
}

//class son extends B {
//    public void methods() {
//        System.out.print("B의 자손클래스 메서드");ㄱ
//    }
//}
class C implements I{
    public void method() {
        System.out.print("C클래스의 메서드");
    }
}

public class InterfaceTest {
    public static void main(String[] args){
        A a = new A();
//        a.method(new B()); // B 클래스를 직접적으로 사용한 것이 아닌, 파라미터로 전달한 것이다.
        a.method(new C());

//        B b = new son(); // 조상 클래스를 참조변수로 선언하여, 자손 클래스를 제어한다. 다형성의 형 변환.
    }
}

