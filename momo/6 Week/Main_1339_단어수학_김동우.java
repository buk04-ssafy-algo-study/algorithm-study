package 스터디_6주차;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1339_단어수학_김동우 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		String[] input = new String[N];
		int [] alpha = new int[26];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < str.length(); j++) {
				alpha[str.charAt(j)-'A'] += Math.pow(10, str.length()-1-j);
			}
		}
		Arrays.sort(alpha);
		int ans = 0;
		int number = 9;
		for (int i = alpha.length-1; i >= 0; i--) {
			if(alpha[i] == 0) continue;
			ans += alpha[i] * number--;
		}
		System.out.println(ans);
		
	} // main END
}
