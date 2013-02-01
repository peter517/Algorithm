package classicproblom.division;

import java.util.Random;

/*
 *这里要求a和b的位数一样 且位数需要是2的n次方：1,2,4,6…… 
 */
public class LargeNumberMultiplication {

	public static long multiply(long a, long b, int digit) {

		if (a == 0 || b == 0) {
			return 0;
		}
		
		if (digit == 1) {
			return a * b;
		}

		long aFirstHalf = (long) (a / Math.pow(10, digit / 2));
		long aLastHalf = (long) (a % Math.pow(10, digit / 2));

		long bFirstHalf = (long) (b / Math.pow(10, digit / 2));
		long bLastHalf = (long) (b % Math.pow(10, digit / 2));

		//把问题空间缩小后 要确定缩小后的每个子问题面对的是相同的解决办法，只是规模上不一样
		long result1 = multiply(aFirstHalf, bFirstHalf, digit / 2);
		long result2 = multiply(aFirstHalf - aLastHalf, bLastHalf - bFirstHalf, digit / 2);
		long result3 = multiply(aLastHalf, bLastHalf, digit / 2);
		
		if(digit % 2 != 0){
			digit--;
		}
		
		return (long) (result1 * Math.pow(10, digit) + (result1 + result2 + result3) * Math.pow(10, digit / 2) + result3);
	}

	private static int getBitLength(long a) {
		Long l = new Long(Math.abs(a));
		return l.toString().length();
	}

	public static void main(String[] args) {

		int[] data = new int[1000000];
		Random random = new Random();

		for (int i = 0; i < data.length; i++) {
			data[i] = random.nextInt(1000000);
		}
		
		//速度变慢了，自是提供了一个很好的解题思路
		long current = System.currentTimeMillis();
		for (int i = 0; i < data.length; i++) {
			int digit = getBitLength(data[i]);
			multiply(data[i],data[i], digit);
		}
		System.out.println(System.currentTimeMillis() - current);
		
		current = System.currentTimeMillis();
		for (int i = 0; i < data.length; i++) {
			long result = data[i] * data[i];
		}
		System.out.println(System.currentTimeMillis() - current);
	}

}
