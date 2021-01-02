import java.util.Scanner;

/**
 * @author 王协群
 * @date 2020/9/14 10:08
 */
public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int arrLen = scanner.nextInt();
        int[] arr = new int[arrLen];
        int S = scanner.nextInt();
        for (int j = 0; j < arrLen; j++) {
            arr[j] = scanner.nextInt();
        }
        int temp[]= new int[arrLen];
        for (int i = 0; i <arrLen ; i++) {
            temp[i]= S-arr[i];
        }
        long answer = 0;
        for (int i = 0; i <arrLen ; i++) {
            answer+=showAnswer(arr,i+1,arrLen-1,temp[i]);
        }
        System.out.println(answer);




    }
    public static long showAnswer(int[] arr,int left,int right,int nums){
        long answer =0;
        while (left<right){
            if (arr[left]+arr[right]==nums){
                int repeat1 = 0;
                int repeat2= 0;
                if (arr[left]==arr[right]){
                    answer+=(right-left)*(right-left+1)/2;
                    return answer;
                }
                while (arr[left+1+repeat1]==arr[left]){
                    repeat1++;
                }
                while (arr[right-1-repeat2]==arr[right]){
                    repeat2++;
                }
                answer+=(repeat1+1)*(repeat2+1);
                left+=repeat1+1;
                right= right-repeat2-1;

            }else if(arr[left]+arr[right]>=nums){
                right--;
            }else {
                left++;
            }

        }
        return answer;
    }

}