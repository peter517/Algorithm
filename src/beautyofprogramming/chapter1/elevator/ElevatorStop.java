package beautyofprogramming.chapter1.elevator;

/**
 * 1.8
 * 问题描述：
 * 在每层人数给定的情况下，选择最佳的电梯停靠楼层
 * 
 * 主要思路：
 * 方法一：
 * 1.最常规的就是直接对每层计算爬楼总数，取最小值，时间复杂度为O(n*n)
 * 
 * 方法二：
 * 1.计算大于某层i下的总人数N3，从而得出等于某层下的人数N2，和小于某层下的总人数N1
 * 2.从底向上，如果 对于某层i,N3 + N2 > N1,则继续往上找
 * 3.如果到达顶层，或者N3 + N2 <= N1,则停止查找，此时的i为最优解
 * 
 */
public class ElevatorStop {
	
	//每层的人数，从0层开始
	public int[] personCount = {1,1,1,1,6};
	public  int floorTotal = personCount.length;
	//记录第0层到第i层的人数总数
	public int[] personCountTotal = new int[floorTotal];
	
	public int personTotal = 0;
	public int levelToStop = 0;
	
	public void initPersonCountTotal(){
		
		personCountTotal[0] = personCount[0];
		personTotal = personCount[0];
		
		for (int i = 1; i < floorTotal; i++){
			personCountTotal[i] = personCount[i] + personCountTotal[i - 1];
			personTotal += personCount[i];
		}
		
	}
	
	public void search(){
		
		initPersonCountTotal();
		
		for (int i = 0; i < floorTotal; i++){
			
			int N1 = personCountTotal[i];
			int N2 = personCount[i];
			int N3 = personTotal - N2 - N1;
			
			if (N1 >= N2 + N3){
				levelToStop = i;
				return;
			}
		}
		
		levelToStop = floorTotal - 1;
		
	}
	
	public void output(){
		System.out.println("levelToStop = " + levelToStop);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ElevatorStop obj = new ElevatorStop();
		obj.search();
		obj.output();
	}

}
