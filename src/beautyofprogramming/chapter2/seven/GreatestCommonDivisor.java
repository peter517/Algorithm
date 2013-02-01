package beautyofprogramming.chapter2.seven;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 2.7
 * 问题描述：
 * 对于两个数a,b,求最大公约数
 * 
 * 主要思路：
 * 解法一：
 * 辗转相除法，即a和b的最大公约数等于b和a%b的最大公约数
 * 
 * 解法二：
 * 当a和b数字比较大的时候，用除法效率太低，即利用a和b的最大公约数等于b和a-b的最大公约数(a > b)
 * 
 * 解法三：
 * 当解法二中a和b相差比较远的时候，效率也不高，即使用提取公共因子发，设a和b的最大公约数为gcd(a,b)
 * 1.如果a是偶数，b是偶数，则gcd(a, b) = 2 * gcd(a, b)
 * 2.如果a是奇数，b是偶数，则gcd(a, b) = gcd(a, b / 2)
 * 3.如果a是偶数，b是奇数，则gcd(a, b) = gcd(a / 2, b)
 * 4.如果a是奇数，b是奇数，则gcd(a, b) = gcd(a - b, b); (a > b)
 * 5.一直到a或b为0时
 *
 */
public class GreatestCommonDivisor {

	public int a = 18;
	public int b = 24;
	
	public int gcdNum = 0;
	
	/**
	 * 解法一
	 */
	public int gcd1(int a, int b){
		if(a == 0){
			return b;
		}
		if(b == 0){
			return a;
		}
		
		return gcd1(b, a % b);
	}
	
	/**
	 * 解法二
	 */
	public int gcd2(int a, int b){
		if(a == 0){
			return b;
		}
		if(b == 0){
			return a;
		}
		
		if(a >= b){
			return gcd1(a - b, b);
		}else{
			return gcd1(b - a, a);
		}
	}
	
	/**
	 * 解法三
	 */
	public int gcd3(int a, int b){
		
		if(a == 0){
			return b;
		}
		if(b == 0){
			return a;
		}
		
		if(!isOdd(a) && !isOdd(b)){
			return 2 * gcd3(a / 2 , b / 2);
		}else if(isOdd(a) && !isOdd(b)){
			return gcd3(a, b / 2);
		}else if(!isOdd(a) && isOdd(b)){
			return gcd3(a / 2, b);
		}else{
			if(a >= b){
				return gcd3(a - b, b);
			}else{
				return gcd3(b - a, a);
			}
		}
	}
	
	public boolean isOdd(int a){
		return (a % 2 == 0 )? false : true;
	}
	
	public void search(){
//		gcdNum = gcd1(a, b);
//	    gcdNum = gcd2(a, b);
		gcdNum = gcd3(a, b);
	}
	
	public void output(){
		System.out.println(gcdNum);
	}

	public static void main(String[] args) {

		GreatestCommonDivisor obj = new GreatestCommonDivisor();
		obj.search();
		obj.output();
		
	}

}
