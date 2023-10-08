/* https://www.acmicpc.net/problem/1107
 * 브루트포스
 * 1. 고장나지 않은 버튼으로 타겟 숫자와 제일 유사한 숫자 만든다.
 * 	1-1. 현재 번호 100에서 N까지 가야하므로 100-N을 초기값으로 설정
 * 	1-2. 0 ~ 999999 중 고장난 버튼이 없는 수 선택
 * 2. 유사한 수에서 타겟 숫자로 가려면 + 혹은 -를 몇 번 눌러야 하는 지 계산
 * 	2-1. 유사한 숫자 - 타겟 숫자 : Math.abs(i-N)
 * 	2-2. 최종적으로 누르는 횟수 = Math.abs(i-N) + 유사한 숫자 길이
 *	2-3. 유사한 숫자에서 타겟 숫자로 가는 방법 중 최솟값 갱신
 * 3. 초기 100번에서 +,-만 눌러서 가는 경우와 2-3 경우를 비교하여 최솟값 갱신
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
