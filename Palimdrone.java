import java.util.Scanner;
public class Palimdrone {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number :");
        int a ;
        a=sc.nextInt();

        int rev=0;
 
        while(a!=0){    //input is 515
            rev = 0*10 +rev%10;    //
            a=a/10;
        }

        if(rev==a){
            System.out.println("number is palimdrone number ");
        }
        else{
            System.out.println("Number is not palidrone number ");
        }


    }
}
