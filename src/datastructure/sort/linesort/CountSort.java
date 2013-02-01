package datastructure.sort.linesort;

import java.util.Random;

public class CountSort<T> {

	private static int getMaxNumOfArray(int[] data) {
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < data.length; i++) {
			if (max <= data[i]) {
				max = data[i];
			}
		}
		return max;
	}

	/**
	 * 计数排序
	 */
	public static int[] countSort(int[] srcData) {
		
         final int maxNum = getMaxNumOfArray(srcData);
         int[] countArray = new int[maxNum + 1];
       
         for (int i = 0; i < srcData.length; i++) {
        	 countArray[srcData[i]]++;
         }
         for (int i = 1; i <= maxNum; i++) {
        	 countArray[i] = countArray[i -1] + countArray[i];
         }
         
         int[] dstData = new int[srcData.length];
         for (int i = srcData.length - 1; i >= 0 ; i--) {
        	 
        	 int newIdex = countArray[srcData[i]] - 1;
        	 
        	 dstData[newIdex] = srcData[i];
        	 countArray[srcData[i]]--;
         }
         
         return dstData;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		int[] data = { 0, 3, 7, 8, 6, 1, 1, 13, 12 };
		
		int[] data = new int[50];
		Random random = new Random();

		for (int i = 0; i < 50; i++) {
			data[i] = random.nextInt(100);
		}
		
		int[] dstData = countSort(data);
		for (int i = 0; i < data.length; i++) {
			System.out.println(dstData[i]);
		}
	}

}
