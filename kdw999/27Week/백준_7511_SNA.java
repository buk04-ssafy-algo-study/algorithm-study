import java.io.*;
import java.util.*;


public class Main {

    static int n;
    static int[] parents;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테케

        for(int t=1; t<=T; t++){
            sb.append("Scenario "+t+":\n");
            n = Integer.parseInt(br.readLine()); // 유저 수
            int k = Integer.parseInt(br.readLine()); // 관계 수
            parents = new int[n+1];

            // 첫 뿌리는 자기 자신을
            for(int i=1; i<=n; i++) parents[i] = i;

            // 친구 관계 엮기
            for(int i=0; i<k; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                union(x, y);
            }

            int m = Integer.parseInt(br.readLine());
            
            // 경로 연결됐는지
            for(int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                if (find(u) == find(v)) sb.append(1);
                else sb.append(0);
                sb.append("\n");
            }
            sb.append("\n");
        }
        sb.append("\n");
        System.out.println(sb);
    }
    
    // 부모 통일
    private static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if(aParent < bParent) parents[bParent] = aParent;
        else if (bParent < aParent) parents[aParent] = bParent;

    }
    
    // 내 뿌리를 찾아서
    private static int find(int n) {
        if(n == parents[n]) return n;
        else return parents[n] = find(parents[n]);
       }
    }
