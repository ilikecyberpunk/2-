import java.util.Scanner;

public class 시간분초변환 {
    public void main(String[] args){
        Scanner s = new Scanner(System.in);

        System.out.print("변환할 초를 입력하세요 : ");
        int cho = s.nextInt();

        int sec = cho%60;
        int min = (cho/60)%60;
        int hr = (cho/60)/60;

        System.out.println(hr);
        System.out.println(min);
        System.out.println(sec);


    }
}
