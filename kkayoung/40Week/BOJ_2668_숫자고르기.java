// https://www.acmicpc.net/problem/2668
import java.io.*;
import java.util.*;

public class Main {

    static int[] arr;
    static boolean[] used;
    static List<Integer> answer;
	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		answer = new ArrayList<>();
        arr = new int[N+1];
        used = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
		for (int i=1;i<=N;i++) {
			used[i] = true;
			n = i;
			dfs(i);
			used[i] = false;
		}

		Collections.sort(answer);
		StringBuilder sb = new StringBuilder();
		sb.append(answer.size()).append("\n");
		for(int i=0;i<answer.size();i++) {
			sb.append(answer.get(i)).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void dfs(int now) {
		if(arr[now]==n) {
			answer.add(n);
			return;
		}

		if(!used[arr[now]]) {
			used[arr[now]] = true;
			dfs(arr[now]);
			used[arr[now]] = false;
		}
	}

}
