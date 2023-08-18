import java.lang.annotation.Inherited;

public class Main {
    // Xlint옵션으로 에러를 확인한 후 SuppressWarings를 사용하여
    // 경고문을 제거할 수 있다.
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        deprecate d = new deprecate();
        d.parentMethod(); // deprecated된 메서드 사용
    }
}

@Inherited
@interface SuperAnno {} // 상속할 조상 애노테이션 이름 선언

@SuperAnno
class AnnoParent {} // 조상 클래스에 조상 애노테이션을 선언한다.

// @SuperAnno라는 조상 애노테이션을 선언하지 않아도,
// 자손 클래스에 조상 클래스에서 선언했던 애노테이션이 자동으로 추가된다.
class AnnoChild extends AnnoParent {}


class Parent {
    void parentMethod(){
    }
}

class Child extends Parent {
    @Override
    void parentMethod() {}
//    void parentmethod() { } // 조상 메서드의 이름을 잘못 적었음.
    // @를 사용하여 애너테이션을 선언하고, 조상 메서드의 이름을 오타낼 경우,
    // 애너테이션을 사용하지 않으면 새로 생성된 것이라서 오류가 발생하지 않지만,
    // 애너테이션을 사용하고 오타를 내면 오버라이드가 잘못 됐다고 에러로 알려준다.
    // 그래서 애너테이션을 적는 습관을 들이는 것이 중요하다.
}

class deprecate extends Parent{
    @Override
    @Deprecated // 이 메서드를 사용하지 말라고 컴파일러에게 알려주는 것이다.
    void parentMethod(){ }
    // javac를 사용하여 컴파일하면 deprecated된 메서드 사용이라고 경고문이 나온다.
}

@interface Datetime{
    String yymmdd();
    String hhmmss();
}

// interface 애너테이션을 선언하면 애너테이션을 사용할때 추상 메서드 내용을 채워주면 된다.
@interface  TestInfo{
    int count();
    String testedBy();
    String[] testTools();
//    TestType testType
//    DateTime testDate();
}

//@TestInfo(
//        count = 3, testedBy="Kim", testDate = @Datetime(yymmdd = "160101", hhmmss = "235959")
//)

@interface Testinfo1 {
    String value();
}

// 애너테이션을 interface 타입으로 생성했을때,
// 요소가 하나이고 요소 이름이 value인 경우, 애너테이션을 호출할 때
// 예를 들어서 @Testinfo1(value = "passed")와 같이 value라는 요소의 이름을
// 생략해도 된다.
@Testinfo1("passed")
class NewClass{}

@interface Testinfo2 {
    String[] testTools();
}

// 하지만 애너테이션의 요소 타입이 배열인 경우,
// 괄호를 무조건 사용해야 한다
// 그리고 값이 없을 경우에도 {}를 반드시 만들어야 한다.
// 애너테이션 요소에 값을 지정해주지 않으면, default값은 1이다.
@Testinfo2(testTools = {"JUnit", "AutoTester"})
class NewClass2{}

// 모든 애너테이션의 조상은 Annotation이지만,
// @interface TestInfo extends Annotation{}와 같이 상속이 불가능하다.
// Annotation의 타입이 interface인데도, 사용할때 추상메서드를 구현하지 않아도 된다.

// @Test("value")와 달리 @Test만 사용하는 경우 마커 애너테이션이라고 한다.
// 딱히 정의된 요소는 없지만 컴파일러한테 애너테이션만 전해주는 것 만으로도
// 큰 의미라고 할 수 있다.

// 애너테이션 괄호 안에 매개변수를 선언할 수 없고, 예외가 불가능하고,
// 요소로 지네릭같은 타입 매개변수를 정의할 수 없다.

@interface AnnoTest {
    int id = 100;
//    String major(int i, int j); // 매개변수 에러
//    String minor() throws Exception; // 예외 에러
//    ArrayList<T> List(); // 타입 매개변수 에러 -> 여기는 지네릭 타입 썼음
}