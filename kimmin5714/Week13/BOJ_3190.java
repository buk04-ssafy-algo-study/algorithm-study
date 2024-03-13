package Week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Position{
    int r,c;    

    public Position(int r, int c) {
        super();
        this.r = r;
        this.c = c;
    }

    @Override
    public String toString() {
        return "Position [r=" + r + ", c=" + c + "]";
    }
}
class Dir{
    int x;
    char c;
    public Dir(int x, char c) {
        super();
        this.x = x;
        this.c = c;
    }
    @Override
    public String toString() {
        return "Dir [x=" + x + ", c=" + c + "]"+"\n";
    }
}
public class BOJ_3190 {
    static int n, k, L, d=0, time;
    static int delr[] = {0,1,0,-1}, delc[] = {1,0,-1,0}; //우하좌상
    static Stack<Position> snakeMove;
    static Queue<Dir>changeDir;
    static List<Position> apple;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        apple = new ArrayList<>();
        changeDir = new ArrayDeque<>();
        
        for(int i=0;i<k;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            apple.add(new Position(r-1, c-1));
        }

        L = Integer.parseInt(br.readLine());
        for(int i=0;i<L;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            changeDir.add(new Dir(x,c));
        }

        snakeMove = new Stack<>();
        snakeMove.add(new Position(0,0));

        play();

    }
    
    private static void play() {
        while(true) {
            Position cur = snakeMove.lastElement();
             if(!changeDir.isEmpty() && time == changeDir.peek().x) {
                char newDir = changeDir.poll().c;
                
                switch(newDir) {
                case 'L':
                    d = (d+3)%4;
                    break;
                case 'D':
                    d = (d+1)%4;
                    break;
                }
            }
            int nr = cur.r+delr[d];
            int nc = cur.c+delc[d];

            if(nr<0 || nr>=n || nc<0 || nc>=n) break;
            if(crushSnake(nr,nc)) break;

            if(eatApple(nr,nc))
                snakeMove.add(new Position(nr,nc));
            else {
                snakeMove.remove(0);
                snakeMove.add(new Position(nr,nc));
            }
            
            time++;
        }
        System.out.println(time+1);
    }
    private static boolean crushSnake(int r, int c) {
    	Position tmp;
        
    	for(int i=0;i<snakeMove.size();i++) {
            tmp = snakeMove.get(i);
            if(tmp.r == r && tmp.c == c) return true;
        }
        return false;
    }
    private static boolean eatApple(int r, int c) {
        for(int i=apple.size()-1;i>=0;i--) {
            if(apple.get(i).r == r && apple.get(i).c == c) {
                apple.remove(i);
                return true;
            }
        }
        return false;
    }
}
