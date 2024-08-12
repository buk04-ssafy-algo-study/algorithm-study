// https://www.acmicpc.net/problem/16235
import java.util.*;
import java.io.*;

public class Main {
	static int N;					
	static int M;					
	static int K;					
	static int[][] foods;			
	static int[][] lands;			
	static ArrayList<Tree> trees = new ArrayList<>();		
	static Deque<Integer> deadTrees = new LinkedList<>();
  static class Tree {
            int row;		
            int col;		
            int age;		
            boolean dead;	

            public Tree(int row, int col, int age) {
                this.row = row;
                this.col = col;
                this.age = age;
            }
        }

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
		st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
            
    foods = new int[N][N];
		lands = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				foods[i][j] = Integer.parseInt(st.nextToken());
				lands[i][j] = 5;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken())-1;
      int y = Integer.parseInt(st.nextToken())-1;
      int z = Integer.parseInt(st.nextToken());
      trees.add(new Tree(x, y, z));
		}

		// 나이 순 정렬
		Collections.sort(trees, (t1, t2) -> t1.age - t2.age);
		
		while (K != 0) {
			spring();		
			summer();		
			fall();			
			winter();		
			K--;			
		}

		System.out.println(trees.size());
	}

	public static void spring() {
		for (int i = 0; i < trees.size(); i++) {
			Tree tree = trees.get(i);
			if (lands[tree.row][tree.col] < tree.age) {
				// 나무는 죽음
				deadTrees.add(i);
				continue;
			}
			lands[tree.row][tree.col] -= tree.age;
			tree.age++;
		}
	}

	public static void summer() {
		// 죽은 나무들 하나씩 탐색
		while (!deadTrees.isEmpty()) {
			int deadTreeIndex = deadTrees.pollLast();
			Tree deadTree = trees.get(deadTreeIndex);
			// 죽은 나무의 나이 / 2 만큼 양분 땅에 추가
			int food = deadTree.age / 2;
			lands[deadTree.row][deadTree.col] += food;
			deadTree.dead = true;
		}
	}

	public static void fall() {
		int[] moveRow = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] moveCol = {-1, 0, 1, -1, 1, -1, 0, 1};
		ArrayList<Tree> newTrees = new ArrayList<>();
		for (int p = 0; p < trees.size(); p++) {
			Tree tree = trees.get(p);
			if (tree.dead) { // 죽음
				continue;
			}
			if (tree.age % 5 == 0) {
				for (int i = 0; i < 8; i++) {
					int nr = tree.row + moveRow[i];
					int nc = tree.col + moveCol[i];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
						continue;
					}
					newTrees.add(new Tree(nr, nc, 1));
				}
			}
		}
		// 기존 나무 추가
		for (Tree tree : trees) {
			if (!tree.dead) {
				newTrees.add(tree);
			}
		}
		trees = newTrees;
	}

	public static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				lands[i][j] += foods[i][j];
			}
		}
	}

}
