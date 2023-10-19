import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_듣보잡{
	static int N, M;
	
	static MapString, Boolean map = new HashMap();
	static int cnt = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		ListString list = new ArrayList();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i  N; i++) {
			map.put(br.readLine(), true);
		}
		for (int i = 0; i  M; i++) {
			String tmp = br.readLine();
			if(map.containsKey(tmp)) {
				cnt++;
				list.add(tmp);
			}
		}
		Collections.sort(list);
		sb.append(cnt + n);
		for (int i = 0; i  list.size(); i++) {
			sb.append(list.get(i) + n);
		}
		System.out.println(sb);
	}
}
