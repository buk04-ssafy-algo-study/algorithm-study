package SSAFY.study.algo.week70s.week70;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2666_벽장문의이동 {

    static int n;
    static int[] doors; // 문의 개폐 여부를 나타내는 배열
    static int useSequenceCount;
    static int[] useSequence;   // 사용할 문을 순서대로 저장한 리스트
    static int minCount;
    static int MAX_MOVES = 100;   // 벽장문을 움직이는 최대 횟수, 널널하게 100으로 잡음, 오버플로우 날까봐 Integer.MAX_VALUE 안씀

    public static void main(String[] args) throws IOException {
        init();
        calcMinCount(0, 0, Arrays.copyOf(doors, n));
        System.out.println(minCount);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int openDoor = Integer.parseInt(st.nextToken())-1;

        doors = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == openDoor) {    // 열려있는 지 여부만 저장
                doors[i] = 1;   // 열린 문 1로 체크
                if (st.hasMoreTokens()) openDoor = Integer.parseInt(st.nextToken())-1;
            }
        }
        useSequenceCount = Integer.parseInt(br.readLine());
        useSequence = new int[useSequenceCount];
        for (int i = 0; i < useSequenceCount; i++) useSequence[i] = Integer.parseInt(br.readLine())-1;
        minCount = Integer.MAX_VALUE;
    }

    static void calcMinCount(int useSequenceIndex, int moveCount, int[] nowDoors) { // prev 정보는 이전에 움직인 것과 똑같은 문을 반대로 움직이는 것을 방지하기 위해 사용
        if (useSequenceIndex == useSequenceCount) { // 기저, 모든 문을 다 사용해본 경우
            minCount = Math.min(minCount, moveCount);
            return;
        }

        int nowDoor = useSequence[useSequenceIndex];
        if (nowDoors[nowDoor] == 1) calcMinCount(useSequenceIndex+1, moveCount, Arrays.copyOf(nowDoors, n));  // 만약 지금 문이 그냥 열려있는 문이면 바로 다음 재귀 진행시킴

        int leftDoorIndex = -1*MAX_MOVES;  // 지금 문으로부터 왼쪽에 있는 문 중 가장 가까운 열린 문의 인덱스
        int rightDoorIndex = MAX_MOVES; // 오른 쪽
        for (int i = Math.max(0, nowDoor-1); i >= 0; i--) { // 지금 문으로부터 왼쪽 문들 체크
            if (nowDoors[i] == 1) {
                leftDoorIndex = i;
                break;
            }
        }
        for (int i = Math.min(nowDoor+1, n-1); i < n; i++) {    // 지금 문으로부터 오른쪽 문들 체크
            if (nowDoors[i] == 1) {
                rightDoorIndex = i;
                break;
            }
        }

        if (leftDoorIndex != -1*MAX_MOVES && rightDoorIndex != MAX_MOVES) { // 현재 벽장문 양쪽에 열린 문이 있는 경우, 둘 다 보내줌
            calcMinCount(useSequenceIndex+1, moveCount+(nowDoor-leftDoorIndex), makeMovedDoors(nowDoors, leftDoorIndex, nowDoor));
            calcMinCount(useSequenceIndex+1, moveCount+(rightDoorIndex-nowDoor), makeMovedDoors(nowDoors, rightDoorIndex, nowDoor));
        } else if (leftDoorIndex != -1*MAX_MOVES) {   // 왼쪽에만 열린 문이 있는 경우 그 중 가장 가까운 문으로
            calcMinCount(useSequenceIndex+1, moveCount+(nowDoor-leftDoorIndex), makeMovedDoors(nowDoors, leftDoorIndex, nowDoor));
        } else if (rightDoorIndex != MAX_MOVES) {  // 오른쪽에만 ~
            calcMinCount(useSequenceIndex+1, moveCount+(rightDoorIndex-nowDoor), makeMovedDoors(nowDoors, rightDoorIndex, nowDoor));
        }
    }

    static int[] makeMovedDoors(int[] nowDoors, int closeDoor, int openDoor) {
        int[] movedDoors = Arrays.copyOf(nowDoors, n);
        movedDoors[closeDoor] = 0;
        movedDoors[openDoor] = 1;

        return movedDoors;
    }

}
