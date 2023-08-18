package hashCode;

class Card {
    String kind;
    int number;

    Card() {
        this("SPADE" , 1);
    }

    // 클래스와 동일한 이름의 메서드를 특징으로 가진 생성자,
    // 생성자를 사용하는 이유는 인스턴스를 호출할 때 편리하게 초기화하기 위해서 사용한다.
    Card(String kind, int number) {
        this.kind = kind;
        this.number = number;
    }

    public String toString() {
        return "kind:" + kind + ", number:" + number;
    }
}

public class hashCode {
    public static void main(String[] args) {
        Card c1 = new Card();
        Card c2 = new Card();

        System.out.println(c1.toString());
        System.out.println(c2.toString());
    }
}
