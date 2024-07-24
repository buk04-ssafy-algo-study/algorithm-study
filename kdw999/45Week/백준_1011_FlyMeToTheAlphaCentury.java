import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int num = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i<num ; i++) {
			String[] str = br.readLine().split(" ");
			long ly = Long.parseLong(str[1])-Long.parseLong(str[0]);
			int max = (int)Math.sqrt(ly);
			
			if(max == Math.sqrt(ly)) {
				sb.append(max*2-1).append("\n");
			}else if(ly <= (max*max + max)) {
				sb.append(max*2).append("\n");
			}else {
				sb.append(max*2+1).append("\n");
			}
		}
		System.out.println(sb.toString());
		}
		}