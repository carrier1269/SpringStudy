package exception_throw;

public class exception_throw {
    public static void main(String args[]) {
        try{
            Exception e = new Exception("고의로 발생시켰음.");
            throw e;
        }

        catch (Exception e) {
            System.out.println("에러 메시지 : " + e.getMessage());
            e.printStackTrace();
        }

        // throw new Exception(); // 체크하는 예외에는 예외처리를 안하면 에러가 난다.
        throw new RuntimeException(); // 체크하지 않는 예외기 때문에 에러가 발생하지 않는다.
    }
}

class exception_method {
    // 예외를 발생시키는 명령문은 throw를 사용하고,
    // 예외를 메서드에 선언할 때는 throws를 사용한다.

    // 오버라이딩 규칙
    // 1. 선언부 일치
    // 2. 자손 접근제어자가 조상보다 좁게 하면 안된다.
    // 3. 조상보다 많은 예외를 선언하면 안된다.
//    void method() throws Exception1, Exception2, ... ExceptionN { // 예외처리를 해주어야 한다.
//
//    }

//    static void startInstall() throws SpaceException, MemoryException {
//        if(!enoughSpace())
//            throw new SpaceException("설치할 공간이 부족합니다.");
//        if(!enoughMemory())
//            throw new MemoryException("메모리가 부족합니다.");
//    }

    void method1() throws Exception { // 모든 종류의 예외가 발생이 가능하다.

    }

    // 동일 기능 메서드에서 동일 예외처리가 발생하고 나면, 다른 동일 예외처리는 발생하지 않는다.

    // try-catch를 통해 예외처리 후, finally를 사용하면 예외 발생여부에 상관없이 무조건 마지막으로 실행시킨다.
}