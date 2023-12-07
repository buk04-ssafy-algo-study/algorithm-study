// https://www.acmicpc.net/problem/23814
import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		// input
		long D = Long.parseLong(br.readLine());
		st = new StringTokenizer(br.readLine());
		long N = Long.parseLong(st.nextToken());
		long M = Long.parseLong(st.nextToken());
		long K = Long.parseLong(st.nextToken());

		long[][] res = new long[4][2]; // 만두 개수, 볶음밥 개수
		long NmodD = N%D;
		long MmodD = M%D;
		// case 1
		res[0][0] = N/D+M/D+K/D;
		res[0][1] = K;
		// case 2; 볶음밥 인원을 짜장으로 이동
		res[1][0] = N/D + (M+(D-MmodD))/D + (K-(D-MmodD))/D;
		res[1][1] = K-(D-MmodD); 
		// case 3; 볶음밥 인원을 짬뽕으로 이동
		res[2][0] = (N+(D-NmodD))/D + M/D + (K-(D-NmodD))/D;
		res[2][1] = K-(D-NmodD);		
		// case 4; 볶음밥 인원을 짜장, 짬뽕으로 이동
		res[3][0] = (N+(D-NmodD))/D + (M+(D-MmodD))/D + (K-(D-NmodD)-(D-MmodD))/D;
		res[3][1] = K-(D-NmodD)-(D-MmodD);
		// 볶음밥 수 내림차순, 만두 수 내림차순 정렬
		Arrays.sort(res, new Comparator<long[]>(){
			@Override
			public int compare(long[] o1, long[] o2){
				if(o1[0]!=o2[0]) return (int)(o2[0]-o1[0]);
				return (int)(o2[1]-o1[1]);
			}	
		});
		long answer = res[0][1];
		// output
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}

}
