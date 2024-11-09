import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15961 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = (new BufferedReader(new InputStreamReader(System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] allSushi = new int[N]; // 벨트 위 초밥
        int[] cntSushi = new int[d+1]; // 초밥별 갯수

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            allSushi[i] = Integer.parseInt(st.nextToken());
            if (i < k) // 초기 k개 초밥 갯수 세기
                cntSushi[allSushi[i]]++;
        }

        cntSushi[c]++; // 서비스 초밥

        int cnt = 0;
        int max = 0;
        for(int i : cntSushi) // 초기 k개 초밥 종류
            if(i>0) cnt++;

        max = cnt;
        for(int i=k;i<N+k;i++) { // 회전 초밥이므로 N+k까지
            if(--cntSushi[allSushi[i-k]] == 0) cnt--; // 맨 앞 초밥 빼기
            if(cntSushi[allSushi[i%N]]++ == 0) cnt++; // 뒷 초밥 추가
            max = Math.max(max, cnt);
        }
        System.out.print(max);
    }
}
