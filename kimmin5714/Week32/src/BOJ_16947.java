import java.util.*;
 
public class BOJ_16947{
 
    static ArrayList<Integer>[] list;
    static int n;
    static boolean[] isCycle;
 
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
 
        n = scan.nextInt();
 
        list = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
 
        //연결 정보를 받아서 인접리스트로 저장
        for(int i = 0; i < n; i++) {
            int n1 = scan.nextInt();
            int n2 = scan.nextInt();
            list[n1].add(n2);
            list[n2].add(n1);
        }
 
        isCycle = new boolean[n + 1]; //각 역마다 싸이클 여부 저장
        
        //1~n 보면서 싸이클 발생하는 구간 체크
        for(int i = 1; i <= n; i++) {
            if(checkCycle(i, i, i)) break;
            isCycle = new boolean[n + 1];
        }
 
        int[] result = new int[n + 1];
        for(int i = 1; i <= n; i++) {
        	//싸이클 아닌 경우에는 bfs로 최소 거리 찾기
            if(!isCycle[i]) result[i] = bfs(i);
        }
 
        for(int i = 1; i <= n; i++) 
        	System.out.print(result[i] + " ");
    }
 
    public static int bfs(int node) {
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        q.add(new Node(node, 0));
        visited[node] = true;
 
        while(!q.isEmpty()) {
            Node current = q.poll();
            
            //싸이클에 포함된 노드 발견하면 현재 count 반환
            if(isCycle[current.v]) return current.count;
 
            //현재 노드에서 인접한 노드 탐색
            for(int i = 0; i < list[current.v].size(); i++) {
                int next = list[current.v].get(i);
                if(!visited[next]) { //다음 노드가 방문 안한 노드면 방문
                    visited[next] = true;
                    q.add(new Node(next, current.count + 1));
                }
            }
        }
        return 0;
    }
 
    //이전에 방문한 노드, 현재 노드, 시작 노드로 확인 (dfs)
    public static boolean checkCycle(int prev, int node, int start) {
        isCycle[node] = true;
 
        for(int i = 0; i < list[node].size(); i++) { //현재 노드와 인접한 구간 체크
            int next = list[node].get(i);
 
            if(!isCycle[next]) { 
            	//인접한 곳 싸이클 아니면 싸이클인지 탐색 (재귀)
                if(checkCycle(node, next, start)) return true;       
            } 
            //다음 노드가 바로 이전에 방문한 노드가 아니고, 
            //다음 노드가 탐색을 시작했던 노드인 경우는 싸이클 발생한 것
            else if(next != prev && next == start) return true;
        }
        
        isCycle[node] = false;
        
        return false;
    }
 
    public static class Node {
        int v;
        int count;
 
        public Node(int v, int count) {
            this.v = v;
            this.count = count;
        }
    }
}
