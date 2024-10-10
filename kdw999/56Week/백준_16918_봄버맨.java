package Week56;

import java.io.*;
import java.util.*;

public class 백준_16918_봄버맨 {
	
	static char[][] map;
	static Queue<int[]> q = new LinkedList<>();
	static int R, C, N;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		// 초기 상태
		for(int i=0; i<R; i++) {
			String line = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		for (int i = 2; i <= N; i++) {
            if (i % 2 == 1) {
             
                for (int k = 0; k < R; k++) {
                    for (int j = 0; j < C; j++) {
                    	
                    	// 폭탄 위치 저장
                        if (map[k][j] == 'O') {
                            q.add(new int[]{k, j});
                        }
                    }
                }
                
                // 폭탄 터뜨리기 전에 폭탄 채우는 작업
                for (char[] m : map) {
                    Arrays.fill(m, 'O');
                }
                
                // 폭탄 터트리기
                bfs();
            }

        }
		
		// 짝수 차례 때 끝나면 맵을 폭탄으로 채움
		if (N % 2 == 0) {
            for (char[] m : map) {
                Arrays.fill(m, 'O');
            }
        }
		
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
	}
	
	static void bfs() {
		
        while (!q.isEmpty()) {
            int[] t = q.poll();
            int x = t[0];
            int y = t[1];
            
            map[x][y] = '.';
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dr[i];
                int ny = y + dc[i];
                if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                    if (map[nx][ny] == 'O') {
                        map[nx][ny] = '.';
                    }
                }
            }
        }
    }
}
