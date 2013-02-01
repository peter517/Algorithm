package classicproblom.recursion;

public class Hanio {

	/**
	 * 从a到c
	 */
	public static void hanio(int n, char a, char c, char b){
		
		if(n == 0){
			return;
		}else{
			hanio(n - 1, a, b, c);
			System.out.println(a + "-->" + b);
			hanio(n - 1, b, c, a);
		}
		
	}
	public static void main(String[] args) {
            hanio(2, 'a', 'c', 'b');
	}

}
