package _15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_리모컨_못푼거 {
    static char[] N;
    static int M;
    static String[] brokenBtn;
    static boolean[] btnCheck;
    
    static int cnt = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        N = br.readLine().toCharArray();
        M = Integer.parseInt(br.readLine());
        brokenBtn = new String[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
			brokenBtn[i] = st.nextToken();
		}
//        brokenBtn = br.readLine().split(" ");
        
        btnCheck = new boolean[10];
        // 고장난 번호 false로 설정 하는 for문 
        for (int i = 0; i < 10; i++) {
            btnCheck[i] = true;
            for (String s : brokenBtn) {
                if(i == Integer.parseInt(s)) {
                    btnCheck[i] = false;
                    break;
                }
            }
        }
        
        // 가장 가까운 채널 숫자를 찾는 for문
        for (int i = 0; i < N.length; i++) {
            sb.append(sol(N[i] - '0'));
            cnt++;
        }
        int initVal = Integer.parseInt(sb.toString());
        System.out.println("initVal : " + initVal);
        int target = Integer.parseInt(new String(N));
        
        int firstAns = Math.abs(initVal - target) + cnt;
        int secondAns = Math.abs(100 - target);
        if(firstAns < secondAns) {
        	System.out.println(firstAns);
        }else {
        	System.out.println(secondAns);
        }
       
        
//        System.out.println(nowChannel);
//        System.out.println(initVal);
//        System.out.println(Math.abs(initVal - target) + cnt);
        
        
    }
    
    static int sol(int num) {
        int up = num;
        int upFlag = 0; 
        int down = num;
        int downFlag = 0;

        while(up < 10) {
            if(btnCheck[up]) break; // 높은 숫자가 사용할 수 있다면.
            up++;
            upFlag++;
        }
        while(down >= 0) {
            if(btnCheck[down]) break; // 낮은 숫자가 사용할 수 있다면.
            down--;
            downFlag++;
        }
        // 번호를 안 눌러도 되면
        if(up == num) return num;
        if(down < 0) return up;
        if(upFlag <= downFlag) {
            return up;
        }
        else {
            return down;
        }
    }
}