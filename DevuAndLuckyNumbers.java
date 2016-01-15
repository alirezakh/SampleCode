import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) {
		/* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */        
		Scanner scanner = new Scanner(System.in);        
		String line = scanner.nextLine();
		String[] tokens = line.split(" ");
		int x = Integer.parseInt(tokens[0]);
		int y = Integer.parseInt(tokens[1]);
		int z = Integer.parseInt(tokens[2]);

		long total = 0;		
		long[] multiplier = new long[301];
		long mod = 1000000007;
		multiplier[0] = 1;
		for(int i = 1; i < 301; i++){
			multiplier[i] = (multiplier[i-1] * 10) % mod; 
		}
		long[][][] sumation = new long[101][101][101];
		sumation[0][0][0] = 0;
		long[][][] counter = new long[101][101][101];
		counter[0][0][0] = 1;
		
		for(int i = 0; i <= x; i++){
			for(int j = 0; j <= y; j++){
				for(int k = 0; k <= z; k++){
					if(i > 0){
						counter[i][j][k] = (counter[i][j][k] + counter[i-1][j][k]) % mod;
						sumation[i][j][k] = (sumation[i][j][k] + 4 * multiplier[i + j + k - 1] * counter[i-1][j][k] + sumation[i-1][j][k]) % mod; 
					}
					if(j > 0){
						counter[i][j][k] = (counter[i][j][k] + counter[i][j-1][k]) % mod;
						sumation[i][j][k] = (sumation[i][j][k] + 5 * multiplier[i + j + k - 1] * counter[i][j-1][k] + sumation[i][j-1][k]) % mod;
					}
					if(k > 0){
						counter[i][j][k] = (counter[i][j][k] + counter[i][j][k-1]) % mod;
						sumation[i][j][k] = (sumation[i][j][k] + 6 * multiplier[i + j + k - 1] * counter[i][j][k-1] + sumation[i][j][k-1]) % mod;
					}
					total = (total + sumation[i][j][k]) % mod;
				}
			}
		}
		System.out.println(total);
	}
}
