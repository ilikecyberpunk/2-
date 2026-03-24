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
            if (shit % 2 != 0) {
             shit++;
             continue;   // 조건식으로 돌아감
         }
            System.out.print(shit + ",");
            if(shit == 22) break;    //반복문 종료
            shit++;

        } while (shit < 30);

        System.out.println();
        //배열
        int intarray[];
        int[] intArray;

        intarray = new int[5];
        int ii[] = new int[5]; //둘다 선언과 동시에 배열 생성함

        int iii[] = {1,2,3,4,5};
        //레퍼런스 치환으로 두 레퍼런스가 하나의 배열 공유
        int myarray[] = iii;
        System.out.println(myarray);
        System.out.println(myarray[0]);

        //배열의 크기 = length
        System.out.println("배열의 크기 : "+myarray.length);
        
        //for each문
        int h[] = {1,2,3,4,5};
        int kum = 0;
        for(int k : h){
            kum += k;
        }
        System.out.println(kum);
        
        //2차원 배열
        int dimention2[][] = new int[2][5];
        int dimention2_1[][] = {
            {1,2,3},
            {4,5,6}
        };

        //예외 처리
        try{
            int a;
        }
        catch(Exception e){
            System.out.println("오류입니다");
        }
        finally{
            System.out.println("try문에서 오류 나든 말든 얘는 실행된다");
        }
    }
    
        
}
