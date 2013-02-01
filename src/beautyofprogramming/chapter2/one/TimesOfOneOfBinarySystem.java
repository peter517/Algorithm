package beautyofprogramming.chapter2.one;

/**
 * 2.1
 * 问题描述：
 * 计算二进制中1出现的次数
 * 
 * 基本思路
 * 可以用除法和移位法，这样的复杂度为O(log(n)),这里有一个方法，其复杂度只跟二进制中1的个数有关
 * 
 * data为源数据,设count = 0;
 * 1.如果data != 0,那么data = data & (data - 1),count++;
 * 2.直至data == 0,count就是结果
 * 
 * 证明：
 * data - 1造成的结果有三种：
 * 1.data二进制中的最后一位有1变为0
 * 2.data二进制中除了最后一位的某一位由1变为0，或者某几位的由0变为1
 * 3.无论是1还是2，0变成1将会在与操作后被消除，最后的效果就是某一位由1变成0
 * 所以data与data-1进行与操作后，data中的二进制中1的数目将减1
 * 
 * 时间复杂度：O(m),m为data二进制中1的个数
 * 
*/
public class TimesOfOneOfBinarySystem {

	public int data = 0xf0f0;
	public int count = 0;
	
	public void search(){
		while(data != 0){
			data = data & (data - 1);
			count++;
		}
	}
	
	public void output(){
		System.out.println(count);
	}
	
	public static void main(String[] args) {
		TimesOfOneOfBinarySystem obj = new TimesOfOneOfBinarySystem();
		obj.search();
		obj.output();
	}

}
