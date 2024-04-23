import java.io.*;
import java.time.LocalDate;
import java.util.*;
 

class Node implements Comparable<Node>{
	int num;
	int cnt;
	public Node(int num, int cnt) {
		this.num = num;
		this.cnt = cnt;
	}
	@Override
	public int compareTo(Node o) {
		if(this.cnt == o.cnt) { // 숫자 갯수가 같다면 숫자 오름차순 정렬
			return this.num - o.num;
		}
		return this.cnt - o.cnt; // 숫자 갯수 오름차순 정렬
	}
}
 
public class Main {
	
		
		static int[][] map; // 배열 복사 맵
		static int row_len, col_len; // 행, 열 길이
		
	public static void main(String[] args) throws IOException {
	 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		map = new int[101][101]; // 크기가 100을 넘지 않음
		for(int i=0;i<3;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<3;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		row_len = 3; // 초기 배열은 크기가 3X3
		col_len = 3;
		
		int ans = -1; // 100초가 지나도 k값 못 만들면 -1 출력
		int cnt = 0;
		while(cnt <= 100) {
			if(map[r-1][c-1] == k) {
				ans = cnt;
				break;
			}
			
			// R 연산
			if(row_len >= col_len) {
				row_cal();
			} 
			// C 연산
			else {
				col_cal();
			}

			cnt++;
		}
		
		System.out.println(ans);
	}
	
	public static void row_cal() {
		int[][] map_copy = new int[101][101]; // map을 바로 대입하지 않는 이유 -> 깊은 복사 때문, 객체를 객체에 대입하면 주소값이 들어감
		int col = 0;
		for(int i=0;i<row_len;i++) {
			HashMap<Integer, Integer> hash = new HashMap<>(); // 한 행에 대해 숫자, 숫자 갯수를 담는 Map
			
			for(int j=0;j<col_len;j++) {
				if(map[i][j] == 0) continue; // 0은 없는거야
				if(hash.containsKey(map[i][j])) { // 해당하는 숫자가 있다면
					hash.put(map[i][j], hash.get(map[i][j])+1); // 해당 숫자 갯수에 +1 해서 다시 맵에 넣어
				} else {
					hash.put(map[i][j], 1); // 없다면 갯수 1로 하고 맵에 넣어
				}
			}
			ArrayList<Node> list = new ArrayList<>(); // 숫자, 숫자 갯수를 객체를 담을 리스트
			for(Map.Entry<Integer, Integer> entry:hash.entrySet()) { // Entry는 Map의 key, value를 받기위한 기능
				list.add(new Node(entry.getKey(), entry.getValue())); // list에 숫자, 숫자 갯수 객체를 저장
			}
			col = Math.max(col, list.size()*2); // 행 연산은 열의 길이가 2배 늘어남
			Collections.sort(list); // 정렬
			for(int p=0;p<list.size();p++) { // 한 행에 있는 {숫자, 숫자갯수} 다 탐색
				if(p >= 50) break; // 크기가 100까지라 2배했을 때 0~99넘어가면 안됨
				Node node = list.get(p); 
				map_copy[i][2*p] = node.num; // 한 행에서 열에 숫자 먼저 저장
				map_copy[i][2*p+1]= node.cnt; // 숫자 뒤에 숫자갯수 저장
			}
		}
		col_len = Math.min(99, col); // 열 길이 초기화
		map = map_copy; // 바꾼 맵을 기존 맵에 덮어씌워
	}
	
	public static void col_cal() {
		int[][] map_copy = new int[101][101];
		int row = 0;
		for(int j=0;j<col_len;j++) {
			HashMap<Integer, Integer> hash = new HashMap<>();
			for(int i=0;i<row_len;i++) {
				if(map[i][j] == 0) continue;
				if(hash.containsKey(map[i][j])) {
					hash.put(map[i][j], hash.get(map[i][j])+1);
				} else {
					hash.put(map[i][j], 1);
				}
			}
			ArrayList<Node> list = new ArrayList<>();
			for(Map.Entry<Integer, Integer> entry:hash.entrySet()) {
				list.add(new Node(entry.getKey(), entry.getValue()));
			}
			row = Math.max(row, list.size()*2);
			Collections.sort(list);
			for(int p=0;p<list.size();p++) {
				if(p >= 50) break;
				Node node = list.get(p);
				map_copy[2*p][j] = node.num;
				map_copy[2*p+1][j]= node.cnt;
			}
		}
		row_len = Math.min(99, row);
		map = map_copy;
	}

  }