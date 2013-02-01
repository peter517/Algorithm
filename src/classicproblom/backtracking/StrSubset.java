package classicproblom.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 问题描述：
 * 输出一个各个元素不相同的字符数组的各个子集，不考虑子集中的排列顺序
 * 
 * 主要思路（回溯法）：
 * 
 * 设字符数组长度为n
 * 
 * 1.分别考虑子集长度为1到n的情况
 * 2.对于某个m,(1 <= m <= n)，问题可以描述为C(n,m),即在n个元素中取m个元素
 * 3.
 *            C(n - 1,m)           取n个元素的头元素
 * C(n,m) = 
 *            C(n - 1,m - 1)       不取n个元素的头元素
 *            
 * 这里的递归有些特别，有两个变量需要控制
 */

public class StrSubset {
	
	public char[] c = {'a', 'b', 'c'};
	
	public void search(){
		for(int i = 0; i < c.length; i++){
			search(c, 0, i + 1, new ArrayList<Character>());
		}
		
	}
	
	public void search(char[] c, int start, int m, List<Character> list){
		
		if(m == 0){
			for(int i = 0; i < list.size(); i++){
				System.out.print(list.get(i));	
			}
			System.out.print(" ");
			return;
		}
		
		if(start >= c.length){
			return;
		}
		
		list.add(c[start]);
		
		search(c, start + 1, m - 1, list);
		
		int index = Collections.binarySearch(list, c[start]);
		if(index != -1){
			list.remove(index);
		}

		search(c, start + 1, m, list);
		
	}

	public static void main(String[] args) {
		
		StrSubset obj = new StrSubset();
		obj.search();
		
		
		

	}

}
