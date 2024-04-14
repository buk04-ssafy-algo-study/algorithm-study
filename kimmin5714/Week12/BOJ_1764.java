/*
* hashSet Collections.sort 안됨 : list로 변환 
* 시간 초과 : 두번째 String들 받으면서 비교 (for문 사용 수 줄임), list에서 HashSet으로 변경하여 해결
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1764 {
	static int n,m;
	static HashSet<String> arr, arr2;
	static List<String> result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new HashSet<>();
		arr2 = new HashSet<>();

		for(int i=0;i<n;i++) 
			arr.add(br.readLine());
		
		for(int i=0;i<m;i++) {
			String tmp = br.readLine();
			if(arr.contains(tmp)) 
				arr2.add(tmp);
		}
		
		result = new ArrayList<>(arr2);
		Collections.sort(result);

		System.out.println(result.size());
		for(String string3 : result) {
			System.out.println(string3);
		}
	}
}
