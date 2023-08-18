public class Main {
    public static void main(String[] args) {

        // 열거형을 사용하여 값이 0으로 똑같지만, 타입이 다르기 때문에, false를 반환한다.
        // C언어에도 열거형이 있지만, C언어에서는 값만 가지고 판단하는데, 자바에서는 타입에 안전한 열거형 제공, 타입까지 판단한다.

//        if(Card.Kind.CLOVER == Card.Value.TWO){
//        }

        // 열거형은 비교 연산자를 사용할 수 없다. CompareTo()를 사용하여 작성할 수 있다.
//        if(Card.Kind.CLOVER > Card.Value.THREE){
//        }

        // 셋다 같은 표현이다.
        Card.Kind k1 = Card.Kind.CLOVER;
        Card.Kind k2 = Card.Kind.valueOf("CLOVER");
        Card.Kind k3 = Enum.valueOf(Card.Kind.class, "CLOVER"); // Enum은 최대 조상 클래스로써 모든 열거형 호출 가능.

        System.out.println(k1);

        // switch case문을 작성할 때, 예를 들어서 클래스를 만들지 않고, 전역으로 enum 열거형 상수를 만들었을때
        // enum Kind { CLOVER, HEART, DIAMOND, SPACE } 라고 했을때
        // Kind.CLOVER으로 작성하는것이 아닌, CLOVER으로 작성하면 된다. 그냥 문법이라 어쩔 수 없다.

        // enum 메서드에서 name()은 상수명을 반환, ordinal()은 순서값을 정수로 반환한다.

        for (EnumTest e : EnumTest.values()){
            System.out.printf("%s = %d%s%n", e.name(), e.getValue(), e.getSymbol());
        }
//        System.out.printf("d%n", EnumTest.valueOf("East"));

        EnumTest e1 = EnumTest.EAST;

        System.out.println(e1);

        EnumTest e2 = EnumTest.of(1);

        System.out.println(e2.getValue());
}

//class Card {
//    static final int CLOVER = 0;
//    static final int HEART = 1;
//    static final int DIAMOND = 2;
//    static final int SPACE = 3;
//
//    static final int TWO = 0;
//    static final int THREE = 1;
//    static final int FOUR = 2;
//
//    final int kind;
//    final int num;
//}

// 위에 card 클래스를 열거형으로 바꾸면 아래와 같이 작성하면 된다.

static class Card {
    enum Kind {CLOVER, HEART, DIAMOND, SPACE}
    // enum은 위를 예를 들어서 0, 1, 2, 3으로 자동으로 값을 부여해준다.

    enum Value {TWO, THREE, FOUR}

    // 불연속적인 열거형 상수일 경우. 상수명 뒤에 (1)처럼 원하는 값을 추가할 수 있다.
    // enum Kind {CLOVER(2), HEART(5), DIAMOND(1), SPACE(7)}

//    final Kind kind;
//    final Value value;
}
}