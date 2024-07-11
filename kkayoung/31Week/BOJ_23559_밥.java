// https://www.acmicpc.net/problem/23559
import java.io.*;
import java.util.*;

public class Main {

    static int N, X;
    static int[][] value;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        value = new int[N][2];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            value[i][0] = Integer.parseInt(st.nextToken());
            value[i][1] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(value, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int oper1 = o1[0]-o1[1]; // diff of A
                int oper2 = o2[0]-o2[1]; // diff of B
                return oper2-oper1;
            }
        });

        int answer = 0;
        int n=0;
        while(X-((N-n)*1000) >=4000) {
            if(value[n][1]>value[n][0]) {
                break;
            }
            answer += value[n][0];
            X -= 5000;
            n++;
        }
        while(n<N) {
            // B메뉴만 먹음
            answer += value[n][1];
            n++;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}
