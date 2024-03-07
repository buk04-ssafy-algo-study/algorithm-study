import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_골드4_16434_드래곤앤던전_이분탐색 {
	static class Room {
		int t;
		int a;
		int h;

		public Room(int t, int a, int h) {
			this.t = t;
			this.a = a;
			this.h = h;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int HAtk = Integer.parseInt(str[1]);
		long start = 1, end = 0;

		Room rooms[] = new Room[N];

		for(int i = 0;i < N;i++) {
			str = br.readLine().split(" ");
			int t = Integer.parseInt(str[0]);
			int a = Integer.parseInt(str[1]);
			int h = Integer.parseInt(str[2]);
			rooms[i] = new Room(t, a, h);
			if(t == 1) 
				end += ((long)a * h); //몬스터의 공격력과 체력을 곱한값 = 최대로 필요한 체력
		}

		while(start <= end) {
			long mid = (start + end) / 2;
			long HCurHp = mid; //용사 현재 체력 초기화 
			long tempHAtk = HAtk; //용사 임시 공격력 
			boolean success = true;
			for(int i = 0;i < N;i++) {
				if(rooms[i].t == 1) {
					//몬스터의 생명을 공격력으로 나누어서 계산 
					//용사 선공이라 몬스터한테 공격 안당할 수도 있음
					if(rooms[i].h % tempHAtk == 0) 
						HCurHp -= rooms[i].a * ((rooms[i].h / tempHAtk) - 1);
					else HCurHp -= rooms[i].a * (rooms[i].h / tempHAtk);
					if(HCurHp <= 0) {
						success = false; //용사 죽음
						break;
					}
				}
				else if(rooms[i].t == 2) {
					tempHAtk += rooms[i].a;
					HCurHp += rooms[i].h;
					if(HCurHp > mid) HCurHp = mid;
				}
			}

			if(success) end = mid - 1; //성공했으면 더 작은 최대 체력 탐색
			else start = mid + 1; //실패했으면 더 큰 최대 체력 탐색
		}

		System.out.println(start);
	}

}


