import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author 王协群
 * @date 2020/9/18 17:17
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        for (int j = 0;  j<times ; j++) {
            int arrLen = scanner.nextInt();
            int K = scanner.nextInt();
            long M = scanner.nextLong();
            int[] A = new int[arrLen];
            for (int i = 0; i <arrLen ; i++) {
                A[i]= scanner.nextInt();
            }
            int[] sortedArr = new int[arrLen];
            for (int i = 0; i <arrLen ; i++) {
                sortedArr[i]= A[i];
            }
            quickSort(sortedArr,0,arrLen-1);
            int left =0;
            int right = arrLen-1;
            int middle=0;

            while (true){
                ArrayList<Integer>arrayList = new ArrayList();
                middle =(left+right)/2;
                int a = sortedArr[middle];
                for (int i = 0; i <arrLen ; i++) {
                    if (A[i]>a){
                        arrayList.add(i);
                    }}
                long hah=judge(arrayList,K,arrLen);
                if (hah==M){
                    System.out.println(a);
                    break;
                }
                if (left==right-1){
                    if (hah>M){
                        System.out.println(sortedArr[right]);
                        break;
                    }else {
                        System.out.println(sortedArr[left]);
                        break;
                    }
                }

                if (hah<M){

                    right=middle;

                }if (hah>M){

                    left=middle;

                }



            }



        }
    }
    public static long judge(ArrayList<Integer> arrayList,int k,int arrLen){
        if (arrayList.size()<k){
            return 1;
        }
        long count =1;
        for (int i = 0; i <arrayList.size()+1-k ; i++) {
            if (i==0){
                count +=(arrayList.get(i)+1)*(arrLen-arrayList.get(i+k-1));
            }else {
                count +=(arrayList.get(i)-arrayList.get(i-1))*(arrLen-arrayList.get(i+k-1));
            }

        }
        return count;
    }
    public static void quickSort(int[]arr,int left,int right){
        if (left>right){
            return;
        }
        int l =left;
        int r = right;
        int b = arr[left];
        while (right!=left){
            while (arr[right]>=b&&right>left){
                right--;
            }
            while (arr[left]<=b&&right>left){
                left++;
            }
            if (right!=left){
                int tep = arr[right];
                arr[right]=arr[left];
                arr[left]=tep;
            }
        }
        arr[l]=arr[right];
        arr[right]=b;

        quickSort(arr,l,right-1);
        quickSort(arr,right+1,r);
    }



}