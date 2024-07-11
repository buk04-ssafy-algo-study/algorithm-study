// https://www.acmicpc.net/problem/1263
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int answer = -1;

        int N = Integer.parseInt(br.readLine());
        int[][] work = new int[N][2];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            work[i][0] = Integer.parseInt(st.nextToken());
            work[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(work, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(b[1], a[1]);
            }
        });

        boolean impossible = false;
        int now = work[0][1]-work[0][0];
        for(int i=1;i<N;i++) {
            int T = work[i][0];
            int S = work[i][1];

            if(now>S) {
                now = S;
            } 

            now -= T;
        }
        if(now<0) impossible = true;
        
        if(!impossible) answer = now;
        System.out.println(answer);
    }

}
