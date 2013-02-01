package datastructure.tree;

import java.util.Stack;


/**
 * 树的深度搜索，递归和非递归两种形式
 * 
 */

public class VisitTreeByDepth {

	
	/**
	 * 递归方法
	 */
	public void searchByRecursive(TreeNode root){
		
		if(root == null){
			return;
		}
		
		visit(root);
		searchByRecursive(root.leftChild);
		searchByRecursive(root.rightChild);
		
	}
	
	/**
	 * 非递归方法
	 */
     public void search(TreeNode root){
		
    	 if(root == null){
    		 return;
    	 }

		TreeNode cur = root;
		Stack<TreeNode> s = new Stack<TreeNode> ();
		
		while(cur != null || !s.empty()){
			
			while(cur != null){
				
				visit(cur);	
				
				s.push(cur);
				cur = cur.leftChild;
				
			}
			
			TreeNode printNode = s.pop();
			//visit(printNode); 放在这里就是中序遍历
			cur = printNode.rightChild;
			
		}
		
	}

	private void visit(TreeNode cur) {
		System.out.print(cur.data);
	}
	
	/**
	 * 非递归方法
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		VisitTreeByDepth obj = new VisitTreeByDepth();
		obj.search(TreeTools.createTree());
	}

}
