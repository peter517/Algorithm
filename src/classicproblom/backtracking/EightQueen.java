package classicproblom.backtracking;

import java.awt.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * 八皇后问题
 */

public class EightQueen {

	private static int validArrangeNum = 0;

	/**
	 * 主函数，找有效排列
	 */
	public static int findValidQueenArrange() {

		int j = 1;// 第一个皇后肯定是放在第一行中,i代表列，j代表行
		for (int i = 1; i <= 8; i++) {

			List<Point> pointSelectedList = new ArrayList<Point>();
			pointSelectedList.add(new Point(i, j));

			//从第二行开始找
			findNextPointForQueen(pointSelectedList, j + 1);
		}

		return validArrangeNum;
	}

	/**
	 * 找寻下一个皇后
	 */
	private static void findNextPointForQueen(List<Point> pointSelectedList, int n) {

		for (int j = n; j <= 8; j++) {// j从n+1起始，代表往下一行放皇后，剪枝操作，为了减少搜索空间
			
			for (int i = 1; i <= 8; i++) {
				
				boolean isQualified = isPointQualifiedForQueen(pointSelectedList, i, j);

				if (isQualified) {
					
					pointSelectedList.add(new Point(i, j));

					if (pointSelectedList.size() == 8) {

						validArrangeNum++;

						pointSelectedList.remove(new Point(i, j));// 回溯后的复还处理
						return;
					}

					// 这里两条语句很关键
					findNextPointForQueen(pointSelectedList, j + 1);// 查找下一行
					pointSelectedList.remove(new Point(i, j));// 回溯后的复还处理
					
				}
			}
		}
	}

	/**
	 * 该位置是否能放一个皇后
	 */
	private static boolean isPointQualifiedForQueen(List<Point> pointSelectedList, int i, int j) {

		boolean isQualified = true;

		for (Point point : pointSelectedList) {
			// 剪枝操作
			if (Math.abs(point.x - i) == Math.abs(point.y - j)) {
				isQualified = false;
				break;
			} else if (point.x == i) {
				isQualified = false;
				break;
			}
		}
		return isQualified;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(findValidQueenArrange());
	}

}
