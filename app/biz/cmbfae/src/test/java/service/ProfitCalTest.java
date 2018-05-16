package service;

import java.math.BigDecimal;

public class ProfitCalTest {

	
	/**计算万元收益= ((1+T日收益率）^(1/365)-1)*10000 公式
	 * 
	 * */
	public static BigDecimal CalProfit(BigDecimal profit){
		double s=(double)1/365;
		double pow = Math.pow(profit.doubleValue()+1, s);
		BigDecimal dd=new BigDecimal((pow-1)*10000);
		BigDecimal setScale = dd.setScale(4, BigDecimal.ROUND_DOWN);

		return setScale;

	}
	
	public static void main(String[] args) {
		
		BigDecimal calProfit = CalProfit(new BigDecimal(0.05));
		System.out.println(calProfit);
		
	}
	
}
