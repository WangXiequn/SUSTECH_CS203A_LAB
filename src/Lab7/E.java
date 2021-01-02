import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in  = new Scanner(System.in);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            int l = in.nextInt();
            long[] arr = new long[l];
            long[] temp = new long[l];
            long sum = 0;
            for (int j = 0; j < l; j++) {
                arr[j] = in.nextInt();
                sum+=arr[j];
            }
            mergeSort(arr,0,l-1,temp);
            int left = 0 ;
            int right = l-1;
            while (!(left==right||left-1==right)){
                long tep = arr[right];
                arr[right] = arr[left];
                arr[left] = tep;
                left++;
                right--;
            }
            long answer = 0;
            for (int j = l-1; j > 0 ; j--) {
                long su = arr[j]+arr[j-1];
                answer+= su;
                arr[j]=0;
                arr[j-1] = su;
                int s = j-1;
                while (s>0&&arr[s]>arr[s-1]){
                    long tep = arr[s-1];
                    arr[s-1] = arr[s];
                    arr[s] = tep;
                    s--;
                }

            }

            System.out.println(answer);
        }
    }

    public static void mergeSort(long[] arr,int low,int high,long[] tmp) {
        if (low >= high) {
            return;
        }
        mergeSort(arr,low,(high+low)/2,tmp);
        mergeSort(arr,(high+low)/2+1,high,tmp);
        merge(arr,low,high,tmp);

    }
    public static void merge(long[] arr,int low,int high,long[]tep){
        int mid = (low+high)/2;
        int leftArr = low;
        int rightArr = mid+1;

        for (int i = 0; i <high-low+1 ; i++) {
            if (leftArr>mid){
                tep[low+i]=arr[rightArr];
                rightArr++;
                continue;
            }
            if (rightArr>high){
                tep[low+i]=arr[leftArr];
                leftArr++;
                continue;
            }
            if (arr[rightArr]<arr[leftArr]){
                tep[low+i]=arr[rightArr];
                rightArr++;
                continue;
            }
            if (arr[rightArr]>=arr[leftArr]){
                tep[low+i]=arr[leftArr];
                leftArr++;
            }
        }
        for (int i = 0; i <high-low+1 ; i++) {
            arr[low+i]=tep[low+i];
        }

    }
}