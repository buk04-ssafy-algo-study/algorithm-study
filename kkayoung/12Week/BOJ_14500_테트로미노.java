import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, M, answer;
	static int[][] paper;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		paper = new int[N][M];
		answer = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[N][M];
		for (int r = 0; r < N; r++) { // (0,0)부터 (N,M) 까지 모든 칸에 5가지 테트로미노를 모두 배치해봄
			for (int c = 0; c < M; c++) {
				visited[r][c] = true;
				solution(r, c, 1, paper[r][c]);
				visited[r][c] = false;
			}
		}
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}

	static void solution(int r, int c, int cnt, int sum) { // 행, 열, 선택 칸 개수, 점수
		if (cnt == 4) { // 4칸 모두 선택 == 테트로미노 완성
			answer = Math.max(answer, sum);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dir[d][0];
			int nc = c + dir[d][1];
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc])
				continue;

			visited[nr][nc] = true;
			solution(nr, nc, cnt + 1, sum + paper[nr][nc]);
			visited[nr][nc] = false;

			if (cnt == 2) { // 2칸을 선택했다면 ㅗ,ㅏ,ㅓ,ㅜ 모양 테트로미노 만들 수 있다
				visited[nr][nc] = true;
				solution(r, c, cnt + 1, sum + paper[nr][nc]); // nr,nc로 이동하지 않음
				visited[nr][nc] = false;
			}

		}
	}
}
