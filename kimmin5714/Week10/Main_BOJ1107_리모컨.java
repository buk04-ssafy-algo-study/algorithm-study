/* https://www.acmicpc.net/problem/1107
 * 
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_BOJ1107_리모컨 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);

		List<Integer> buttons = new ArrayList<Integer>();
		
		str = br.readLine().split(" ");
		for(int i = 0; i<M; i++) 
			buttons.add(Integer.parseInt(str[i]));

		int ans = Math.abs(100-N);	
		int min = 987654321;
		int cnt = 0;

		for(int i = 0; i<=999999; i++) {

			String str2 = String.valueOf(i);
			boolean chk = true;
			for(int j = 0; j<str2.length(); j++) {
				if(buttons.contains(str2.charAt(j)-'0')) {
					chk=false; 
					break;
				}
			}
			
			if(!chk) continue;

			cnt = str2.length() + Math.abs(i-N);	

			if(cnt < min) min = cnt;
		}	
		if(min < ans) ans = min;
		System.out.println(ans);
	} 
}
