// https://www.acmicpc.net/problem/19583
// 맵 사용(이름, 출석현황)
// 입실하면 출석현황을 1로 설정
// 입실한 사람 중에서 퇴실한 사람의 출석현황을 2로 설정
// 출석현황이 2인 사람 수를 출력
import java.io.*;
import java.util.*;

public class Main{

	static Map<String, Integer> attendance;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		String S = st.nextToken();
		String E = st.nextToken();
		String Q = st.nextToken();

		attendance = new HashMap<>();
		String line = null;
		while((line=(br.readLine()))!=null){
			String[] splited= line.split(" ");
			if(splited.length<2) break;
			String time = splited[0];
			String nickname = splited[1];

			// time <= S 이면 입실체크
			if(time.compareTo(S)<=0){
				attendance.put(nickname, 1);
			}
			// E <= time <= Q 이면 퇴실체크
			if(E.compareTo(time)<=0 && time.compareTo(Q)<=0){
				int status = attendance.getOrDefault(nickname, -1);
				if(status != -1 && status != 2){
					attendance.put(nickname,2);
				}
			}
		}

		int answer = 0;
		Collection<Integer> val = attendance.values();
		for(int v:val){
			if(v==2) answer++;
		}
				
		// output
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		
	}
}
