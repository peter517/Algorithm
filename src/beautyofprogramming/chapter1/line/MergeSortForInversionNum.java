package beautyofprogramming.chapter1.line;

public class MergeSortForInversionNum {
    
	public int count = 0;
	public int[] data;
	
	public MergeSortForInversionNum(int[] data){
		this.data = data;
	}
	
	/**
	 * 分解解空间
	 */
	private void mergeSort(int start, int end){
		
		if(start >= end){
			return;
		}
		
		int mid = (end + start) / 2;
		mergeSort(start, mid);
		mergeSort(mid + 1 , end);
		
		merge(start, end);
	}
	
	/**
	 * 对每个空间进行排序操作，这里没用哨兵
	 */
	private void merge(int start, int end){
		
		int mid = (end + start) / 2;
		int[] buf = new int[end - start + 1];
		
		int leftIndex = start;
		int rightIndex = mid + 1;
		int bufIndex = 0;
		
		//第一次排序
		while(true){
			
			if(leftIndex > mid || rightIndex > end){
				break;
			}
			
			if(leftIndex <= mid && data[leftIndex] <= data[rightIndex] ){
				buf[bufIndex++] = data[leftIndex++];
			}else if(rightIndex <= end && data[leftIndex] > data[rightIndex]){
				//计算逆数 方法一
				count += (mid - leftIndex + 1);
				buf[bufIndex++] = data[rightIndex++];
			}
			
		}
		
		//左子序列或右子序列遍历完成后，再进行排序
		if(leftIndex > mid){
			while(rightIndex <= end){
				buf[bufIndex++] = data[rightIndex++];
			}
		}else{
			//计算逆数 方法二 这个方法有缺陷
//			count += (mid - leftIndex + 1) * (end - mid);
			while(leftIndex <= mid){
				buf[bufIndex++] = data[leftIndex++];
			}
			  
		}
		
		for (int i = start; i <= end; i++){
			data[i] = buf[i - start];
		}
		
	}
	
	/**
	 * 获得逆序数
	 */
	public int getInversionNum(){
		mergeSort(0, data.length - 1);
		return count;
	}
	
}
