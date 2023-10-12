import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_4195_친구네트워크 {
	static int[] parent;
	static int[] level;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());
			parent = new int[n * 2];
			level = new int[n * 2];
			for (int j = 0; j < n*2; j++) {
				parent[j] = j;
				level[j] = 1;
			}
			
			int index = 0;
			Map<String, Integer> map = new HashMap<>();
			
			for (int j = 0; j < n; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				
				if(!map.containsKey(a)) {
					map.put(a, index++);
				}
				if(!map.containsKey(b)) {
					map.put(b, index++);
				}
				sb.append(union(map.get(a), map.get(b)) + "\n");
			}
		}
		System.out.println(sb);
	}
	
	static int find(int x){
		if(x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
	
	static int union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x != y) {
			parent[y] = x;
			level[x] += level[y];
			
			level[y] = 1;
		}
		return level[x];
	}
}
