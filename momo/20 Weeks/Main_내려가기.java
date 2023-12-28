import java.util.*;
import java.io.*;
public class Main_내려가기{
    static int N;
    static int[][] arr;
    static int[] maxDp;
    static int[] minDp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        maxDp = new int[3];
        minDp = new int[3];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        maxDp[0] = arr[0][0];
        maxDp[1] = arr[0][1];
        maxDp[2] = arr[0][2];
        minDp[0] = arr[0][0];
        minDp[1] = arr[0][1];
        minDp[2] = arr[0][2];
        for(int i=1;i < N;i++) {
            int a = Integer.max(maxDp[0], maxDp[1]) + arr[i][0];
            int b = Integer.max(maxDp[2],Integer.max(maxDp[0], maxDp[1])) + arr[i][1];
            int c = Integer.max(maxDp[1], maxDp[2]) + arr[i][2];
            maxDp[0] = a;
            maxDp[1] = b;
            maxDp[2] = c;


            int a2 = Integer.min(minDp[0], minDp[1]) + arr[i][0];
            int b2 = Integer.min(minDp[2],Integer.min(minDp[0], minDp[1])) + arr[i][1];
            int c2 = Integer.min(minDp[1], minDp[2]) + arr[i][2];
            minDp[0] = a2;
            minDp[1] = b2;
            minDp[2] = c2;
        }
        sb.append(Integer.max(maxDp[0],Integer.max(maxDp[1], maxDp[2])) + " ");
        sb.append(Integer.min(minDp[0],Integer.min(minDp[1], minDp[2])));
        System.out.println(sb);
    }
}
