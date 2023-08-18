package InnerClassTest;

class AAA {
    int i = 100;
    BBB b = new BBB();

    class BBB {
        void method() {
//            AAA a = new AAA();
//            System.out.println(a.i);
            System.out.println(i); // 위와 같이 객체 생성할 필요없이 외부 클래스(AAA 클래스)의 멤버에 접근이 가능하다.
        }
    }
}

//class CCC {
//    BBB b = new BBB(); // BBB 클래스는 AAA의 내부에 있기 때문에 이렇게 접근할 수 없다.
//}
public class InnerTest {
//    BBB b = new BBB();
//    b.method();

    AAA a = new AAA();
}
