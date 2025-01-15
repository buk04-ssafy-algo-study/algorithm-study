package Week70;

import java.util.*;
import java.io.*;

public class 백준_14658_하늘에서별똥별이빗발친다 {

	static int N, M, K, L;
	static List<int[]> list;
	static int max = 0;
	public static void main(String[] args) throws IOException{
		
		init();
		shootingStar();
		System.out.println(K-max); // 총 별똥별 - 덮인 별똥별
	}
	
	private static void shootingStar() {
		
		for(int i=0; i<list.size(); i++) {
			for(int j=0; j<list.size(); j++) {
			
			// 별똥별들의 좌표 조합
			int y = list.get(i)[0];
			int x = list.get(j)[1];
			
					// 현재 칸 기준 오른쪽 아래 방향을 L만큼 트램펄린으로 덮은 별똥별 갯수 찾기
					int cnt = checkRectangle(x, y);
					max = Math.max(max, cnt);
			}
		}
	}
	
	private static int checkRectangle(int x, int y) {

		// 덮이는 별똥별 체크
		int num = 0;
		
		for(int j=0; j<list.size(); j++) {
 
			int y2 = list.get(j)[0];
			int x2 = list.get(j)[1];
			
			// 별똥별이 트램펄린에 덮히면
			if(x2 <= (x+L) && y2 <= (y+L) && x2 >= x && y2 >= y ) {
				num++;
			}
		}
		
		return num;
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			list.add(new int[] {y, x});
		}
	}
}
