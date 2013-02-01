package beautyofprogramming.chapter1.cpu;

/**
 * 1.1
 * 绘制出指定CPU值的直线
 * 主要思路：
 * 利用Thread.sleep(waitTime)来释放CPU资源，使得本程序暂停
 */
public class CPUCurve {

	private static int timeInterval = 100;
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {

	
		//
//		float targetValueOfCPU = 50;
//		float maxValueOfCPU = 100;
//		float minValueOfCPU = 0;
//		int runTime = (int) ((targetValueOfCPU / (maxValueOfCPU - minValueOfCPU)) * timeinterval);
//		int waitTime = timeinterval - runTime;
		
//		while(true){
//			long startTime = System.currentTimeMillis();
//			
//			while(System.currentTimeMillis() - startTime < runTime)
//				;
//			
//				Thread.sleep(waitTime);
//			}
		
		
	    //绘制出指定CPU值的正弦直线
		double n = 0.0;
		
		while(true){
			long startTime = System.currentTimeMillis();
			//利用相应的正弦值，计算出占用CPU的时间
			int runTime = (int) ((Math.sin(Math.PI * n) + 1) * timeInterval / 2);
			int waitTime = timeInterval - runTime;
			while(System.currentTimeMillis() - startTime < runTime)
				;
			
				Thread.sleep(waitTime);
				n += 0.1;
			}
		}

}
