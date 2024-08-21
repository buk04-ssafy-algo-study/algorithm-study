package study.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_���4_1967_Ʈ�������� {
	
	static class Node {
		int connect;	// ����� ��� ��ȣ
		int weight;	// ����ġj
		
		public Node(int connect, int weight) {
			this.connect = connect;
			this.weight = weight;
		}
		
		@Override
		public String toString() {
			return this.connect + " " + this.weight;
		}
	}

	static int answer;
	static int n, result, nodeIdx;
	static List<Node>[] tree;	// Ʈ�� ���� ����
	static boolean[] isVisited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		isVisited = new boolean[n+1];
		dfs(nodeIdx, 0);
		
		System.out.println(result);
	}
	
	static void init() throws NumberFormatException, IOException {
		answer = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		tree = new ArrayList[n+1];
		for(int i = 0; i< n+1; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 0; i < n-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			tree[parent].add(new Node(child, weight));
			tree[child].add(new Node(parent, weight));
		}
		isVisited = new boolean[n+1];
		dfs(1, 0);	// ���� �� ������ �Ÿ� ���ϱ�
	}
	
	static void dfs(int idx, int dis) {
		isVisited[idx] = true;
		
		if (result < dis) {
			result = dis;
			nodeIdx = idx;
		}
		
		for (int i = 0; i < tree[idx].size(); i++) {
			if (!isVisited[tree[idx].get(i).connect]) {
				dfs(tree[idx].get(i).connect, dis + tree[idx].get(i).weight);
			}
		}
	}

	
}
