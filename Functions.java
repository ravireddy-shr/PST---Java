import java.util.Scanner;
public class Functions {
    Scanner sc = new Scanner(System.in);
    void case1() {
        int a, b, sum;
        System.out.println("Case 1");
        System.out.print("Enter two numbers: ");
        a = sc.nextInt();
        b = sc.nextInt();
        sum = a + b;
        System.out.println("Sum = " + sum); 
    }
    void case2(int a, int b) {
        int sum = a + b;
        System.out.println("Case 2");
        System.out.println("Sum = " + sum);
    }
    int case3() {
        int a, b;
        System.out.println("Case 3");
        System.out.print("Enter two numbers: ");
        a = sc.nextInt();
        b = sc.nextInt();
        return a + b;
    }
    int case4(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        Functions obj = new Functions();

        obj.case1();

        obj.case2(10, 20);

        int result1 = obj.case3();
        System.out.println("Sum = " + result1);

        int result2 = obj.case4(30, 40);
        System.out.println("Case 4");
        System.out.println("Sum = " + result2);
    }
    
}