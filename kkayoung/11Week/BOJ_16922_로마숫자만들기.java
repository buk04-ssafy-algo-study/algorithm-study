// https://www.acmicpc.net/problem/16922
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class Main {

	static Set<Integer> nums;
	static int N;
	static int[] roman = { 1, 5, 10, 50 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(in.readLine());
		nums = new HashSet<>(); // 문자 개수 N개를 사용해서 만든 숫자들의 집합

		comb(0, 0, 0); // 중복 조합

		out.write(String.valueOf(nums.size()));
		out.flush();
		out.close();

	}

	static void comb(int cnt, int start, int num) {
		if (cnt == N) {
			nums.add(num);
			return;
		}

		for (int i = start; i < 4; i++) {
			comb(cnt + 1, i, num + roman[i]);
		}
	}
}
