import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(10); // 배열의 길이를 정해주며 인스턴스 참조 변수를 생성한다.

        // autoboxing에 의해서 기본형이 참조형으로 자동 변환
        list.add(5);
        // 이렇게 써도 list.add(new Interger(5));로 오토박싱에 의해 자동 변환된다.

        list.add(new Integer(4));
        list.add(new Integer(2));
        list.add(new Integer(0));
        list.add(new Integer(1));
        list.add(new Integer(3));

        // list.subList는 읽기 전용이기 때문에, 새로운 객체를 생성해줌으로써 변환이 가능하도록 해준다.
        ArrayList list1 = new ArrayList(list.subList(1,4));

        System.out.println(list);
        System.out.println(list1);

        Collections.sort(list);
        Collections.sort(list1);

        list1.add("B");
        list1.add("C");
        list1.add(3, "A");

        System.out.println(list);
        System.out.println(list1);

        list1.set(3, "AA");

        System.out.println(list1);

        // indexOf()는 지정된 객체의 위치(인덱스)를 알려준다.
        System.out.println("index = " + list1.indexOf(new Integer(1)));

        // 없는경우 -1을 반환한다.
        System.out.println("index = " + list1.indexOf(new Integer(4)));

        // 객체를 생성하지 않고 그냥 기본형 숫자를 매개변수로 대입한다면, index 5에 있는 객체를 삭제한다.
        list1.remove(5);
        System.out.println(list1);

        // 새로운 객체를 생성하여 대입하면, index값이 아닌 실제 value의 값을 삭제한다.
        list1.remove(new Integer(4));
        System.out.println(list1);

        // 0번째 인덱스에 있는 원소의 값을 가져온다.
        System.out.println(list1.get(0));

        // list 배열에 0번째 원소가 list1에 포함되어있을 경우 true를 반환한다.
        if(list1.contains(list.get(0))){
            System.out.println(true);
        }
        else{
            System.out.println(false);
        }


    }
}