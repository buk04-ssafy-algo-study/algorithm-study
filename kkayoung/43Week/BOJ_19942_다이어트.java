// https://www.acmicpc.net/problem/19942
import java.io.*;
import java.util.*;

public class Main {

    static int N, mp, mf, ms, mv;
    static int[][] ingredient;
    static boolean[] choose;
    static int minCost = Integer.MAX_VALUE;
    static String answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        ingredient = new int[N+1][5];
        choose = new boolean[N+1];
        st = new StringTokenizer(br.readLine());
        mp = Integer.parseInt(st.nextToken());
        mf = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        mv = Integer.parseInt(st.nextToken());        

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            ingredient[i][0] = Integer.parseInt(st.nextToken()); // p
            ingredient[i][1] = Integer.parseInt(st.nextToken()); // f
            ingredient[i][2] = Integer.parseInt(st.nextToken()); // s
            ingredient[i][3] = Integer.parseInt(st.nextToken()); // v
            ingredient[i][4] = Integer.parseInt(st.nextToken()); // c
        }

        pick(1, 0, 0, 0, 0, 0);

        if(minCost==Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minCost);
            System.out.println(answer);
        }
    }

    static void pick(int idx, int p, int f, int s, int v, int c) {
        if(minCost<c) return;
        if(idx>N){
            if(p<mp || f<mf || s<ms || v<mv) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            
            for(int i=1;i<=N;i++) {
                if(choose[i]) {
                    sb.append(i).append(" ");
                }
            }

            if(c==minCost) {
                if(answer!=null && sb.toString().compareTo(answer)>0) return;    
            }
            
            minCost = c;
            answer = sb.toString();
            
            return;
        }
        
        pick(idx+1, p, f, s, v, c);
        
        choose[idx] = true;
        pick(idx+1, p+ingredient[idx][0], f+ingredient[idx][1], s+ingredient[idx][2], v+ingredient[idx][3], c+ingredient[idx][4]);
        choose[idx] = false;
    }
}
