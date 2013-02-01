package classicproblom.division;

import java.util.Random;

public class TheKthLargeNum {

	public static int k;
	public static int value;
	public static boolean isStopFind = false;

	/**
	 * 解法一
	 * 快排
	 */
	
	private static int partition(int[] data, int start, int end) {

		int i = start;
		int j = end;
		int standard = data[start];

		while (i < j) {

			while (i < j && data[j] >= standard) {
				j--;
			}
			if (i < j) {
				data[i++] = data[j];
			}

			while (i < j && data[i] < standard) {
				i++;
			}
			if (i < j) {
				data[j--] = data[i];
			}
		}

		data[i] = standard;
		return i;
	}

	public static void quikSort(int[] data, int start, int end) {

		//这里把等号去掉就能让数组中每个值的下标都能成为standard
		if (start > end || isStopFind) {
			return;
		}

		int standard = partition(data, start, end);
		
		if (standard == k - 1) {
			value = data[standard];
			isStopFind = true;
			return;
		}
		
		quikSort(data, start, standard - 1);
		quikSort(data, standard + 1, end);

	}
	
	
	
	
	/**
	 * 解法二
	 * 利用计数法
	 */
	
    private static int getMaxNumOfArray(int[] data) {
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < data.length; i++) {
			if (max <= data[i]) {
				max = data[i];
			}
		}
		return max;
	}
    
    private static int getMinNumOfArray(int[] data) {
		
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < data.length; i++) {
			if (min >= data[i]) {
				min = data[i];
			}
		}
		return min;
	}
    
    public static void getTheKthLargeNumByCount(int[] data, int min, int max){
    	
    	if (min > max || isStopFind == true){
    		return;
    	}
    	
    	int mid = (min + max) / 2;
    	int count = 0;
    	boolean isMemberOfDataArray = false;
    	
    	for (int i = 0; i < data.length; i++){
    		if(data[i] > mid){
    			count++;
    		}
    		if(data[i] == mid){
				isMemberOfDataArray = true;
			}
    	}


    	if(count == k - 1){
    		if(isMemberOfDataArray){
    			value = mid;
        		isStopFind = true;
    		}else{
    			getTheKthLargeNumByCount(data, min, mid - 1);
    		}
    	}else if (count > k - 1){
    		getTheKthLargeNumByCount(data, mid + 1, max);
    	}else if (count < k - 1){
    		getTheKthLargeNumByCount(data, min, mid - 1);
    	}
    }
    
    /**
	 * 解法三
	 * 冒泡法
	 */
    public static void getTheKthLargeNumByBubbling(int[] data){
    	
    	for (int i = 0; i < TheKthLargeNum.k; i++){
    		for (int j = 0; j < data.length - i - 1; j++){
    			if (data[j] > data[j + 1]){
    				int temp = data[j + 1];
    				data[j + 1] = data[j];
    				data[j] = temp;
    			}
    		}
    	}
    	TheKthLargeNum.value = data[data.length - TheKthLargeNum.k];
    	
    }
    
	public static void main(String[] args) {
//		 int[] data = {5,4,7,2,2,1};

		// 寻找第四小的数
		TheKthLargeNum.k = 2;

		int[] data = new int[15];
		Random random = new Random();

		for (int i = 0; i < 15; i++) {
			data[i] = random.nextInt(100);
		}

//		quikSort(data, 0, data.length - 1);
//		getTheKthLargeNumByCount(data, getMinNumOfArray(data), getMaxNumOfArray(data));
		getTheKthLargeNumByBubbling(data);
		
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}

		System.out.println("TheKthLargeNum.value = " + TheKthLargeNum.value);
	}

}
