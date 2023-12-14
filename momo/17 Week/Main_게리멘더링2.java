import java.util.*;
import java.io.*;


public class Main {

  static int[] picks;
  static int min = Integer.MAX_VALUE;
  static int N;
  static int[][] arr;
  static int totalPeople = 0;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());
    picks = new int[2];
    arr = new int[N][N];

    for(int i = 0; i<N;i++){
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j<N; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
        totalPeople += arr[i][j];
      }
    }

    solve();

  }

  static void solve() {
    // 1. 모든 좌표에서 경계의 길이 d1, d2의 경우의 수를 모두 뽑는다.
    for(int i = 0; i<N;i++){
        for(int j = 0; j<N; j++) {
          permutation(0, i,j);
        }
      }
      System.out.println(min);
  }
  
  static void permutation(int depth, int x, int y) {
    if(depth == 2) {
      boolean[][] border = new boolean[N][N];
      int d1 = picks[0];
      int d2 = picks[1];
      if(x + d1 + d2 >= N) return;
      if(y - d1 < 0 || y + d2 >= N) return;
        // 경계선 세팅
        for (int i = 0; i <= d1; i++) {
            border[x + i][y - i] = true;
            border[x + d2 + i][y + d2 - i] = true;
        }

        for (int i = 0; i <= d2; i++) {
            border[x + i][y + i] = true;
            border[x + d1 + i][y - d1 + i] = true;
        }

        int[] peopleSum = new int[5];

        // 1 구역 인구수
        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (border[i][j]) break;
                peopleSum[0] += arr[i][j];
            }
        }

        // 2 구역 인구수
        for (int i = 0; i <= x + d2; i++) {
            for (int j = N - 1; j > y; j--) {
                if (border[i][j]) break;
                peopleSum[1] += arr[i][j];
            }
        }

        // 3 구역 인구수
        for (int i = x + d1; i < N; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if (border[i][j]) break;
                peopleSum[2] += arr[i][j];
            }
        }

        // 4 구역 인구수
        for (int i = x + d2 + 1; i < N; i++) {
            for (int j = N - 1; j >= y - d1 + d2; j--) {
                if (border[i][j]) break;
                peopleSum[3] += arr[i][j];
            }
        }

        // 5 구역 인구수
        peopleSum[4] = totalPeople;

        for (int i = 0; i < 4; i++) {
            peopleSum[4] -= peopleSum[i];
        }

        // 정렬
        Arrays.sort(peopleSum);

        // 최대 - 최소
        min = Math.min(min, peopleSum[4] - peopleSum[0]);
      return;
    }

    for(int i=1; i<N;i ++) {
      picks[depth] = i;
      permutation(depth + 1, x, y);
    }
  }
}
