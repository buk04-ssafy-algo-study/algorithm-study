// https://www.acmicpc.net/problem/1976
import java.io.*;
import java.util.*;

public class Main {

    static int N, M, INF;
    static int[][] adjMatrix;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        INF = 200*200;
        // 1. 입력
        N = Integer.parseInt(in.readLine());
        M = Integer.parseInt(in.readLine());
        adjMatrix = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(in.readLine());
            for(int j=0;j<N;j++){
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
                if(adjMatrix[i][j]==0) adjMatrix[i][j] = INF; // not connected
            }
        }
        // 2. 플로이드 워셜
        floydWarshall();
        // 3. 여행 계획에 포함된 도시들 중 i-1번째와 i번째 도시가 연결되어 있는지 확인
        String answer = "YES";
        int[] plan = new int[M];
        st = new StringTokenizer(in.readLine());
        for(int i=0;i<M;i++){
            plan[i] = Integer.parseInt(st.nextToken())-1;
            if(i==0) continue;
            int src = plan[i-1];
            int dst = plan[i];
            if(adjMatrix[src][dst]==INF){
                answer = "NO";
                break;
            }
        }
        // 4. 출력
        out.write(answer);
        out.flush();
        out.close();
    }
    
    static void floydWarshall(){
        for(int k=0;k<N;k++){
            for(int src=0;src<N;src++){
                adjMatrix[src][src] = 0;
                for(int dst=0;dst<N;dst++){
                    if(k==src || k==dst || src==dst) continue;
                    adjMatrix[src][dst] = Math.min(adjMatrix[src][dst],adjMatrix[src][k]+adjMatrix[k][dst]);
                }
            }
        }
    }
}
