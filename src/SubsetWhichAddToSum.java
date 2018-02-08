import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubsetWhichAddToSum {

	//print all subsets
	public static void subSetsRecursive(int[] arr,int j,int sum,int sumSoFar,List<Integer> subSets){
		if(sumSoFar == sum){
			System.out.println(subSets.toString());
		}else{
			for(int i=j;i<arr.length;i++){
				subSets.add(arr[i]);
				sumSoFar+=arr[i];
				if(sumSoFar<=sum)
					subSetsRecursive(arr, i+1, sum, sumSoFar, subSets);
				subSets.remove(new Integer(arr[i]));
				sumSoFar= sumSoFar - arr[i];
			}
		}
	}
	
	//count of subsets using recursion
	public static int subSetsCountRecursion(int[] arr,int sum,int n){
		if(sum==0){
			return 1;
		}
		else if(sum<=0 || n<0){
			return 0;
		}
		else if(sum<arr[n]){
			return subSetsCountRecursion(arr, sum, n-1);
		}else{
			return subSetsCountRecursion(arr, sum-arr[n], n-1) + subSetsCountRecursion(arr, sum, n-1);
		}
	}
	
	//count of subsets using dp memoization
	public static int subSetsCountDPMemo(int[] arr,int sum,int n,Map<String,Integer> mem){
		String key = sum+":"+n;
		if(mem.containsKey(key)){
			return mem.get(key);
		}else{
			int count;
			if(sum==0){
				return 1;
			}else if(sum<0 || n<0){
				return 0;
			}else if(sum<arr[n]){
				count = subSetsCountDPMemo(arr, sum, n-1, mem);
			}else{
				count = subSetsCountDPMemo(arr, sum-arr[n], n-1,mem) + subSetsCountDPMemo(arr, sum, n-1,mem);
			}
			mem.put(key, count);
			return count;
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {2,4,6,10};
		List<Integer> subSets = new ArrayList<>();
		subSetsRecursive(arr,0,16,0,subSets);
		System.out.println(subSetsCountRecursion(arr, 16, arr.length-1));
		Map<String, Integer> mem = new HashMap<>();
		System.out.println(subSetsCountDPMemo(arr, 16, arr.length-1, mem));
	}
	
}
