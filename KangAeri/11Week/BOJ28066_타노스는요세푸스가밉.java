import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int i=0, num=0,k=0;
		String[] in= br.readLine().split(" ");
		num = Integer.parseInt(in[0]);
		k = Integer.parseInt(in[1]);
		
		Queue<Integer> que = new ArrayDeque<>();
		
		for(i=1; i<=num; i++) {
			que.offer(i);
		}
		
		while(num>=1) {
			if(num<k) {
				System.out.println(que.poll());
				break;
			} else {
				que.offer(que.poll());
				for(i=1; i<k; i++) {
					que.poll();
				}
				num-=(k-1);
			}	
		}
	}
}