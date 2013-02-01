package classicproblom.recursion;

import datastructure.tree.TreeTools;
import datastructure.tree.TreeNode;

/**
 * 
 * 题目：
 * 判断一棵树是否被另一棵树包含
 * 
 * 主要思路：
 * 这里用了两次递归，要写在不同的函数，不然很难实现
 * 
 */
public class TreeContainJudgeMent {

	public boolean isContain = false;
	
	/**
	 * 
	 * 判断一棵树是否被另一棵树包含
	 */
	public void search(TreeNode src, TreeNode dst){
		
		//迭代出口
		if (src == null){
			return;
		}
		
		//是否找到结果
		if(!isContain){
			
			isContain = cmpTree(src, dst);
			
			search(src.leftChild, dst);
			search(src.rightChild, dst);
			
		}
	}
	
	/**
	 * 
	 * 判断两树是否相等
	 */
	public boolean cmpTree(TreeNode src, TreeNode dst){
		
		//迭代出口 如果同时达到叶子节点，说明两树相等
		if(src == null && dst == null){
			return true;
		}else if(src == null || dst == null){
			return false;
		}
		
		if (src.data == dst.data){
			return cmpTree(src.leftChild,dst.leftChild) && cmpTree(src.rightChild,dst.rightChild);
		}else{
			return false;
		}
	}
	
	public void output(){
		System.out.println(isContain);
	}
	
	public static void main(String[] args) {

		TreeContainJudgeMent obj = new TreeContainJudgeMent();
		obj.search(TreeTools.createTree(), TreeTools.createAnotherTree());
		obj.output();

	}

}
