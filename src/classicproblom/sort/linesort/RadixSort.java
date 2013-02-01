package classicproblom.sort.linesort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class DataObject {

	public int data;
	public int digitCount;
	public List<Integer> digitList = new ArrayList<Integer>();

	public DataObject(int data) {
		this.data = data;
		initData(data);
		this.digitCount = this.digitList.size();
	}

	private void initData(int data) {
		while (data != 0) {
			digitList.add(data % 10);
			data = data / 10;
		}
	}
}

public class RadixSort {

	private static int getMaxNumOfArray(DataObject[] dataObject, int digit) {

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < dataObject.length; i++) {
			int data = (Integer) dataObject[i].digitList.get(digit);
			if (max <= data) {
				max = data;
			}
		}
		return max;
	}

	/**
	 * 计数排序
	 */
	private static DataObject[] countSort(DataObject[] srcDataObjectArray, int digit) {

		final int maxNum = getMaxNumOfArray(srcDataObjectArray, digit);

		int[] countArray = new int[maxNum + 1];

		for (int i = 0; i < srcDataObjectArray.length; i++) {
			int data = (Integer) srcDataObjectArray[i].digitList.get(digit);
			countArray[data]++;
		}
		for (int i = 1; i <= maxNum; i++) {
			countArray[i] = countArray[i - 1] + countArray[i];
		}

		DataObject[] dstDataObjectArray = new DataObject[srcDataObjectArray.length];
		
		//这里排序是不稳定的
//		for (int i = 0; i < srcDataObject.length; i++) {
//			int data = (Integer) srcDataObject[i].digitList.get(digit);
//			dstDataObject[countArray[data] - 1] = srcDataObject[i];
//			countArray[data]--;
//		}
		
		//这里排序才是稳定的，从尾部向头部遍历
		for (int i = srcDataObjectArray.length - 1; i >= 0 ; i--) {
			int data = (Integer) srcDataObjectArray[i].digitList.get(digit);
			dstDataObjectArray[countArray[data] - 1] = srcDataObjectArray[i];
			countArray[data]--;
		}

		return dstDataObjectArray;
	}

	public static DataObject[] radixSort(DataObject[] srcDataObjectArray, int digit) {

		DataObject[] dstDataObjectArray = srcDataObjectArray;
		for (int i = 0; i < digit; i++) {
			dstDataObjectArray = countSort(dstDataObjectArray, i);
		}
		return dstDataObjectArray;
	}

	/**
	 * 基数排序中使用到了排序的稳定性
	 */
	public static void main(String[] args) {

//		int[] data = { 100, 623, 107, 118, 736, 121, 151, 303, 302 };
		 int[] data = new int[50];
		 Random random = new Random();
		
		 for (int i = 0; i < data.length; i++) {
		 data[i] = random.nextInt(100) + 100;
		 }
		 
		DataObject[] dataObject = new DataObject[data.length];
		for (int i = 0; i < dataObject.length; i++) {
			dataObject[i] = new DataObject(data[i]);
		}

		//针对三位数进行处理
		int digit = 3;
		for (int i = 0; i < dataObject.length; i++) {
			if(dataObject[i].digitCount != digit){
				System.out.println("error");
				return;
			}
		}
		
		DataObject[] dstDataObject = radixSort(dataObject, digit);

		for (int i = 0; i < dataObject.length; i++) {
			System.out.println(dstDataObject[i].data);
		}

	}

}
