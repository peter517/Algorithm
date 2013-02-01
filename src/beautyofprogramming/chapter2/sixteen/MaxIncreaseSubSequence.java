package beautyofprogramming.chapter2.sixteen;


/**
 * 2.16
 * 问题描述
 * 求一维数组中最长递增子序列，非连续的
 * 
 * 主要思路：动态规划
 * 该题不适合用枚举法进行计算
 * 
 * 设有数组data[],长度为n,count[i]表示以data[i]结尾的的最长子序列的长度
 * 
 * 1.对于data[0],count[0] = 1
 * 2.对于data[i]
 *               
 * count[i] =   max{1, data[j] + 1} (0 <= j < i) 
 * 当data[i] > data[j]时，data[i]可能取值为 data[j] + 1
 * 
 * 3.count[n - 1]即为所求解
 * 
 * 时间复杂度O(n ^ 2)
 * 
 */
public class MaxIncreaseSubSequence {
	
	public final int[] data = {1,-1,3,-3,5,1,5,3,7,-8};
    public int[] count = new int[data.length];

    public void search(){
    	
    	count[0] = 1;
    	
    	for(int i = 1; i < data.length; i++){
    		int max = 1;
    		for(int j = 0; j < i; j++){
    			
    			if(data[i] > data[j] && max <= count[j]){
    				max = count[j] + 1;
    			}
    		}
    		count[i] = max;
    	}
    	
    }
    
    private int getMax(){
    	int max = 0;
    	for(int i = 1; i < count.length; i++){
    		if(max < count[i]){
    			max = count[i];
    		}
    	}
    	
    	return max;
    }
    
    public void output(){
    	System.out.println(getMax());
    }
    
	public static void main(String[] args) {

		MaxIncreaseSubSequence obj = new MaxIncreaseSubSequence();
		obj.search();
		obj.output();
	}

}
