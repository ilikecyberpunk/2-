import java.util.Scanner;

public class chap3장{
    public static void main(String[] args){

        //for 문으로 1에서 10까지의 수 더하기
        int i, sum=0;
        for(i=1; i<=10; i++) { // 1~10까지 반복
            sum += i;
            System.out.print(i); // 더하는 수 출력
        if(i<=9) // 1~9까지는 '+' 출력
            System.out.print("+");
        else{ // i가 10인 경우
            System.out.print("="); // '=' 출력하고
            System.out.print(sum); // 덧셈 결과 출력
            }
        }
        System.out.println();
        //while문
        //while문을 이용하여 정수를 여러 개 입력 받고 평균을 출력하라. 0이 입력되면 입력을 종료한다.
        int num = 0;
        while(num < 10){
            System.out.print(num+',');
            num++;
        }

        //do while 문
        //do구문은 조건에 관계없이 무조건 1번 실행함
        int shit = 0;
        do {
            if(i%2 != 0)continue;
            System.out.print(shit+","); 
            shit++;
        }while(shit < 30);
        
    }
}
