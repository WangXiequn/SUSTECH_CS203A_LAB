import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author 王协群
 * @date 2020/9/8 10:17
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        for (int i = 0; i <times ; i++) {
            int c = scanner.nextInt();
            int r = scanner.nextInt();
            if (c*r==1){
                System.out.println("Bob");
            }else {
                System.out.println("Alice");
            }


        }


    }

}



