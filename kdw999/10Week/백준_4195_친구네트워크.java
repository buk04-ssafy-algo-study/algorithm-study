package Week10;

import java.util.*;
import java.io.*;

public class 백준_4195_친구네트워크 {
	
	static int[] parent;
	static int[] level;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t=0; t<T; t++) {
			 int F = Integer.parseInt(br.readLine());
			 
	            parent = new int[F * 2];
	            level = new int[F * 2];
	            for (int i = 0; i < F * 2; i++) {
	                parent[i] = i;
	                level[i] = 1;
	            }
	 
	            int idx = 0;
	            Map<String, Integer> map = new HashMap<>();
	 
	            for (int i = 0; i < F; i++) {
	                StringTokenizer st = new StringTokenizer(br.readLine());
	                String a = st.nextToken();
	                String b = st.nextToken();
	 
	                // 맵에 넣은 이름이 아니라면 해당 이름 넣고 카운팅
	                if (!map.containsKey(a)) {
	                    map.put(a, idx++);
	                }
	 
	                if (!map.containsKey(b)) {
	                    map.put(b, idx++);
	                }
	 
	                sb.append(union(map.get(a), map.get(b)) + "\n");
	            }
	        }
		System.out.println(sb);
} // main
	
	  public static int find(int x) {
	        if (x == parent[x]) {
	            return x;
	        }
	 
	        return parent[x] = find(parent[x]);
	    }
	 
	    public static int union(int x, int y) {
	        x = find(x);
	        y = find(y);
	 
	        // 항상 x < y인 값이 들어온다고 가정
	        if (x != y) {
	            parent[y] = x;
	            level[x] += level[y]; // y에 있던 층의 개수를 더해 줌.
	 
	            // 어차피 y의 부모는 항상 x이므로 level[y]를 접근할 일은 없으므로 꼭 이렇게 초기화해 줄 필요는 없음.
	            level[y] = 1;
	        }
	 
	        return level[x];
	    }
	 
	}
