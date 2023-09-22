import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ_14425_문자열집함 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int n = Integer.parseInt(temp[0]);
		int m = Integer.parseInt(temp[1]);

		Map<String, Integer> str = new HashMap<>();
		List<String> list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			str.put(br.readLine(),i);
		}

		for (int i = 0; i < m; i++) {
			list.add(br.readLine());
		}
		int cnt = 0;
		for (String string : list) {
			if(str.containsKey(string)) cnt++;
		}
		System.out.println(cnt);
	}
}