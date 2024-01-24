package Week24;

//		A=1, B=2, C=3, D=4, E=5, F=6, G=7, H=8, I=9, J=10
//		K=11, L=12;

//		11 13 15 17
//		04 13 22 41
//		04 15 26 37
//		11 22 33 44
//		17 26 35 44
//		31 33 35 37
import java.io.*;
import java.util.*;


class Node{
    int x, y;
    Node(int x, int y){
        this.x=x;
        this.y=y;
    }
}
public class 백준_3976_매직스타 {
	
    static char map[][] = new char[5][9]; // 매직스타 맵
    static boolean visited[] = new boolean[12]; // 사용한 알파벳 체크
    static ArrayList<Node>list = new ArrayList<>(); // x위치 저장리스트
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // A = 65
        
        for(int i=0; i<5; i++) {
            String str = br.readLine();
            
            for(int j=0; j<9; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j]=='x') { // x위치에 알파벳 넣어야하니 위치 기록
                    list.add(new Node(i,j));
                }
                
                else if(map[i][j]!='.') {
                	//  x도 .도 아니라면 초기 알파벳임
                    visited[map[i][j]-65] = true;
                }
            }
        }
        dfs(0);
    }
    public static void dfs(int idx) {
    	
    	// idx는 A ~ L 12번 탐색하는 인덱스
        if(idx == list.size()) { // 12번 탐색했다면
            if(check()) { 
                for(int i=0; i<5; i++) {
                	for(int j=0; j<9; j++) {
                        sb.append(map[i][j]);
                    }
                	sb.append("\n");
                }
                
                System.out.println(sb);
                System.exit(0); // 재귀로 돌아가지않고 답 바로 출력하고 끝내기
            }
            else {
                return;
            }
        }
        for(int i=0; i<12; i++) {   // A~L 
            
            if(!visited[i]) {   // 해당 알파벳이 쓰이지 않았다면 작은 알파벳부터 체크하기
                Node a = list.get(idx);
                char alpha = (char) (65+i); // A = 65, B = 66 ~~~
                map[a.x][a.y]= alpha; // B가 안쓴 숫자라면 저장해놓은 x위치에 B를 넣음, 순서대로 저장해놓은 x위치에 알파벳도 순서대로 넣는 것
                visited[i] = true; // 사용한 알파벳 표시
                dfs(idx+1); // 재귀
                visited[i] = false; // 재귀 사용 후 돌아오면 안쓴 상태로 돌리기
                map[a.x][a.y]= '.'; 
            }
        }
    }
    public static boolean check() {
        int sum1 = (map[0][4]-'A'+1) + (map[1][3]-'A'+1)+ (map[2][2]-'A'+1)+(map[3][1]-'A'+1);
        int sum2 = (map[0][4]-'A'+1)+(map[1][5]-'A'+1)+(map[2][6]-'A'+1)+(map[3][7]-'A'+1);
        int sum3 = (map[1][1]-'A'+1)+(map[1][3]-'A'+1)+(map[1][5]-'A'+1)+(map[1][7]-'A'+1);
        int sum4 = (map[3][1]-'A'+1)+(map[3][3]-'A'+1)+(map[3][5]-'A'+1)+(map[3][7]-'A'+1);
        int sum5 = (map[4][4]-'A'+1)+(map[3][3]-'A'+1)+(map[2][2]-'A'+1)+(map[1][1]-'A'+1);
        int sum6 = (map[4][4]-'A'+1)+(map[3][5]-'A'+1)+(map[2][6]-'A'+1)+(map[1][7]-'A'+1);
        
        //6줄의 합이 다 25이라면
        if(sum1==26 && sum2 ==26 && sum3==26 && sum4==26 && sum5==26 && sum6==26) return true;
        return false;
    }
}