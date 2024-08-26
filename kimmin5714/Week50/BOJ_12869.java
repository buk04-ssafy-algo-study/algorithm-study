import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12869 {
    private static int res, N;
    private static int[][][] dp;
    private static int[][] num = {{9,3,1},{9,1,3},{3,9,1},{3,1,9},{1,9,3},{1,3,9}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int[] arr = new int[3];
        dp = new int[61][61][61];
        res = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++)
            arr[i] = Integer.parseInt(st.nextToken());

        dfs(arr, 0);
        System.out.print(res);
    }

    private static void dfs(int[] start, int cnt) {
        if(res < cnt) return; // 최솟값보다 횟수가 큰 경우
        if(dp[start[0]][start[1]][start[2]] !=0
        && dp[start[0]][start[1]][start[2]] <= cnt) return; // 이전 최솟값보다 현재 횟수가 큰 경우
        if(start[0] == 0 && start[1] ==0 && start[2] == 0){ // 다 죽은 경우 최솟값 갱신
            res = Math.min(res, cnt);
            return;
        }

        dp[start[0]][start[1]][start[2]] = cnt; // 현재 횟수 저장

        for(int i=0;i<6;i++){ // 공격 6가지 경우

            // 데미지
            int n1 = start[0] - num[i][0];
            if(n1<0) n1 = 0;

            int n2 = start[1] - num[i][1];
            if(n2<0) n2 = 0;

            int n3 = start[2] - num[i][2];
            if(n3<0) n3 = 0;

            // dfs
            dfs(new int[]{n1,n2,n3}, cnt+1);
        }
    }
}
