package try_catch;

//public class try_catch {
//    public static void main(String args[]) {
//        System.out.println(1);
//        try {
//            System.out.println(2);
//        } catch (Exception e) {
//            System.out.println(3); // 예외가 발생하지 않으므로 3은 출력되지 않는다.
//        }
//        System.out.println(4);
//    }
//}

public class try_catch{
    public static void main(String args[]) {
        System.out.println(1);
        try {
            System.out.println(0/0); // 예외발생
            System.out.println(2);
        } catch (ArithmeticException ae) {
            // 예외가 발생을 했는데, ArithmeticException이 맞다면,
            if (ae instanceof ArithmeticException) {
                System.out.println(3); // Arithmetic예외가 발생하므로 0/0나누기 할때 중지시키고 catch문 안의 명령문을 실행시킨다. 3부터 출력함.
            }
        } catch (Exception e) {
            System.out.println(4); // 모든 예외 처리가 된다.
        }
    }
}
