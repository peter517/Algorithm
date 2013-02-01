package datastructure.tree;

import java.util.Scanner;

/**
 * Java中不管是基本类型还是库类或自定义类，参数都是传值，同时不能自己释放空间
 * 所以下面的中用data值为-1的节点作为哨兵
 */



public class TreeTools {
	
	public static TreeNode createTree(){
		
		TreeNode root = new TreeNode(0);
		
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		
		root.leftChild = node1;
		root.rightChild = node2;
		
	    node1.leftChild = node3;
	    node1.rightChild = node4;
	    
	    node4.leftChild = node5;
		
		return root;
	}
	
	public void f(){
	
		//iterative export
		
		f();
		f();
		
		//do something
		
		return;
	}
	
	public static TreeNode createAnotherTree(){
		
		TreeNode root = new TreeNode(1);
		
		TreeNode node1 = new TreeNode(3);
		TreeNode node2 = new TreeNode(4);
		
		root.leftChild = node1;
		root.rightChild = node2;
		
		return root;
	}

    public static void createTree(TreeNode obj){
    	
    	Scanner in = new Scanner(System.in);
    	if(in.hasNextInt()){
    		obj.data = in.nextInt();
    	}
    	
    	if (obj.data == -1){
    		return;
    	}else{
    		obj.leftChild = new TreeNode(0);
    		obj.rightChild = new TreeNode(0);
    		createTree(obj.leftChild);
    		createTree(obj.rightChild);
    	}
    	
    }
    
    public static void printTree(TreeNode obj){
    	
    	if (obj == null || obj.data == -1){
    		return;
    	}
    	
    	System.out.print(obj.data + " ");
    	if (obj.leftChild != null){
    		printTree(obj.leftChild);
    	}
    	if (obj.rightChild != null){
    		printTree(obj.rightChild);
    	}
    }
    
	public static void main(String[] args) {

		TreeTools tree = new TreeTools();
		tree.printTree(TreeTools.createTree());
		
	}
}
