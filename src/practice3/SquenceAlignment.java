package practice3;

public class SquenceAlignment {

	// gap cost
	private static final int delta = 10;
	// mismatch cost
	private static final int alpha = 3;

	public static void main(String args[]){
		System.out.println("Minimum Cost is "+ alignment(args[0], args[1]));
	}

	private static int alignment(String x, String y){
		int[][] opt = new int[x.length()+1][y.length()+1];
		for(int i = 0;i <= x.length();i++) opt[i][0] = delta*i;
		for(int j = 0;j <= y.length();j++) opt[0][j] = delta*j;

		int mismatch = alpha;
		for(int i = 1;i <= x.length();i++){
			for(int j = 1;j <= y.length();j++){
				mismatch = (x.charAt(i-1) == y.charAt(j-1))? 0: alpha;
				int cost1 = mismatch + opt[i-1][j-1];
				int cost2 = delta + opt[i-1][j];
				int cost3 = delta + opt[i][j-1];
				opt[i][j] = Math.min(cost1, cost2);
				opt[i][j] = Math.min(cost3, opt[i][j]);
			}
		}
		return(opt[x.length()][y.length()]);
	}
}
