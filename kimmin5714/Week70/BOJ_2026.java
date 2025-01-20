import javax.management.StringValueExp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2026 {
    private static List<Integer>[] friendList;
    private static StringBuilder sb;
    private static int K, N, F;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        friendList = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            friendList[i] = new ArrayList<>();
        }

        for (int i = 0; i < F; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (!friendList[a].contains(b)) {
                friendList[a].add(b);
            }
            if (!friendList[b].contains(a)) {
                friendList[b].add(a);
            }
        }
        for (int i = 1; i <= N; i++) { // 번호가 제일 작은 것 출력하기 위해 오름차순
            Collections.sort(friendList[i]);
        }
        for (int i = 1; i <= N; i++) { // 1~N 학생 친구들 탐색
            List<Integer> friends = friendList[i];
            if (friends.size() < K - 1) continue; // K명 이상이어야 한다..

            List<Integer> res = new ArrayList<>(); // 선발 학생 리스트
            res.add(i);

            findGroup(i, friends, res); // i번째 학생 친구들끼리 묶을 수 있는지

            if (res.size() == K) { // K명 선발된 경우 출력
                Collections.sort(res);
                for (int j = 0; j < res.size(); j++)
                    sb.append(res.get(j) + "\n");
                break;
            }
        }
        if (sb.length() == 0) {
            System.out.print(-1);
        } else {
            System.out.print(sb);
        }
    }

    private static void findGroup(int cur, List<Integer> friends, List<Integer> res) {

        for (int i = 0; i < friends.size(); i++) {
            int friend = friends.get(i);

            if (isFriend(friend, res)) res.add(friend); // 전에 선발되었던 친구들과 모두 친구인지 확인
            if (res.size() == K) return;
        }
    }

    private static boolean isFriend(int friend, List<Integer> res) {
        for (int i = 0; i < res.size(); i++) {
            // 전에 선발된 사람과 한 명이라도 친구가 아닌 경우 false
            if (!friendList[res.get(i)].contains(friend)) return false;
        }
        return true;
    }
}