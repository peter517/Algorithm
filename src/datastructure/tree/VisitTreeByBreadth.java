package datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 树的广度搜索，递归和非递归两种形式
 * 
 */

public class VisitTreeByBreadth {
	
	
	TreeNode root = TreeTools.createTree();
	
	public void search(){
		//searchByRecursive(root);
		search(root);
	}

	/**
	 * 递归方法
	 */
	public void searchByRecursive(TreeNode root){
		
		if(root == this.root){
			visit(root);
		}
		
		if(root == null){
			return;
		}
		
		
		visit(root.leftChild);
		visit(root.rightChild);
		
		searchByRecursive(root.leftChild);
		searchByRecursive(root.rightChild);
		
	}
	
	/**
	 * 非递归方法
	 */
     public void search(TreeNode root){
		
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		
		q.add(root);
		
		while(!q.isEmpty()){
			
			TreeNode cur = q.poll();
			
			visit(cur);
			
			if(cur.leftChild != null){
				q.add(cur.leftChild);
			}
			
			if(cur.rightChild != null){
				q.add(cur.rightChild);
			}
			
		}
	 }

	private void visit(TreeNode cur) {
		
		if(cur != null){
			System.out.print(cur.data);
		}

	}
	
	public static void main(String[] args) {
		
		VisitTreeByBreadth obj = new VisitTreeByBreadth();
		obj.search();

	}

}
