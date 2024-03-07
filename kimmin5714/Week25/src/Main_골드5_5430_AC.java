import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_골드5_5430_AC {
	static StringBuilder sb = new StringBuilder(); 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			char[] p = br.readLine().toCharArray();	// 문제에서 p에 해당하는 명령어
			int n = Integer.parseInt(br.readLine());

			// 1. StringTokenizer는 구분할 문자를 합쳐서 넘겨주면 자동 파싱
			st = new StringTokenizer(br.readLine(), "[],"); 
			
			// 2. split 사용하는 방법
			// String input = br.readLine();
			// String[] s = input.subString(1, input.length - 1).split(","); 
			//subString 안쓰고 split으로 구분자 넘겨주면, 맨 앞 [도 구분자로 인식되어 빈 문자열 저장
			
			ArrayDeque<Integer> q = new ArrayDeque<Integer>();
			
			for(int i = 0; i < n; i++) 
				q.add(Integer.parseInt(st.nextToken()));
			
			AC(p, q);
		}
		System.out.println(sb);
	}

	public static void AC(char[] p, ArrayDeque<Integer> q) {
		boolean isRight = true;
		for(int i=0;i<p.length;i++) {
			
			if(p[i] == 'R') {
				isRight = !isRight;	// 방향을 바꿔준다.
				continue;
			}
			else if(p[i] == 'D') {
				if(isRight) {
						if(q.pollFirst() == null) {
						sb.append("error\n");
						return;
					}

				}
				else {
					if(q.pollLast() == null) {
						sb.append("error\n");
						return;
					}	
				}
			}
		}

		// 남은 요소 출력
		sb.append("[");	

		if(q.size() > 0) {
			if(isRight) {	// 정방향
				sb.append(q.pollFirst());
				while(!q.isEmpty()) {
					sb.append(',').append(q.pollFirst());
				}
			}
			else {	// 역방향일경우 
				sb.append(q.pollLast());
				while(!q.isEmpty()) {
					sb.append(',').append(q.pollLast());
				}
			}
		}

		sb.append("]").append("\n");
	}
}