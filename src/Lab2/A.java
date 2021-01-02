import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author 王协群
 * @date 2020/9/14 10:08
 */
public class Main {
    public static void main(String[] args) {

        long mod = 1000000000+7;

        Scanner scanner = new Scanner(System.in);
        int times=scanner.nextInt();
        for (int i = 0; i <times ; i++) {

            long nums = scanner.nextLong();
            long temp = (nums)*(nums+1)/2;
            temp= temp%mod;
            long answer = temp*temp;
            answer= answer%mod;
            System.out.println(answer);

        }
    }
}