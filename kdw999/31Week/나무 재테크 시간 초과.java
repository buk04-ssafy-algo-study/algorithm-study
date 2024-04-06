import java.io.*;
import java.util.*;



public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 맵 크기
		int M = Integer.parseInt(st.nextToken()); // 초기 심을 나무 갯수
		int K = Integer.parseInt(st.nextToken()); // K년 후 나무 갯수 검사
		
		// 초기 나무 맵에 저장된 양분들은 5로 초기화
		List<Integer>[][] tree = new ArrayList[N+1][N+1]; // 나무 맵
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				tree[i][j] = new ArrayList<>();
			}
		}
		
		// 초기 땅 맵은 양분이 5로 저장
		int[][] earth = new int[N+1][N+1]; // 양분이 저장된 땅
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				earth[i][j] = 5;
			}
		}
		
		int[][] A = new int[N+1][N+1]; // 겨울 때 땅에 넣을 양분이 적힌 맵
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// M만큼 나무 맵에 초기 나무 위치, 나이 지정
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			
			tree[r][c].add(age);
			
			// 나이 어린 나무부터 양분을 먹이기 위해 오름차순 정렬
//			Collections.sort(tree[r][c]);
		}
		
		while(K > 0) {
			K--;
			
				// 봄 & 여름
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					
					// 해당 위치에 나무가 없으면 다음 탐색
					if(tree[i][j].size() == 0) continue;
					
					// 해당 위치에 있는 나무들 가져와서 어린 나무부터 양분 먹이기
					for(int k=0; k<tree[i][j].size(); k++) {
						int age = tree[i][j].get(k);
						
						// 땅에 있는 양분이 현재 나무 나이보다 많다면, 양분 빼먹고 나무 나이 증가
						if(earth[i][j] >= age) {
							earth[i][j] = earth[i][j] - age;
							tree[i][j].set(k, age+1);
						}
						
						// 양분을 못먹은 나무는 즉사
						else {
							// 즉사한 나무는 겨울 때 양분 추가할 배열에 저장
							int energy = tree[i][j].get(k)/2;
							A[i][j] += energy;
							
							tree[i][j].remove(k);
							k--;
						}
					}
				}
			}
				
			// 가을은 나무 맵만 건드리고 겨울은 땅 맵에 양분만 추가하는 별개의 작업이라 같이 진행
				// 가을 & 겨울
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					
					// 땅에 양분 추가
					earth[i][j] += A[i][j];
					
					// 해당 위치에 나무가 없으면 다음 탐색
					if(tree[i][j].size() == 0) continue;
					
					for(int k=0; k<tree[i][j].size(); k++) {
						
						// 나무 나이 5배수 확인
						if(tree[i][j].get(k) % 5 == 0) {
							
							// 8방향에 아기 나무 추가, 양분 어린 순으로 먹이기 위해 정렬
							if(i-1>0 && j-1>0) {
								tree[i-1][j-1].add(1);
								Collections.sort(tree[i-1][j-1]);
							}
							if(i-1>0) {
								tree[i-1][j].add(1);
								Collections.sort(tree[i-1][j]);
							}
							if(i-1>0 && j+1<=N) {
								tree[i-1][j+1].add(1);
								Collections.sort(tree[i-1][j+1]);
							}
							if(j-1>0) {
								tree[i][j-1].add(1);
								Collections.sort(tree[i][j-1]);
							}
							if(j+1<=N) {
								tree[i][j+1].add(1);
								Collections.sort(tree[i][j+1]);
							}
							if(i+1<=N && j-1>0) {
								tree[i+1][j-1].add(1);
								Collections.sort(tree[i+1][j-1]);
							}
							if(i+1<=N) {
								tree[i+1][j].add(1);
								Collections.sort(tree[i+1][j]);
							}
							if(i+1<=N && j+1<=N) {
								tree[i+1][j+1].add(1);
								Collections.sort(tree[i+1][j+1]);
							}
						}
					}
				}
			}
				
		}// while
		
		int treeNum=0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				treeNum += tree[i][j].size();
			}
		}
		System.out.println(treeNum);
	}
}