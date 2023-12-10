// https://www.acmicpc.net/problem/1043
import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static List<int[]> partyMembers = new ArrayList<>(); // 파티 참여자
	static boolean[] knows;
	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int answer = 0;
		
		// input
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // # of people
		M = Integer.parseInt(st.nextToken()); // # of party
		knows = new boolean[N+1];
		parents = new int[N+1];

		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		for(int i=0;i<t;i++){
			knows[Integer.parseInt(st.nextToken())] = true;
		}
		init();

		for(int i=0;i<M;i++){
			st = new StringTokenizer(br.readLine());
			int peopleCnt = Integer.parseInt(st.nextToken());
			int[] people = new int[peopleCnt];
			for(int c=0;c<peopleCnt;c++){
				people[c] = Integer.parseInt(st.nextToken());
			}
			partyMembers.add(people);
			int idx = 0;
			// union
			while(idx+1<peopleCnt) {
				union(people[idx], people[idx+1]);
				idx++;
			}
		}

		for(int idx=1;idx<=N;idx++) {
			if(knows[find(idx)]){
				knows[idx] = true;
			}
		}

		// 거짓말 가능한 파티 개수 계산
		answer = M;
		for(int partyIdx=0;partyIdx<M;partyIdx++){ // partyIdx번째 파티
			for(int memberIdx:partyMembers.get(partyIdx)){ // 참여자
				if(knows[memberIdx]) {
					answer--;
					break;
				}
			}
		}

		// output
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}

	static int find(int a) {
		if(a==parents[a]) return a;
		else return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b){
		a = find(parents[a]);
		b = find(parents[b]);
		if(a==b) return false;
		if(knows[a]) parents[b] = a;
		else parents[a] = b;
		return true;
	}

	static void init() {
		parents = new int[N+1];
		for(int i=1;i<=N;i++){
			parents[i] = i;
		}
	}
}
