package beautyofprogramming.chapter1.books;

/**
 * 1.4
 * 问题描述：
 * 购买N本书，对于打折情况进行分析，怎样分批购买
 * 
 * 主要思路：
 * 回溯法找出最优解，复杂度较高
 */
public class BuyBooksByBackTracking {

	
	public static int[] bookCount = {2, 3, 4, 5};//能打折的购买的个数
	public static float[] bookDisCount = {0.05f, 0.1f, 0.2f, 0.25f};//相应的折扣
	public static int bookPrivilegeTpyeCount = bookDisCount.length;
	
	public static int[] bookPrivilegeTpyeBuffer = new int[bookDisCount.length];
	public static int[] bookPrivilegeTpyeResult = new int[bookDisCount.length];
	
	public static int bookTotal = 8;
	public static float disCountTotalBuffer = 0;
	public static float disCountTotalResult = 0;
	
	public void search(int bookToal){
		
		if (bookToal < 2){
			
			if (bookToal < 0){
				return;
			}
			if(disCountTotalBuffer > disCountTotalResult){
				updateResultData();
			}
			return;
		}
		
		for (int i = 0; i < bookPrivilegeTpyeCount; i++){
			
			bookPrivilegeTpyeBuffer[i]++;
			disCountTotalBuffer += bookDisCount[i] * bookCount[i]; 
			
			search(bookToal - bookCount[i]);
			
			disCountTotalBuffer -= bookDisCount[i] * bookCount[i];
			bookPrivilegeTpyeBuffer[i]--;
		}
		
	}

	private void updateResultData() {
		disCountTotalResult = disCountTotalBuffer;
		for (int i = 0; i < bookPrivilegeTpyeCount; i++){
			bookPrivilegeTpyeResult[i] = bookPrivilegeTpyeBuffer[i];
		}
	}
	
	public void output(){
		System.out.println("disCountTotalResult = " + disCountTotalResult);
		for (int i = 0; i < bookPrivilegeTpyeCount; i++){
			System.out.println(bookPrivilegeTpyeResult[i] + "次" + bookCount[i] + "本");
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BuyBooksByBackTracking obj = new BuyBooksByBackTracking();
		obj.search(BuyBooksByBackTracking.bookTotal);
		obj.output();
	}

}
