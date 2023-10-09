// https://www.acmicpc.net/problem/1541
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

		String expr = in.readLine();
		String[] nums = expr.split("-");

		int answer = 0;
		String[] plusVal = nums[0].split("\\+");
		for (String val : plusVal) {
			answer += Integer.parseInt(val);
		}

		for (int i = 1; i < nums.length; i++) {
			plusVal = nums[i].split("\\+");
			int val = 0;

			for (String num : plusVal) {
				val += Integer.parseInt(num);
			}
			answer -= val;
		}

		out.write(String.valueOf(answer));
		out.flush();
		out.close();
	}
}
