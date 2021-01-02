import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            long n = in.nextLong();
            long k = 2;
            long s = 1;
            long h = -1;

            while (n-s>0){
                n= n-s;
                s=k*s;
                h++;
            }
            System.out.println(h+2);
        }
    }
}
