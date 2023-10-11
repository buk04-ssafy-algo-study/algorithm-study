import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split("-");
		int result = 0;
		
		for (int i = 1; i < split.length; i++) {
			String[] sp = split[i].split("\\+");
			int sum = 0;
			for (int j = 0; j < sp.length; j++) {
				sum += Integer.parseInt(sp[j]);
			}
			sum *= -1;
			result += sum;
		}
		String[] sp = split[0].split("\\+");
		int sum = 0;
		for (int j = 0; j < sp.length; j++) {
			sum += Integer.parseInt(sp[j]);
		}
		result += sum;
		System.out.println(result);
		
	}
}