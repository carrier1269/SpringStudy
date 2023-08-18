public class Main {
    public static void main(String[] args) {
//        Object obj = new Object() {
//            int max(int a, int b){
//                return a > b ? a : b;
//            }
//        };
//
//        int value = obj.max(3,5);

        // 함수형 인터페이스를 사용하는 방법
//        MyFunction2 MF = new MyFunction2() {
              // 오버라이딩 - 접근 제어자는 좁게 못바꾼다.
//            @Override
//            public int max(int a, int b) {
//                return a > b ? a : b;
//            }
//        };

        // 람다식에 의해 위의 방법이 요약된 것이다.
        // 객체명, 타입, 메서드명, 파라미터 타입 생략 가능
        // 람다식을 다루기 위한 참조변수의 타입은 함수형 인터페이스를 사용해야 한다.
        MyFunction2 MF = (a, b) -> a > b ? a : b;


        System.out.println(MF.max(3, 5));
    }
}

// 함수형 인터페이스
@FunctionalInterface
interface  MyFunction2 { // 함수형 인터페이스는 단 하나의 추상 메서드만 가져야 한다.
    public abstract int max(int a, int b);
}
