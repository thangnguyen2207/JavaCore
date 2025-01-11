package baitap1;
import java.util.Scanner;

public class Bai1 {

	public static void main(String[] args) {
		int a, b, c;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Nhap a: ");
		a = scanner.nextInt();
		System.out.print("Nhap b: ");
		b = scanner.nextInt();
		System.out.print("Nhap c: ");
		c = scanner.nextInt();
		
		scanner.close();
		
		System.out.println("So lon nhat: " + Math.max(a, Math.max(b, c)));
		System.out.println("So be nhat: " + Math.min(a, Math.min(b, c)));
	}

}
