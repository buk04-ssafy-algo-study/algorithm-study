package 스터디_6주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


public class Main_12865_평범한배낭_박나린_조합 {
	// 입력 변수
	static int N;
	static int K;
	static Node[] arr;
	
	//dfs 쓰일 변수
	static List<Node> list;
	static int max;
	
	// Node 클래스
	static class Node {
		int w;
		int v;
		public Node(int w, int v) {
			super();
			this.w = w;
			this.v = v;
		}
		@Override
		public String toString() {
			return "Node [w=" + w + ", v=" + v + "]";
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); // 물품의 수 N
		K = Integer.parseInt(st.nextToken()); // 최대 무게 K
		
		arr = new Node[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			arr[i] = new Node(w, v);
		}
		

		max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			list = new LinkedList<>();
			dfs(0,i,0);
		}
		
		System.out.println(max);
	} // main END
	
	static void dfs(int cnt, int start, int sum) {
		
		// 70을 넘어가면 가지치기를 하고 리턴 된 함수스택에서는 가중치를 계산 해주어야 함
		if(sum > K) {
			// 최근 들어온 Node를 지워줌
			sum -= list.get(list.size() - 1).w;
			list.remove(list.size() - 1);
			
			int currentV = 0;
			for (Node node : list) {
				System.out.println("w : " + node.w + " // " + "v : " + node.v);
				currentV += node.v;
			}
			System.out.println("=======================");
			//지금 구한 가치값이  max보다 크면 대입
			if(currentV > max) max = currentV;
			return;
		}
		
		if(sum == K) {
			int currentV = 0;
			for (Node node : list) {
				currentV += node.v;
			}
			//지금 구한 가치값이  max보다 크면 대입
			if(currentV > max) max = currentV;
			return;
		}
		
		for (int i = start; i < N; i++) {
			sum += arr[i].w;
			list.add(arr[i]);
			dfs(cnt + 1, i + 1, sum);
		}
	}
}










