package test;

public class Test{

	public int i = 0;
	public  final void test(){
		i++;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test obj = new Test();
		obj.test();
		System.out.println(obj.i);
		
	}
	

}


