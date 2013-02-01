package classicproblom.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态规划最主要的还是找到最优子结构
 * 这里currentLongestLength[i][j]表示x数组取x[1],……x[i]时，y数组取y[1],……y[j]时，最大公共子序列
 * 
 *                                   0                                                                   当i==0,j==0;
 * currentLongestLength[i][j] =      currentLongestLength[i-1][j-1] + 1                                  当x[i] == y[j]
 *                                   max{currentLongestLength[i-1][j],currentLongestLength[i][j-1]}      当x[i] != y[j]
 */
public class LCSOfDiscrete {

	//状态表示
	public static final int selected = 0x01;
	public static final int up = 0x02;
	public static final int left = 0x03;

	public int[] x = { 0, 1, 1, 5, 2, 3, 5 };
	public int[] y = { 0, 1, 3, 7, 5, 2, 6, 8, 5 };

	public int[][] currentLongestLength = new int[x.length][y.length];
	public int[][] statusRecord = new int[x.length][y.length];
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
					statusRecord[i][j] = LCSOfDiscrete.selected;
				} else {
					if (currentLongestLength[i - 1][j] > currentLongestLength[i][j - 1]) {
						currentLongestLength[i][j] = currentLongestLength[i - 1][j];
						statusRecord[i][j] = LCSOfDiscrete.up;
					} else {
						currentLongestLength[i][j] = currentLongestLength[i][j - 1];
						statusRecord[i][j] = LCSOfDiscrete.left;
					}
				}

			}
		}

		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < y.length; j++) {
				System.out.print(statusRecord[i][j] + " ");
			}
			System.out.println("");
		}
	}

	/**
	 * 根据最优解结构寻找解
	 */
	public void getLongestCommonSubsequence() {

		int i = x.length - 1;
		int j = y.length - 1;

		while (i != 0 && j != 0) {
			int status = statusRecord[i][j];
			switch (status) {
			case LCSOfDiscrete.selected:
				resultList.add(x[i]);
				i--;
				j--;
				break;
			case LCSOfDiscrete.left:
				j--;
				break;
			case LCSOfDiscrete.up:
				i--;
				break;
			}
		}
	}

	public static void main(String[] args) {

		LCSOfDiscrete lsc = new LCSOfDiscrete();

		lsc.searchLongestCommonSubsequence();
		lsc.getLongestCommonSubsequence();

		for (int i = lsc.resultList.size() - 1; i >= 0; i--) {
			System.out.print(lsc.resultList.get(i) + " ");
		}
	}

}
