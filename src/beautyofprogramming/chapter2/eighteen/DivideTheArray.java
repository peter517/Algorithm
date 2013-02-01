package beautyofprogramming.chapter2.eighteen;


/**
 * 2.18
 * 问题描述：
 * 分割一个只含正数的2n大小数组成大小n的两个子数组，使得这两个子数组之和最接近
 * 
 * 主要思路：
 * 
 * 有数组data[],长度为2n，总和为sum
 * 
 * 解法一：回溯法
 * 依次判断每个元素是否要加入最终解，以最终形成的n子数组之和最接近 sum / 2为判断函数
 * 
 * 如果总和超过了sum/2,则可以进行剪枝操作
 * 
 * 时间复杂度 O(2^n)
 * 
 * 解法二：动态规划法
 * 
 * 设set(i)表示大小为i的子数组总和出现的情况，下标从0开始，有：
 * 
 * set(i + 1) = ((each item in set(k)) + data[i + 1]),(0 <= k < i)
 * 
 * 最终解为set(n - 1)中最接近sum/2的值
 * 
 * 由于也要依次判断每个元素是否要加入最终解，时间复杂度 O(2^n)
 * 
 * 
 * 解法三：动态规划
 * 
 * 设table[i][v]为大小为i的子数组总和是否能为v
 * 
 * 这样v的取值范围为 0 < v < sum /2;
 * 
 */
public class DivideTheArray {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
           String a;
	}

}
