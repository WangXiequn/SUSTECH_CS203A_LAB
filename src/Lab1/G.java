import java.util.Scanner;
/**
 * @author 王协群
 * @date 2020/9/8 10:17
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long times = scanner.nextLong();
        for (long i = 0; i <times ; i++) {
            long n = scanner.nextLong();
            long s = scanner.nextLong();
            if (getSumOfDigits(getDigits(n))<=s){
                System.out.println(0);
                continue;
            }
            long temp = s;
            int j;
            long[] array = getDigits(n);
            for ( j = 0; j <20 ; j++) {
                if (array[j]!=0){
                    break;
                }

            }
            int k;
            for ( k = j; k <array.length ; k++) {
                if (array[k]>=temp){
                    break;
                }else {
                    temp-=array[k];
                }
            }
            array[k-1]+=1;

            for ( k=k; k <array.length ; k++) {

                array[k]=0;
            }
            long answer=0;
            for (int l = 0; l <19 ; l++) {
//                answer+=(long)array[l]*Math.pow(10,18-l);
                for (int m = 0; m <18-l ; m++) {
                    array[l]=array[l]*10;
                }
                answer+=array[l];
            }
            answer=answer-n;

            System.out.println(answer);

        }




    }
    public static long getSumOfDigits(long[] array){
        long answer =0;
        for (long i:
                array) {
            answer+=i;
        }
        return answer;
    }
    public static long[] getDigits(long num){
        long[] answer = new long[19];
        for (int i = 0; i <=18 ; i++) {
            answer[18-i]  = num%10;
            num = num/10;

            if (num==0){
                break;
            }



        }
        return answer;

    }
    public static long getNum(long[]array){
        long answer =0;
        for (int i = 0; i <array.length ; i++) {
            answer+=array[array.length-i-1]*Math.pow(10,i);
        }
        return answer;
    }

}

