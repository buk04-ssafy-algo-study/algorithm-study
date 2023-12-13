// https://www.acmicpc.net/problem/4803
import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] parent;
	static boolean[] treeRoot; // 트리의 루트인가?

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// input
		int caseNum = 1;
		while(true){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if(N==0 && M==0) break;
			
			init();
			for(int e=0;e<M;e++){
				st = new StringTokenizer(br.readLine());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				union(v, w);
			}
      // tree root 개수 count
			Set<Integer> root = new HashSet<>();
			for(int i=1;i<=N;i++){
				int v = find(i);
				if(treeRoot[v]==true)
					root.add(v);
			}
			int total = root.size();
      
			sb.append("Case ").append(caseNum).append(": ");
			if(total==0){
				sb.append("No trees.\n");
			} else if (total==1){
				sb.append("There is one tree.\n");
			} else{
				sb.append("A forest of ").append(total).append(" trees.\n");
			}
			caseNum++;
		}
		
		// output
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static boolean union(int v, int w) {
		v = find(parent[v]);
		w = find(parent[w]);
		if(v==w || treeRoot[v]==false || treeRoot[w]==false) { // cycle 발생 또는 트리가 아닌 것과 트리를 병합 시도
			treeRoot[v] = false; // v는 더이상 트리가 아님
			treeRoot[w] = false; // w는 더이상 트리가 아님
			return false;
		}

		if(v<w) {
			parent[w] = v;
		}
		else {
			parent[v] = w;
		}
		return true;
	}

	static int find(int v) {
		if(parent[v]==v) return v;
		return parent[v] = find(parent[v]);
	}

	static void init() {
		parent = new int[N+1];
		treeRoot = new boolean[N+1];
		for(int i=1;i<=N;i++) parent[i] = i;
		Arrays.fill(treeRoot, true);
	}
}
