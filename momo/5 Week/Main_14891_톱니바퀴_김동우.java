import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_14891_톱니바퀴_김동우 {
    static int[][] wheel;
    static int[][] init;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        wheel = new int[5][9];
        init = new int[5][9];
        
        for (int i = 1; i <= 4; i++) {
            String str = br.readLine();
            for (int j = 1; j <= 8; j++) {
                wheel[i][j] = str.charAt(j-1) - '0';
            }
        }
        for (int i = 0; i < wheel.length; i++) {
            System.arraycopy(wheel[i], 0, init[i], 0, wheel[i].length);
        }
                
        int K = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < K; i++) {
            String[] split = br.readLine().split(" ");
            int number = Integer.parseInt(split[0]); //톱니바퀴의 번호
            int dir = Integer.parseInt(split[1]); // 1은 시계방향, -1은 반시계
            
            rotate(number, dir);
            left(number, dir * -1);
            right(number, dir * -1);
            for (int j = 0; j < wheel.length; j++) {
                System.arraycopy(wheel[j], 0, init[j], 0, wheel[j].length);
            }
        }
        int ans = 0;
        if(wheel[1][1] == 1) ans += 1;
        if(wheel[2][1] == 1) ans += 2;
        if(wheel[3][1] == 1) ans += 4;
        if(wheel[4][1] == 1) ans += 8;
        System.out.println(ans);
    }
    static void rotate(int number, int dir) {
        if(dir == -1 ) {
            int next = wheel[number][1];
            for (int i = 1; i < 8; i++) {
                wheel[number][i] = wheel[number][i+1];
            }
            wheel[number][8] = next;
        }else {
            int next = wheel[number][8];
            for (int i = 8; i > 1; i--) {
                wheel[number][i] = wheel[number][i-1];
            }
            wheel[number][1] = next;
        }
    }
    static void left(int number, int dir) {
        if(number-1 < 1) return;
        int left = number-1;
        if(init[number][7] != init[left][3]) {
            rotate(left, dir);
        }
        left(number-1, dir*-1);
    }
    static void right(int number, int dir) {
        if(number+1 > 4) return;
        int right  = number+1;
        if(wheel[number][3] != wheel[right][7]) {
            rotate(right, dir);
        }
        right(number+1, dir*-1);
    }
}