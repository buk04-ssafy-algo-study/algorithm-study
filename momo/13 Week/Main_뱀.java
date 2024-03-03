import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_뱀 {
	static int N;
	static int[][] map;
	static int K, L;
	
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	
	static int dir = 0;
	
//	static int[] moveNum;
//	static char[] moveDeg;
	static Map<Integer, String> moveInfo;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			
			map[r][c] = 1;
		}
		
		L = Integer.parseInt(br.readLine());
		moveInfo = new HashMap<>();
//		moveNum = new int[L];
//		moveDeg = new char[L];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			moveInfo.put(Integer.parseInt(st.nextToken()), st.nextToken());
//			moveNum[i] = Integer.parseInt(st.nextToken());
//			moveDeg[i] = st.nextToken().charAt(0);
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		q.add(0);
		int time = 0;
		int pr = 0;
		int pc = 0;
		
		while(true) {
			int nr = pr + dr[dir];
			int nc = pc + dc[dir];
			time++; 
            
			// 벽 체크
			if(nr<0 || nc<0 || nr>N-1|| nc> N-1) {
				break;
			}
            
			// 몸통 박음
			if(q.contains(nr*N + nc)){
				break;
			}
			
			// 이동함 
			if(map[nr][nc] ==1) {
				map[nr][nc] = 0;
				q.add(nr*N +nc);
			}else {
				q.add(nr*N +nc);
				q.poll();
			}
            
			// 방향 전환 
			if(moveInfo.containsKey(time)) {
				String data = moveInfo.get(time);
				if(data.equals("D")) {
					dir +=1;
					if(dir==4)  dir=0;
				}else {
					dir -=1;
					if(dir==-1) dir=3;
				}
			}
			pc = nc;
			pr = nr;
		}
		System.out.println(time);
	}
}
