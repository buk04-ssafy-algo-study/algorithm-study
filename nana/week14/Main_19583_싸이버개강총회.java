import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main_19583_싸이버개강총회 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		HashSet<String> member = new HashSet<>();
		HashSet<String> result = new HashSet<>();

		String[] SEQ = br.readLine().split(":| ");

		int S = Integer.parseInt(SEQ[0]) * 100 + Integer.parseInt(SEQ[1]);
		int E = Integer.parseInt(SEQ[2]) * 100 + Integer.parseInt(SEQ[3]);
		int Q = Integer.parseInt(SEQ[4]) * 100 + Integer.parseInt(SEQ[5]);

		String line = "";

		while ((line = br.readLine()) != null && !line.isEmpty()) {

			String[] chat = line.split(":| ");

			int time = Integer.parseInt(chat[0]) * 100 + Integer.parseInt(chat[1]);
			String name = chat[2];

			if (time <= S)
				member.add(name);

			if (time >= E && time <= Q) {
				if (member.contains(name))
					result.add(name);
			}
		}

		System.out.println(result.size());
	}
}
