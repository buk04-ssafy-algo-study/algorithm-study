package SSAFY.study.algo.week20s.week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16434_드래곤앤던전 {

    static class Knight {   // 용사의 정보를 나타내는 클래스
        long maxHp;
        long curHp;
        long atk;

        public Knight(long maxHp, long curHp, long atk) {
            this.maxHp = maxHp;
            this.curHp = curHp;
            this.atk = atk;
        }

        public void gotAttack(Room r) {   // 용에게 공격당할 때 사용하는 함수
            this.curHp = this.curHp - (long) r.a;  // 드래곤의 공격력 만큼 체력 감소 처리
//            System.out.println("용사 공격당함, 남은 체력: " + this.curHp);
        }

        public void getPortion(Room r) {
            this.curHp = Math.min(this.maxHp, this.curHp + r.h);  // 체력 증가시킴
            this.atk = this.atk + (long) r.a;    // 공격력 증가시킴
        }
    }

    static class Room { // 방의 정보를 나타내는 클래스
        int info;   // 1: 몬스터, 2: 포션
        int a;  // 몬스터일 경우 공격력, 포션일 경우 용사의 공격력 증가치
        long h;  // 몬스터일 경우 체력, 포션일 경우 용사의 체력 증가치

        public Room(int info, int a, long h) {
            this.info = info;
            this.a = a;
            this.h = h;
        }

        public void gotAttack(Knight k) {   // 몬스터일 경우 용사한테 공격당할 때 사용하는 함수
            this.h -= k.atk;
//            System.out.println("용 공격당함, 남은 체력: " + this.h);
        }
    }

    static int N;
    static Room[] rooms;

    public static void main(String[] args) throws IOException {
        long initialKnight = init();
        System.out.println(calcMaxHealth(initialKnight));
    }

    static long init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        long initialKnightAtk = Long.parseLong(st.nextToken());  // 일단 최대치를 모르므로 임시로 처리
        rooms = new Room[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            rooms[i] = new Room(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken()));
        }

        return initialKnightAtk;
    }

    static public long calcMaxHealth(long initialKnightAtk) {
        long minMaxHealth = Long.MAX_VALUE;
        long left = 0;
        long right = Long.MAX_VALUE;
        long mid, remainHealth;
        while (left <= right) {
            mid = (left+right) / 2L;
//            System.out.println(left + " " + right);
//            System.out.println(mid);
            remainHealth = doSimulation(new Knight(mid, mid, initialKnightAtk));
//            System.out.println(remainHealth);
//            System.out.println("--------------");
            if (remainHealth > 0) { // 남은 체력이 많으면 우측 포인터 감소 처리
                minMaxHealth = Math.min(minMaxHealth, mid);
                right = mid-1;
            } else left = mid+1;
        }

        return minMaxHealth;
    }

    static long doSimulation(Knight knight) {
        for (int i = 0; i < N; i++) {
            if (rooms[i].info == 1) {
                long originDragonHp = rooms[i].h;  // 용의 원래 체력, 전투 종료 시 원상복구를 위함
                knight = fightWithDragon(knight, rooms[i]);
                rooms[i].h = originDragonHp;    // 체력 원상복구

                if (knight.curHp <= 0) return knight.curHp;  // 용사가 드래곤과의 전투 후 결과가 체력이 0 이하라면 사망한 것임.
            }
            else knight.getPortion(rooms[i]);
//            System.out.println("방에서의 결과: " + knight.curHp + " " + knight.atk);
        }

        return knight.curHp;    // 기사의 남은 체력을 반환
    }

    static Knight fightWithDragon(Knight knight, Room room) {
//        while (true) {
//            room.gotAttack(knight);
//            if (room.h <= 0) break;  // 공격받은 몬스터의 체력이 0이하가 되면 전투 종료
//
//            knight.gotAttack(room);
//            if (knight.curHp <= 0) break;   // 몬스터의 공격을 받고 용사의 체력이 0이하가 되면 전투 종료
//        }
        long monsterNeedAtk = (long) Math.ceil((double) knight.curHp / room.a); // 각 상대를 쓰러뜨리는 데 필요한 공격의 횟수. 소수점 때문에 계산이 조금 난잡하긴 한데 이렇게 해결해야 정확한 값이 나옴
        long knightNeedAtk = (long) Math.ceil((double) room.h / knight.atk);
        if (monsterNeedAtk < knightNeedAtk) {   // 용이 더 적은 횟수로 용사를 쓰러뜨릴 수 있는 경우
            knight.curHp -= room.a * monsterNeedAtk;
        } else {    // 그 외의 경우는 용사가 용을 쓰러뜨릴 수 있는 경우임
            knight.curHp -= room.a * (knightNeedAtk-1);
        }

        return knight;
    }

}
