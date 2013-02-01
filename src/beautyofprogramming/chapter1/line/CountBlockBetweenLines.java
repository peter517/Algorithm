package beautyofprogramming.chapter1.line;


/**
 * 问题描述：
 * 有两条平行于y轴的直线A和B，还有许多不和y轴平行的线将这两条直线A和B之间的区域瓜分，且直线两两不相交
 * 求被瓜分出来的块数
 * 
 * 主要思路
 * 分治法，在合并排序过程中，归纳出公式，求逆数
 */

class Line{
	public float k;
	public float b;
	public float x1;
	public float x2;
	public int order;
}

public class CountBlockBetweenLines {
	
	public Line[] lineArray = new Line[3];
	public int[] data = new int[lineArray.length];
	
	public int leftX = 0;
	public int rightX = 0;
	
	public void initData(){
		
		for (int i = 0; i < lineArray.length; i++){
			lineArray[i] = new Line();
		}
		
		lineArray[0].b = 0;
		lineArray[0].k = 1;
		
		lineArray[1].b = 2;
		lineArray[1].k = -1;
		
		lineArray[2].b = 1;
		lineArray[2].k = 0;

		initX();
	}
	
	/**
	 * 初始化左右横坐标相应的纵坐标
	 */
	public void initX(){
		for (int i = 0; i < lineArray.length; i++){
			lineArray[i].x1 = lineArray[i].b + leftX * lineArray[i].k;
			lineArray[i].x2 = lineArray[i].b + rightX * lineArray[i].k;
		}
	}
	
	/**
	 * 左横坐标排序
	 */
	public void sortLeftX(){
		for (int i = 0; i < lineArray.length; i++){
			for (int j = 1; j < lineArray.length - i; j++){
				if(lineArray[j - 1].x1 > lineArray[j].x1){
					Line line = lineArray[j - 1];
					lineArray[j - 1] = lineArray[j];
					lineArray[j] = line;
				}
			}
		}
		
		for(int i = 0; i < lineArray.length; i++){
			lineArray[i].order = i;
		}
	}
	
	/**
	 * 右横坐标排序
	 */
	public void sortRightX(){
		for (int i = 0; i < lineArray.length; i++){
			for (int j = 1; j < lineArray.length - i; j++){
				if(lineArray[j - 1].x2 > lineArray[j].x2){
					Line line = lineArray[j - 1];
					lineArray[j - 1] = lineArray[j];
					lineArray[j] = line;
				}
			}
		}
		
		//把逆序数组保存
		for(int i = 0; i < lineArray.length; i++){
			data[i] = lineArray[i].order;
		}
		
	}
	
	/**
	 * 获取相交点个数
	 */
	private int getNodeCount(){
		MergeSortForInversionNum mergSort = new MergeSortForInversionNum(data);
		return mergSort.getInversionNum();
	}
	
	public void output(){
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
		System.out.println("blocks = " + (getNodeCount() + lineArray.length + 1));
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CountBlockBetweenLines obj = new CountBlockBetweenLines();
		obj.initData();
		obj.sortLeftX();
		obj.sortRightX();
		obj.output();
	}

}
