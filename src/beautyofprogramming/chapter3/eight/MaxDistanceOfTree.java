package beautyofprogramming.chapter3.eight;

import datastructure.tree.TreeTools;
import datastructure.tree.TreeNode;

/**
 * 
 * 3.8
 * 问题描述：
 * 树中任意两节点最大间距
 * 
 * 主要思路：典型分治法
 * 
 * 分治公式：
 * 每个父节点的深度为n，左节点的深度为leftN，右节点的深度为rightN
 * 则： n = leftN + rightN + 1;
 */

public class MaxDistanceOfTree {
	
	public int maxDistance = 0;

	public int getDistance(TreeNode root){
		
		if(root == null){
			return 0;
		}
		
		int leftDistance = getDistance(root.leftChild);
		int rightDistance = getDistance(root.rightChild);
		
		maxDistance =  Math.max(maxDistance, leftDistance + rightDistance + 1);
		
		return Math.max(leftDistance + 1, rightDistance + 1);
		
	}
	
	public void output(){
		System.out.println(maxDistance);
	}
	
	public static void main(String[] args) {

		MaxDistanceOfTree obj = new MaxDistanceOfTree();
		System.out.println(obj.getDistance(TreeTools.createTree()));
		obj.output();

	}

}
