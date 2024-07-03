import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for(int t=1;t<=T;t++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] time = new int[n+1];
            int[] degree = new int[n+1];
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

            for(int i=0;i<=n;i++)
                adj.add(new ArrayList<>());

            st = new StringTokenizer(br.readLine());
            for(int i=1;i<=n;i++)
                time[i] = Integer.parseInt(st.nextToken());

            for(int i=1;i<=k;i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                adj.get(a).add(b);
                degree[b]++;
            }

            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());

            Queue<Integer> q = new ArrayDeque<>();
            int[] tmpTime = new int[n+1]; //소요시간

            for(int i=1;i<=n;i++){
                tmpTime[i] = time[i];

                if(degree[i] == 0)
                    q.offer(i);
            }

            while(!q.isEmpty()){
                int cur = q.poll();

                for(int i=0;i<adj.get(cur).size();i++){
                    int next = adj.get(cur).get(i);

                    //System.out.println(t);
                    tmpTime[next] = Math.max(tmpTime[next], tmpTime[cur]+time[next]);
                    degree[next]--;

                    if(degree[next] == 0) {
                        q.offer(next);
                    }
                }
            }
            sb.append(tmpTime[w]+"\n");
        }
        System.out.print(sb);
    }
}
