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

        for (int j = 0; j <times ; j++) {
            int a = scanner.nextInt();
            int[] array = new int[a];
            for (int i = 0; i <a ; i++) {
                array[i]=scanner.nextInt();

            }
            System.out.println(findMax(array));
        }

    }
    public static int findMax(int[] array) {

        if (array.length==2){
            return array[0]-array[1];
        }
        if (array.length==3){
            return compare(array[0]-array[1],array[0]-array[2],array[1]-array[2]);
        }
        int[]left = new int[array.length/2];
        int[] right = new int[array.length-left.length];

        for (int i = 0; i <left.length ; i++) {
            left[i] = array[i];
        }
        for (int i = left.length; i < array.length; i++) {
            right[i-left.length] = array[i];
        }

        return compare(findMax(left),findMax(right),findMaxNum(left)-findMinNum(right));
    }
    public static int findMaxNum(int[] array) {
        int max = array[0];
        for (int i = 0; i <array.length ; i++) {
            if (array[i]>max) {
                max=array[i];
            }
        }
        return max;
    }
    public static int findMinNum(int[] array) {
        int min = array[0];
        for (int i = 0; i <array.length ; i++) {
            if (array[i]<min) {
                min=array[i];
            }
        }
        return min;
    }
    public static int compare(int a , int b , int c){
        if (a>=b&&a>=c){
            return a;
        }
        if (a<=b&&b>=c){
            return b;
        }
        return c;
    }


}