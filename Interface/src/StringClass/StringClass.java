package StringClass;

public class StringClass {
}

// String s = "hello";
// String b = "hello"; -> hello가 같으므로 s와 b는 같은 주소에 생성이 된다.

// String s = new String("hello");
// String b = new String("hello"); -> hello가 같지만, new 메서드를 사용하여 객체를 새로 생성했으므로, 다른 주소값에 저장이 되고, 이는 비효율적이다.

// String (String s)
// String (char[] value) // char[] c = {'h', 'e', 'l', 'l', 'o'}
// String (StringBuffer buf) // String은 immutable, 불변의 특성을 지니고 있어 변경이 불가능하지만, StringBuffer을 사용하면 변경이 가능하게 만들어준다.
// char charAt (int index), String s = "Hello" -> char c = s.charAt(1); -> Hello에서 첫번째 인덱스 값인 e를 c 변수에 저장을 한다.

// int i = "aaa".compareTo("bbb"); 문자열과 사전순서를 비교하는데, 왼쪽이 작으면 -1, 같으면 0, 더 크면 1을 반환한다.

// String s = "Hello"; String S2 = s.concat(" Hi"); -> S2 = "Hello Hi"

// String s = "Hello"; boolean b = s.contains("He"); -> true 반환

// String file = "hello.txt";, boolean b = file.endsWith(".txt"); -> true 반환

// String s = "Hello";, boolean b = s.equals("Hello"); -> true 반환

// String s = "hello";, boolean b = s.equalsIgnoreCase("Hello"); -> true 반환, 소문자 대문자를 구별하지 않고 비교를 한다.

// String s = "hello";, int idx1 = s.indexOf("o"); int idx2 = s.indexOf("1"); -> 4반환, -1반환(없는 값일 경우 -1반환)

// String s = "java.lang.Object"; boolean b = s.startWith("java"); -> true

// String s = "java.lang.Object"; String c = s.substring(10); 10인덱스 위치부터 문자열을 반환한다. -> Ojbect