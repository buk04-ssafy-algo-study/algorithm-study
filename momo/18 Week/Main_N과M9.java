import java.util.*;
import java.io.*;
public class Main_N과M9 {
    static int N,M;
    static int[] num;
    static Set<String> set;
    static boolean[] visit;
    static int[] picks;
    static StringBuilder sb;

    public static void main(String args[]) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        num = new int[N];
        visit = new boolean[N];
        picks = new int[M];
        set = new LinkedHashSet<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num);
        permutation(0);
        Iterator<String> it = set.iterator();
        while(it.hasNext()) {
            sb.append(it.next() + "\n");
        }
        System.out.println(sb);

    }
    static void permutation(int depth) {
         if(depth == M) {
            StringBuilder tmp = new StringBuilder();
            for(int i=0; i< M; i++) {
                tmp.append(picks[i] + " ");
            }
            set.add(tmp.toString());
            return;
         }

         for(int i=0; i<N;i++) {
            if(visit[i]) continue;
            picks[depth] = num[i];
            visit[i] = true;
            permutation(depth + 1);
            visit[i] = false;
         }
    }
} 