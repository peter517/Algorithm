package classicproblom.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题描述：
 * 求两个字符串的连续最长子序列
 * 
 * 递归法：
 * 计算每个解空间的可能解，最后选取最优解
 * 
 * 有字符串x，y,长度分别为m，n,对于0 < i < m, 0 < j < n,
 * x(a1,a2)表示字符串下标为a1至a2的子字符串
 * 
 * 1.如果x(i)==y(j),那么解空间可以分解为x(i + 1, m)与y(j + 1, n);x(i, m)与y(j + 1, n)；x(i + 1, m)与y(j, n)
 * 2.如果x(i)!=y(j),那么解空间可以分解为x(i, m)与y(j + 1, n)；x(i + 1, m)与y(j, n)
 * 3.以参数的方式记录每个解空间的解
 */

public class LCSOfSerial {

	List<String> resultList = new ArrayList<String>();

	public void longestCommonSubsequence(String x, String y, StringBuffer result) {

		if (x.length() == 0 || y.length() == 0) {
			return;
		}

		if (x.charAt(0) == y.charAt(0)) {
			//与非连续不一样
			//遍历完这条路径后 else后面的分支路径还要考虑
			result.append(x.charAt(0));
			longestCommonSubsequence(x.substring(1), y.substring(1), result);
		}
		
		longestCommonSubsequence(x.substring(1), y, new StringBuffer());
		longestCommonSubsequence(x, y.substring(1), new StringBuffer());
		
		if (result.length() != 0) {
			resultList.add(new String(result));
		}

	}

	private static String getMaxStr(LCSOfSerial lsc) {
		String maxStr = "";
		for (String str : lsc.resultList) {
			if (str.length() >= maxStr.length()) {
				maxStr = str;
			}
		}
		return maxStr;
	}

	public static void main(String[] args) {

		LCSOfSerial lsc = new LCSOfSerial();
		StringBuffer result = new StringBuffer();

		String xString = "685685";
		String yString = "6855";
		lsc.longestCommonSubsequence(xString, yString, result);

		String maxStr = getMaxStr(lsc);

		System.out.println(maxStr);
		System.out.println(lsc.resultList.size());

	}

}
