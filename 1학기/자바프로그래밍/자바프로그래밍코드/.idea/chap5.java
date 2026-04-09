public class chap5{

    //상속 -> extends로 선언
    // 부모 클래스 = super class
    //자식 클래스 = sub class
    static class Point{   // static 추가
        int x = 0;
        int y = 0;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        protected void setdata(){
            this.x = 2; 
            this.y = 3;
            System.out.println(x + y);
        }

        private void changedata(int x, int y){  //얘는 서브라도 안됨
            this.x = x;
            this.y = y;
        }
    }

    //서브 클래스 객체는 슈퍼 클래스 객체의 맴버 포함함
    static class ColorPoint extends Point{   
        public ColorPoint(){
            super(0, 0);   // 부모 생성자 호출 필수
            setdata();
        }
    }

    //자바 상속의 특징
    // 다중상속 불가능 (클래스 기준)
    // 근데 서브 클래스라도 슈퍼 클래스의 private는 상속 못함
    // 디폴트나 public이나 protected는 같은 패키지면 다 되고, protected는 패키지랑 상관없이 서브면 다 됨

    //생성자 순서
    // 슈퍼가 가장 먼저 실행되고, 그다음 그 서브들이 실행됨 -> 그 클래스의 슈퍼가 가장 우선

    //super() 로 슈퍼 클래스의 생성자 명시적 선택하기
    static class Point2 extends Point{   // static + 대문자 수정
        Point2(){
            super(23,53);
        }
    }

    static class animal{
        public void sounds(){
            System.out.println("aaaaa");
        }
    }

    static class cat extends animal{
        public void sounds(){
            System.out.println("ya ong"); //오버라이딩 = 슈퍼클래스 메소드 중복 작성 = 슈퍼클래스 메소드 무력화 => 다형성 구현함 -> 
        }
    }
    //오버라이딩과 오버로딩의 차이는 뭘까
    //오버로딩은 그냥 슈퍼와 중복된 메소드고, 오버라이딩은 아에 새로운 기능을 재정의
    static class dog extends animal{
        public void sounds(){
            System.out.println("barking");
        }
    }

    static class puppy extends dog{
        public void sounds(){
            System.out.println("fah");
        }
    }


    //추상클래스 : abstract class
    //추상메소드 : 메소드의 코드는 없고 원형만 선언
    abstract class suka{
        abstract public void badwords();


    //꼭 추상클래스 안에 추상메소드 없어도 됨
        public static void goodwords(){
            
        }

    }

//    class fault { 
//        abstract public void f(); // 추상 메소드
//    }
// 오류. 추상 메소드를 가지고 있으므로 abstract로 선언되어야 함

    //추상클래스 상속
    //하면 서브 클래스도 추상임 abstract해줘야함
    abstract class bulyat extends suka{
        abstract public void sobadwords();{

        }
    }

    //인터페이스
    //클래스가 앞으로 구현해야 하는 메소드들의 추상형
    interface phoneInterface{
        public static final int TIMEOUT = 10000;  //public static final 생략가능
        void sendCall();
        public abstract void recieveCall(); // public abstract생략가능
        public default void sheet(){

        } //default메소드는 public생략 가능
    }

    //인터페이스 구성 요소
    //상수 : public만 허용, public static final 생략
    // 추상 메소드 : public abstract 생략 가능

    // default 메소드
        // ◼ 인터페이스에 코드가 작성된 메소드
        // ◼ 인터페이스를 구현하는 클래스에 자동상속
        // ◼ public 접근 지정만 허용. 생략 가능

    //  private 메소드
    //     ◼ 인터페이스 내에 메소드 코드가 작성 되어야 함
    //     ◼ 인터페이스 내에 있는 다른 메소드에 의해서 만 호출가능

    // static 메소드
    //     ◼ public, private 모두 지정 가능. 생략하면 public

    //인터페이스 상속
    interface mobile extends phoneInterface{
        void games();
        private void av(){

        }
        
    }
    //다중상속도 가능
    interface mp3 extends mobile, phoneInterface{

    }

    //인터페이스의 메소드 구현하는법
    //implements 키워드 사용
    class letsmakeit implements phoneInterface{
        public void sendCall() {
            System.out.println("ㅅㅂ"); 
        }
       
        public void recieveCall() {
            
        }
       
        public void sheet() {
            
           
        }
        //메소드 추가 작성
        public void sheeeet(){
            System.out.println("bissa niga");
        }
    }









    public static void main(String[] args){   // static 추가
        Point x = new Point(1, 2);   // 생성자 맞게 수정
        ColorPoint y = new ColorPoint();

        //업캐스팅 = 부모 클래스 레퍼런스가 자식 클래스 레퍼런스 참조
        // 다운캐스팅 = 자식 클래스 레퍼런스가 부모 클래스 레퍼런스 참조
        // 다운캐스팅은 반드시 명시적 타입 변환지정
        cat pin = new cat();
        animal jake = new animal(); 
        jake = pin; // 업캐스트 = 부모클래스가 자식 참조 = 부모타입변수로 자식 클래스 참조
        jake.sounds();

        animal shibal = new cat(); // 업케스팅
        cat gaesekki = (cat)shibal; // 다운캐스팅

        animal lion = new animal();
        lion = new cat();
        cat tiger = (cat)lion; //일반적으로는 업캐스팅 → 다운캐스팅 순서로 사용합니다.
        tiger.sounds();

        animal kevin = new animal();
        kevin = new cat();
        kevin = new dog(); //아니 tlqkf kevin이 cat을 가르키는지 dog을 가르키는지 puppy를 가르키는지 모르겠어  
        kevin = new puppy();

        //어떻게 식별할까
        // 객체레퍼런스instanceof클래스타입
        // 해서 true나오면 
        System.out.println(kevin instanceof cat); //why false? bc lastly it referanced puppy
        System.out.println(kevin instanceof dog); // super class라 true
        System.out.println(kevin instanceof puppy); //true  

        suka o;
        // suka o = new suka(); -> 추상클래스는 인스턴스 불가능
        
        //인터페이스는 생성 불가능
        // new phoneInterface();   -> 오류
        //레퍼런스 변수는 가능
        phoneInterface nword;

    }
}