import java.util.Scanner;
/*
 * Given a M * N grid of characters (P,Q). You are allowed to flip any number of columns 
 * i.e. change P to Q and Q to P. 
 * Maximize number of rows that can have same symbols after all possible flipping
 * 
 * Sample Input:
 * 7 4
TTPT
PPTT
TPPP
PTPT
PPTP
PPTP
PTTT
 * 
 */
public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		
		int[][] arr = new int[M][N];
		
		int p = 0;
		/*
		 * take input into 1,0 so that you can do Bit operations
		 */
		while(p < M){
			String next = sc.next();
			String[] splitarr = next.split("");
			for(int i = 0;i<N;i++){
				if(splitarr[i].equals("T"))
					arr[p][i] = 1;
				else
					arr[p][i] = 0;
			}
			p++;
		}
		
		findArray(arr,M,N);
	}

	private static void findArray(int[][] arr,int M,int N) {
		int[] max = new int[M];
		int[] oneArr = new int[M];
		int duplicate_total = 0;
		for(int i = 0;i< M;i++){
			for(int j = i+1;j<M;j++){
				/*
				 * Check for All Zeros and All Ones
				 */
				if((convertBinary(arr[i]) & convertBinary(arr[j])) == 0 ){
					if((convertBinary(arr[i]) | convertBinary(arr[j])) == Math.pow(2,N) - 1){
						oneArr[j] = 1;
					}
					max[i]++;
				}
				if(convertBinary(arr[i]) == convertBinary(arr[j])){
					duplicate_total++;
				}	
					
			}
		}
		int maxNum = max[0];
		/*
		 * Find Max of all the maximums
		 */
		for(int i = 0;i< M;i++){
			if(maxNum < max[i])
				maxNum = max[i];
		}
		System.out.println(maxNum-duplicate_total);
	}

	private static int convertBinary(int[] is) {
		String sp = "";
		for(int i = 0;i<is.length;i++){
			sp = sp + is[i];
		}
		return Integer.parseInt(sp, 2);
	}
	
	

}
