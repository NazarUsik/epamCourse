package ua.nure.usik.practice1;

public class Part2{
	public static void main(String[] args){
		try {
			int result = 0;
			for (int i = 0; i < args.length; i++) {
				result += Integer.parseInt(args[i]);
			}
			System.out.println(result);
		} catch (IndexOutOfBoundsException ex){
		System.out.print("Error");
		}
	}
}