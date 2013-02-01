package beautyofprogramming.chapter2.four;

import java.util.ArrayList;
import java.util.List;

/**
 * 2.4
 * 问题描述：
 * 计算从1到N中，某个数字k（0……9）出现的次数
 * 
 * 主要思路：
 * 
 * 解法一：
 * 1.对于i，i属于1……N，计算k出现的次数
 * 2.对所有i进行求和
 * 
 * 时间复杂度O(n * log(n))
 * 
 * 解法二：
 * 对于数字N，其各进制位上的数值分别为{a,b,c,……,m,……}
 * 对于m位置上出现k的次数,可分为下面三种情况：
 * 1.如果 m > k, m所处的位置上出现k的次数将受{a,……,m - 1}的影响
 * 2.如果 m == k, m所处的位置上出现k的次数将受{a,……,m - 1}与{m + 1,……}的影响
 * 3.如果 m < k, m所处的位置上出现k的次数将受{a,……,m - 1}的影响
 * 
 * 打个比方
 * 设N为123202，即 m > k
 * 在百位上出现1的次数就为：(123 + 1) * 100
 * 设N为123102，即 m == k
 * 在百位上出现1的次数就为：(123) * 100 + 2 + 1
 * 设N为123002，即 m < k
 * 在百位上出现1的次数就为：(123) * 100
 * 
 * * 时间复杂度O(log(n))
 * 
*/
public class TimesOfNum {

	public int data = 100;
	public int num = 1;
	public long timesOfNum = 0;
	
	public List<Integer> dataDigitsList = new ArrayList<Integer>();
	public TimesOfNum(){
		initDataDigitsList();
	}
	
	public void initDataDigitsList(){
		
		int dataBuf = data;
		
		while(dataBuf != 0){
			dataDigitsList.add(dataBuf % 10);
			dataBuf /= 10;
		}
		
	}
	
	public void search(){
		
		for (int i = 0; i < dataDigitsList.size(); i++){
			
			int higherNum = (int) (data / Math.pow(10, i + 1));
			int lowerNum = (int) (data % Math.pow(10, i));
			
			countNumOnEachDigit(i, higherNum,lowerNum);
			
		}
	}

	private void countNumOnEachDigit(int i, int higherNum, int lowerNum) {
		if (dataDigitsList.get(i) > num){ 
			int times = (int) ((higherNum + 1) * Math.pow(10, i)) ;
		    timesOfNum += times;
		}else if (dataDigitsList.get(i) == num){
			int times = (int) ((higherNum) * Math.pow(10, i) + lowerNum + 1);
		    timesOfNum += times;
		}else if (dataDigitsList.get(i) < num){
			int times = (int) ((higherNum) * Math.pow(10, i));
		    timesOfNum += times;
		}
	}

	public void output(){
		System.out.println(timesOfNum);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TimesOfNum obj = new TimesOfNum();
		obj.search();
		obj.output();
		
//		ObjectInputStream is;
	}


}
