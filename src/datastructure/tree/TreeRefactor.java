package datastructure.tree;


/**
 * 根据输入的二叉树前序和中序遍历序列重构二叉树
 * 
 * 主要思路：
 * 
 * 1.先访问前序遍历的第一个元素，然后在中序遍历中找到该元素
 * 2.根据中序中根节点的位置，分别求出左边子树元素个数和右边子树元素的个数
 * 3.递归对左子树和右子树进行求解，不断构造整棵树
 * 
 */
public class TreeRefactor {
	
	private String preOrder = "013452";
	private String midOrder = "315402";
	private TreeNode root = null;
	
	public void search(){
		search(root, preOrder.length() , preOrder, midOrder);
	}

	public void search(TreeNode cur, int curLen, String preOrder, String midOrder){
		
		if(preOrder == null || midOrder == null){
			return;
		}
		
		//如果是空树
		if(cur == null){
			cur = new TreeNode(getItem(preOrder, 0));
		}
		
		//遍历到叶子节点
		if(curLen == 1){
			return;
		}
		
		int leftLen = 0;
		int i = 0;
		
		while( i < midOrder.length() && !getItem(preOrder, 0).equals(getItem(midOrder, i))){
			i++;
			leftLen++;
		}
		
		//如果前序和中序不对应
		if(i == midOrder.length()){
			return;
		}
		
		int rightLen = curLen - leftLen - 1;
		
		if(leftLen > 0){
			search(cur.leftChild , leftLen, preOrder.substring(1), midOrder);
		}
		
		if(rightLen > 0){
			search(cur.rightChild , rightLen, preOrder.substring(rightLen), midOrder.substring(rightLen));
		}
		
		
	}

	private Integer getItem(String str, int i) {
		String buf = str.charAt(i) + "";
		return Integer.valueOf(buf);
	}
	

	
	public void output(){
		
		TreeTools.printTree(root);
		
	}
	public static void main(String[] args) {
		
		TreeRefactor obj = new TreeRefactor();
		obj.search();
		obj.output();

	}

}
