import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_14889_스타트와링크 {
    static int R;
    static int min;
    static int N;
    static int[] picks;
    static boolean[] isVisited;
    static int[][] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        picks = new int[n/2];
        N = n;
        min = Integer.MAX_VALUE;
        R = n / 2;
        isVisited = new boolean[n+1];
        comb(0, 1);
        System.out.println(min);
    }
    
    static void comb(int cnt, int start) {
        if(cnt == R) {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
				if(!isVisited[i]) {
					list.add(i);
				}
			}
            int first = 0;
            for (int i : picks) {
				for (int j : picks) {
					if(i == j) continue;
					first += arr[i][j];
				}
			}
            int second = 0;
            for (int i : list) {
				for (int j : list) {
					if(i == j) continue;
					second += arr[i][j];
				}
			}
            int result = Math.abs(first - second);
            if(min > result) min = result;
            return;
        }
        
        for (int i = start; i <= N; i++) {
            picks[cnt] =i;
            isVisited[i] = true;
            comb(cnt + 1, i + 1);
            isVisited[i] = false;
        }
    }
}