import java.util.Scanner;

/**
 * @author 王协群
 * @date 2020/9/14 10:08
 */
public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int arrLen = scanner.nextInt();
        int [] arr =new int[arrLen];
        int times=scanner.nextInt();
        for (int j = 0; j <arrLen ; j++) {
            arr[j]=scanner.nextInt();
        }
        for (int i = 0; i <times ; i++) {
            int en = scanner.nextInt();
            int answer =showAnswer(arr,en,0,arr.length);
            if (answer==0){
                System.out.println("Accept");

            }
            else {
                System.out.println(answer);
            }
        }
    }
    public static int showAnswer(int[]sortedArr,int nums,int left,int right){
        if (nums>=sortedArr[sortedArr.length-1]){
            return nums-sortedArr[sortedArr.length-1];
        }
        if (left==right-1){
            return nums-sortedArr[left];
        }else {
            int pointer = (right+left)/2;
            if (nums==sortedArr[pointer]||nums==sortedArr[pointer-1]){
                return 0;
            }
            if (nums>sortedArr[pointer]){
                left=pointer;
                return showAnswer(sortedArr,nums,left,right);
            }
            if (nums<sortedArr[pointer-1]){
                right=pointer-1;
                return   showAnswer(sortedArr,nums,left,right);
            }
            return nums-sortedArr[pointer-1];


        }
    }


}