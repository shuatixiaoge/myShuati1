public class Solution {
    public int countPrimes(int n){
		boolean[] isPrime=new boolean[n];
		for(int i=2;i<n;i++){//1 is not a prime number
			isPrime[i]=true;
		}
		for(int i=2;i*i < n;i++){
			if(isPrime[i]==false) continue;
			for(int j=i*i;j<n;j+=i){//p2+p
				isPrime[j]=false;
			}
		}
		int count=0;
		for(int i=0;i<n;i++){
			if(isPrime[i]) count++;
		}
		return count;
	}
}
