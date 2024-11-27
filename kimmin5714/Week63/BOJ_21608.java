import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point implements Comparable<Point> {
    int r, c, friend, blank;

    public Point(int r, int c, int friend, int blank) {
        this.r = r;
        this.c = c;
        this.friend = friend;
        this.blank = blank;
    }

    @Override
    public String toString() {
        return "Point{" +
                "r=" + r +
                ", c=" + c +
                ", friend=" + friend +
                ", blank=" + blank +
                '}';
    }

    @Override
    public int compareTo(Point o) { // 문제 조건에 맞도록 정렬
        if (this.friend == o.friend) {
            if (this.blank == o.blank) {
                if (this.r == o.r) {
                    return this.c - o.c; 
                }
                return this.r - o.r;
            }
            return o.blank - this.blank;
        }
        return o.friend - this.friend;
    }
}

public class BOJ_21608 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int res = 0;

        HashMap<Integer, int[]> likeFriend = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            list.add(num);

            int[] friends = new int[4];
            friends[0] = Integer.parseInt(st.nextToken());
            friends[1] = Integer.parseInt(st.nextToken());
            friends[2] = Integer.parseInt(st.nextToken());
            friends[3] = Integer.parseInt(st.nextToken());
            likeFriend.put(num, friends);
        }

        int[][] arr = new int[N][N];
        for (int i = 0; i < list.size(); i++) { // 학생 순서대로 탐색
            int curNum = list.get(i);
            int[] friends = likeFriend.get(curNum);

            // 비어있는 칸에 인접한 좋아하는 학생 수, 빈 칸 카운트
            List<Point> blanks = findBlank(N, arr, friends);
            Collections.sort(blanks); // 문제 조건에 맞게 정렬

            arr[blanks.get(0).r][blanks.get(0).c] = curNum; // 가장 적절한 자리에 학생 앉히기
        }

        for (int i = 0; i < list.size(); i++) { // 만족도 조사
            int curNum = list.get(i);
            int[] friends = likeFriend.get(curNum);

            int friendCnt = findFriendCnt(N, arr, curNum, friends); // 인접 칸에 좋아하는 학생 수 카운트
            if (friendCnt == 1) res++;
            else if (friendCnt == 2) res += 10;
            else if (friendCnt == 3) res += 100;
            else if (friendCnt == 4) res += 1000;
        }
        System.out.print(res);
    }

    private static int findFriendCnt(int N, int[][] arr, int num, int[] friends) { // 인접칸의 좋아하는 학생수 카운트
        int[] delr = {-1, 1, 0, 0};
        int[] delc = {0, 0, -1, 1};
        int friendCnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] != num) continue;
                for (int k = 0; k < 4; k++) {
                    int nr = i + delr[k];
                    int nc = j + delc[k];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    for (int l = 0; l < 4; l++)
                        if (arr[nr][nc] == friends[l]) friendCnt++;
                }
            }
        }
        return friendCnt;
    }

    private static List<Point> findBlank(int N, int[][] arr, int[] friends) { // 빈칸에 인접한 칸들의 정보 저장
        int[] delr = {-1, 1, 0, 0};
        int[] delc = {0, 0, -1, 1};

        List<Point> blankList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] != 0) continue;

                int blankCnt = 0, friendCnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nr = i + delr[k];
                    int nc = j + delc[k];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    if (arr[nr][nc] == 0) blankCnt++;
                    for (int l = 0; l < 4; l++)
                        if (arr[nr][nc] == friends[l]) friendCnt++;
                }
                blankList.add(new Point(i, j, friendCnt, blankCnt));
            }
        }
        return blankList;
    }
}