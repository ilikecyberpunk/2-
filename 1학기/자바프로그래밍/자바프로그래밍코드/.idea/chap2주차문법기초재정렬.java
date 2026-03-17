import java.math.BigDecimal;
import java.util.Scanner;

public class chap2주차문법기초재정렬{
    public static void main(String[] args){

        //LLM 쓰는데 왜 코딩 배워야함 : 문법을 알아야 ai가 만든 코드의 오류를 식별할 수 있음

        //참조 타입의 이해 : 실제 데이터를 저장하는 대신, 객체가 저장된 메모리 주소를 변수에 저장
        // 참조 타입 종류 : String, class, interface, array
        // heap영역에 실제 객체 데이터 저장


        
        //스코프 : 변수나 메서드에 접근할 수 있는 범위
        //어떤 코드 블록 안에서 선언된 식별자가 어디까지 유효한지를 결정하는 규칙
        // 종류 : 클래스 스코프, 메서드 스코프, 블록 스코프

        //final 키워드 : 변수를 상수로 표현 -> 값 변경 불가능
        final int num = 10;
        

        //연산자 우선순위 실수 빈발 케이스 1 : 산술 vs 비교
        int a = 10;
        int b = 32;
        int c = 33;
        boolean result = a + b > c;  // 산술이 먼저 된 후 비교한다 -> 의도가 다르면 ()씌워

        // 케이스2: 논리연산자(&& vs ||)
        boolean aa = true;
        boolean bb = false;
        boolean cc = true;
        boolean rresult = aa || bb && cc;  // &&(and) 연산이 ||(or) 연산보다 우선이라 bb &&cc 하고 aa||함

        //케이스 3 : 증감 연산자 
        int i = 5;
        System.out.println(++i * 2); // i를 먼저 증가시킨 후 연산
        i = 5;
        System.out.println(i++*2); // 연산 진행이 끝난 후 i증감

        //자동 형변환
        int num1 = 10;
        long bigNum = num1; // 자동 형변환 inb -> long
        double decimal = bigNum; // 자동 형변환 long -> double

        //강제 형변환 : 큰 타입을 작은 타입으로 강제 변환시 데이터 손실 ㄱㄴ
        double a1 = 23.324;
        System.out.println((int)a1);

        //부동 소수점의 정밀도 문제
        double result1 = 0.1 + 0.2;
        System.out.println(result1); //0.3000000...4로 나옴
        //BigDecimal 활용
        BigDecimal result2 = new BigDecimal("0.1");
        BigDecimal result3 = new BigDecimal("0.2");
        BigDecimal sum = result2.add(result3);
        System.out.println(sum);

    }
}