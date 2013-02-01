package classicproblom.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/*
 * 动态规划最主要的还是找到最优子结构
 * 这里currentLongestLength[i][j]表示x数组取x[1],……x[i]时，y数组取y[1],……y[j]时，最大连续公共子序列
 * 
 *                                   0                                                                   当i==0,j==0;
 * currentLongestLength[i][j] =      currentLongestLength[i-1][j-1] + 1                                  当x[i] == y[j]
 *                                   0                                                                   当x[i] != y[j]
 */
public class LSCOfSerial {

		public int[] x = { 0, 0, 1, 5, 2, 3, 6, 6, 8, 5, 3};
		public int[] y = { 0, 1, 3, 7, 5, 2, 6, 8, 5};

		public int[][] currentLongestLength = new int[x.length][y.length];
		public List<Integer> resultList = new ArrayList<Integer>();

		/*
		 * 建立最优解结构
		 */
		public void searchLongestCommonSubsequence() {

			for (int i = 0; i < x.length; i++) {
				currentLongestLength[i][0] = 0;
			}
			for (int j = 0; j < y.length; j++) {
				currentLongestLength[0][j] = 0;
			}

			for (int i = 1; i < x.length; i++) {
				for (int j = 1; j < y.length; j++) {

					if (x[i] == y[j]) {
						currentLongestLength[i][j] = currentLongestLength[i - 1][j - 1] + 1;
					} else {
						currentLongestLength[i][j] = 0;
					}

				}
			}

			for (int i = 0; i < x.length; i++) {
				for (int j = 0; j < y.length; j++) {
					System.out.print(currentLongestLength[i][j] + " ");
				}
				System.out.println("");
			}
		}

		/*
		 * 根据最优解结构寻找解
		 */
		public void getLongestCommonSubsequence() {

			int count = 0;
			int lsciEnd = 0;
			int lscjEnd = 0;
			
			for (int i = 1; i < x.length; i++) {
				for (int j = 1; j < y.length; j++) {
					int currentCount = currentLongestLength[i][j];
					if(currentCount >= count){
						count = currentCount;
						lsciEnd = i;
						lscjEnd = j;
				   }
				}
			}
			
			while (currentLongestLength[lsciEnd][lscjEnd] >= 1){
				resultList.add(x[lsciEnd]);
				lsciEnd--;
				lscjEnd--;
			}

		}

		public static void main(String[] args) {

			LSCOfSerial lsc = new LSCOfSerial();

			lsc.searchLongestCommonSubsequence();
			lsc.getLongestCommonSubsequence();

			System.out.println("result:");
			for (int i = lsc.resultList.size() - 1; i >= 0; i--) {
				System.out.print(lsc.resultList.get(i) + " ");
			}
		}


}
