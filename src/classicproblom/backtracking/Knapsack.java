package classicproblom.backtracking;

public class Knapsack {

	public final int goodsCount = 4;// 物品个数
	public final int capacity = 10;// 背包容量

	public int[] weight = { 0, 5, 2, 3, 5 };// 每个物品的重量
	public int[] value = { 0, 7, 4, 9, 8 };// 每个物品的价值

	public int[] result = new int[goodsCount + 1];// 最优解
	public int[] resultBuffer = new int[goodsCount + 1];// 最优解
	public int maxValue = 0;

	/*
	 * 用递归法求最优解，如果按物品重量升序排序，可以进行剪枝
	 * 或者按照性价比降序排序，这种情况可以在每次搜索的时候计算其上界，进行剪枝
	 */
	public void knapsack(int startIndex, int capacity, int maxValue) {

		if (this.maxValue <= maxValue) {
			this.maxValue = maxValue;
			for (int i = 1; i < weight.length; i++) {
				result[i] = resultBuffer[i];
			}
		}

		if (startIndex >= weight.length) {
			return;
		}

		if (weight[startIndex] <= capacity) {//搜素左子树

			capacity -= weight[startIndex];
			maxValue += value[startIndex];
			// 放入这个物品
			resultBuffer[startIndex] = 1;

			knapsack(startIndex + 1, capacity, maxValue);

			// 撤销放入这个物品的动作
			resultBuffer[startIndex] = 0;
			capacity += weight[startIndex];
			maxValue -= value[startIndex];

		}
		
		//搜素右子树，这里不能加else
		resultBuffer[startIndex] = 0;
		knapsack(startIndex + 1, capacity, maxValue);

	}

	public static void main(String[] args) {

		Knapsack dp = new Knapsack();
		dp.knapsack(1, dp.capacity, 0);
		System.out.println(dp.maxValue);

		for (int i = 1; i < dp.weight.length; i++) {
			System.out.print(dp.result[i] + " ");
		}
	}

}
