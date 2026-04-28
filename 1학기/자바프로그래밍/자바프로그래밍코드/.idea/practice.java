import java.util.Vector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;


interface PhoneInterface{
    final int Timeout = 1000;
    void sendCall();
    void recieveCall();
    default void printLogo(){
        System.out.println("***PHONE***");
    }
}


class samsung implements PhoneInterface{
    public void sendCall(){
        System.out.println("여보세요");
    }

    public void recieveCall(){
        System.out.println("전화받았습니다");
    }
}

class Point{
    private int x, y;
    public Point(int x, int y) {
        this.x= x;
        this.y= y;
    }
    public String toString() {
        return "(" + x + "," + y + ")";  
    }
}

class ssum<T>{
    T num1;
    T num2;

    ssum(T num1, T num2){
        this.num1 = num1;
        this.num2 = num2;
    }

    //연산지원 ㄴㄵ
}




public class practice{
    public static void main(String[] args){
        
       samsung a = new samsung();
       a.sendCall();
       a.printLogo();










       Vector<String> aa = new Vector<String>(4);
       aa.add(0, "안녕하세요");
       System.out.println(aa.size());
       System.out.println(aa.capacity());
       System.out.println(aa.get(0));
       aa.lastElement();
       aa.elementAt(0);
       aa.remove(0);
       aa.removeAllElements();

       Vector<Point> bb = new Vector<Point>(3);
       bb.add(new Point(23, 32));


       ArrayList<String>cc = new ArrayList<String>(2);
       cc.add(1, "앙기모띠");
       cc.add(0, "시발");
       cc.get(1);
       cc.size();
       cc.remove(1);
       cc.clear();

        // Vector<Integer> aa = new Vector<Integer>();
        // aa.add(12);
        // aa.add(1,24);

        // Iterator<Integer> bb = aa.iterator();
        // while(bb.hasNext()){
        //     int n = bb.next();
        //     System.out.println(n);
    // }

        HashMap<Integer, String> h = new HashMap<Integer, String>(3);
        h.put(1, "인간1");
        h.put(2, "인간2");
        h.get(2);
        h.remove(1);
        /*요소 개수*/ h.size();





    }
}