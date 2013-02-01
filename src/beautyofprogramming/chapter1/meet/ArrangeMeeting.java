package beautyofprogramming.chapter1.meet;

import java.util.HashMap;
import java.util.Map;


/**
 * 1.9
 * 问题描述：
 * n个会议要举行，每个会议之间参加的人数可能有重叠，总人数为m个，求最少安排多少会议个场所
 * 
 * 主要思路
  * 该问题是着色问题，典型的NP完全问题，只能用回溯法找
 * 
 * 1.根据每个人选择的会议，计算出会议之间的互斥特性，用n * n的二维矩阵进行存储，时间复杂度为O(n * n * m)
 * 2.对一个会议，申请一个场所，然后处理第下一个会议
 * 3.对于第i个会议，编号为i，尝试1……n个放入场所，一直递归到最后一个会议
 * 4.如果这次安排满足条件，则进行记录，返回上层继续寻找解
 * 5.直到所有解空间都遍历完一次,时间复杂度O((n - 1) ^ n)
*/

public class ArrangeMeeting {

	private int meetCount = 5;
	
	int[][] meetConflictArray = new int[meetCount + 1][meetCount + 1];
	
	private int minRoomCount = Integer.MAX_VALUE;
	private int maxRoomCount = meetCount;
	
	//从下标1开始，数组值对应着房间编号，1……meetCount
	private int[] meetBufferArray = new int[meetCount + 1]; 
	private int[] meetResultArray = new int[meetCount + 1]; 
	
	private class Person{
		int[] meetArray = new int[meetCount + 1];
	}
	private Person[] personArray = new Person[4];
	

	public ArrangeMeeting(){
		initPersonArray();
		initMeetConflictArray();
		meetBufferArray[1] = 1;
	}
	
	public void initPersonArray(){
		
		for (int i = 0; i < personArray.length; i++){
			personArray[i] = new Person();
		}
		
		personArray[0].meetArray[1] = 1;
		personArray[0].meetArray[2] = 3;
		personArray[0].meetArray[3] = 5;
		
		personArray[1].meetArray[1] = 1;
		personArray[1].meetArray[2] = 4;
		personArray[1].meetArray[3] = 5;
		
		personArray[2].meetArray[1] = 5;
		personArray[2].meetArray[2] = 2;
		personArray[2].meetArray[3] = 4;
		
		personArray[3].meetArray[1] = 5;
		personArray[3].meetArray[2] = 3;
		personArray[3].meetArray[3] = 2;
		
	}
	
	public void initMeetConflictArray(){
		
		for (int i = 0; i < personArray.length; i++){
			for (int j = 1; j < personArray[i].meetArray.length; j++){
				for (int k = j; k < personArray[i].meetArray.length; k++){
					meetConflictArray[personArray[i].meetArray[j]][personArray[i].meetArray[k]] = 1;
				}
			}
		}
		
		for (int i = 1; i <= meetCount; i++){
			for (int j = 1; j <= meetCount; j++){
				if(meetConflictArray[i][j] == 1){
					meetConflictArray[j][i] = 1;
				}
			}
		}
		
	}
	
	/**
	 * 从第二个会议开始回溯
	*/
	public void search(){
		search(2);
	}
	
	public void search(int k){
		
		if(isConflict(k - 1)){
			return;
		}
		
		//如果遍历到了叶子节点
		if(k == meetCount + 1){
			
			int count = getRoomCount();
			
			if(minRoomCount > count){
				updateResultData(count);
			}
			return;
		}
		
			for (int j = 1; j <= maxRoomCount; j++){
				
				meetBufferArray[k] = j;
				search(k + 1);
			
		}
	}

	
	private void updateResultData(int count) {
		minRoomCount = count;
			for (int i = 1; i <= meetCount; i++){
				meetResultArray[i] = meetBufferArray[i];
		}
	}
	
	/**
	 * 编号为1……k的会议是否冲突
	*/
	public boolean isConflict(int k){
		
		for (int i = 1; i <= k; i++){
			for (int j = i + 1; j <= k; j++){
				if(meetBufferArray[i] == meetBufferArray[j] && meetConflictArray[i][j] == 1){
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 获得房间数目
	*/
	public int getRoomCount(){
		
		Map map = new HashMap();
		for (int i = 1; i <= meetCount; i++){
			    map.put(meetBufferArray[i], "");
			}
		
		return map.size();
	}
	
	public void output(){
		for (int i = 1; i <= meetCount; i++){
			for (int j = 1; j <= meetCount; j++){
				System.out.print(meetConflictArray[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
		for (int i = 1; i <= meetCount; i++){
			System.out.print(meetResultArray[i] + " ");
		}
		
		System.out.println("minMeetCount = " + minRoomCount);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrangeMeeting obj = new ArrangeMeeting();
		obj.search();
		obj.output();
	}

}
