import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18405_2 {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Virus> queue = new ArrayDeque<>();
		
		N = Integer.parseInt(st.nextToken());
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int current = Integer.parseInt(st.nextToken());
				if(current == 0) continue;				
				
				queue.add(new Virus(r, c, current));
			}
		}
		
		
		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken())-1;
		int c = Integer.parseInt(st.nextToken())-1;
		
		int minDistance = 201;
		int minValue = 1001;
		boolean isExist = false;
		while(!queue.isEmpty()) {
			Virus virus = queue.poll();
			
			int distance = Math.abs(r - virus.r) + Math.abs(c - virus.c);
			if(distance > t) {
				continue;
			}
			
			if(distance > minDistance) {
				continue;
			}
			
			if(distance < minDistance) {
				minDistance = distance;
				minValue = virus.value;
			} else {
				if(minValue > virus.value) {
					minValue = virus.value;
				}
			}
			
			isExist = true;
			
		}

		if(isExist) {
			System.out.print(minValue);
		} else {
			System.out.print(0);
		}
	}
}


class Virus {
	
	int r;
	int c;
	int value;
	int distance;
	
	public Virus(int r, int c, int value) {
		this.r = r;
		this.c = c;
		this.value = value;
	}
}