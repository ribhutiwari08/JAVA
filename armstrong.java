import java.util.Scanner;
public class armstrong {
    public static void main(String[] args) {
        int a , b , sum=0;

        System.out.println( "Enter the number :");
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();

        while(a>0){
            b=a%10;
            sum = sum +b*b*b;
            a =a/10;

        }
        System.out.println( sum);
    }
}
