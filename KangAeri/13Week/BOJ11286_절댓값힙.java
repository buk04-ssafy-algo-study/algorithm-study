import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class BOJ11286_절댓값힙 {

	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Float> pq = new PriorityQueue<Float>();
		
		int n=0,cmd=0,i=0;
		float x=0;
		n = Integer.parseInt(br.readLine());
		
		for(i=0; i<n; i++) {	
			cmd = Integer.parseInt(br.readLine());
			if(cmd==0) {
				if(pq.isEmpty()) {
					sb.append("0\n");
				} else {
					x = pq.poll();
					if((float)Math.floor(x)<x) {
						cmd=(int)Math.floor(x);
					} else {
						cmd=(int)x*-1;
					}
					sb.append(cmd+"\n");
				}
			} else {
				if(cmd>0) {
					x = (float)cmd + 0.1f;
				} else {
					x = (float)Math.abs(cmd);
				}
				pq.offer(x);
			}	
		}
		
		System.out.println(sb);
	}
	
}
