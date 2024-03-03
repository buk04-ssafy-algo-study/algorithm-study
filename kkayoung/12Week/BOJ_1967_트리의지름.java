import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
    
    static class Node{
        int num, weight; // 노드 번호, 가중치
        Node(int num, int weight){
            this.num = num;
            this.weight = weight;
        
        }
    }
    static boolean[] visited;
    static int farNode; // 가장 먼 노드
    static List<Node>[] adjList;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(in.readLine());
        adjList = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(in.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[parent].add(new Node(child,weight));
            adjList[child].add(new Node(parent, weight));
        }

        // 1번 노드에서 가장 먼 노드 farNode 찾기
        max = Integer.MIN_VALUE;
        visited = new boolean[N+1];
        dfs(1, 0);

        // farNode번 노드에서 가장 먼 노드 찾기
        max = Integer.MIN_VALUE;
        visited = new boolean[N+1];
        dfs(farNode, 0);

        out.write(String.valueOf(max));
        out.flush();
        out.close();
    }

    static void dfs(int n, int weightSum){
        visited[n] = true;
        for(Node node:adjList[n]){
            int num = node.num;
            int weight = node.weight;
            if(visited[num]==true) continue;
            dfs(num,weightSum+weight);
        }
        if(weightSum>max) { // n번 노드를 방문하기까지의 가중치 합이 max보다 크다면 max 갱신
            max = weightSum;
            farNode = n;
        }
        // System.out.println("n: "+n+", weightSum: "+weightSum+", max: "+max);
    }

}
