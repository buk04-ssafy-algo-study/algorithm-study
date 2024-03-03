package _15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_리모컨OK {
    static int N;
    static int M;
    static String[] brokenBtn;
    static boolean[] btnCheck;
    
    static int cnt = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        btnCheck = new boolean[10];
        if(M != 0) st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < M; i++) {
        	int n = Integer.parseInt(st.nextToken());
			btnCheck[n] = true;
		}
        int ans = Math.abs(N - 100);
        for (int i = 0; i <= 999999; i++) {
        	String str = String.valueOf(i);
        	int len = str.length();
        	
        	boolean isBreak = false;
        	for (int j = 0; j < len; j++) {
				if(btnCheck[str.charAt(j) - '0']) {
					isBreak = true;
					break;
				}
			}
        	if(!isBreak) {
        		int min = Math.abs(N- i) + len;
        		ans = Math.min(min, ans);
        	}
		}
        System.out.println(ans);
    }
}