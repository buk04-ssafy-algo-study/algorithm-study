import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Star implements Comparable<Star> {
    int r, c;

    public Star(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public int compareTo(Star o) { // 행 오름차순, 열 오름차순
        if (this.r == o.r)
            return this.c - o.c;
        return this.r - o.r;
    }
}

public class BOJ_14658 {
    private static int N,M,L,K;
    private static List<Star> starList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        starList = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            starList.add(new Star(y,x));
        }

        Collections.sort(starList);

        int maxCnt = 0;
        for (int i = 0; i < K; i++) {
            Star curStar = starList.get(i);
            int r = curStar.r; // 별1의 행 번호

            for(int j=0;j<K;j++) {
                Star nextStar = starList.get(j);
                int nc = nextStar.c; // 별2의 열 번호

                // (별1 행, 별2 열)을 트램펄린의 좌상단으로 간주
                maxCnt = Math.max(maxCnt, countStar(r,nc));
            }
        }
        System.out.print(K - maxCnt);
    }

    private static int countStar(int r, int nc) {
        // 별이 (r,nc)~(r+L,nc+L) 범위에 몇 개 포함되는지
        int cnt = 0;

        for(int i=0;i<K;i++) {
            Star star = starList.get(i);
            if(r <= star.r && star.r <= r+L
                && nc<=star.c && star.c <= nc+L)
                cnt++;
        }

        return cnt;
    }
}