package classicproblom.iteration;

import java.util.Stack;

/**
 * 给出一个栈的push序列，判断一个pop序列是否正确
 * 
 * 主要思路：
 * 1.不断利用pushStr序列压栈，判断popStr得第一个元素是否在pushStr序列中存在，
 * 2.如存在，则同时移动popStr和pushStr的指针
 * 3.当push操作已经完成时，不断判断栈s中的元素是否和popStr序列的元素一一对应
 * 
 */
public class JudgPopByPush {

	private String pushStr = "12345";
	private String popStr = "45321";

	public void search() {
		System.out.print(isCorrect());
	}

	public boolean isCorrect() {

		int popIndex = 0;
		int pushIndex = 0;

		Stack<Character> s = new Stack<Character>();

		while (true) {

			while (pushIndex < pushStr.length() && pushStr.charAt(pushIndex) != popStr.charAt(popIndex)) {
				s.push(pushStr.charAt(pushIndex));
				pushIndex++;
			}
			
			if(pushIndex < pushStr.length()){
				popIndex++;
				pushIndex++;
			}

			//如果push操作已经完成
			if (pushIndex >= pushStr.length()) {
				//如果每次出栈的元素和popStr中的数据一致
				if (!s.empty() && s.pop() == popStr.charAt(popIndex++)) {
					//当遍历完popStr序列时
					if (popIndex >= popStr.length()) {
						return true;
					}
				} else {
					return false;
				}
			}

		}

	}

	public static void main(String[] args) {

		JudgPopByPush obj = new JudgPopByPush();
		obj.search();
	}

}
