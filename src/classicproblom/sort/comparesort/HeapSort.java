package classicproblom.sort.comparesort;

import java.util.Random;

class Data {

	public int[] dataArray;
	public int dataLength;

	public Data(int[] dataArray) {
		this.dataArray = dataArray;
		dataLength = dataArray.length;
	}
}

public class HeapSort {

	/**
	 * 堆排序
	 */
	public static void heapSort(Data obj) {

		buildHeap(obj);

		int dataLength = obj.dataLength;
		for (int i = dataLength - 1; i >= 2; i--) {

			int temp = obj.dataArray[1];
			obj.dataArray[1] = obj.dataArray[i];
			obj.dataArray[i] = temp;
			obj.dataLength--;

			maxHeap(obj, 1);
		}

		obj.dataLength = dataLength;
	}

	/**
	 * 对于给定数组，建立最大堆
	 */
	private static void buildHeap(Data obj) {

		for (int i = obj.dataLength / 2; i >= 1; i--) {
			maxHeap(obj, i);
		}
	}

	/**
	 * 对于指定节点，保证最大堆特性
	 */
	private static void maxHeap(Data obj, int i) {

		int left = 2 * i;
		int right = 2 * i + 1;
		int parent = i;
		int maxIndex = parent;// 假设堆顶值最大

		if (parent < obj.dataLength - 1) {

			if (left < obj.dataLength && obj.dataArray[left] > obj.dataArray[parent]) {
				maxIndex = left;
			}
			if (right < obj.dataLength && obj.dataArray[right] > obj.dataArray[maxIndex]) {
				maxIndex = right;
			}

			if (maxIndex != parent) {

				int temp = obj.dataArray[maxIndex];
				obj.dataArray[maxIndex] = obj.dataArray[parent];
				obj.dataArray[parent] = temp;

				maxHeap(obj, maxIndex);
			}

		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// int[] data = { 0, 3, 7, 8, 6, 100, 102, 103};

		int[] data = new int[50];
		Random random = new Random();

		for (int i = 0; i < 50; i++) {
			data[i] = random.nextInt(100);
		}

		Data obj = new Data(data);

		// maxHeap(obj, 1);
		// buildHeap(obj);
		heapSort(obj);

		for (int i = 1; i < obj.dataLength; i++) {
			System.out.println(obj.dataArray[i]);
		}
	}

}
