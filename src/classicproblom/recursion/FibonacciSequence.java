package classicproblom.recursion;


/**
 * 问题描述：
 * 计算斐波纳契数列
 * 
 * 递归法:
 * 各个解之间的关系
 * f(n) = f(n - 1) + f(n - 2)
 * 
 * 用resultTable[]来存储已求解，减少递归次数
 */
public class FibonacciSequence {


	public int n = 9;
	public int result = 0;
	public int count = 0;
	
	public void search(){
		result = f(n);
	}
	
	public int[] resultTable = new int[n];
	
	public int f(int n){
		
		//如果之前计算过那么之间返回
		if (resultTable[n - 1] != 0){
			return resultTable[n - 1];
		}
		
		//计算迭代次数
		count++;
	
		if (n == 1 ){
			resultTable[0] = 1;
			return 1;
		}else if(n == 2){
			resultTable[1] = 2;
			return 2;
		}else{
			
			//记录每次计算的解
			resultTable[n - 1] = f(n - 1) + f(n - 2);
			return resultTable[n - 1];
		}
		
	}
	
	public void output(){
		System.out.println(result);
		System.out.println("count = " + count);
	}
	
	
	public static void main(String[] args) {
		
		FibonacciSequence obj = new FibonacciSequence();
		obj.search();
		obj.output();

	}

}
