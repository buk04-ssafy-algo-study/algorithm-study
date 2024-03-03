import java.io.*;
import java.util.*;

public class Main_1043_거짓말 {

    private static int N, M, res;
    private static boolean[] truth, isVisited;
    private static List<Integer>[] people, party;

    private static void makePeople() {
        // 사람들의 관계를 양방향으로 연결해준다.
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < people[i].size(); j++) {
                people[people[i].get(j)].add(i);
            }
        }
    }

    private static void dfs(int index) {
        // 진실을 아는 사람과 연결된 사람들은 모두 진실을 알게 된다.
        isVisited[index] = true;
        truth[index] = true;

        for (int i = 0; i < people[index].size(); i++) {
            if (!isVisited[people[index].get(i)]) {
                dfs(people[index].get(i));
            }
        }
    }

    private static void countParty() {
        for (int i = 1; i <= M; i++) {
            boolean knew = false;
            for (int j = 0; j < party[i].size(); j++) {
                // 파티에 참석한 사람 중 한명이라도 진실을 안다면
                // knew = true
                if (truth[party[i].get(j)]) {
                    knew = true;
                    break;
                }
            }

            // 모두가 진실을 모른다면 과장할 수 있는 파티의 수 +1
            if (!knew) res++;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        res = 0;

        people = new ArrayList[N + 1];
        party = new ArrayList[M + 1];

        // 초기화
        // 사람은 N명, 파티의 수는 M개
        for (int i = 1; i <= N; i++) {
            people[i] = new ArrayList<>();
        }
        for (int i = 1; i <= M; i++) {
            party[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        truth = new boolean[N + 1];
        for (int i = 0; i < n; i++) {
            int temp = Integer.parseInt(st.nextToken());
            // 진실을 아는 사람 체크
            truth[temp] = true;
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()); // 참석자의 수
            int a = Integer.parseInt(st.nextToken());   // 가장 처음 참석자
            party[i].add(a);    // 가장 첫 참석자를 파티에 참석시킴
            for (int j = 0; j < num - 1; j++) {
                int temp = Integer.parseInt(st.nextToken());
                people[a].add(temp);    // 첫 참석자와 관계를 맺어줌
                party[i].add(temp); // 파티에 참석시켜줌
            }
        }

        makePeople();   // 참석자들을 모두 연결

        isVisited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (truth[i]) {
                // 진실을 아는 사람이 있다면 dfs를 통해 모두에게 진실을 알림
                dfs(i);
            }
        }

        countParty();   // 모두 진실을 모르는 파티의 수

        // System.out.println(Arrays.toString(truth));
        System.out.println(res);
    }
}
