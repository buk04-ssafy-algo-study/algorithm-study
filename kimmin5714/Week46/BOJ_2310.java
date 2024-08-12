import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Room {
    int num;
    String info;
    int money;
    List<Integer> adjList;

    public Room(int num, String info, int money) {
        this.num = num;
        this.info = info;
        this.money = money;
        this.adjList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "room{" +
                "info=" + info +
                ", money=" + money +
                ", adjList=" + adjList +
                '}';
    }
}

public class BOJ_2310 {
    static List<Room> roomList;
    static boolean isFinished;
    static boolean[] visited;
    static int n2, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        while (true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            n2 = n;
            if (n == 0) break;

            roomList = new ArrayList<>();
            int idx = -1;
            while (n-- > 0) {
                st = new StringTokenizer(br.readLine());
                Room newRoom = new Room(++idx, st.nextToken(), Integer.parseInt(st.nextToken()));

                while (true) {
                    int tmp = Integer.parseInt(st.nextToken());
                    if (tmp == 0) break;
                    newRoom.adjList.add(tmp);
                }
                roomList.add(newRoom);
            }

            //dfs
            isFinished = false;
            visited = new boolean[n2];
            dfs(0,0);

            if(isFinished)
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }

    private static void dfs(int start, int money) {
        if(isFinished) return;
        if(start == n2-1) {
            isFinished = true;
            return;
        }
        for(int i=0;i<roomList.get(start).adjList.size();i++) {
            int nextRoomNum = roomList.get(start).adjList.get(i) - 1;
            if (visited[nextRoomNum])
                continue;
            Room curRoom = roomList.get(nextRoomNum);
            switch (curRoom.info) {
                case "L":
                    if (money < curRoom.money)
                        money = curRoom.money;
                    break;
                case "T":
                    money -= curRoom.money;
                    break;
            }

            if(money<0) return;

            visited[curRoom.num] = true;
            dfs(nextRoomNum, money);
            visited[curRoom.num] = false;
        }
    }
}

