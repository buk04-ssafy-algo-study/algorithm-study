/*
* 1. 우선순위 큐
*   1-1. 클래스에 comparable 구현하여 자동 정렬
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Number implements Comparable<Number>{
	int origin;
	int abs;
	public Number(int origin, int abs) {
		super();
		this.origin = origin;
		this.abs = abs;
	}
	@Override
	public String toString() {
		return "Number [origin=" + origin + ", abs=" + abs + "]";
	}
	@Override
	public int compareTo(Number o) {
		if(this.abs == o.abs)
			return this.origin - o.origin;
		return this.abs - o.abs;
	}
}
public class BOJ_11286 {
	static PriorityQueue<Number> pq = new PriorityQueue<>();
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		for(int i=0;i<n;i++) {
			
			int x = Integer.parseInt(br.readLine());
			
			if(x == 0) {
				if(pq.isEmpty()) 
					System.out.println(0);
				else
					System.out.println(pq.poll().origin);
				continue;
			}
			pq.add(new Number(x, Math.abs(x)));
		}
	}
}
