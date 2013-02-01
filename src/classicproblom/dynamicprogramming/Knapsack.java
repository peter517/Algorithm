package classicproblom.dynamicprogramming;

/**
 * 动态规划最主要的还是找到最优子结构
 * 这里currentValue[i][j]表示当只有下标为i,……,goodsCount的物品可以选的时，切包容量为j，最优解
 * 
 * 
 * currentLongestLength[i+1][j] = 
 * 
 * currentValue[i][j] =  max{currentValue[i+1][j],当weight[i] > j 
 * currentLongestLength[i + 1][j -weight[i]] +value[i]} 当weight[i] <= j
 * 
 */
public class Knapsack {

	public final int goodsCount = 3;// 物品个数
	public final int capacity = 5;// 背包容量

	public int[] weight = { 5, 2, 3 };// 每个物品的重量
	public int[] value = { 8, 4, 9 };// 每个物品的价值

	// 在i方向要扩展一个空间，是有利于在求第一个最优解时，即当i == goodsCount时，方便和接下来的情况统一求解
	// 在j方向要扩展一个空间,完全是为了数字和实际情况对应
	public int[][] currentValue = new int[goodsCount + 1][capacity + 1];// 子问题的最优解空间

	public int[] result = new int[goodsCount];// 最优解

	/**
	 * 建立最优解结构
	 */
	public void knapsack() {

		// 当只有下标为i,……,goodsCount个物品可以选时
		for (int i = goodsCount; i >= 1; i--) {

			for (int j = 1; j <= capacity; j++) {
				if (j < weight[i - 1]) {
					currentValue[i - 1][j] = currentValue[i][j];
				} else {
					currentValue[i - 1][j] = Math.max(currentValue[i][j], currentValue[i][j - weight[i - 1]] + value[i - 1]);
				}
			}
		}
	}

	/**
	 * 根据最优解结构寻找解
	 */
	public void Traceback() {

		int currnetCapacity = capacity;

		for (int i = 0; i < goodsCount; i++) {
			if (currentValue[i][currnetCapacity] == currentValue[i + 1][currnetCapacity]) {
				result[i] = 0;
			} else if (currentValue[i][currnetCapacity] != currentValue[i + 1][currnetCapacity]) {
				result[i] = 1;
				currnetCapacity = currnetCapacity - weight[i];
			}
		}

	}

	public void output() {

		for (int i = 0; i < goodsCount; i++) {
			for (int j = 1; j <= capacity; j++) {
				System.out.print(currentValue[i][j] + " ");
			}
			System.out.println("");
		}

		for (int i = 0; i < goodsCount; i++) {
			System.out.print(result[i] + " ");
		}
	}

	public static void main(String[] args) {

		Knapsack obj = new Knapsack();
		obj.knapsack();
		obj.Traceback();
		obj.output();

	}

}
