package practice2;

/*
 * アルゴリズムデザイン
 * http://www.kyoritsu-pub.co.jp/bookdetail/9784320122178
 * 284ページ　演習問題7
 */

public class StockTrade {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int size = 3;
		int[] price = {0, 9, 1, 5};
		DynamicProgramming dp = new DynamicProgramming(size, price);
		dp.execute();
		int[] span = dp.getPair();
		if(span[0] < span[1]){
			System.out.println("最大収益： "+dp.getOPT(size));
			System.out.println("買い: "+span[0]+", 売り： "+span[1]);
		}else{
			System.out.println("収益が0以下になります ");
		}
	}

	static class DynamicProgramming{
		int size;
		int[] price;
		int[] opt;
		int start;
		int end;

		DynamicProgramming(int s, int[] p){
			size = s;
			price = p;
			opt = new int[size+1];
		}

		public void execute(){
			int tmp_new = 0;
			int tmp_m = 0;
			start = 1;
			end   = 2;
			opt[2] = price[2] - price[1];
			for(int i=3;i<=size;i++){
				tmp_new = price[i] - price[i-1];
				tmp_m = price[i] - price[start];
				if(tmp_new > tmp_m){
					if(tmp_new > opt[i-1]){
						opt[i] = tmp_new;
						start = i - 1;
						end = i;
					}else{
						opt[i] = opt[i-1];
					}
				}else{
					if(tmp_m > opt[i-1]){
						opt[i] = tmp_m;
						end = i;
					}else{
						opt[i] = opt[i-1];
					}
				}
			}
		}

		public int getOPT(int n){
			return(opt[n]);
		}

		public int[] getPair(){
			int[] pair = {start, end};
			return pair;
		}
	}

}
