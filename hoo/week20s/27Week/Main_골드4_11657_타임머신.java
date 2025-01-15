package SSAFY.study.algo.week20s.week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_골드4_11657_타임머신 {

    static class node {
        int start, end, w;

        public node(int start, int end, int w) {
            this.start = start;
            this.end = end;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N,M;
        long res[];
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        node[] adj = new node[M+1];
        for(int i=1;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            adj[i] = new node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        res = new long[N+1];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[1] = 0;

        for(int i=1;i<N;i++){
            for(int j=1;j<=M;j++){
                node tmp = adj[j];
                if(res[tmp.start] == Integer.MAX_VALUE) continue;
                if(res[tmp.end] > res[tmp.start]+tmp.w)
                    res[tmp.end] = res[tmp.start]+tmp.w;
            }
        }

        boolean flag = false;
        for(int j=1;j<=M;j++){
            node tmp = adj[j];
            if(res[tmp.start] == Integer.MAX_VALUE) continue;
            if(res[tmp.end] > res[tmp.start]+tmp.w)
                flag = true;
        }

        if(flag)
            System.out.println("-1");
        else {
            for (int i = 2; i < N + 1; i++) {
                if(res[i] == Integer.MAX_VALUE) System.out.println("-1");
                else
                    System.out.println(res[i]);
            }
        }
    }

}
