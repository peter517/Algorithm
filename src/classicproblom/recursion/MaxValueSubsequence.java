package classicproblom.recursion;


/**
 *问题描述：
 *对于整型数组，求总和最大的子序列
 *
 *主要思路：
 *解法一：直接穷举法 + 剪枝
 *1.对于数组data[],从头结点开始遍历
 *2.对于从i结点开始遍历,遍历到j，sum = data[i] + …… + data[j]
 *3.如果sum >= 0 ,记录最大和
 *4.如果sum <0 ,从i + 1结点开始遍历
 *
 * 时间复杂度O(n^2)
 * 
 * 解法二：
 * 1.对于数组data[],从头结点开始遍历
 * 2.记录data[i]到data[j]之间的总和，记录最大总和maxSum和当前总和currentSum, 如果小于零，那么从j+1开始遍历
 * 3.不断更新maxSum，直到遍历结束
 * 
 * 时间复杂度O(n)
 * 
 * 解法三：分治法
 * 找data[]中最常子序列就是找data[0]……data[i]和data[i + 1]……data[n]最常子序列的拼接
 * 
 * 有三种情况：
 * 1.最长子序列在data[0]……data[i]中
 * 2.最长子序列在data[i]……data[n]中
 * 3.最长子序列在需要在data[0]……data[i]和data[i + 1]……data[n]中拼接
 * 
 */

public class MaxValueSubsequence {

	
	public final static int[] dataArray = {-3,1,7,-1,-2,2,4};
	public final int length = dataArray.length;
	
	public int maxSum = 0;
	public int[] result = new int[length];
	
	/**
	 * 迭代版
	 */
	public void search(){
		
		for (int i = 0; i < length; i++){
			int sumBuf = 0;
			for(int j = i; j < length; j++){
				
				sumBuf += dataArray[j];
				
				if(maxSum < sumBuf){
					maxSum = sumBuf;
				}
				
				if(maxSum < 0){
					break;
				}
			}
		}
	}
	
	/**
	 * 递归版
	 */
	public void search(int start){
		
		if (start >= length){
			return;
		}
		
		int sumBuf = 0;
		for (int i = start; i < length; i++){
			sumBuf += dataArray[i];
			
			if(maxSum < sumBuf){
				maxSum = sumBuf;
			}
			
			if(maxSum < 0){
				search(start + 1);
			}
		}
		
		search(start + 1);
	}
	
	/**
	 * 剪枝
	 */
	public void searchByPruning(){
		
		int currentSum = 0;
		for (int i = 0; i < length; i++){
			
			currentSum += dataArray[i];
			
			if (currentSum < 0){
				currentSum = 0;
				continue;
			}
			
			maxSum = Math.max(maxSum, currentSum);
		}
		
	}
	
	/**
	 * 分治
	 */
	public Range searchByDivision(int start, int end){
		
		if (start == end) {
			Range r = new Range(start, end);
			return r;
		}

		int mid = (start + end) / 2;

		Range rLeft = searchByDivision(start, mid);
		Range rRight = searchByDivision(mid + 1, end);
		
		return Range.combinRange(rLeft, rRight, new Range(start, end));
		
		
	}

	
	public void output(){
		
		System.out.println(maxSum);
		
		Range r =  searchByDivision(0, dataArray.length - 1);
		
		for (int i = r.start; i <= r.end; i++){
			System.out.print(dataArray[i] + " ");
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MaxValueSubsequence obj = new MaxValueSubsequence();
//		obj.search(0);
		obj.searchByPruning();
		obj.output();
	}

}


class Range{
	
	public int start = 0;
	public int end = 0;
	
	public Range() {

	}

	public Range(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	public static Range combinRange(Range rLeft, Range rRight, Range r){
		
		//左边的最大子序列
		int maxLenLeft = sum(rLeft.start, rLeft.end);
		//右边的最大子序列
		int maxLenRight = sum(rRight.start, rRight.end);
		
		//计算中间区域的最大子序列
		int mid = (r.start + r.end) / 2;
		int maxLenFromMidToLeft = 0;
		int lenBuf = 0;
		int leftIndex = 0;
		
        //从中点出发	，向左	
		for (int i = mid; i >= r.start; i--){
			lenBuf += MaxValueSubsequence.dataArray[i];
			if(lenBuf >= maxLenFromMidToLeft){
				maxLenFromMidToLeft = lenBuf;
				leftIndex = i;
			}
		}
		
		int maxLenFromMidToRight = 0;
		int rightIndex = 0;
		lenBuf = 0;
		
		//从中点出发	，向右
		for (int i = mid; i <= r.end; i++){
			lenBuf += MaxValueSubsequence.dataArray[i];
			if(lenBuf >= maxLenFromMidToRight){
				maxLenFromMidToRight = lenBuf;
				rightIndex = i;
			}
		}
		
	    int max = Math.max(maxLenFromMidToLeft + maxLenFromMidToRight, Math.max(maxLenLeft, maxLenRight));
			
	    if (max == maxLenLeft){
	    	return new Range(rLeft.start, rLeft.end);
	    }else if(max == maxLenRight){
	    	return new Range(rRight.start, rRight.end);
	    }else{
	    	return new Range(leftIndex, rightIndex);
	    }
		
		
	}
	
	
	private static int sum(int start, int end){
		
		int sum = 0;
		for (int i = start; i <= end; i++){
			sum += MaxValueSubsequence.dataArray[i];
		}
		return sum;
	}
	
}
