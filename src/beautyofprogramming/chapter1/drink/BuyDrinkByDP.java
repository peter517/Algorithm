package beautyofprogramming.chapter1.drink;

/**
 * 1.6
 * 问题描述：
 * 饮料购买，求最大满意度，类似0-1背包，只是饮料的数量不止一瓶
 * 
 * 主要思路：
 * 动态规划，寻找递归最优解
 * 这里currentValue[i][j]表示当只有下标为i,……,goodsCount的物品可以选的时，切包容量为j，最优解
 *                       
 * (其中k为1,……,count[i])：                      
 *                       currentLongestLength[i + 1][j]                                                                      当k * weight[i] > j                                                     
 * currentValue[i][j] =       
 *                       max{currentValue[i + 1][j],max{currentLongestLength[i + 1][j - k * weight[i]] + value[i] * k}}       当k * weight[i] <= j 
 *                       
 */
public class BuyDrinkByDP {

	public final int typeCount = 3;// 饮料种类
	public final int totalCapacity = 14;// 饮料总容量

	public int[] volume = { 5, 2, 4};// 单个饮料的体积
	public int[] count = {2, 2, 2};// 每种饮料的数量
	public int[] popularity = {8, 4, 9};// 饮料的受欢迎程度

	//在i方向要扩展一个空间，是有利于在求第一个最优解时，即当i == typeCount时，方便和接下来的公式统一求解
	//在j方向要扩展一个空间,完全是为了数字和实际情况对应
	public int[][] currentValue = new int[typeCount + 1][totalCapacity + 1];// 子问题的最优解空间
	public int[] result = new int[typeCount];// 最优解
	
	
	public void search(){
		
		for(int i = typeCount; i >= 1; i--){
			for(int j = 1; j <= totalCapacity; j++){
				
				int max = currentValue[i][j];
				for(int k = 1; k <= count[i - 1]; k++){
					if(k * volume[i - 1] <= j){
						int temp = k * popularity[i - 1] + currentValue[i][j - k * volume[i - 1]];
						if(max < temp){
							max = temp;
						}
					}
				}
				currentValue[i - 1][j] = max;
			}
		}
		
		
	}
	
	/*
	 * 暂时没有想出显示最优解的办法
	 */
//	public void traceBack(){
//		
//		int total = totalCapacity;
//		for(int i = 1; i <= typeCount - 1; i++){
//			if(currentValue[i][total] != currentValue[i + 1][total]){
//				result[i] = (currentValue[i][total] - currentValue[i + 1][total]) / popularity[i];
//				total = total - result[i] * volume[i];
//			}else{
//				result[i] = 0;
//			}
//		}
//		
//		if(total >= volume[typeCount]){
//			result[typeCount] = total / volume[typeCount];
//		}
//	}
	
	public void output(){
		
		for(int i = 0; i < typeCount; i++){
			for(int j = 0; j <= totalCapacity; j++){
				System.out.print(currentValue[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BuyDrinkByDP obj = new BuyDrinkByDP();
		obj.search();
//		obj.traceBack();
		obj.output();
		
	}

}
