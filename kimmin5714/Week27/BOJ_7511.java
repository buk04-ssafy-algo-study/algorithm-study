package Week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_7511 {
	static int n, parents[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T;
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++){
			sb.append("Scenario "+t+":\n");
			n = Integer.parseInt(br.readLine());
			int k = Integer.parseInt(br.readLine());
			parents = new int[n+1];
			for(int i=1;i<n+1;i++)
				parents[i] = i;

			for(int i=0;i<k;i++){
				StringTokenizer st = new StringTokenizer(br.readLine());
				union(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			int m = Integer.parseInt(br.readLine());
			for(int i=0;i<m;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());

				if (find(u) == find(v))
					sb.append(1);
				else {
					sb.append(0);
				}
				sb.append("\n");
			}
			sb.append("\n");
		}
		sb.append("\n");
		System.out.println(sb);
	}
	private static void union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);

		if(aParent < bParent) {
			parents[bParent] = aParent;
		}
		else if (bParent < aParent){
			parents[aParent] = bParent;
		}
	}
	private static int find(int n) {
		if(n == parents[n]) return n;
		else
			return parents[n] = find(parents[n]);
	}
}
