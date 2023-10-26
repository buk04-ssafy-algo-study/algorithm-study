import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_카잉달력 {
	static int T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int first = x;
			int last = lcm(N,M);
			
			while(true) {
				if(first > last) {
					sb.append(-1 + "\n");
					break;
				}
				
				else if(((first% N) == 0 ? N : first % N) == y) {
					sb.append(first + "\n");
					break;
				}
				first += M;
			}
		}
		System.out.println(sb);
	}
	
	static int lcm(int a, int b) {
		return a*b/gcd(a,b);
	}
	
	static int gcd(int a, int b) {
		int tmp;
		while(b > 0) {
			tmp = a %b;
			a = b;
			b = tmp;
		}
		return a;
	}
}
