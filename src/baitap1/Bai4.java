package baitap1;

public class Bai4 {

	public static void main(String[] args) {
		int rows = 10;
		
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= rows; j++) {
				if (i == 1 || i == rows || j == 1 || j == rows) {
					System.out.print("* ");
				} else {
					System.out.print("  ");
				}
			}
			System.out.println("");
		}
	}

}
