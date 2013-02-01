package beautyofprogramming.chapter2.ten;

/**
 * 解法三 另一种分治法,求最大值 在将解空间分开后
 * 不是调用单独的函数 而是在后面直接写入操作，然后直接返回值
 * 即每次分解空间还要带回返回值
 * 
 * 时间复杂度：O(n)
 */
public class MinAndMaxByDivision {

	public int[] data = { 3, 1, -4, 5, 10, 6, -1 };

	Result result = new Result();

	public void search() {
		result = search(0, data.length - 1);
	}

	public Result search(int start, int end) {

		if (start == end) {
			Result r = new Result(data[start], data[start]);
			return r;
		}

		int mid = (start + end) / 2;

		Result rLeft = search(start, mid);
		Result rRight = search(mid + 1, end);
		
		return Result.cmpResult(rLeft, rRight);
	}

	public void output() {
		System.out.println("max = " + result.max);
		System.out.println("min = " + result.min);
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MinAndMaxByDivision obj = new MinAndMaxByDivision();
		obj.search();
		obj.output();

	}

}

class Result {

	public Result() {

	}

	public Result(int max, int min) {
		this.max = max;
		this.min = min;
	}

	public int max = 0;
	public int min = 0;
	
	public static Result cmpResult(Result rLeft, Result rRight){
		
		int max = rLeft.max > rRight.max ? rLeft.max : rRight.max;
		int min = rLeft.min < rRight.min ? rLeft.min : rRight.min;
		
		return new Result(max, min);
	}
	
}
