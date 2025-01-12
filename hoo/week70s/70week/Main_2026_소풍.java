package SSAFY.study.algo.week70s.week70;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2026_소풍 {

    static class Student {
        int studentNumber;
        int friendNumber;

        public Student(int studentNumber, int friendNumber) {
            this.studentNumber = studentNumber;
            this.friendNumber = friendNumber;
        }

        @Override
        public String toString() { return this.studentNumber + " " + this.friendNumber; }
    }

    static int K, N, F;
    static List<List<Student>> studentList;
    static boolean isFound; // N명인 친구 관계가 찾아졌는 지 여부 저장하는 변수
    static PriorityQueue<Integer> kFriendsQueue;

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 1; i <= N; i++) {
            if (studentList.get(i).size() < K-1) continue;   // 친구가 본인 포함 K명이 애초에 안되면 건너 뜀

            int[] friends = new int[K];
            friends[0] = i;
            boolean[] isChecked = new boolean[N+1];
            isChecked[i] = true;
            findKFriends(i, 1, friends, isChecked);
            if (isFound) break;
        }

        if (kFriendsQueue.isEmpty()) {  // 만약 친구 관계를 못찾았다면 -1 출력하고 종료
            System.out.println(-1);
            return;
        }
        StringBuilder sb = new StringBuilder(); // 정답 출력
        while (!kFriendsQueue.isEmpty()) sb.append(kFriendsQueue.poll()).append("\n");
        System.out.println(sb);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        studentList = new ArrayList<>();
        for (int i = 0; i <= N; i++) studentList.add(new ArrayList<>());    // 일단은 N명의 학생 모두에 대해 친구 리스트 생성

        int studentNumber, friendNumber;
        for (int i = 0; i < F; i++) {
            st = new StringTokenizer(br.readLine());
            studentNumber = Integer.parseInt(st.nextToken());
            friendNumber = Integer.parseInt(st.nextToken());
            studentList.get(studentNumber).add(new Student(studentNumber, friendNumber));   // 상호적인 관계이므로 양방향 모두 저장
            studentList.get(friendNumber).add(new Student(friendNumber, studentNumber));
        }
        for (int i = 1; i <= N; i++) {
            Collections.sort(studentList.get(i), new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {    // 출력 조건에 부합한 값을 내도록 친구들을 오름차순 정렬해줌
                    return o1.friendNumber - o2.friendNumber;
                }
            });
        }
        isFound = false;
        kFriendsQueue = new PriorityQueue<>();
    }

    static void findKFriends(int nowStudentNumber, int friendCount, int[] friends, boolean[] isChecked) {
        if (isFound) return;    // 이미 K명의 친구를 찾았다면 중단
        if (friendCount == K) { // K명의 친구 관계를 이루는 학생들을 찾았다면 찾았음 표시 후 함수 종료
            isFound = true;
            for (int i = 0; i < K; i++) kFriendsQueue.offer(friends[i]);

            return;
        }

        List<Student> nextStudentList = studentList.get(nowStudentNumber);
        for (Student s : nextStudentList) { // 현재 학생의 친구들에 대해
            if (isChecked[s.friendNumber]) continue;   // 현재 학생이나 이미 체크한 친구는 건너 뜀
            if (!isFriend(friends, friendCount, s.friendNumber)) continue;  // 만약 이전 친구들과 친구가 아니라면 이 학생은 건너 뜀

            friends[friendCount] = s.friendNumber;
            isChecked[s.friendNumber] = true;
            findKFriends(s.friendNumber, friendCount+1, friends, isChecked);
            friends[friendCount] = 0;
            isChecked[s.friendNumber] = false;
        }
    }

    static boolean isFriend(int[] friends, int friendCount, int nextStudent) {
        List<Student> nextStudentList = studentList.get(nextStudent);
        for (int i = 0; i < friendCount; i++) {
            boolean isFriend = false;
            for (Student s : nextStudentList) {
                if (s.friendNumber == friends[i]) {
                    isFriend = true;
                    break;
                }
            }
            if (!isFriend) return false;    // 이전까지 네트워크에 친구 아닌 학생 한 명이라도 섞여있으면 이 학생은 소풍 못감
        }

        return true;
    }

}
