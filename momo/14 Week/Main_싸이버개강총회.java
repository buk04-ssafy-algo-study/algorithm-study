import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_싸이버개강총회 {
	static int S,E,Q;
	static int ans = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		Set<String> start = new HashSet<>();
		Set<String> end = new HashSet<>();
		
		st = new StringTokenizer(br.readLine());
		String[] tmp;
		tmp = st.nextToken().split(":");
		S = Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);
		tmp = st.nextToken().split(":");
		E = Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);
		tmp = st.nextToken().split(":");
		Q = Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);
		
		String input ="";
		while(((input=br.readLine()) !=null) && (input.length() >0)) {
			st = new StringTokenizer(input);
			tmp = st.nextToken().split(":");
			int time = Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);
			if(time > Q) break;
			
			// 개강총회 시작전 혹은 시작 때 들어온 사람들
			if(time <= S) {
				start.add(st.nextToken());
			}
			// 총회 끝 ~ 스트리밍 끝 사람들
			else if(time >= E && time <= Q ) {
				String name = st.nextToken();
				if(start.contains(name)) {
					ans++;
					start.remove(name);
				}
			}
			input="";
		}
		System.out.println(ans);
	}
}
