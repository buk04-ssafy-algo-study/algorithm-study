package _15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main_뿌요뿌요 {
	static char[][] map;

	static int[] dr = new int[] { -1, 1, 0, 0 };
	static int[] dc = new int[] { 0, 0, -1, 1 };

	static boolean[][] visit;
	static List<int[]> list;

	static int cnt = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[12][6];

		// map 입력받기
		for (int i = 0; i < 12; i++) {
			String line = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		while (true) {
			boolean flag = true;
			visit = new boolean[12][6];
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] == '.')
						continue;
					list = new ArrayList<>();
					bfs(new int[] { i, j }, map[i][j]);

					if (list.size() >= 4) {
						flag = false;
						for (int j2 = 0; j2 < list.size(); j2++) {
							map[list.get(j2)[0]][list.get(j2)[1]] = '.';
						}
					}
				}
			}

			if (flag)
				break;
			fall();
			cnt++;
		}
		System.out.println(cnt);
	}

	public static void fall() {
		for (int i = 0; i < 6; i++) {
			for (int j = 11; j > 0; j--) {
				if (map[j][i] == '.') {
					for (int k = j - 1; k >= 0; k--) {
						if (map[k][i] != '.') {
							map[j][i] = map[k][i];
							map[k][i] = '.';
							break;
						}
					}
				}
			}
		}
	}

	static void bfs(int[] pos, char ch) {
		boolean[][] visit = new boolean[12][6];
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(pos);
		visit[pos[0]][pos[1]] = true;
		list.add(pos);
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				// 경계체크
				if (nr < 0 || nc < 0 || nr >= 12 || nc >= 6)
					continue;
				// 방문체크
				if (visit[nr][nc])
					continue;
				// 같은글자인지 체크
				if (map[nr][nc] != ch)
					continue;
				visit[nr][nc] = true;
				list.add(new int[] { nr, nc });
				q.offer(new int[] { nr, nc });
			}

		}
	}
}
