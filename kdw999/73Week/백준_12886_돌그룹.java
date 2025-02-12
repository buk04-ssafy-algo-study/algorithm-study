package Week73;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int A, B, C;
    public static boolean[][] visited;
    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        if ((A + B + C) % 3 != 0) { // 3개의 합이 3으로 나눠지지 않으면 불가능
            System.out.println("0");
            return;
        }

        simulate();
        System.out.println(answer);
    }

    public static void simulate() {
        Queue<Node> q = new LinkedList<>();

        // 돌 그룹을 초기 상태로 큐에 넣고 방문 처리
        visited = new boolean[2001][2001]; // 2001 크기로 설정
        q.offer(new Node(A, B, C));
        visited[A][B] = true;

        while (!q.isEmpty()) {
            Node temp = q.poll();
            int a = temp.a;
            int b = temp.b;
            int c = temp.c;

            if (a == b && b == c) {
                answer = 1; // 세 값이 같다면 정답은 1
                return;
            }

            // 두 값이 다르면 연산 수행
            if (a != b) {
                int na = a > b ? a - b : a + a;
                int nb = a > b ? b + b : b - a;

                if (!visited[na][nb]) {
                    q.offer(new Node(na, nb, c));
                    visited[na][nb] = true; // 방문 처리
                }
            }

            if (b != c) {
                int nb = b > c ? b - c : b + b;
                int nc = b > c ? c + c : c - b;

                if (!visited[nb][nc]) {
                    q.offer(new Node(a, nb, nc));
                    visited[nb][nc] = true; // 방문 처리
                }
            }

            if (a != c) {
                int na = a > c ? a - c : a + a;
                int nc = a > c ? c + c : c - a;

                if (!visited[na][nc]) {
                    q.offer(new Node(na, b, nc));
                    visited[na][nc] = true; // 방문 처리
                }
            }
        }
    }
}

class Node {
    int a, b, c;

    public Node(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}

