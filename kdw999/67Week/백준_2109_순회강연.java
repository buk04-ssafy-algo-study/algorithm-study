package Week67;

import java.io.*;
import java.util.*;


public class 백준_2109_순회강연 {
	
	 static int n, maxDay;
	    static List<Schedule> schedules = new ArrayList<>();
	    static boolean[] checked;

	    static class Schedule implements Comparable<Schedule> {
	        int day, pay;

	        public Schedule(int day, int pay) {
	            this.day = day;
	            this.pay = pay;
	        }

	        @Override
	        public int compareTo(Schedule o) {
	            if (o.pay != this.pay) return o.pay - this.pay;
	            return this.day - o.day;
	        }
	    }

	    public static void main(String[] args) throws IOException {
	        init();
	        int result = solve();
	        print(result);
	    }

	    static void init() throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        n = Integer.parseInt(br.readLine());
	        StringTokenizer st;
	        maxDay = 0;

	        for (int i = 0; i < n; i++) {
	            st = new StringTokenizer(br.readLine());
	            int pay = Integer.parseInt(st.nextToken());
	            int day = Integer.parseInt(st.nextToken());
	            maxDay = Math.max(maxDay, day);
	            schedules.add(new Schedule(day, pay));
	        }

	        checked = new boolean[maxDay + 1];
	    }

	    static int solve() {
	        PriorityQueue<Schedule> queue = new PriorityQueue<>(schedules);
	        int totalPay = 0;

	        while (!queue.isEmpty()) {
	            Schedule s = queue.poll();

	            for (int i = s.day; i > 0; i--) {
	                if (!checked[i]) {
	                    checked[i] = true;
	                    totalPay += s.pay;
	                    break;
	                }
	            }
	        }

	        return totalPay;
	    }

	    static void print(int result) {
	        System.out.println(result);
	    }
	}