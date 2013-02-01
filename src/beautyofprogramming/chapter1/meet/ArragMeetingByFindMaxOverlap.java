package beautyofprogramming.chapter1.meet;


/**
 * 1.9
 * 问题描述：
 * n个会议要举行，每个会议之间的起始时间点都知道，求最少安排多少会议个场所
 * 
 * 解法二：
 * 对于时间区间的最大互斥判断，即是求最大重叠次数
 * 1.对所有区间的起始时间进行整体排序，时间复杂度为O(n * log(n))
 * 2.设最大重叠数为maxMeetCount，遍历排序后的数组
 * 遇到一个开始时间，则maxMeetCount加一
 * 遇到一个结束时间，则maxMeetCount减一
 * 3.整个过程中 maxMeetCount的最大值为所需要的最少房间，时间复杂度为O(n)
 *
 */
public class ArragMeetingByFindMaxOverlap {

	private int meetCount = 4;

	private Meet[] meetArray = new Meet[meetCount];
	private Time[] timeArray = new Time[meetCount * 2];

	private int maxMeetCount = 0;

	public ArragMeetingByFindMaxOverlap() {
		initMeet();
	}

	public void initMeet() {

		for (int i = 0; i < meetCount; i++) {
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

	public void sortByAllTime() {

		for (int i = 0; i < meetCount; i++) {
			timeArray[i] = meetArray[i].startTime;
			timeArray[i + meetCount] = meetArray[i].endTime;
		}

		for (int i = 0; i < 2 * meetCount; i++) {
			for (int j = 1; j < 2 * meetCount - i; j++) {

				if (timeArray[j - 1].value >= timeArray[j].value) {

					// 如果结束点和开始点value是一样的，且结束点在前面，则不进行交换
					if (timeArray[j - 1].value == timeArray[j].value && timeArray[j - 1].type == TimeType.end && timeArray[j].type == TimeType.start) {
						continue;
					}

					Time time = timeArray[j - 1];
					timeArray[j - 1] = timeArray[j];
					timeArray[j] = time;
				}

			}
		}

	}

	public void searchByAllTime() {

		sortByAllTime();

		int max = 0;
		for (int i = 0; i < 2 * meetCount; i++) {
			if (timeArray[i].type == TimeType.start) {
				max++;
				if (max > maxMeetCount) {
					maxMeetCount = max;
				}
			} else {
				max--;
			}
		}
	}

	public void output() {
		for (int i = 0; i < meetCount; i++) {
			System.out.print(meetArray[i].startTime.value + "");
		}
		System.out.println();

		System.out.println("maxMeetCount = " + maxMeetCount);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArragMeetingByFindMaxOverlap obj = new ArragMeetingByFindMaxOverlap();
		obj.searchByAllTime();
		obj.output();
	}

}
