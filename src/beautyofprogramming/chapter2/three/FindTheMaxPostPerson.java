package beautyofprogramming.chapter2.three;


/**
 * 2.3
 * 问题描述：
 * 需找在一个贴吧发帖最多的ID，已知该ID发帖数超过总数的一半
 * 
 * 主要思路
 * 对于一个无序数组，排序前和排序后得到的思路是不一样的，但是排序要付出(n * log(n))的时间复杂度代价
 * 
 * 解法一：
 * 对数组进行排序，取中间值
 * 时间复杂度：(n * log(n))
 * 
 * 解法二：
 * 利用HashMap,<ID,count>的键值对，遍历一次数组，当发现出现次数超过总数的一半时，相应ID既为所求
 * 时间复杂度：(n)
 * 
 * 解法三:
 * 设发帖最多的ID个数为a，其他ID的总和为b，根据已知条件,a > m
 * 所以 a - 1 > b - 1,所以对于ID集合，当删除两个不同的ID时，发帖最多的ID仍然占总ID的一半以上
 * 
 * 存放ID的数组为idArray
 * 1.初始设resultId = idArray[0],同时设count = 1；
 * 2.对于data[i],i为1……n,如果data[i] = resultId,那么count++;
 * 3.否则，判断count是否为0,如果为零，那么设resultId = idArray[i]，count = 1；
 * 4.最后的resultId即为结果
 * 
*/
public class FindTheMaxPostPerson {

    //解法三
	public int[] idArray = {4,4,3,4,4,3,3,4,3};
	public int resultId = 0;
	
	public void search(){
		
		resultId = idArray[0];
		int count = 1;
		
		for (int i = 1; i < idArray.length; i++){
			if(resultId == idArray[i]){
				count++;
			}else{
				if(count == 0){
					resultId = idArray[i];
					count++;
				}else{
					count--;
				}
			}
		}
	}
	
	public void output(){
		System.out.println(resultId);
	}
	
	public static void main(String[] args) {

		FindTheMaxPostPerson obj = new FindTheMaxPostPerson();
		obj.search();
		obj.output();
	}

}
