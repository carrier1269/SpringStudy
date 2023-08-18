import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Student> map = new HashMap<String, Student>();

        map.put("자바왕", new Student("자바왕", 1, 1, 100, 100, 100));

        Student s = map.get("자바왕");

        System.out.println(s.ban);

//        System.out.println(map.get("자바왕").name);
        System.out.println(map.get("자바왕").ban); // 참조변수 s를 생성하지 않고, 지네릭스 기법을 사용하여 요약한 코드.
    }
}

class Student {
    String name = "";
    int ban;
    int no;
    int kor;
    int eng;
    int math;

    Student(String name, int ban, int no, int kor, int eng, int math) {
        this.name = name;
        this.ban = ban;
        this.no = no;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }
}