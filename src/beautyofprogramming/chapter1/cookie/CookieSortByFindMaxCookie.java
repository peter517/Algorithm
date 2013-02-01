package beautyofprogramming.chapter1.cookie;

/**
 * 问题描述：
 * 对一叠煎饼进行翻转使其有序，找出最少的翻转次数（只能从最上面进行整体翻转）
 * 
 * 主要思路：
 * 简单找规律，归纳出公式，迭代执行
 * 公式：
 * 1.对于1……n个煎饼，选择最大煎饼i
 * 2.旋转1……i直接的所有煎饼，使得最大的煎饼i置顶
 * 3.旋转1……n，使得最大的煎饼i在最底下
 * 4.令n = n - 1，重复1-3，直至n == 1
 */
public class CookieSortByFindMaxCookie {

	public static int[] cookieArray = {3, 2, 1, 6, 5, 4, 9, 8, 7, 0 };
//	public static int[] cookieArray = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
	public static final int count = cookieArray.length;
	public static int minSwapCount = 0;
	public static int[] cookieArrayResult = new int[count];
	
	public int getMaxReverseTimes() {
		return (count - 1) * 2;
	}

	public void reverse(int start, int end) {
		if (start == end) {
			return;
		}

		int startIndex = start;
		int endIndex = end;

		while (startIndex < endIndex) {
			int temp = cookieArray[startIndex];
			cookieArray[startIndex] = cookieArray[endIndex];
			cookieArray[endIndex] = temp;
			startIndex++;
			endIndex--;
		}
		
		minSwapCount++;
	}
	
	public boolean isSorted(){
		for (int i = count - 1; i >= 1; i--) {
			if(cookieArray[i] < cookieArray[i - 1]){
				return false;
			}
		}
		return true;
	}

	/**
	 * 获得最大烧饼的坐标
	 */
	public int getMaxWeightCookieIndex(int start, int end) {
		
		int max = Integer.MIN_VALUE;
		int maxIndex = 0;
		for (int i = start; i < end; i++) {
			if (max < cookieArray[i]) {
				max = cookieArray[i];
				maxIndex = i;
			}
		}
		return maxIndex;
		
	}

	/**
	 * 颠倒最大的烧饼两次
	 */
	public void searchByFindMaxFirst() {
		
		for (int i = count - 1; i >= 0; i--) {
			int maxIndex = getMaxWeightCookieIndex(0, i);
//			System.out.println("maxIndex = " + maxIndex);
//			System.out.println("cookieArray[maxIndex] = " + cookieArray[maxIndex]);
			//通过两次翻转来把最大的烧饼压在最下面
			reverse(0, maxIndex);
			reverse(0, i);
			
			if(isSorted()){
				break;
			}
		}
		
	}
	
	public void outputCookieArray() {
		
		for (int i = 0; i < count; i++) {
             System.out.println(cookieArray[i]);
		}
		System.out.println("minSwapCount = " + minSwapCount);
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CookieSortByFindMaxCookie m = new CookieSortByFindMaxCookie();
		m.searchByFindMaxFirst();
		m.outputCookieArray();
		
	}

}
