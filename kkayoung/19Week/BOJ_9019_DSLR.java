// https://www.acmicpc.net/problem/9019
import java.io.*;
import java.util.*;

public class Main {	

	static int from, to;
	static Queue<Integer> q;
	static HashMap<Integer, String> map;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;

		// input
		int T = Integer.parseInt(br.readLine());
		for(int tc=0;tc<T;tc++){
			map = new HashMap<>();
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());

			sb.append(bfs()).append("\n");
		}
		
		// output
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static String bfs() {
		StringBuffer buf = new StringBuffer();
		String res = null;
		q = new ArrayDeque<>();
 		q.offer(from);
		map.put(from, new String());

		while(!q.isEmpty()){
			int now = q.poll();
			
			int next = now;
			// D
			next = (now>=5000) ? (2*now)%10000 : 2*now;
			res = enqueue(now, next, q, "D", buf);
			// S
			next = (now==0) ? 9999 : now-1;
			if(res == null) res = enqueue(now, next, q, "S", buf);
			// L
			next = (now/1000)+(now%1000)*10;
			if(res == null) res = enqueue(now, next, q, "L", buf);
			// R
			next = (now%10)*1000+(now/10);
			if(res == null) res = enqueue(now, next, q, "R", buf);

			if(res!=null) return res;
		}
		return null;
	}

	static String enqueue(int now, int next, Queue<Integer> q, String cmd, StringBuffer buf){
		if(map.get(next) != null) return null; // next를 이미 방문함

		buf.setLength(0);
		buf.append(map.get(now)).append(cmd); // next까지 도달하는 경로를 map에 저장
		map.put(next, buf.toString());
		if(next == to) // next와 목표 숫자가 같다면 bfs 종료
			return map.get(next); // 명령어 리턴
		
		q.offer(next);
		return null;
	}
}
