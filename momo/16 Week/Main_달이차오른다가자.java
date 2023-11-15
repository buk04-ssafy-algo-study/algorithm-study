package _16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_달이차오른다가자 {
	static class Node {
		int r;
		int c;
		int cnt;
		int key;

		public Node(int r, int c, int cnt, int key) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.key = key;
		}
	}

	static int N, M;
	static char[][] maze;
	static Node start;
	static boolean[][][] visit;

	static boolean flag = false;

	static Map<Character, Integer> key = new HashMap<>();
	static Map<Character, Integer> door = new HashMap<>();

	static int min = Integer.MAX_VALUE;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		maze = new char[N][M];
		visit = new boolean[64][N][M];
		key.put('a', 1);
		key.put('b', 1 << 1);
		key.put('c', 1 << 2);
		key.put('d', 1 << 3);
		key.put('e', 1 << 4);
		key.put('f', 1 << 5);

		door.put('A', 1);
		door.put('B', 1 << 1);
		door.put('C', 1 << 2);
		door.put('D', 1 << 3);
		door.put('E', 1 << 4);
		door.put('F', 1 << 5);

		// 데이터 입력
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = input.charAt(j);
				// 시작 좌표 설정
				if (maze[i][j] == '0') {
					start = new Node(i, j, 0, 0);
				}
			}
		}

		System.out.println(bfs());
	}

	static int bfs() {
		// 로직 작성
		// 1. BFS 탐색
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(start.r, start.c, 0, 0));
		// 키 아무것도 없을 때 방문 처리
		visit[0][start.r][start.c] = true;

		int cnt = 0;
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (maze[cur.r][cur.c] == '1') {
				return cur.cnt;
			}
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				// 경계 탐색
				if (nr >= N || nr < 0 || nc >= M || nc < 0)
					continue;
				char pos = maze[nr][nc];
				// 벽 탐색
				if (maze[nr][nc] == '#')
					continue;

				// 방문 확인
				if (visit[cur.key][nr][nc])
					continue;

				// . or 0 or 1 일 때 한 칸 갈 수 있음,
				if (pos == '.' || pos == '0' || pos == '1') {
					q.offer(new Node(nr, nc, cur.cnt + 1, cur.key));
					visit[cur.key][nr][nc] = true;
				}
				// 키 일 때,
//							pos == 'a' || pos == 'b' ||pos == 'c' ||pos == 'd' ||pos == 'e' ||pos == 'f'
				if (key.containsKey(pos)) {
					int newKey = cur.key | key.get(pos);
					// 현재 키상태에서 아직 방문하지 않았다면
					if (!visit[newKey][nr][nc]) {
						visit[cur.key][nr][nc] = true;
						visit[newKey][nr][nc] = true;
						q.offer(new Node(nr, nc, cur.cnt + 1, newKey));
					}
					continue;
				}

				// 문일 때,
				if (door.containsKey(pos)) {
					// 현재 문에 맞는 키를 가지고 있다면
					if ((cur.key & door.get(pos)) > 0) {
						visit[cur.key][nr][nc] = true;
						q.offer(new Node(nr, nc, cur.cnt + 1, cur.key));
					}
				}
			}
		}
		return -1;
	}
}
