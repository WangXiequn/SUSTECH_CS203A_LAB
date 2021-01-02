import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        for (int i = 0; i <times ; i++) {
            int n = scanner.nextInt();
            if (n%2==1){
                n = n -1;
            }
            System.out.println(f(n));
        }
    }
    public static long f(int n){
        if (n==0||n==1||n==2||n==3){
            return 1;
        }

        if (n%4==0){
            return f(n/2)*2+f(n/2-2);
        }else {
            return f(n/2+1)+f(n/2-1)*2;
        }
    }
}