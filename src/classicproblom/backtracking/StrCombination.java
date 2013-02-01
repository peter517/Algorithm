package classicproblom.backtracking;

/**
 * 问题描述：
 * 输出一个各个元素不相同的字符数组的所有组合排列
 * 
 * 主要思路（回溯法）：
 * 
 * 模仿组合排列的思路
 * 
 * 1.第一个位置可以使字符数组中的任意元素
 * 2.选中了第一个位置的元素后，第二个位置的元素在剩下的元素中选
 * 
 */

public class StrCombination {

	public char[] c = {'a', 'b', 'c'};
	
	public void search(){
		search(c, 0);
	}
	
	public void search(char[] c, int start){
		
		if(start == c.length){
			System.out.println(new String(c));
			return;
		}
		
		for (int i = start; i < c.length; i++){
			swap(c, start, i);
			search(c, start + 1);
			swap(c, start, i);
		}
		
	}

	private void swap(char[] c, int m, int n){
		char temp = c[m];
		c[m] = c[n];
		c[n] = temp;
	}
	
	public static void main(String[] args) {
		
		StrCombination obj = new StrCombination();
		obj.search();

	}

}
