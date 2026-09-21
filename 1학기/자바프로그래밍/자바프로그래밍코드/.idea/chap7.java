import java.util.Vector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

// 즉, 어떤 타입을 넣든지 그 타입에 맞게 동작하는 재사용 가능한 클래스를 만든 것입니다.
class for_GenericClass<T>{
    T ob;

    public for_GenericClass(T ob){
        this.ob = ob;
    }

    public void printType(){
        System.out.println(ob.getClass());
    }
}

class onlyInt<E>{
    E a;
    
    public void push(E num){

    }

    public void pop(E num){

    }
}

class Point{
    int x, y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

//제네릭
class generic<E>{
    E num1 ;
    E num2 ;

    generic(E num1, E num2){
        this.num1 = num1;
        this.num2 = num2;
    }

}



public class chap7 {
    //콜렉션 : 객체들의 컨테이너
    //제네릭 :  모든 종류의 데이터 타입을 다룰 수 있도록 일반화 된 타입 매개변수로 클래스(인터페이스)나 메소드를 작성하는 기법
    public static void main(String[] args){
        for_GenericClass<Double> d = new for_GenericClass(23.5);
        d.printType();

        // Vector<데이터타입> 의 특징 : 배열을 가변 크기로 다룰 수 있는 컨테이너
        // 객체, null삽입 가능
        // 벡터의 맨 뒤, 중간에 객체 삽입 가능
        // 임의의 위치에 있는 객체 삭제 가능
        // 기본타입은 ㄴ
        Vector<Integer> a = new Vector(7); //벡터생성

        a.add(4); // 요소 삽입
        a.add(5);
        a.add(8);

        int n = a.size(); //요소 개수
        int s = a.capacity(); // 벡터의 용량

        System.out.println("요소 개수 : "+n);
        System.out.println("벡터의 용량 : "+s);

        a.add(0, 2); // 요소 중간 삽입 (위치, 값)

        //요소 얻어내기
        System.out.println(a.get(0));

        //요소 삭제
        a.remove(0);

        //마지막 요소
        int last_a_ob = a.lastElement();
        System.out.println("벡터의 마지막 요소 : " + last_a_ob);
        
        //요소
        a.elementAt(0);

        //모든 요소 삭제
        a.removeAllElements();

        

        try{
            System.out.println(a.get(0));
        }
        catch(Exception e){
            System.out.println("오류 원인  : " +e);
        }




        //활용
        Vector<Point> p = new Vector<Point>();

        p.add(new Point(1,2));
        p.add(new Point(23,235));

        p.remove(1);

        for(int i=0; i < p.size(); i++){
            System.out.println(p.get(i));
        }

        Vector<String> aaa = new Vector<String>(3);
        aaa.add("안녕");
        aaa.add("하세요");

        aaa.size();
        aaa.capacity();

        aaa.add(2, "반갑습니다");

        



        //ArrayList 특
        // 가변 크기 배열을 구현한 클래스
        // 벡터와 거의 동일함 - 요소 삽입삭제검색 등 기능 거의 동일함

        ArrayList<String> aa = new ArrayList<String>(4);

        aa.add("minecraft");
        aa.add(1, "counter strike");
        aa.add("brawlhalla");

        System.out.println("요소 개수 : " + aa.size());
        System.out.println("요소 알아내기 : " + aa.get(2));

        aa.remove(2);

        //전체삭제에서 좀 다르네
        aa.clear();


        //화룡
        ArrayList<String> bb = new ArrayList<String>();
        Scanner ss = new Scanner(System.in);

        //입력
        for(int i = 0; i < 4; i++){
            System.out.print(i+"번째 인간의 이름을 입력하세요 : ");
            bb.add(ss.next());
        }

        //출력
        System.out.println();
        for(int i = 0; i< a.size() ; i++){
            System.out.println(bb.get(i));
        }

        //iterator<E> 인터페이스
        // 리스트 요소 순차 검색
        // hasNext - 값이 있냐 없냐 
        Vector<Integer> tt = new Vector<Integer>(); 
        tt.add(1);
        tt.add(2532);
        tt.add((int)23.5);


        Iterator<Integer> it = tt.iterator();

        //iterator이용해서 모든 정수 출력하기
        while(it.hasNext()){
            int nn = it.next();
            System.out.println(nn);
        }

        //모든 정수 더하기 시팔
        int ssssum = 0;
        while(it.hasNext()){
            ssssum += it.next();
        }

        System.out.println(ssssum);


        // 키(key)와 값(value)의 쌍으로 구성되는 요소를 다루는 컬렉션
        HashMap<Integer, String>hh = new HashMap<Integer, String>();

        //값 넣기
        hh.put(1, "1번 인간");
        hh.put(2, "2번 인간");

        //요소 검색 
        System.out.println(hh.get(1));
        System.out.println(hh.get(2));

        //요소 개수
        System.out.println(hh.size());


        // 이건 어려워서 활용 안함..


        


        
        


        

        



    

}
}