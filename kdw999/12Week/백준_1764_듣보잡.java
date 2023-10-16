package algorhitm;

import java.util.*;
import java.io.*;
	public class Main {
		// 듣못 명단에 있는 애랑
		// 보못 명단에 있는 애들을 map 차례대로 넣으면서 중복되는 듣보잡이 있다면 해당 친구의 값을 1로 지정
    // 듣보잡 수 만큼 배열 만들고 듣보잡 애들을 초기화 이후 정렬하고 출력 
		
		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			StringBuilder sb = new StringBuilder();
			
			Map<String, Integer> map = new HashMap<>();
			
			int N = Integer.parseInt(st.nextToken()); // 듣잡
			int M = Integer.parseInt(st.nextToken()); // 보잡
			
			int cnt=0; // 듣보 몇명인지 체크
			
			for(int i=0; i<N+M; i++) {
				String name = br.readLine();
				if(map.containsKey(name)){
					cnt++;
					map.put(name, 1);
				}
				else{
					map.put(name, 0);
				}
				
			}
			
			String[] nerd = new String[cnt];
			
			int idx=0;
			for(String s : map.keySet()) {
				if(map.get(s) == 1) {
					nerd[idx++] = s;
				}
			}
			
			sb.append(nerd.length+"\n");
			Arrays.sort(nerd);
			for(String s : nerd) {
				sb.append(s+"\n");
			}
			System.out.println(sb);
		}
	}
