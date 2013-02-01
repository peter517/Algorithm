package classicproblom.dynamicprogramming;

/**
 * 问题描述： 计算斐波纳契数列
 * 
 * 迭代法：
 * 
 * 申请一个n大小的空间，下标为i的位置存放相应f(i + 1)的解
 * 这个其实不算动态规划
 * 
 */
public class FibonacciSequence {

	public final int n = 9;
	public int result = 0;

	public int[] resultArray = new int[n];

	public void search() {
		result = f(n);

	}

	public int f(int n) {

		if (n == 1) {
			return 1;
		} else if (n == 2) {
			return 2;
		} else {
			resultArray[0] = 1;
			resultArray[1] = 2;

			for (int i = 2; i < n; i++) {
				resultArray[i] = resultArray[i - 1] + resultArray[i - 2];
			}

			return resultArray[n - 1];
		}
	}

	public void output() {
		System.out.println(result);
	}

	public static void main(String[] args) {

		FibonacciSequence obj = new FibonacciSequence();
		obj.search();
		obj.output();

	}

}
