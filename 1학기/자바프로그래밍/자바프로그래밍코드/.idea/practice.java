import java.util.InputMismatchException;
import java.util.Scanner;
public class practice{
    // int radius;
    // String name;

    // public practice(){
    //     radius = 1; name = "";
    // }

    // public practice(int r, String n){
    //     radius = r; name = n;
    // }

    
    // public double getArea(){
    //     return 3.14*radius*radius;
    // }

    
class Point{
    private int x, y;
    public void set(int x, int y){
        this.x = x;
        this.y = y;

    }
    public void showPoint(){
        System.out.println("("+x+","+y+")");
    }
}

class ColorPoint extends Point{
    private String color;
    public void setColor(String color){
        this.color = color;
    }
    public void showColorPoint(){
        System.out.print(color);
        showPoint();
    }
}



    public static void main(String[] args){
        // int i, sum = 0;

        // for(i = 0; i <= 10; i++){
        //     sum += i;
        //     System.out.print(i);

        //     if(i <= 9){
        //         System.out.print("+");
        //     }
        //     else{
        //         System.out.print("=");
        //         System.out.print(sum);

            // }
        // }

        // for(int i = 1; i< 10; i++){
        //     for(int j = 1; j < 10; j ++){
        //         System.out.print(i + "*" + j + "=" + i*j);
        //         System.out.print("\t");
        //     }
        //     System.out.println();
        // }

        // Scanner scanner =  new Scanner(System.in);

        // int intArray[];
        // intArray = new int[5];

        // int min = 0;
        // System.out.println("양수 5개를 입력하세요");

        // for(int i = 0; i < 5; i++){
        //     intArray[i] = scanner.nextInt();
        //     if(intArray[i] > min) {
        //         min = intArray[i]; 
        //     }
        //     if(intArray[i] < min){
        //         min = intArray[i];
        //     }
        
        // }
        // System.out.print("가장 작은 수는 " + min + "입니다");

        // int n[] = {1,2,3,4,5};
        // int sum = 0;

        // for(int k : n){
        //     System.out.print(k + "");
        //     sum += k;
        // }
        // System.out.println("합은 " + sum)

        // String f[] = {"사과", "배", "바나나", "체리", "딸기", "포도"};
        // for(String s : f){
        //     System.out.print(s + "");
        // }

        // double score [][] = {
        //     {3.3, 3.4},
        //     {3.5, 3.6},
        //     {3.7, 4.0},
        //     {4.1, 4.2}
        // };

        // double sum = 0;

        // for(int year = 0; year < score.length; year++){
        //     for(int term = 0; term < score[year].length; term++){
        //         sum += score[year][term];
        //     }

        // int n = score.length;
        // int m = score[0].length;

        // System.out.println("전체학년 평균은 " + sum/(n*m));
        // }

    //     Scanner scanner = new Scanner(System.in);
    //     System.out.println("정수 3개를 입력하세요 ");
    //     int sum = 0, n = 0;
    //     for(int i = 0; i < 3; i++){
    //         System.out.print(i + ">>");
    //         try{
    //             n = scanner.nextInt();
    //         }
    //         catch(InputMismatchException e){
    //             String ex = scanner.nextLine();

    //             System.out.println("정수가 아닙니다 다시 입력하세요");
    //             i --;
    //             continue;
    //         }
    //         sum += n;
    //     }
    //     System.out.println("합은 " + sum);
    //     scanner.close();
    // }

    // practice pizza = new practice(10, "자바피자");
    // double area = pizza.getArea();
    // System.out.println(pizza.name + "의 면적은 : " + area);
    
    // practice donut = new practice(); // Circle 객체 생성, 반지름 1
    // donut.name = "도넛피자";
    // area = donut.getArea();
    // System.out.println(donut.name + "의 면적은 " + area);

    practice b = new practice();
    Point a = b. new Point();

    a.set(12,425);
    a.showPoint();

    

}
}