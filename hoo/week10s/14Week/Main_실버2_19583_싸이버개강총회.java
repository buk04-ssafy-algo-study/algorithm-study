package SSAFY.study.algo.week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main_실버2_19583_싸이버개강총회 {

    static String start, endMeeting, endStreaming;

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = st.nextToken();
        endMeeting = st.nextToken();
        endStreaming = st.nextToken();

        System.out.println(checkAttendance(br));
    }

    static int checkAttendance(BufferedReader br) throws IOException, ParseException {
        Map<String, Integer> attendanceMap = new HashMap<>();
        HashSet<String> attendanceSet = new HashSet<>();

        String input;
        while ((input = br.readLine()) != null && input.length() != 0) {    // 입력 끝 처리하기
            String[] inputArr = input.split(" ");
            String time = inputArr[0];
            String name = inputArr[1];
            if (start.compareTo(time) >= 0) {   // 출석 조건 : 개강총회 시작 전 채팅 시작
                attendanceMap.put(name, 1);
            } else if (time.compareTo(endMeeting) >= 0 && endStreaming.compareTo(time) >= 0) {   // 출석 조건 : 개강총회 종료 후 ~ 스트리밍 종료 전 채팅
                int isAttendance = attendanceMap.getOrDefault(name, 0);
                if (isAttendance != 0) attendanceSet.add(name);    // 0이 아니면 개총 시작 전 출석 남긴 사람, 출석 처리
            }
        }

        return attendanceSet.size();
    }

}
