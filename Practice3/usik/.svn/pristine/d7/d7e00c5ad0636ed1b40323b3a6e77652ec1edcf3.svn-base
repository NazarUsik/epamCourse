package ua.nure.usik.practice1;

public class Part4{
	public static void main(String[] args){
		try {
			int a = Integer.parseInt(args[0]);
			int b = Integer.parseInt(args[1]);
			int res = gcd(a, b);
			System.out.println(res);
		} catch (IndexOutOfBoundsException ex){
			System.out.println("Error");
		}
	}
	private static int gcd(int a, int b){
		if(b == 0){
			return a;
		} else {
			return gcd(b, a % b);
		}
	}
}