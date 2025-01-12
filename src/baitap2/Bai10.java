package baitap2;

import java.util.Random;
import java.util.Scanner;

public class Bai10 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Nhap so luong phan tu: ");
		int n = scan.nextInt();
		
		scan.close();
		
		int[] numbers = new int[n];
		Random rad = new Random();
		for(int i = 0; i < numbers.length; i++) {
			numbers[i] = rad.nextInt(1000);
		}
		
		for(int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");
		}
	}

}
