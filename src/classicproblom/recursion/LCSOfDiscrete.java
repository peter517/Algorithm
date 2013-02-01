package classicproblom.recursion;

/**
 * 这里StringBuffer result不也能够用成String result，因为String是新开辟一个空间，
 * 这样各个递归之间就不能共享数据了
 * 
 * 总的来说， 递归的时候
 * 参数如果传值，那么是每个被递归函数的私有数据，
 * 如果是传引用，那么是每个被递归函数的公有数据
 * 
 * 问题描述：
 * 求两个字符串的非连续最长子序列
 * 
 * 递归法：
 * 当x(i)==y(j)时，作为最优解
 * 当x(i)!=y(j)时，计算每个解空间的可能解，最后选取最优解
 * 
 * 有字符串x，y,长度分别为m，n,对于0 < i < m, 0 < j < n,
 * x(a1,a2)表示字符串下标为a1至a2的子字符串
 * 
 * 1.如果x(i)==y(j),那么把x(i)加入解中，继续求x(i + 1, m)与y(j + 1, n)的解
 * 2.如果x(i)!=y(j),那么求x(i, m)与y(j + 1, n)的解和x(i + 1, m)与y(j, n)的解，取两者的最大值
 * 3.以参数的方式保存最终解
 * 
 * 与连续不一样的是，不连续最大子序列不需要保存每个解空间的值，以参数的方式可以求出最后的解
 */

public class LCSOfDiscrete {

	public void longestCommonSubsequence(int[] x, int[] y, int i, int j, StringBuffer result) {

		if (i >= x.length || j >= y.length) {
			return;
		}
		if (x[i] == y[j]) {
			result.append(x[i]);
			longestCommonSubsequence(x, y, i + 1, j + 1, result);
		} else {
			
			StringBuffer result1 = new StringBuffer();
			StringBuffer result2 = new StringBuffer();

			//要注意每个递归开始时候的状态是否和分析时一样
			longestCommonSubsequence(x, y, i + 1, j, result1);
			longestCommonSubsequence(x, y, i, j + 1, result2);
			
			result.append(getLongestString(result1, result2));
			
		}
		
	}
	
	
	public void longestCommonSubsequence(String x, String y, StringBuffer result) {
		
		if (x.length() == 0 || y.length() == 0) {
			return;
		}
		
		if (x.charAt(0) == y.charAt(0)) {//遍历完这条路径后 else后面的分支路径就不用考虑了
			
			result.append(x.charAt(0));
			longestCommonSubsequence(x.substring(1), y.substring(1), result);
			
		} else {
			
			StringBuffer result1 = new StringBuffer();
			StringBuffer result2 = new StringBuffer();

			longestCommonSubsequence(x.substring(1), y, result1);
			longestCommonSubsequence(x, y.substring(1), result2);
			
			result.append(getLongestString(result1, result2));
			
		}
		
	}

	/**
	 * 获得最长字符串
	 */
	private StringBuffer getLongestString(StringBuffer result1, StringBuffer result2) {
		
		if (result1.length() > result2.length()) {
				return result1;
			} else {
				return result2;
		} 
	}

	public static void main(String[] args) {

		LCSOfDiscrete lsc = new LCSOfDiscrete();
		StringBuffer result = new StringBuffer();
		
//		int[] x = { 0, 1, 5, 2, 3, 5 ,4, 4, 4};
//		int[] y = { 0, 1, 3, 7, 5, 2, 6, 8, 5};
//		
//		lsc.longestCommonSubsequence(x, y, 0, 0, result);
//		System.out.println(result);
//		
//		result.delete(0, result.length());
		
		String xString = "001523512";
		String yString = "01523512";
		lsc.longestCommonSubsequence(xString, yString, result);
		System.out.println(result);

	}
}
