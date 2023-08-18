package Anonymous_class;

import java.awt.*;
import java.awt.event.*;
import java.beans.EventHandler;

//public class Anonymous_class {
//    public static void main(String[] args){
//        Button b = new Button("start");
//        b.addActionListener(new EventHandler());
//    }
//}
// 1회성 호출 클래스이기 때문에 익명 클래스를 사용하는 것이 이득이다.
//class EventHandler implements ActionListener {
//    public void actionPerformed(ActionListener e) {
//        System.out.println("ActionEvent occurred!!!");
//    }
//}

public class Anonymous_class {
    public static void main(String[] args) {
        Button b = new Button("start");

        // 파라미터에 클래스 이름이 없으므로 조상을 호출하고, {}안에 클래스 내용을 작성하면 된다.
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ActionEvent occurred!!!");
            }
        });
    }
}
