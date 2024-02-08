package Week26;

import java.util.*;
import java.io.*;

public class 백준_16434_드래곤앤던전 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		long hAtk = Integer.parseInt(st.nextToken()); // 히어로 어택
		
		long curHp=0; // 현 체
		long maxHp=0; // 최 체
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			// 몬스터 방
			if(t == 1) {
				
				// 몬스터에게 공격받은 횟수
				long monAtkNum=0;
				
				// 용사가 5번 공격하고 피가 남으면 
				// 용사는 6번 공격하고 몬스터가 죽기에 몬스터에게 5번만 공격받음
				if(h % hAtk > 0) {
				    monAtkNum += (h/hAtk);	
				}
				
				// 5번 공격하고 몬스터가 딱 죽으면 용사는 4번의 공격만 받은 상태
				else if(h % hAtk == 0) {
					monAtkNum += (h/hAtk)-1; 
				}
				
				// 몬스터를 잡기위해 소모된 hp
				curHp -= a * monAtkNum;
				
				// 필요 최대 체력 갱신
				maxHp = Math.min(maxHp, curHp);
				
			}
			
			// 포션 방
			else if(t==2) {
				
				// 용사 공격력 증가
				hAtk += a;
				curHp += h;
				
				// 포션은 최대 체력이상으로 회복될 수 없다.
				if(curHp > 0) curHp = 0;
			}
		}
		System.out.println(Math.abs(maxHp)+1);
	}
}
