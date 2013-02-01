/**
 * 
 */
package beautyofprogramming.chapter2.two;

/**
 * 2.2
 * 问题描述：
 * 对于N!,求某个因子出现的次数
 * 
 * 主要思路：
 * 
 * 解法一：
 * 1.对于i，i属于1……N,求i出现目标因子的个数ci
 * 2.最终结果就是，对i属于1……N的所有ci求和
 * 
 * 时间复杂度：(n * log(n))
 * 
 * 解法二：
 * 设目的因子为a
 * 1.因子a^i对最终a的个数的贡献为i个
 * 2.对于N!,a^i出现的次数为  : N / (a ^ i),
 * 由于在求 N / (a ^ (i - k))，k属于1……k时,也会计算 a ^ i出现的次数，所以不能简单的相加
 * 3.这样，第一次求至少含有一个a因子的个数，即 N / a
 * 4.当 a^i < N,求至少含有i个a因子的个数 ，即N / (a ^ i)，这样对于某个m = (a ^ i),m属于1……N,被计算了i次，没有重复
 * 5.最后的结果即为：N / a + …… N / (a ^ i) + ……（a^i < N）
 * 
 * 时间复杂度：以a为底的N的对数
 * 
*/
public class TimesOfFactor {

	public int factorial = 26;//表示阶乘
	public int factor = 5;
	public int result = 0;
	
	public void search(){
		
		int exponent = 1;
		int buf = 0;
		
		do{
			buf = (int) (factorial / Math.pow(factor, exponent++));
			result += buf;
		}while(buf != 0);
	}
	
	public void output(){
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		TimesOfFactor obj = new TimesOfFactor();
		obj.search();
		obj.output();
		
	}

}
