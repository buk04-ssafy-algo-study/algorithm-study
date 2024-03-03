import java.util.*;

public class Solution {

	private static int[][] maze;
	private static int n, m, k, startX, startY, endX, endY;
	private static String result;
	private static boolean flag;
	private static int[] index;
	private static int[][] delta = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };
	// down, left, right, up

	private static void comb(int count) {

		if (count == k) {
			if (!flag) {
				if (mazeEscape()) {
					result = Arrays.toString(index);
					flag = true;
				}
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			index[count] = i;
			if (!flag) {
				comb(count + 1);
			}
		}
	}

	private static boolean mazeEscape() {

		int x = startX;
		int y = startY;

		for (int i = 0; i < k; i++) {
			int d = index[i];

			int nx = x + delta[d][0];
			int ny = y + delta[d][1];

			if (nx <= 0 || ny <= 0 || nx > n || ny > m)
				return false;

			x = nx;
			y = ny;
		}

		if (x == endX && y == endY)
			return true;
		else
			return false;

	}

	public String solution(int n, int m, int x, int y, int r, int c, int k) {
		String answer = "";

		this.n = n;
		this.startX = x;
		this.startY = y;
		this.endX = r;
		this.endY = c;
		this.m = m;
		this.k = k;

		// n, m 미로의 크기 | x, y 출발 지점 | r, c 탈출 지점 | k 문자열의 길이
		maze = new int[n + 1][m + 1];
		index = new int[k];

		Arrays.fill(index, Integer.MAX_VALUE);

		String comp = index.toString();
		result = index.toString();

		comb(0);

		// result의 숫자를 문자로 바꾸는 작업
		if (comp.equals(result)) {
			answer = "impossible";
		} else {
			for (int i = 0; i < result.length(); i++) {
				char ch = result.charAt(i);
				if (ch == '0')
					answer += 'd';
				else if (ch == '1')
					answer += 'l';
				else if (ch == '2')
					answer += 'r';
				else if (ch == '3')
					answer += 'u';
			}
		}

		return answer;
	}
}
