import java.util.Scanner;


public class chap2장 { 
    // chap2code라는 이름의 클래스 선언, public으로 선언하면 다른 클래스에서 접근 가능


    public static int sum(int n, int m){
        return n+m;
    }
    // 함수네

    public static void main(String[] args){

        //자바 프로그램은 main()에서 실행 시작
        //public static void으로 선언
        //string[] arg로 실행 인자 전달받음


        int i = 20;
        int s;
        char a;

        s = sum(i, 10);
        a = '?';
        System.out.println(a);
        System.out.println("hello");
        System.out.println(s);
        System.out.println("앙기모띠");

        //식별자 : 클래스나 변수등에 붙이는 이름
        // 첫 문자 숫자x, if사용 ㄴㄴ, false사용 ㄴㄴ, null사용 ㄴㄴ, %사용 ㄴㄴ
        // 한글로 가능, $ _ 사용 가능
        int 가격 = 1000;
        System.out.println(가격);
        
        //자바의 데이터 타입 : 8개
        // boolean(1bit), char(2byte),         byte(1byte  정수형기본타입), short(2byte), int(4byte), long(8byte),       float(4byte), double(8byte)
        // 레퍼런스 타입 : 1개이며, 용도는 다음 3가지
        // reference abt class, reference abt interface, reference about array

        //문자열은 기본 타입이 아님
        // String 클래스로 문자열 표현
        String toolname = "JDK";
        //문자열이 섞인 연산은 문자열 연결로
        System.out.println(toolname + 1.4);
        
        //변수 -> 프로그램 실행 중에 값을 임시 저장하기 위한 공간
        int radius;
        double weight = 60.3;
        char c1, c2, c3 = 'c';
        
        //리터럴 -> 프로그램에서 직접 표현한 값, 정수 실수 문자 논리 문자열 리터럴 있음
        //값 자체를 직접 코드에 적어 놓은 것  =  실수 리터럴60.3, 문자열 리터럴'c'

        //정수 리터럴 -> 10진수 8진수 16진수 2진수 리터럴
        //15 -> 10진수 15
        // 015 -> 0부터 시작하면 8진수.. 10진수로 13
        // 0x15 -> 0x로 시작하면 16진수 .. 10진수로 21
        // 0b0101 -> 0b로 시작하면 2진수.. 10진수로 5

        // 실수 리터럴 -> 소수점 형태나 지수 형태로 표현한 실수
        // 12, 12.0, .1234, 0.1234, 1234E-4
        double d = 0.1234;
        double e = 1234E-4; // = 1234x10-4 이므로 0.1234와 동일
        // 숫자 뒤에 f나 d 를 명시적으로 붙이기도 함
        float f = 0.1234f;
        double k = .1234d;

        //null 리터럴 -> 레퍼런스에 대입 사용
        // 기본 타입에 사용 불가
        // int n = null;  -> 에러
        String str = null;

        //문자열 리터럴
        String st = "Good";

        //상수
        // 변경 불가능 한 수 -> 수정 불가
        // final 키워드 사용
        final double PI = 3.141592;

        //var 키워드 
        // 지역 변수의 타입을 자동으로 추론해 주는 키워드
        var n = 10; //int로 자동 추론
        var text = "mommy"; // String으로 추론

        //타입 변환
        //자동 타입 변환
        long aa = 25; // int타입인 25가 자동으로 long타입으로 전환
        double dd = 3.14 * 10; // 실수 연산 위해 10이 10.0으로 자동 변환
        
        //강제 타입 변환
        // ()안에 개발자가 명시적으로 타입 변환 지정
        int nn = 300;
        byte bb = (byte)nn;
        System.out.println(bb); //값 손실 우려 있음
        
        System.out.println("\n");
        byte 비 = 127;
        int ii = 100;
        System.out.println(+비+ii); // 비가int타입으로자동변환
        System.out.println(10/4);
        System.out.println(10.0/4); // 4가4.0으로자동변환
        System.out.println((char)0x12340041);
        System.out.println((byte)(비+ii));
        System.out.println((int)2.9 + 1.8); // 2 + 1.8
        System.out.println((int)(2.9 + 1.8)); // 4
        System.out.println((int)2.9 + (int)1.8); // 2+1
        
        //System.in 
        //자바의 표준 입력 스트림
        // 입력되는 키를 byte로 리턴하는 저수준임

        //임포트 필요 (맨 위 코드 참고)
        Scanner aaaa = new Scanner(System.in); // scanner 객체 생성
        String name = aaaa.next();
        int age = aaaa.nextInt();
        double height = aaaa.nextDouble();
        
        System.out.println("이름 : " + name);
        System.out.println("나이 : "+ age);
        System.out.println("키 : " + height);

        //if 문
        Scanner bbbb = new Scanner(System.in);
        System.out.println("점수를 입력하세요 : ");
        int score = bbbb.nextInt();
        if (score > 70){
            System.out.println("축하합니다");
        }else if(score < 70 && score > 40){
            System.out.println("수고하셨습니다");
        }else{
            System.out.println("....");
        }

        //switch문
//          switch문은 식과case 문의 값과 비교
//           case의 비교값과 일치하면 해당case의 실행 문장 수행  
//          ◼ break를 만나면 switch문을 벗어남

//           case의 비교값과 일치하는 것 이 없으면 default문실행
//            ◼ default 문은생략가능
        char grade = 'B';

        switch(grade){
            case 'A':
                System.out.println("A입니다 축하합니다");
                break;
            case 'B':
                System.out.println("B입니다 축하합니다");
                break;
            case 'C':
                System.out.println("C입니다 축하합니다");
                break;
            default:
                System.out.println("탈락입니다.");
        }
        
        

        //◼ 언젠가break를 만날때까지계속내려가면서실행
        //그래서 잘못하면 무한번 실행할 수 있음
        

        

    }
    
}
