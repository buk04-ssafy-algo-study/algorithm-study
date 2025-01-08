package Week69;

import java.util.*;
import java.io.*;

public class 백준_16928_뱀과사다리게임 {

	static class Point{
		
		int location, num;
		
		public Point(int location, int num) {
			this.location = location;
			this.num = num;
		}
	}
	
	static int N, M, result;
	static int[] map, dist;
	static int[] move = {1, 2, 3, 4, 5, 6};
	public static void main(String[] args) throws IOException  {
		
		init();
		snakeAndLadder();
		System.out.println(dist[100]);
	}
	
	private static void snakeAndLadder() {
		
		Queue<Point> q = new ArrayDeque<>();
		
		q.offer(new Point(1, 0));
		
		while(!q.isEmpty()) {
			
			Point p = q.poll();
			
			// 현재 칸에서 1~6칸 만큼 더함
			for(int i=0; i<6; i++) {
				
				int nl = p.location + move[i];
				int nn = p.num + 1;
				
				// 위치가 100을 넘기면 안됨
				if(nl > 100) continue;
 
				// 해당 위치에 뱀이나 사다리가 있다면
				if(map[nl] != 0) {
					nl = map[nl];
				}
				
				if(dist[nl] > nn) {
					
					dist[nl] = nn;
					q.offer(new Point(nl, nn));
				}
			}
		}
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[101];
        dist = new int[101];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        for(int i=0; i<N+M; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int entrance = Integer.parseInt(st.nextToken());
        	int exit = Integer.parseInt(st.nextToken());
        	
        	map[entrance] = exit;
        }
	}
}
