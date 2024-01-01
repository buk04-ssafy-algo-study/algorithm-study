// https://www.acmicpc.net/problem/2251
import java.io.*;
import java.util.*;

public class Main {

	static boolean[][] visited;
	static int A, B, C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		visited = new boolean[A+1][B+1];

		dfs(0, 0);

    // visited[0][b] : A물통이 비어있음
		for(int b=B;b>=0;b--) {
			if(!visited[0][b]) continue;
			sb.append(C-b).append(" ");
		}
			
		// output
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int nowA, int nowB) { // 현재 A, B 물통에 있는 물의 양
		if(visited[nowA][nowB]) return;

		visited[nowA][nowB] = true;
		int nowC = C-nowA-nowB; // 현재 C 물통에 있는 물의 양
		int spaceA = A-nowA; // A 물통 여유 공간
		int spaceB = B-nowB; // B 물통 여유 공간
		int spaceC = C-nowC; // C 물통 여유 공간
		int tmp;
		// System.out.println(String.format("A= %d, B=%d, C=%d", nowA, nowB, nowC));

		if(nowA > 0) {
			// A->B
			tmp = Math.min(nowA, spaceB); // 현재 A 물통 물 양과 B 물통 여유 공간 크기 비교
			dfs(nowA-tmp, nowB+tmp);
			// A->C
			tmp = Math.min(nowA, spaceC);
			dfs(nowA-tmp, nowB);
		}
		if(nowB > 0) {
			// B->A
			tmp = Math.min(nowB, spaceA);
			dfs(nowA+tmp, nowB-tmp);
			// B->C
			tmp = Math.min(nowB, spaceC);
			dfs(nowA, nowB-tmp);
		}
		if(nowC > 0) {
			// C->A
			tmp = Math.min(nowC, spaceA);
			dfs(nowA+tmp, nowB);
			// C->B
			tmp = Math.min(nowC, spaceB);
			dfs(nowA, nowB+tmp);
		}
	}
}
