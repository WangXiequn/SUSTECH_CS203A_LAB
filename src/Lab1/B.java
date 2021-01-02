import java.util.Scanner;

/**
 * @author 王协群
 * @date 2020/9/8 10:17
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        long[] answer = new long[times];
        for (int i = 0; i <times ; i++) {
            long num = scanner.nextLong();
            answer[i]= showAnswer(num);
        }
        for (int i = 0; i <times ; i++) {
            System.out.println(answer[i]);
        }
    }
    public static long showAnswer(long n){
        if (n==1){
            return 2;
        }
        return (long) (((3*showAnswer(n-1))+2)%1000000007);
    }

}
