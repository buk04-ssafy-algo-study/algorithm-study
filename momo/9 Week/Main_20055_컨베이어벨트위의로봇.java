import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_20055_컨베이어벨트위의로봇 {
	static int k;
    static int n;
    static LinkedList<Belt> conve;
    static boolean[] robot;
    static int cnt;
    static class Belt {
    	int negoo;
    	boolean robot;
    	public Belt(int negoo) {
    		this.negoo = negoo;
    		robot = false;
    	}
    	public void robotUp() {
    		robot = true;
    		negoo--;
    	}
    }
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		conve = new LinkedList<>();
		cnt = 0;
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n*2; i++) {
			conve.add(new Belt(Integer.parseInt(st.nextToken())));
		}
		
		while(k>0) {
			MoveConve();
			MoveRobot();
		}
		System.out.println(cnt);
	}
	private static void MoveConve() {
		cnt++;
		conve.add(0, conve.removeLast());
		if(conve.get(n-1).robot) conve.get(n-1).robot = false;
	}
	private static void MoveRobot() {
		for (int i = n-1; i >0; i--) {
			//로봇 없으면
			if(!conve.get(i).robot) continue;
			//로봇있거나 내구도 0
			if(conve.get(i+1).robot || conve.get(i+1).negoo <=0) continue;
			
			// 로봇 다음으로, 내구도 까금
			conve.get(i).robot = false;
			conve.get(i+1).robotUp();
			if(conve.get(i+1).negoo <= 0) k--;
			
			// 내리는 위치면 로봇내림
			if(i+1 == n-1) conve.get(i+1).robot = false;
		}
		// 올리는 위치 내구도 1 이상이면 로봇 올림
		if(conve.get(0).negoo >0) {
			conve.get(0).robotUp();
			if(conve.get(0).negoo <= 0) k--;
		}
	}
}
