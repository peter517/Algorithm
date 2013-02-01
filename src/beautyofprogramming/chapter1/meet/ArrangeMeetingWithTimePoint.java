package beautyofprogramming.chapter1.meet;

/**
 * 1.9
 * 问题描述：
 * n个会议要举行，每个会议之间的起始时间点都知道，求最少安排多少会议个场所
 * 
 * 解法一：
 * 主要思路（贪心算法）
 * 
 * 1.根据每个人选择的会议，对所有会议的起始点进行升序排序
 * 2.对第一个会议，申请一个场所，然后处理第下一个会议
 * 3.设目前场所个数为k，对于第i个会议，编号为i，先查看在1……k中其与哪些会议互斥，
 * 如果互斥个数大于等于k，则新申请一个场所，并把编号为i的会议放在这个场所
 * 如果互斥个数小于k，则在n中随机选择一个场所
 * 4.直到所有会议处理完毕，至此2-4步骤的时间复杂度为O(n ^ 2)
 * 
 * 贪心算法合理性证明：
 * 1.对于第k个会议，假设其与第1……k-1个会议冲突时间点为TS1……TE1，所以k选择的房间只能在0……TS1之间，或者新申请一个房间
 *   与第k+1……n个会议的冲突时间点为TS2……TE2
 * 2.因为会议开始时间是升序排列的，所以k会议选择房间对第k+1……n个会议选择房间没有影响
 * 3.这样每步就可以进行贪心算法
 * 
 */

class TimeType{
	static final int start = -1;
	static final int end = -2;
}

class Time{
	public Time(int type){
		this.type = type;
	}
	int value;
	int type;
}

class Meet{
	Time startTime = new Time(TimeType.start);
	Time endTime = new Time(TimeType.end);
}


public class ArrangeMeetingWithTimePoint {

    private int meetCount = 4;
	
    Meet[] meetArray = new Meet[meetCount];
    
	private int maxMeetCount = 0;
	
	public ArrangeMeetingWithTimePoint(){
		initMeet();
	}
	
	public void initMeet(){
		
		for (int i = 0; i < meetCount; i++){
			meetArray[i] = new Meet();
			meetArray[0].startTime.type = TimeType.start;
		}
		
		meetArray[0].startTime.value = 2;
		meetArray[0].endTime.value = 3;
		
		meetArray[1].startTime.value = 3;
		meetArray[1].endTime.value = 6;
		
		meetArray[2].startTime.value = 3;
		meetArray[2].endTime.value = 4;
		
		meetArray[3].startTime.value = 1;
		meetArray[3].endTime.value = 5;
		
		
	}
	
	public void sortByStartTime(){
		
		for (int i = 0; i < meetCount; i++){
			for (int j = 1; j < meetCount - i; j++){
				
				if (meetArray[j - 1].startTime.value > meetArray[j].startTime.value){
					Meet meet = meetArray[j - 1];
					meetArray[j - 1] = meetArray[j];
					meetArray[j] = meet;
				}
				
			}
		}
	}
	
    public boolean isOverlap(Meet meetBefore, Meet meetAfter){
    	
    	int meetBeforeLength = meetBefore.endTime.value - meetBefore.startTime.value;
    	int meetAfterLength = meetAfter.endTime.value - meetAfter.startTime.value;
    	
    	if ((meetAfter.endTime.value - meetBefore.startTime.value) >= meetBeforeLength + meetAfterLength){
    		return false;
    	}else{
    		return true;
    	}
    	
    }
	
	
	public void searchByStartTime(){
		
		sortByStartTime();
		
		maxMeetCount = 1;
		for (int i = 1; i < meetCount; i++){
			
			for (int j = 0; j < i; j++){
				if(!isOverlap(meetArray[j],meetArray[i])){
					maxMeetCount++;
					break;
				}
			}
			
		}
	}
	
	
	public void output(){
		for (int i = 0; i < meetCount; i++){
				System.out.print(meetArray[i].startTime.value + "");
			}
			System.out.println();
		
		System.out.println("maxMeetCount = " + maxMeetCount);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrangeMeetingWithTimePoint obj = new ArrangeMeetingWithTimePoint();
		obj.searchByStartTime();
		obj.output();
	}

}
