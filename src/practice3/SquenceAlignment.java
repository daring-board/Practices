package practice3;

public class SquenceAlignment {

	// gap cost
	private static final int delta = 2;
	private static final String ch = "aiueo";

	public static void main(String args[]){
		System.out.println("Minimum Cost is "+ alignment(args[0], args[1]));
	}

	//calc optimal alignment by DynamicPrograming
	private static int alignment(String x, String y){
		int m = x.length();
		int n = y.length();
		int[][] opt = new int[m+1][n+1];
		for(int i = 0;i <= m;i++) opt[i][0] = delta*i;
		for(int j = 0;j <= n;j++) opt[0][j] = delta*j;

		int mismatch = 0;
		for(int i = 1;i <= m;i++){
			for(int j = 1;j <= n;j++){
				mismatch = calcMisMatchCost(x.charAt(i-1), y.charAt(j-1));
				int cost1 = mismatch + opt[i-1][j-1];
				int cost2 = delta + opt[i-1][j];
				int cost3 = delta + opt[i][j-1];
				opt[i][j] = Math.min(cost1, cost2);
				opt[i][j] = Math.min(cost3, opt[i][j]);
			}
		}
		return(opt[m][n]);
	}

	// mismatch cost
	private static int calcMisMatchCost(char a, char b){
		boolean flag1 = ch.matches(".*" + a + ".*");
		boolean flag2 = ch.matches(".*" + b + ".*");
		if(a != b){
			if(flag1 && flag2) return 1;
			else if(!flag1 && !flag2) return 1;
		    else return 3;
		}
		return(0);
	}
}
