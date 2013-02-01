package beautyofprogramming.chapter1.cookie;

/**
 * 1.3
 * 问题描述：
 * 对一叠煎饼进行翻转使其有序，找出最少的翻转次数（只能从最上面进行整体翻转）
 * 
 * 主要思路：
 * 回溯法找出最优解，对于最优解是求次数，剪枝函数的判定标准参数可以更新的
 * 具体来说，对于求最小值的最优解，剪枝的步骤：
 * 1.遍历到某个非叶子节点时所计算的次数 + 遍历到叶子的最少次数(估计值) < 最小值
 * 2.结束此次遍历，选择其它节点进行遍历
 */
public class CookieSortByBackTracking {

	 public static int[] cookieArrayBuffer = {3, 2, 1, 6, 5, 4, 9, 8, 7, 0 };
//	 public static int[] cookieArray = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
//	public static int[] cookieArray = { 3, 1, 2, 4 ,6 ,5};
	public static final int count = cookieArrayBuffer.length;
	
	public static int minSwapCount = Integer.MAX_VALUE;
	public static int maxSwapCount = (count - 1) * 2;
	public static int searchCount = 0;

	public static int[] cookieArrayResult = new int[count];
	public static int[] cookieArraySwapBuffer = new int[maxSwapCount + 1];
	public static int[] cookieArraySwapReuslt = new int[maxSwapCount + 1];

	public void reverse(int start, int end) {
		if (start == end) {
			return;
		}

		int startIndex = start;
		int endIndex = end;

		while (startIndex < endIndex) {
			int temp = cookieArrayBuffer[startIndex];
			cookieArrayBuffer[startIndex] = cookieArrayBuffer[endIndex];
			cookieArrayBuffer[endIndex] = temp;
			startIndex++;
			endIndex--;
		}
	}

	public boolean isSorted() {
		for (int i = count - 1; i >= 1; i--) {
			if (cookieArrayBuffer[i] < cookieArrayBuffer[i - 1]) {
				return false;
			}
		}
		return true;
	}

	public int getEstmateMinSwapCount() {

		int minSwapCount = 0;
		for (int i = 1; i < count; i++) {
			if (Math.abs(cookieArrayBuffer[i - 1] - cookieArrayBuffer[i]) != 1) {
				minSwapCount++;
			}
		}
		return minSwapCount;
	}

	/*
	 * 穷举法
	 */
	public void searchByExhaustiveSearch(int stepCount) {

		searchCount++;
		
		//这里的最少只是一个估计值，在minReverseTimes次也不一定能排序成功
		//进行剪枝,maxSwapCount越小越好
		if (stepCount + getEstmateMinSwapCount() > maxSwapCount) {
			return;
		}

		//如果排序成功
		if (isSorted()) {
			//这一步剪枝参数的更改很重要
			//回溯法查询中，剪枝参数不一定像传统那样不变的
			//可以根据回溯中间参数更改剪枝参数
			if (stepCount < maxSwapCount) {
				maxSwapCount = stepCount;
				updateResultData();
			}
			return;
		}

		for (int i = 0; i < count; i++) {
			reverse(0, i);
			cookieArraySwapBuffer[stepCount] = i;
			searchByExhaustiveSearch(stepCount + 1);
			reverse(0, i);
		}
	}

	private void updateResultData() {
		for (int i = 0; i < count; i++) {
			cookieArrayResult[i] = cookieArrayBuffer[i];

		}
		for (int i = 0; i < maxSwapCount; i++) {
			cookieArraySwapReuslt[i] = cookieArraySwapBuffer[i];
		}
	}

	public void outputCookieArrayResult() {
		
		for (int i = 0; i < count; i++) {
			System.out.println(cookieArrayResult[i]);
		}
		
		System.out.println("cookieArraySwapReuslt:");
		for (int i = 0; i < maxSwapCount; i++) {
			System.out.println(cookieArraySwapReuslt[i]);
		}
		
		System.out.println("maxSwapCount = " + maxSwapCount);
		System.out.println("searchCount = " + searchCount);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CookieSortByBackTracking m = new CookieSortByBackTracking();
		m.searchByExhaustiveSearch(0);
		m.outputCookieArrayResult();

	}

}
