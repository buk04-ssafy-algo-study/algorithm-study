import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 못 풀었슴... 엄청 쉬워보였는데 안풀림.. 무한 루프에 빠짐 ㅠ 
 */
 
public class Main_17837_새로운게임2 {
    static final int WHITE = 0;
    static final int RED = 1;
    static final int BLUE = 2;
    
    static boolean gameFlag;
    
    static int n;
    
    static int[] dCOl = {0,1,-1,0,0};
    static int[] dRow = {0,0,0,1,1};
    
    static Map[][] map;
    static Horse[] horses;
    
    static int blueStack;
    
    static class Horse {
        int row;
        int col;
        int dir;
        Horse up;
        Horse down;
        public Horse(int row, int col, int dir) {
            super();
            this.row = row;
            this.col = col;
            this.dir = dir;
            down = null;
            up = null;
        }
		@Override
		public String toString() {
			return "Horse [row=" + row + ", col=" + col + ", dir=" + dir + ", up=" + up + ", down=" + down + "]";
		}
    }
    static class Map {
        int color;
        Horse horse;
        public Map(int color) {
			super();
			this.color = color;
			this.horse = null;
		}
		@Override
		public String toString() {
			return "Map [color=" + color + ", horse=" + horse + "]";
		}
    }
        
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken()); // 체스판의 크기
        int k = Integer.parseInt(st.nextToken()); // 말의 개수
        
        map = new Map[n+1][n+1];
        for (int i = 0; i < args.length; i++) {
			
		}
        horses = new Horse[k+1];
        // map에 색깔 정보 입력
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                map[i][j] = new Map(Integer.parseInt(st.nextToken()));
            }
        }
        // 말의 정보 입력
        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int row = Integer.parseInt(st.nextToken());
            int col= Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            horses[i] = new Horse(row, col, dir);
        }
        gameFlag = false;
        blueStack = 0;
        int cnt = 0;
        while(true) {
            if(cnt > 1000) break;
            for (int i = 1; i <= k; i++) {
                int dir = horses[i].dir;
                int row = horses[i].row;
                int col = horses[i].col;
                int nextRow = row + dRow[dir];
                int nextCol = col + dRow[dir];
                checkMap(nextRow, nextCol, row, col, horses[i]);
                if(gameFlag) {
                	break;
                }
            }
            cnt++;
        }
        if(gameFlag) {
        	System.out.println(cnt);
        }else {
        	System.out.println(-1);
        }
        
        
    }
    static void checkMap(int nextRow, int nextCol, int row, int col, Horse horse) {
    	
    	if(nextRow < 1 || nextRow > n || nextCol < 1 || nextCol > n) {
    		blueStack++;
        	if(blueStack == 2) {
        		blueStack = 0;
        		return;
        	}
        	horse.dir = swapDir(horse.dir);
        	nextRow = horse.row + dRow[horse.dir];
        	nextCol = horse.col + dCOl[horse.dir];
        	checkMap(nextRow, nextCol, row, col, horse);
        	return;
    	}
    	Map now = map[nextRow][nextCol];
        switch (now.color) {
        case WHITE:
            horse.row = nextRow;
            horse.col = nextCol;
            
            // 이미 칸에 다른 말이 있는 경우
            if(now.horse != null) {
            	Horse topHorse = now.horse;
            	// 칸의 가장 위의 말을 구함
            	while(topHorse.up != null) {
            		topHorse = topHorse.up;
            	}
            	topHorse.up = horse;
            }
            // 칸이 비어있는 경우
            else {
            	now.horse = horse; // 칸에 지금의 말을 넣어준다.
            }
            
            blueStack = 0;
            break;
        case RED:
        	// 이미 칸에 다른 말이 있는 경우
            if(now.horse != null) {
            	Horse topHorse = now.horse;
            	// 칸의 가장 위의 말을 구함
            	while(topHorse.up != null) {
            		topHorse = topHorse.up;
            	}
            	// 현재 말들의 순서를 바꿔줌
            	Horse reversUp = null;
            	Horse upHorse = horse;
            	Horse lastHorse = null;
            	while(upHorse.up != null) {
            		Horse upTmp = horse.up;
            		if(upTmp == null) lastHorse = upHorse;
            		upHorse.up = reversUp;
            		horse.down = upTmp;
            		reversUp = upHorse;
            		upHorse = upTmp;
            	}
            	lastHorse.down = topHorse;
            	topHorse.up = lastHorse;
            }
            // 칸이 비어있는 경우
            else {
            	// 현재 말들의 순서를 바꿔줌
            	Horse reversUp = null;
            	Horse upHorse = horse;
            	Horse lastHorse = null;
            	while(upHorse.up != null) {
            		Horse upTmp = horse.up;
            		if(upTmp == null) lastHorse = upHorse;
            		upHorse.up = reversUp;
            		horse.down = upTmp;
            		reversUp = upHorse;
            		upHorse = upTmp;
            	}
            	now.horse = lastHorse; // 칸에 지금의 말을 넣어준다.
            }
            blueStack = 0;
            break;
        case BLUE:
        	blueStack++;
        	if(blueStack == 2) {
        		blueStack = 0;
        		return;
        	}
        	horse.dir = swapDir(horse.dir);
        	nextRow = horse.row + dRow[horse.dir];
        	nextCol = horse.col + dCOl[horse.dir];
        	checkMap(nextRow, nextCol, row, col, horse);
            break;

        default:
            break;
        }
    }
    static int swapDir(int curDir) {
    	if(curDir == 1) return 2;
    	if(curDir == 2) return 1;
    	if(curDir == 3) return 4;
    	if(curDir == 4) return 3;
    	return 0;
    }
}