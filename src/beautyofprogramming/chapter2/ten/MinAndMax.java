package beautyofprogramming.chapter2.ten;

/**
 * 2.10 问题描述： 对于一个数组，求最大值和最小值
 * 
 *主要思路 
 *解法一：
 *1.设max = 0 min = 0 
 *2.遍历一遍数组，对于data[i]，都和max，min比较一次，更新max和min的值
 * 比较次数为 2n
 * 
 *解法二： 
 *1.遍历一遍数组，对于data[i]和data[i + 1]进行比较，然后把两者中最小的放在data[i],最大的放在data[i + 1]
 *2.分别遍历下标为奇数和偶数的数组，分别获得最大值和最小值 比较次数 1.5*n或者1.5*n - 1(数组个数为偶数)
 * 
 *解法三：分治法 
 *找data[]中最大值和最小值就是找data[0]……data[i]和data[i + 1]……data[n]之间的最大值和最小值
 *这里有一个编程技巧就是怎样存储每次计算的最大值和最小值？
 *
 *
 */

public class MinAndMax {

	public int[] data = { 3, 1, -4, 5, 10, 6, -1};
	public int max = Integer.MIN_VALUE;
	public int min = Integer.MAX_VALUE;
	
	public int count = 0;

	/**
	 * 分治法 
	 */
	public void findMaxAndMin(int start, int end) {

		int mid = (start + end) / 2;
		
		int leftMin = data[start];
		int leftMax = data[mid];
		int rightMin = data[mid + 1];
		int rightMax = data[end];
		
		//把每次计算的最小值放在子数组开头，最大值放在子数组末尾
		data[start] = Math.min(leftMin, rightMin);
		data[end] = Math.max(leftMax, rightMax);

	}

	public void search() {
		split(0, data.length - 1);
	}

	public void split(int start, int end) {

		if (start >= end) {
			return;
		}
		
		int mid = (start + end) / 2;

		//这里要弄清楚怎么分的比，当元素只有三个的时候，肯定是左边分两个，右边分一个
		split(start, mid);
		split(mid + 1, end);

		//整棵递归树的次数就是这个函数运行的次数
		findMaxAndMin(start, end);

	}

	public void output() {
		System.out.println("max = " + data[data.length - 1]);
		System.out.println("min = " + data[0]);
	}

	public static void main(String[] args) {

		MinAndMax obj = new MinAndMax();
		obj.search();
		obj.output();

	}

}
