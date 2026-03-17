import java.util.Scanner;
import java.io.File;

public class 과제2 {
    public static void main(String[] arg) {
        Scanner sc1 = new Scanner(new File("Salaries-1.csv"));
        Scanner sc2 = null;

        int cnt = 0;
        int totalSum = 0;

        if (sc1.hasNextLine()) {
            sc1.nextLine();
        }

        while (sc1.hasNextLine()) {
            String line = sc1.nextLine();
            sc2 = new Scanner(line).useDelimiter(",");

            int salary = 0;
            int colIndex = 0;
            while (sc2.hasNext()) {
                String token = sc2.next();
                if (colIndex == 6) { 
                    salary = Integer.parseInt(token.trim());
                }
                colIndex++;
            }

            System.out.println(line + " salary=" + salary);
            totalSum += salary;
            cnt++;
            sc2.close();
        }

        System.out.println("Line : " + cnt + ", Total Salary : " + totalSum);
        sc1.close();
    }
}