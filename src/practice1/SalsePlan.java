package practice1;

/*
 * アルゴリズムデザイン
 * http://www.kyoritsu-pub.co.jp/bookdetail/9784320122178
 * 281ページ　演習問題4
 */

public class SalsePlan {

	static final int p_num = 2;
	static final int size = 4;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m_cost = 10;
		int[][] s_cost = new int[p_num][size];
		String[] places = new String[p_num];

		s_cost[0][0] = 1;
		s_cost[0][1] = 3;
		s_cost[0][2] = 20;
		s_cost[0][3] = 30;
		s_cost[1][0] = 50;
		s_cost[1][1] = 20;
		s_cost[1][2] = 2;
		s_cost[1][3] = 4;
		places[0] = "NY";
		places[1] = "SF";

		DynamicProgramming dp = new DynamicProgramming();
		dp.execute(s_cost, places, m_cost);
		System.out.println("最小コスト："+dp.getOPT());
		String[] plans = dp.getPlans();
		System.out.print("営業計画の系列： ");
		for(int i=0;i<plans.length;i++){
			System.out.print(plans[i]+": ");
		}
		System.out.println();
	}

	static class DynamicProgramming{
		int[] opt;
		String[] plans;
		DynamicProgramming(){
			opt = new int[size];
			plans = new String[size];
		}

		public int getOPT(){
			return(opt[size-1]);
		}

		public String[] getPlans(){
			return(plans);
		}

		public void execute(int[][] s_cost, String[] places, int m_cost){
			opt[0] = (s_cost[0][0] < s_cost[1][0])? s_cost[0][0]: s_cost[1][0];
			plans[0] = (s_cost[0][0] < s_cost[1][0])? places[0]: places[1];
			for(int i = 1;i < size; i++){
				int ny_tmp = s_cost[0][i] + ((plans[i-1] == places[0])? 0: m_cost);
				int sf_tmp = s_cost[1][i] + ((plans[i-1] == places[1])? 0: m_cost);
				if(ny_tmp < sf_tmp){
					opt[i] = opt[i-1] + ny_tmp;
					plans[i] = places[0];
				}else{
					opt[i] = opt[i-1] + sf_tmp;
					plans[i] = places[1];
				}
			}
		}
	}
}
