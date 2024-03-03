// https://www.acmicpc.net/problem/2170
import java.io.*;
import java.util.*;

public class Main {	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int answer = 0;

		// input
		int N = Integer.parseInt(br.readLine());
		int[][] lines = new int[N][2];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			lines[i][0] = Integer.parseInt(st.nextToken());
			lines[i][1] = Integer.parseInt(st.nextToken());
		}
		// 선 시작 좌표를 오름차순 정렬
		Arrays.sort(lines, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}
		});

		int s = lines[0][0], e = lines[0][1]; // 선 시작점, 끝점 좌표
		for(int i=1;i<N;i++) { // 현재 선이 이전 선과 연결됨
			if(lines[i][0]<e) {
				if(e < lines[i][1]){
					e = lines[i][1]; // 끝점 좌표 갱신
				}
			}
			else { // 현재 선과 이전 선이 연결되지 않음
				answer += (e-s);
				s = lines[i][0];
				e = lines[i][1];
			}
		}
		answer += (e-s);
		
		// output
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}
}
