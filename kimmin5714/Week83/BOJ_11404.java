import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] arr = new int[n+1][n+1];
        for(int i=1;i<=n;i++) {
            Arrays.fill(arr[i], 987654321);
            arr[i][i] = 0;
        }

        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[a][b] = Math.min(arr[a][b], c);
        }

        for(int k=1;k<=n;k++) {
            for(int i=1;i<=n;i++) {
                for(int j=1;j<=n;j++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][k]+arr[k][j]);
                }
            }
        }

        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                if(arr[i][j] == 987654321){
                    sb.append(0+" ");
                }else {
                    sb.append(arr[i][j]+" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
