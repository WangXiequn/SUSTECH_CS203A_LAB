import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            long n = in.nextInt();
            long k = in.nextInt();
            long s = 1;
            long h = -1;

            while (n-s>0){
                n= n-s;
                s=k*s;
                h++;
            }

            long x ;
            if(n%k==0){
                x = n/k;
            }else{
                x = n/k +1;
            }
            long a = pow(k,h) - x +n;
            if (h==0){
                a = 1 - x + n;
            }

            System.out.println(a);
        }
    }
    public static long pow(long a, long b) {

        long result = 1;
        long base = a;

        while(b > 0) {
            if((b & 1) != 0) {
                result *= base;
            }
            base *= base;
            b >>= 1;
        }

        return result;

    }
}
