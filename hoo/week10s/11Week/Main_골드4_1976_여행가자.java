package study.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_���4_1976_���డ�� {

	static int N, M;
    static int[][] cities;  // ���� �� ���� ����
    static int[] plan;  // ���� ��ȹ

    public static void main(String[] args) throws IOException {
        init();
        boolean answer = travel();
        if (answer) {
            System.out.println("YES");
            return;
        }

        System.out.println("NO");
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        cities = new int[N+1][N+1]; // ���� ��ȣ�� 1������ �����ϹǷ�
        plan = new int[M];
        StringTokenizer st;
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N+1; j++) {
            	cities[i][j] = Integer.parseInt(st.nextToken());
            	if (i == j) cities[i][j] = 1;
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) plan[i] = Integer.parseInt(st.nextToken());
    }

    static boolean bfs(int from, int to) {  // from ���ÿ��� to ���÷� �� �� �ִ��� �Ǻ� -> ����Ŭ ������ ��
        boolean isPossible = false; // �ش� ���÷� �� �� �ִ��� ���� �Ǵ�, �� �� ���� �� true�� �� ����
        boolean[] isVisited = new boolean[N+1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(from);
        isVisited[from] = true;

        while (!queue.isEmpty()) {
            if (isPossible) break;  // ������ ���÷� �� �� �ִٰ� �Ǵܵ����� �ݺ��� ����
            int nowCity = queue.poll();
            for (int i = 1; i < cities[nowCity].length; i++) {  // ���� ���ö� ����� ���� ���� Ȯ��
                int nextCityConnected = cities[nowCity][i];
                if (nextCityConnected == 1) {    // ����� ���ø� ť�� ����, �ڱ� �ڽ��� �ٽ� ���ļ� �ٸ� ���÷� ���� ��쵵 �����Ƿ� �ش� ��� üũ -> ���� �ٽ� �ϱ�
                    if (i == to) {  // �� ���߿� ������ �����̸� �÷��� true�� �ٲٰ� �ݺ��� ����
                        isPossible = true;
                        break;
                    }
                    if (!isVisited[i]) {
                        queue.offer(i);
                        isVisited[i] = true;
                    }
                }
            }
        }

        return isPossible;
    }

    static boolean travel() {
        boolean isPossible = true;
        for (int i = 0; i < M-1; i++) {
            int from = plan[i];
            int to = plan[i+1];
            isPossible = bfs(from, to);
            if (isPossible == false) break;
        }

        return isPossible;
    }

	
}
