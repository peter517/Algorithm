package beautyofprogramming.chapter1.chess;

/**
 * 1.2
 * 问题描述：
 * 只用一个变量，描述将于帅之间的合理位置
 * 
 * 主要思路：
 * 1.利用二进制的不同位置可以表示多个变量
 * 2.字节的位运算的运用
 */
public class ChessProblem {

	public final static short GRID = 3;
	public final static short HALF_BYTE_LENGTH = 4;
	public final static short LMASK = 0x00f0;
	public final static short RMASK = 0x000f;
	
	/**
	 * 获取左边四位的二进制大小
	 */
	public static short getLBit(short b){
		return (short) ((b & LMASK) >> HALF_BYTE_LENGTH);
	}
	
	/**
	 * 获取右边四位的二进制大小
	 */
	public static short getRBit(short b){
		return (short) (b & RMASK);
	}
	
	/**
	 * 设置左边四位的二进制大小
	 */
	public static short setLBit(short b, int i){
		return (short) ((i << HALF_BYTE_LENGTH) | getRBit(b));
	}
	
	/**
	 * 设置右边四位的二进制大小
	 */
	public static short setRBit(short b, int i){
		return (short) (i | (getLBit(b) << HALF_BYTE_LENGTH));
	}
	
	public static void main(String[] args) {
		
		//解法一
//           for (int i = 0x0011; i <= 0x0099; i++){
//        	   if(((i & 0x000f) != 0) && (i & 0x000f) <= 0x0009 && ((i & 0x00f0) >> 4) <= 0x0009 && (i & 0x000f) % 3 != ((i & 0x00f0) >> 4) % 3){
//        		   System.out.println("将的位置：" + (i & 0x000f) + " 帅的位置：" + ((i & 0x00f0) >> 4));
//        	   }
//           }
		
		//解法二
		//关键点：比解法一高效，一个字节可以表示2个互不相关的4位数字，
//		short i = 1;
//		for (i = setLBit(i, 1); getLBit(i) <= GRID * GRID; i = setLBit(i, getLBit(i) + 1)){
//			for (i = setRBit(i, 1); getRBit(i) <= GRID * GRID; i = setRBit(i, getRBit(i) + 1)){
//				if ((getLBit(i) % 3 != getRBit(i) % 3)){
//					System.out.println("将的位置：" + getLBit(i) + " 帅的位置：" + getRBit(i));
//				}
//			}
//		}
		
		//解法三
		//关键点：比较次数必须要有81次
		short i = 81;
		while (i-- != 0){
			if (i / 9 % 3 != i % 9 % 3){
				System.out.println("将的位置：" + (i / 9  + 1) + " 帅的位置：" + (i % 9 + 1));
			}
		}
	}

}
