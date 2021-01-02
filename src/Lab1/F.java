import java.util.Scanner;

/**
 * @author 王协群
 * @date 2020/9/8 10:17
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();

        for (int j = 0; j < times; j++) {
            int a = scanner.nextInt();
            int[] array = new int[a];
            for (int i = 0; i < a; i++) {
                array[i] = scanner.nextInt();
            }
            int temp=findXOR(array);
            if (findXOR(array) == 0) {
                System.out.println(0);
                continue;
            }
            int answer = 0;
            for (int k = 0; k < array.length; k++) {
                int t =temp^array[k];
                if (array[k]>t){
                    answer+=1;

                }

            }
            System.out.println(answer);



        }

    }


    public static int findXOR(int[] array) {
        int answer = array[0];
        for (int i = 0; i < array.length - 1; i++) {
            answer = answer^array[i + 1];
        }
        return answer;


    }



}
