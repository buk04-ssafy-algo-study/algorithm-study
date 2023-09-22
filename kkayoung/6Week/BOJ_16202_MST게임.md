### 문제
[백준 16202번: MST 게임](https://www.acmicpc.net/problem/16202)  

### 풀이
크루스칼 알고리즘: 간선을 하나씩 선택해서 MST를 찾는 알고리즘  

1. 전처리 작업: 최초, 모든 간선을 가중치에 따라 **오름차순**으로 정렬
2. 턴 수 만큼 아래를 반복

     
   1. 가중치가 가장 낮은 간선부터 선택하면서 트리를 증가시킴  
      - 사이클이 존재하면 남아 있는 간선 중 그 다음으로 가중치가 낮은 간선 선택  
   2. n-1개의 간선이 선택될 때까지 i를 반복, n-1개 간선을 선택할 수 없다면 0 출력, 반복 종료  
   3. 가중치가 가장 낮은 간선 제거  


### 코드
```java
import java.io.*;
import java.util.*;

public class Main {
    
    public static class Edge implements Comparable<Edge>{
        int a, b, weight;
        Edge(int a, int b, int weight){
            this.a = a; // 노드 
            this.b = b; // 노드
            this.weight = weight; // 간선 가중치
        }
        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.weight,o.weight); // 가중치 오름차순 정렬
        }
    }

    static int[] parents;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // # of vertex
        M = Integer.parseInt(st.nextToken()); // # of edge
        K = Integer.parseInt(st.nextToken()); // # of turn

        parents = new int[N+1];
        Edge[] edgeList = new Edge[M+1];
        edgeList[0] = new Edge(0,0,10001);

        for(int i=1;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(x, y, i);
        }
        Arrays.sort(edgeList); // 가중치 오름차순 정렬
        
        int edgeList_idx = 0;
        while(K-->0){
            make(); // init parents arr
            int score = 0;   // score of this turn
            int edge_cnt = 0;// # of selected edge

            for(int i=edgeList_idx;i<=M;i++) { // 간선 선택
                Edge selected = edgeList[i];
                if(!union(selected.a, selected.b)) continue; // 사이클 발생 -> continue
                
                score += selected.weight;
                if(++edge_cnt==N-1) break; // 선택된 간선의 수가 (노드 수 -1)이면 MST가 완성됨
            }
            if(edge_cnt!=N-1) {
                // cannot create MST
                sb.append(0+" ");
                break;
            }
            sb.append(score+" ");
            edgeList_idx++; // 가중치가 가장 간선 제거
        }

        while(K-->0) sb.append(0+" ");
        

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void make() {
        for(int i=1;i<=N;i++)
            parents[i] = i;
    }

    static int find(int x){ // 루트 노드 찾기
        if(x == parents[x]) return x;
        else return parents[x] = find(parents[x]);
    }

    static boolean union(int x, int y){ // 트리 합치기
        x = find(parents[x]);
        y = find(parents[y]);
        if(x==y) return false; // 사이클 발생 시 false 리턴
        parents[x] = y;
        return true;
    }
}
