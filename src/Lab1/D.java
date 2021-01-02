import java.util.Scanner;
/**
 * @author 王协群
 * @date 2020/9/8 10:17
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long times = scanner.nextLong();
        for (int i = 0; i <times ; i++) {
            int length = scanner.nextInt();
            int width = scanner.nextInt();
            int height = scanner.nextInt();
            char[][] answer = new char[(width+height)*2+1][(width+length)*2+1];
            for (int j = 0; j <answer.length ; j++) {
                for (int k = 0; k <answer[0].length ; k++) {
                    answer[j][k] = '.';
                }
            }//初始化
            for (int j = 0; j <width*2 ; j++) {
                if (j%2==0){
                    for (int k = width*2-j; k <(length+width)*2-j+1 ; k++) {
                        if (k%2==0){
                            answer[j][k]='+';
                        }else {
                            answer[j][k]='-';
                        }
                    }
                }
                else {
                    for (int k = width*2-j; k <(length+width)*2-j+1 ; k++) {
                        if (k%2==1){
                            answer[j][k]='/';

                        }
                    }
                }
            }
            for (int j = width*2; j <answer.length ; j++) {
                if (j%2==0){
                    for (int k = 0; k <length*2+1 ; k++) {
                        if (k%2==0){
                            answer[j][k]='+';
                        }else {
                            answer[j][k]='-';
                        }
                    }
                }
                else {
                    for (int k = 0; k <length*2+1; k++) {
                        if (k%2==0){
                            answer[j][k]='|';

                        }
                    }
                }
            }
            for (int j = 0; j <width*2 ; j++) {
                if (j%2==0){
                    for (int k = j; k <j+2*height+1 ; k++) {
                        if (k%2==0){
                            answer[k][answer[0].length-1-j]='+';
                        }else {
                            answer[k][answer[0].length-j-1]='|';
                        }
                    }

                }
                else {
                    for (int k = j; k <j+2*height+1 ; k++) {
                        if (k%2==1){
                            answer[k][answer[0].length-j-1]='/';
                        }
                    }
                }
            }
            for (int j = 0; j <answer.length ; j++) {
                System.out.println(new String(answer[j]));
            }

        }




    }


}