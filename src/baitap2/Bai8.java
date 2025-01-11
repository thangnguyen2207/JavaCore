package baitap2;

import java.util.Arrays;
import java.util.Scanner;

public class Bai8 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Nhap n: ");
		int n = scan.nextInt();
		scan.close();
		
		int[] fibo = new int[n]; 
		int f0 = 1, f1 = 1;
		fibo[0] = f0;
		fibo[1] = f1;
		
		for (int i = 2; i < fibo.length; i++) {
			fibo[i] += f0 + f1;
			f0 = f1;
			f1 = fibo[i];	
		}
		
		System.out.println(Arrays.toString(fibo));
	}

}
