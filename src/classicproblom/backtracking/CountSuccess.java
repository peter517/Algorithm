package classicproblom.backtracking;

/**
 * 问题描述：
 * 打靶十次，每次是0到10环，计算出现最后结果为90环的次数
 * 
 * 主要思路：
 * 遍历整个解空间，进行合适的剪枝
 * 
*/
public class CountSuccess {

	public int count;
	public int finalScore = 90;
	public int maxScore = 10;
	
    public void search(int n, int score){
    	
    	//最后一项剪枝很重要
    	if(n < 0 || score > finalScore || (n * maxScore + score) < finalScore){
    		return;
    	}
    	if(n == 0 && score == finalScore){
    		count++;
    		return;
    	}
    	
    	for (int i = 0; i <= maxScore; i++){
    		search(n - 1, score + i);
    	}
    }
    
    public void output(){
    	System.out.println(count);
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CountSuccess obj = new CountSuccess();
		obj.search(10, 0);
		obj.output();
	}

}
