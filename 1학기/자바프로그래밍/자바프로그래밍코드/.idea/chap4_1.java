class chap4_1 {
    //this is for array class

    public chap4_1(int num){
        System.out.println("this is class chap4_1 " + num);
        
        

        
    }

    //기본 타입이 전달되는 메서드 
    public static void increase1(int m){
        m = m+1;
        System.out.println(m);
    }

    //객체가 전달되는 메서드(ㅅㅂ)
    public static void increase2(Circle n){
        n.radius++;
        System.out.println(n.radius);
    }

    //배열이 전달되는 메서드(롯같다
    //이것도 call by reference와 같은 논리
    public static void increase3(int[] array){
        for (int i = 0; i < array.length; i++){
            array[i] ++;
            System.out.print(array[i]);
        }
        
    }

    public static class Circle {
        int radius;     

        public Circle(int radius) {
            this.radius = radius;
        }
        public void getnnum(){
            System.out.println(this.radius);
        }
    }

    //오버로딩
    // 한 클래스 내에서 이름이 같은 메소드 -- 오버라이드랑 다르다 !!
    public static int getsum(int a, int b){
        return a+b;
    }
    public static int getsum(int a, int b, int c){
        return a+b+c;
    }
    public static int getsum(int a, int b, int c, int d){
        return a+b+c+d;
    }
    
    // 이렇게 매개변수의 개수와 타입이 같은 놈은 오버로딩 실패됨
    // public static int getsum(int a, int b){
    //     return a+b;
    // }
    //

    //final
    //final 클래스 = 더 이상 상속 불가능 클래스
    //final 메소드 = 더 이상 오버라이딩 불가능
    public final class lasthuman{
        final int age = 99;
        final void breath(){
            System.out.println("thank you Jesus");
        }
    }

    

    

    //객체 배열
    public static void main(String[] args){
        chap4_1[] c = new chap4_1[5];
        for (int i = 0; i < 5; i++){
            c[i] = new chap4_1(i);
            
        }

        // / 인자 전달 –기본 타입의 값이 전달되는 경우
        //  call by value
        //  값만 복사 → 원본은 그대로.
        int shi = 200;
        increase1(shi);

        
        // 인자 전달 –객체가 전달되는 경우
        // call by refernce
        // 원본 주소를 전달 → 함수 안에서 바꾸면 원본도 바뀜
        Circle pizza = new Circle(10);   // radius = 10으로 객체 생성
        increase2(pizza);                
        System.out.println(pizza.radius); // 11

        //배열전달 - 배열 통으로 전달 x 레퍼런스만ㅇㅇ
        int shit[] = {1,2,3,4};
        increase3(shit);

        System.out.println();
        //오버로딩
        System.out.println(getsum(1,2));
        System.out.println(getsum(1,2,3));
        System.out.println(getsum(1,2,3,4));

        //객체치환
        Circle j= new Circle(12);
        Circle jj = new Circle(234);
        j = jj;
        j.getnnum();
        jj.getnnum();

        //그리고 자바는 임의로 객체 소멸이 안된다
        // 자바에서 객체 소멸은 =  자바 가상 기계로 되돌려주는 것이다
        
//         가비지 = 가리키는 레퍼런스가 하나도 없는 객체
//           더 이상 접근할 수 없어 사용할 수 없게 된 메모리
        // 위의 코드에서 j 객체는 가비지가 되었다
        jj = null; // 가비지 됨 

        //가비지 컬렉션 = 자바 가상 기계가 가비지 자동으로 회수
        // 가비지 컬렉터에 의해 실행
        //강제 가비지 컬렉터 실행법 
        System.gc();

        //  패키지
        //  상호 관련 있는 클래스 파일(컴파일된 .class)을 저장하여 관리하는 디렉터리
        //  자바 응용프로그램은 하나 이상의 패키지로 구성

        // 접근 지정자
        // 4가지 = private, public, protected, 디폴트(접근지정자 생략)
        // 목적 = 클래스나 일부 맴버 공개 -> 다른 클래스에서 쓰도록
        //        객체 지향 언어의 캡슐화 정책 -> 맴버 보호

        // private = 외부로부터 완벽 차단
        // 디폴트 = 동일 패키지엔 허용
        // protected = 동일 패키지와 자식 클래스까지 허용
        // public = 모든 클래스에 허용

//          public 멤버
//          모든 클래스에게 접근 허용

//         private 멤버
// ◼        동일 클래스 내에만 접근 허용
//          상속 받은 서브 클래스에서 접근 불가

//         protected 멤버
// ◼        같은패키지 내 모든 클래스에게 접근 허용
// ◼        상속 받은 서브 클래스는 다른 패키지에 있어도 접근 가능

//         디폴트(default) 멤버
// ◼        같은 패키지 내의 다른 클래스에게 접근 허용 = 다른 패키지는 ㄴ


        //static 맴버는 객체를 안만들어도 바로 사용 ㄱㄴ
        // 값이 계속 유지됨 (공유됨)
//          프로그램이 끝날 때까지 사라지지 않음
        getsum(234,53);


//  static 메소드는 오직 static 멤버만 접근 가능
//  객체가 생성되지 않은 상황에서도 static 메소드는 실행될 수 있기 때문에, non-static 멤버 활용 불가
//  non-static 메소드는 static 멤버 사용 가능

        










        
    }


}
