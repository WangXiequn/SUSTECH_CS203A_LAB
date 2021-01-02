import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        int[] answer = new int[times];
        for (int i = 0; i <times ; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int n = scanner.nextInt();
            int ab =a^b;
            switch (n%3){
                case 0:answer[i]= a;
                    break;
                case 1:answer[i]=b;
                    break;
                case 2: answer[i]=ab;
                    break;
                default:;
            }

        }
        for (int i = 0; i <times ; i++) {
            System.out.println(answer[i]);
        }
    }
}
