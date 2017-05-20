package practice3;

public class SquenceAlignment {

	// gap cost
	private static final int delta = 2;

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
				if(i==m || j==n){
					mismatch = calcMisMatchCost(x.charAt(i-1), y.charAt(j-1));
				}else{
					mismatch = calcMisMatchCost(x.charAt(i-1), x.charAt(i), y.charAt(j-1), y.charAt(j));
				}
				int cost1 = mismatch + opt[i-1][j-1];
				int cost2 = delta + opt[i-1][j];
				int cost3 = delta + opt[i][j-1];
				opt[i][j] = Math.min(cost1, cost2);
				opt[i][j] = Math.min(cost3, opt[i][j]);
				if(mismatch == 3){
					i++;
					j++;
				}
			}
		}
		return(opt[m][n]);
	}

	// mismatch cost
	private static int calcMisMatchCost(char a, char b){
		String fir = String.valueOf(a);
		String sec = String.valueOf(b);
		if(!fir.equals(sec)){
			if(fir.equals(sec.toUpperCase()))		return 1;
			else if(fir.toUpperCase().equals(sec))	return 1;
			else return 2;
		}
		return(0);
	}

	// mismatch cost
	private static int calcMisMatchCost(char a1, char a2, char b1, char b2){
		String s1 = String.valueOf(a1);
		String s2 = String.valueOf(a2);
		String t1 = String.valueOf(b1);
		String t2 = String.valueOf(b2);
		if(s1.equals(t1)){
			return 0;
		}else{
			if(s1.equals(t1.toUpperCase()))		return 1;
			if(s1.toUpperCase().equals(t1))		return 1;
		}
		if(s1.equals(t2) && t1.equals(s2)) return 2;
		else{
			if(s1.equals(t2.toUpperCase()) && t1.toUpperCase().equals(s2))	return 3;
			else return 2;
		}
	}
}
