package probs;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA5658 {
	private static StringBuilder sb = new StringBuilder();
	private static int pwsize;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tcase=0, t=0, n=0,k=0, i=0,j=0, idx=0, cnt=0, tmp=0,target=0;
		char tmpch;
		String[] in;
		String line;
		char[] nums;	
		int[] res;
		t = Integer.parseInt(br.readLine());
		
		
		for(tcase=1; tcase<=t; tcase++) {
			sb.append("#"+tcase + " ");
			in = br.readLine().split(" ");
			n = Integer.parseInt(in[0]);
			k = Integer.parseInt(in[1]);
			pwsize = n/4; //length of generated number
			nums = new char[n];	
			res = new int[4*pwsize];
			
			//input
			line = br.readLine();		
			for(i=0;i<n;i++) {
				nums[i] = line.charAt(i);
			}
			
			
			//////compute
			cnt=0;
			idx=0;
			do {
				for(i=0; i<4; i++) {
					tmp=0;
					for(j=0; j<pwsize; j++) { //compute a number
						if(nums[i*pwsize+j]>=65) {
							target=(int)nums[i*pwsize+j] -55;
						} else {
							target = (int)nums[i*pwsize+j] -48;
						}
						tmp+=target * Math.pow(16, pwsize-j-1);
					}
					res[idx++] =tmp;
				}
				//rotate
				tmpch = nums[n-1];
				for(i=n-2; i>=0; i--) {
					nums[i+1] = nums[i];
				}
				nums[0] = tmpch;
				
			}while(++cnt<pwsize);//rotate
			
			Arrays.sort(res);
			cnt=1;
			/*
			for(i=4*pwsize-1; i>=0; i--) {
				System.out.println(res[i]);
			}
			System.out.println("---");
*/
			for(i=4*pwsize-1; i>=0; i--) {////hashset
				if(cnt==k) {
					sb.append(res[i] + "\n");
					break;
				}
				if(i>0 && res[i]==res[i-1]) {
					i--;
				}
				cnt++;
				
			}

			
		}
		
		System.out.println(sb);

	}
}
