import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_13305_주유소 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		String[] split = br.readLine().split(" ");
		long[] distance = new long[split.length];
		long[] city = new long[n];
		for (int i = 0; i < split.length; i++) {
			distance[i] = Long.parseLong(split[i]);
		}
		split = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			city[i] = Long.parseLong(split[i]);
		}
		long curPrice = city[0];
		long sum = 0;
		for (int i = 0; i < city.length-1; i++) {
			if(city[i] <= curPrice) {
				curPrice = city[i];
			}
			sum += curPrice*distance[i];
		}
		System.out.println(sum);
		
	}
}
