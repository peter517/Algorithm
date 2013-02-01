package beautyofprogramming.chapter2.eight;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 2.8
 *问题描述：
 *对于指定数N,求最小值M(M > 0),使得M*N的十进制只含1或0
 *
 *主要思路：
 *解法一：
 *
 *1.从1开始往正无穷大遍历，
 *2.对于每个K = i * N,如果K只含1或0，则结束
 *3.否则继续遍历
 *
 *时间复杂度(M / N ) * N 
 *时间复杂度较好，空间复杂度满足不了
 *
 *解法二：
 *
 *从小到大，列出所有满足只含1或的数字，然后对N取模，若为0，则是要找的数字
 * 例如：1, 10 ,11 , 100, 101, 110, 111
 * 对于只含1或0的、范围在10^k + y (0 <= k)(0 <= y < (10^k) - 1)的这个数，其取模如果按照正常情况，共要计算有2^k次,
 * 如果保留10^(k - 1) + y的所有余数，则计算次数将降到N - 1(对N取模为0则停止计算了)
 * 
 * 1.1对N取模，保留其余数，用map保存，为<1, Y>,
 * 2.10^(k) + y(0 < k)(0 <= y < (10^k) - 1)对N取模，保留其余数为<key, Y>,如果key冲突，则取最小的Y
 * 3.重复1-2，若有余数有为0的情况，则停止，返回相应的键值
 * 
 * 
 * 
 * 
 */
public class MinFactor {
	
	public long M1 = 0;
	public long M2= 0;
	
	/**
	 * 解法二
	 */
	public void search(int N){
		
		Map<Long,Long> map = new HashMap<Long,Long>();
		
		if(N == 1){
			M2 = 1;
			return;
		}else{
			//增加余数为1和余数为0的两种情况
			map.put(1L, 1L);
			map.put(0L, 0L);
		}
		
		int k = 1;
		while(true){
			
			Set<Long> set = map.keySet();
			
			//之所以要加一个mapBuf，是因为需要动态的修改map的值，这在遍历map的时候是不允许的
			Map<Long,Long> mapBuf = new HashMap<Long,Long>();
			
			for(Long key:set){
				
				long num = map.get(key) + (long) Math.pow(10, k);
				long residue = num % N;
				
				if(map.get(residue) == null){
					mapBuf.put(residue, num);
					continue;
				}else{
					//如果键值相冲突，则取对应应值最小的那个键值
				   long minNum = Math.min(num, map.get(residue));
				   mapBuf.put(residue, minNum);
				}
				
				//如果第一次遇到余数为0的情况,直接放入
				if(residue == 0 && map.get(0L) == 0L){
					mapBuf.put(residue, num);
				}
				
			}
			
			//如果出现余数为0的情况
			if(map.get(0L) != 0L){
				M2 = mapBuf.get(0L);
				return;
			}
			
			map.putAll(mapBuf);
			k++;
		}
	}
	
	/**
	 * 解法一
	 */
	public void searchByForce(int N){
		int i = N;
		while(true){
				if(isOneOrZero(i)){
					M1 = i;
					return;
			}
			i += N;
		}
	}
	
	public boolean isOneOrZero(int n){
		
		int temp = n;
		while(temp != 0){
			if(temp % 10 != 1 && temp % 10 != 0){
				return false;
			}
			temp /= 10;
		}
		
		return true;
	}
	
	public void output(){
		System.out.println(M2);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MinFactor obj = new MinFactor();
		
	    for(int i = 3; i < 20; i++){
	    	obj.search(i);
			//obj.searchByForce(i);
			obj.output();
	    }
	
	}

}
