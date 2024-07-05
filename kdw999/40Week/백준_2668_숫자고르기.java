package Week40;

import java.util.*;
import java.io.*;

public class 백준_2668_숫자고르기 {
	
	static int[] arr;
	static boolean[] visited;
	static ArrayList<Integer> list;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		arr = new int[N+1];
		visited = new boolean[N+1];
		list = new ArrayList<>();
		
		// 첫 번째 줄 arr[i]번 째 인덱스 밑에 있는 값 저장
		for(int i=1; i<=N; i++) arr[i] = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=N; i++) {
			visited[i] = true;
			dfs(i, i);
			visited[i] = false;
		}
		
		System.out.println(list.size());
		System.out.println(sb);
	}
	
	static void dfs(int value, int i) {
		
		if(arr[value] == i) {
			list.add(arr[value]);
			sb.append(arr[value]+"\n");
		}
		
		if(visited[arr[value]] == false) {
			visited[arr[value]] = true;
			dfs(arr[value], i);
			visited[arr[value]] = false;
		}
	}
}
