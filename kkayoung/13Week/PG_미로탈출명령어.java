// https://school.programmers.co.kr/learn/courses/30/lessons/150365
// 사전 순서(Down -> Left -> Right -> Up)로 탐색한다
class Solution {
    
    static String[] dirStr = {"d","l","r","u"}; // 사전순
    static int[][] dir = {{1,0},{0,-1},{0,1},{-1,0}}; // d l r u
    static StringBuilder answer = new StringBuilder();
    static boolean found = false;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int dist = getDist(x,y,r,c); // 맨해튼 거리
        if(dist>k || dist%2!=k%2) return "impossible";
        
        find(n,m,x,y,r,c,k,""); // 격자크기 nm, 출발위치 xy, 탈출지점 rc, 이동거리 k, 
        return answer.toString();
    }
    
    static boolean available(int n, int m, int nowx, int nowy) {
		if (1 <= nowx && nowx <= n && 1 <= nowy && nowy <= m)
			return true;
		return false; // out of range
	}
    
    static int getDist(int x, int y, int r, int c){
        return Math.abs(x-r)+Math.abs(y-c);
    }
    
    static void find(int n, int m, int x, int y, int r, int c, int k, String path){
        if(getDist(x,y,r,c)>k || getDist(x,y,r,c)%2!=k%2) return;
        if(k==0){ // 더 이상 이동 불가
            if(x==r && y==c){
                answer.append(path);
                found = true;
            }
            return;
        }
        
        for(int i=0;i<4;i++){
            int nx = x+dir[i][0];
            int ny = y+dir[i][1];
            if(available(n, m, nx, ny) && !found){
                find(n,m,nx,ny,r,c,k-1,path+dirStr[i]);
            };
        }       
    }
}
