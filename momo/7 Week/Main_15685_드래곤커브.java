import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_15685_드래곤커브 {
	static boolean[][] arr;
	static int n; // 드래곤 커브의 개수
	static int[] dy = {0,-1,0,1};
	static int[] dx = {1,0,-1,0};
	static Stack<Integer> stack ;
	static int result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = new boolean[101][101];
		int n = Integer.parseInt(br.readLine());
		stack = new Stack<>();
		result = 0;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			stack.clear();
			
			
			int lastX = x+dx[d];
			int lastY = y+dy[d];
			// 0세대에 대하여 arr에 선분 처리
			arr[y][x] = true; 
			arr[lastY][lastX] = true;
			
			// 스택을 생성 해서 방향을 넣어준다.
			stack.push(d);
			
			
			//세대 수만큼 반복 하며 세대 만들어서 배열 true로 바꾸기
			for (int j = 0; j < g; j++) {
				int size = stack.size();
				Stack<Integer> tmp = new Stack<>();
				tmp.addAll(stack);
				
				for(int k = 0; k < size;k++) {
					int nowDir = swap(tmp.pop()); // 스왑해준 방향값
					lastX += dx[nowDir];
					lastY += dy[nowDir];
					
					arr[lastY][lastX] = true;
					stack.push(nowDir);
				}
			}
		}
		checkSquare();
		System.out.println(result);
	}
	static int swap(int dir) {
		if(dir == 0) return 1;
		else if(dir == 1) return 2;
		else if(dir == 2) return 3;
		else return 0;
	}
	
	static void checkSquare() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				//인접한 4칸이 모두 true이면 정사각항임
				if(arr[i][j] && arr[i][j+1] && arr[i+1][j] && arr[i+1][j+1]) {
					result++;
				}
			}
		}
	}
}
