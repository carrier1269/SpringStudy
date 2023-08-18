package InnerClassTest;

public class InnerClassTest {

    private int outerIv = 0;
    class InstanceInner {
        int iiv = outerIv; // private는 원래 같은 클래스에서만 접근이 가능한데,
        // 외부클래스의 private 변수에 대해서는 내부클래스에서 접근이 가능하다는 특징이 있다.
        int iv = 100;
        // static int cv = 100; // 인스턴스 내부 클래스에서는 static 변수를 선언할 수 없다.
        final static int CONST = 100; // final static은 상수이므로 가능.
    }

    static class StaticInner {
        int iv = 200;
        static int cv = 200; // static 클래스에서만 static 멤버를 선언할 수 있다.
    }

    void myMethod() {
        class LocalInner {
            int iv = 300;
            // static int cv = 300; // static 변수 선언 불가.
            final static int CONST = 300;
        }

        int i = LocalInner.CONST; // 메서드 내부에서만 사용 가능하다.
    }

    public static void main(String args[]) {
//        InstanceInner I = new InstanceInner(); // main에 static를 제거해야 사용가능.
        // 인스턴스 멤버가 스태틱 멤버를 사용하는 것은 가능하지만,
        // 스태틱 멤버가 인스턴스 멤버를 사용하는 것은 불가능하다.
//
//        System.out.println(I.iv);

        System.out.println(InstanceInner.CONST);
        System.out.println(StaticInner.cv);
//        System.out.println(LocalInner.CONST); // 지역 내부 클래스는 메서드에서만 호출이 가능하다.
    }
}
