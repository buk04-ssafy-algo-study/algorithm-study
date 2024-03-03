// https://www.acmicpc.net/problem/16562
import java.io.*;
import java.util.*;

public class Main {

    static List<Integer>[] adjList;
    static int[] A;
    static boolean[] visited;
    static int minimum;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		    StringTokenizer st;
        int sum = 0;
		
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        adjList = new List[N+1];
        visited = new boolean[N+1];
        A = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            adjList[i] = new ArrayList<>();
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        // 인접 리스트 생성
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[v].add(w);
            adjList[w].add(v);
        }
        // 친구 찾기
        for(int i=1;i<=N;i++) {
            if(visited[i]) continue;
            minimum = Integer.MAX_VALUE; // 친구 무리 중 친구비 최소값
            findFriend(i);
            sum += minimum;
            if(sum>k) break; // 가진 돈을 초과하면 break
        }

        String answer = (sum>k) ? "Oh no" : String.valueOf(sum);
		    bw.write(answer);
        bw.flush();
        bw.close();
    }

    static void findFriend(int person) {
        visited[person] = true; // 친구 방문 
        minimum = Math.min(minimum, A[person]); // 친구비 최솟값을 갱신
        for(int p:adjList[person]) { // 방문하지 않은 친구들 방문
            if(visited[p]) continue;
            findFriend(p);
        }
    }
}
