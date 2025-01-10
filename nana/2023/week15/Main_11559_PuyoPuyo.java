import java.io.*;
import java.util.*;

public class Main_11559_PuyoPuyo {

    private static int result;
    private static char[][] field = new char[12][6];
    private static int[][] delta = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};  // 하우좌상
    private static List<Point> puyo;
    private static boolean[][] isVisited;

    private static void colorCount(int x, int y, char color) {

        for (int[] d : delta) {
            int nx = x + d[0];
            int ny = y + d[1];

            // 범위를 벗어나면 지나감
            // 방문한 점이거나, 해당 칸의 색이 파라미터로 받은 color와 다르면 지나감
            if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6) continue;
            if (isVisited[nx][ny] || field[nx][ny] != color) continue;

            isVisited[nx][ny] = true;
            colorCount(nx, ny, color);  // 다음 칸을 검사하기 위해 재귀
            puyo.add(new Point(nx, ny));
        }
    }

    private static void remove() {
        // 뿌요 리스트의 모든 포인트를 받아 해당 값을 빈칸인 . 로 변경
        for (Point p : puyo) {
            field[p.x][p.y] = '.';
        }
        // 모든 리스트를 돌았다면 리스트를 비운다.
        puyo.clear();
    }

    private static void clean() {
        // 해당 칸 아래쪽 모든 칸을 검사 -> 가장 아래로 떨어뜨린다
        for(int i =0; i<6; i++){
            for(int j = 11; j>0; j--){
                if(field[j][i] == '.'){
                    for(int k =j-1; k>=0; k--){
                        if(field[k][i] !='.'){
                            field[j][i] = field[k][i];
                            field[k][i] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        result = 0;
        puyo = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            String str = br.readLine();
            for (int j = 0; j < 6; j++) {
                field[i][j] = str.charAt(j);    // 해당 필드에 값 채워넣기
            }
        }

        while (true) {
            boolean isFinished = true;
            isVisited = new boolean[12][6];

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (field[i][j] != '.') {   // . 이 아니라면 뿌요 위치
                        isVisited[i][j] = true;
                        puyo.add(new Point(i, j));
                        // 뿌요의 첫 위치, 해당 값 (RGBPY) 을 기준으로 4개 이상인지 세는 함수
                        colorCount(i, j, field[i][j]);
                        if (puyo.size() < 4) {
                            // 뿌요 리스트의 사이즈가 4보다 작다면 터질 수 없음 -> 리스트를 비움
                            puyo.clear();
                        } else {
                            // 하나의 뿌요만 선택한거기 때문에 끝나지는 않음
                            isFinished = false;
                            // 뿌요를 없애는 remove 함수 수행
                            remove();
                        }
                    }
                }
            }

            if (isFinished) break;  // 만약 선택 가능한 뿌요를 모두 돌았다면 멈춘다
            clean();    // 뿌요를 다 터트렸다면 모든 뿌요는 중력의 영향으로 정리
            result++;   // 1회가 돌았기 때문에 result++
        }
        System.out.println(result);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
