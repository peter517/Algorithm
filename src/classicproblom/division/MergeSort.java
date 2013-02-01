package classicproblom.division;

import java.util.Random;

public class MergeSort {

	/**
	 * 子问题合并算法
	 */
	private static void merge(int[] data, int start, int end) {

		//利用左序列和右序列的有序性，可以在O(end - start)时间复杂度内排好序
		int middle = (start + end) / 2;
		int[] leftArray = new int[middle - start + 2];
		int[] rightArray = new int[end - middle + 1];

		for (int i = start; i <= middle; i++) {
			leftArray[i - start] = data[i];
		}
		leftArray[middle - start + 1] = Integer.MAX_VALUE;

		for (int i = middle + 1; i <= end; i++) {
			rightArray[i - middle - 1] = data[i];
		}
		rightArray[end - middle] = Integer.MAX_VALUE;

		// 这里使用各自的循环变量
		int n = 0;
		int m = 0;

		for (int i = start; i <= end; i++) {

			// 建立哨兵后算法就很巧妙了
			if (leftArray[m] >= rightArray[n]) {
				data[i] = rightArray[n];
				n++;
			} else if (leftArray[m] < rightArray[n]) {
				data[i] = leftArray[m];
				m++;
			}
		}
	}

	/**
	 * 划分问题空间
	 */
	public static void mergeSort(int[] data, int start, int end) {

		if (start < end) {

			int middle = (start + end) / 2;
			mergeSort(data, start, middle);
			mergeSort(data, middle + 1, end);

			merge(data, start, end);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] data = new int[50];
		Random random = new Random();
		
		for (int i = 0; i < 50; i++) {
			data[i] = random.nextInt(100);
		}
		
		mergeSort(data, 0, data.length - 1);
		
		for (int i = 0; i < data.length - 1; i++) {
			System.out.println(data[i]);
//			System.out.println("add = " + (data[i + 1] - data[i]));
		}
	}

}
