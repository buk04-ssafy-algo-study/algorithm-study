package Week50;

import java.io.*;
import java.util.*;

public class 백준_5052_전화번호목록 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < T; t++) {
            
            int N = Integer.parseInt(br.readLine());
            String[] telNum = new String[N];
            
            for (int i = 0; i < N; i++) {
                telNum[i] = br.readLine();
            }
            
            Arrays.sort(telNum);  // 전화번호 목록을 사전순으로 정렬
            
            boolean flag = true;
            
            for (int i = 0; i < N - 1; i++) {
                if (telNum[i + 1].startsWith(telNum[i])) {  // 인접한 두 번호 비교
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        
        System.out.println(sb);
    }
}