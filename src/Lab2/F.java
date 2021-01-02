import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long Xr = scanner.nextLong();
        long Yr = scanner.nextLong();
        long Xc = scanner.nextLong();
        long Yc = scanner.nextLong();
        long time = scanner.nextLong();
        long x = Xr - Xc;
        long y = Yr - Yc;
        char[] periodicallyRun = scanner.next().toCharArray();
        long right = 0;
        long up = 0;
        for (int i = 0; i < periodicallyRun.length; i++) {
            if (periodicallyRun[i] == 'U') {
                up++;
            }

            if (periodicallyRun[i] == 'D') {
                up--;
            }
            if (periodicallyRun[i] == 'R') {
                right++;
            }
            if (periodicallyRun[i] == 'L') {
                right--;
            }
        }
        if (x < 0) {
            right = -right;
            x=-x;
            for (int i = 0; i < periodicallyRun.length; i++) {
                if (periodicallyRun[i] == 'R') {
                    periodicallyRun[i] = 'L';
                    continue;
                }
                if (periodicallyRun[i] == 'L') {
                    periodicallyRun[i] = 'R';
                    continue;
                }
            }
        }
        if (y < 0) {
            up = -up;
            y =-y;
            for (int i = 0; i < periodicallyRun.length; i++) {
                if (periodicallyRun[i] == 'U') {
                    periodicallyRun[i] = 'D';
                    continue;
                }

                if (periodicallyRun[i] == 'D') {
                    periodicallyRun[i] = 'U';
                    continue;
                }
            }
        }

        if (Math.abs(right) + Math.abs(up) == periodicallyRun.length) {
            if ((right > 0 && up >= 0) || (right >= 0 && up > 0)) {
                System.out.println(-1);
                return;
            }
        }
        boolean flag = false;
        long t = 0;
        if (Math.abs(right) + Math.abs(up) < periodicallyRun.length) {
            flag = true;
            long temp = periodicallyRun.length - Math.abs(right) - Math.abs(up);
            t = (x + y) / temp + 2;
        } else {
            long temp1 = 0;

            if (right >= 0 && up < 0) {
                temp1 = 4;
            }
            if (right < 0 && up >= 0) {
                temp1 = 2;
            }
            if (right < 0 && up < 0) {
                temp1 = 3;
            }

            switch ((int) temp1) {
                case 2:
                    t = x / Math.abs(right) ;
                    t+=2;
                    if (periodicallyRun.length * t >= Math.abs(x + right * t) + Math.abs(y + up * t)) {
                        flag = true;
                    }
                    break;
                case 4:
                    t = y / Math.abs(up);
                    t+=2;
                    if (periodicallyRun.length * t >= Math.abs(x + right * t) + Math.abs(y + up * t)) {
                        flag = true;
                    }
                    break;
                case 3:
                    t = Math.max((y / Math.abs(up)), (x / Math.abs(right)));
                    t+=2;
                    if (periodicallyRun.length * t >= Math.abs(x + right * t) + Math.abs(y + up * t)) {
                        flag = true;
                    }
                    break;
                default:
                    break;
            }}
        if (!flag) {
            System.out.println(-1);
            return;
        }


        long polonger = 0;
        long middle;
        while (true) {
            middle = (polonger + t) / 2;
            long s = showWhichStep(periodicallyRun, middle, x, y, right, up);
            if (s == -1) {
                polonger = middle;
            }
            else if (s == 0) {
                t = middle;
            } else {

                System.out.println(middle*periodicallyRun.length+showWhichStep(periodicallyRun, middle, x, y, right, up));
                return;
            }
        }


    }



    public static long showWhichStep(char[] chars, long times, long x, long y, long right, long up) {
        x = x + times * right;
        y = y + times * up;
        if (Math.abs(x + right) + Math.abs(y + up) > chars.length * (times + 1)) {
            return -1;
        } else if (Math.abs(x) + Math.abs(y) <= chars.length * (times)) {
            return 0;
        } else {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == 'U') {
                    y++;
                }

                if (chars[i] == 'D') {
                    y--;
                }
                if (chars[i] == 'R') {
                    x++;
                }
                if (chars[i] == 'L') {
                    x--;
                }
                if (Math.abs(x) + Math.abs(y) <= chars.length * (times) + i + 1) {
                    return i + 1;

                }
            }
        }
        return 0;
    }


}