import java.util.Scanner;
public class practice{
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

        Scanner scanner =  new Scanner(System.in);

        int intArray[];
        intArray = new int[5];

        int min = 0;
        System.out.println("양수 5개를 입력하세요");

        for(int i = 0; i < 5; i++){
            intArray[i] = scanner.nextInt();
            if(intArray[i] > min) {
                min = intArray[i]; 
            }
            if(intArray[i] < min){
                min = intArray[i];
            }
        
        }
        System.out.print("가장 작은 수는 " + min + "입니다");
    }
}