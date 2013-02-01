package classicproblom.sort.comparesort;

import java.util.Random;

public class QuickShort {

	/*
	 * 基于基准排序
	 */
	private static int partition(int[] data, int start, int end) {

		int i = start;
		int j = end;
		int standard = data[start];

		while (i < j) {
			
			while (i < j && data[j] > standard) {
				j--;
			}
			if(i < j){
				data[i++] = data[j];
			}
			
			while (i < j && data[i] < standard ) {
				i++;
			}
			if(i < j){
				data[j--] = data[i];
			}
			
		}
		
		data[i] = standard;
		
		return i;

	}

	/*
	 * 划分问题空间
	 */
	public static void quickSort(int[] data, int start, int end) {
		if (start < end) {
			int standard = partition(data, start, end);
			quickSort(data, start, standard - 1);
			quickSort(data, standard + 1, end);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		 int[] data = { 3, 7, 5, 6, 1, 10, 2, 14,15,555,3,7};

		int[] data = new int[50];
		Random random = new Random();

		for (int i = 0; i < 50; i++) {
			data[i] = random.nextInt(100);
		}
		quickSort(data, 0, data.length - 1);
		// System.out.println(partition(data, 0, data.length - 1));
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
//			System.out.println("add = " + (data[i + 1] - data[i]));
		}

	}

}
