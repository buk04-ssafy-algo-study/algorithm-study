// https://www.acmicpc.net/problem/22251
import java.io.*;
import java.util.*;

public class Main {

    static int[][] nums = {
        {1, 1, 1, 1, 1, 1, 0}, // 0
        {0, 1, 1, 0, 0, 0, 0}, // 1
        {1, 1, 0, 1, 1, 0, 1}, // 2
        {1, 1, 1, 1, 0, 0, 1}, // 3
        {0, 1, 1, 0, 0, 1, 1}, // 4
        {1, 0, 1, 1, 0, 1, 1}, // 5
        {1, 0, 1, 1, 1, 1, 1}, // 6
        {1, 1, 1, 0, 0, 0, 0}, // 7
        {1, 1, 1, 1, 1, 1, 1}, // 8
        {1, 1, 1, 1, 0, 1, 1}  // 9
    };
    static StringBuilder sb = new StringBuilder();
    static int K, P;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		    StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        
        String x = itos(X);
        
        int answer = 0;
        for(int floor=1;floor<=N;floor++) {
            if(floor==X) continue;
            if(cntDiff(x, itos(floor))) answer++;
        }

		bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    static String itos(int n) { // 정수 n을 K자리 문자열로 변형
        sb.setLength(0);
        for(int i=0;i<K-String.valueOf(n).length();i++) {
            sb.append("0");
        }
        sb.append(n);
        return sb.toString();
    }

    static boolean cntDiff(String a, String b) { // a에서 b로 변경 시 반전 개수가 1 이상 P이하면 true 리턴 

        int result = 0;
        for(int i=0;i<K;i++) {
            char charA = a.charAt(i);
            char charB = b.charAt(i);
            if(charA==charB) continue;
            for(int j=0;j<7;j++) {
                if(nums[charA-'0'][j] != nums[charB-'0'][j]) result++;
            }
        }
        return (0<result && result<=P);
    }

}
