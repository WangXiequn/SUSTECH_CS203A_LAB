
import java.util.Scanner;

/**
 * @author 王协群
 * @date 2020/9/14 10:08
 */
public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int arrLen = scanner.nextInt() + 1;
        int[] arr = new int[arrLen];
        int pieces = scanner.nextInt();
        arr[arr.length-1] = scanner.nextInt();
        for (int j = 0; j < arrLen-1; j++) {
            arr[j] = scanner.nextInt();
        }
        int[] arrs = new int[arrLen - 1];
        int sum = arr[arr.length - 1];
        int pointer = 0;
        for (int i = 0; i < arrs.length; i++) {
            arrs[i] = arr[i + 1] - arr[i];

        }
        while (sum -1!= pointer) {
            int middle = (sum + pointer) / 2;
            int temp = thePieces(arrs, middle, pieces);

            switch (temp) {
                case 0:
                    pointer = middle;
                    break;
                case 1:
                    sum = middle;
                    break;
                default:break;

            }


        }
        System.out.println(sum);


    }

    public static int thePieces(int[] arr, int middle, int pieces) {
        int counter = 1;
        int length = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > middle) {
                return 0;
            } else {
                length += arr[i];
                if (length > middle) {
                    counter += 1;
                    length = arr[i];
                }
            }

        }
        if (counter > pieces) {
            return 0;
        } else {
            return 1;}
    }
}