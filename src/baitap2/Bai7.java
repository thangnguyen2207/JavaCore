package baitap2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Bai7 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

        int nTotal;
        while (true) {
        	System.out.print("Nhập số lượng phần tử: ");
            nTotal = scan.nextInt();
            if (nTotal <= 0) 
            	System.out.println("Số lượng phần tử phải lớn hơn 0.");
            else break;
        }

        int[] numbers = new int[nTotal];
        
     // Nhập mảng số nguyên
        for (int i = 0; i < nTotal; i++) {
            System.out.print("Nhập số phần tử thứ " + (i + 1) + ": ");
            numbers[i] = scan.nextInt();
        }
        scan.close();
        
        int[] numbersAsc = numbers.clone();
        Arrays.sort(numbers);
        int[] numbersDesc = Arrays.stream(numbers).boxed()
        	    .sorted(Collections.reverseOrder())
        	    .mapToInt(Integer::intValue)
        	    .toArray();
 
        System.out.println("Mang tu be den lon: " + Arrays.toString(numbersAsc));
        System.out.println("Mang tu lon den be: " + Arrays.toString(numbersDesc));
        System.out.println("Mang nhap vao: " + Arrays.toString(numbers));
	}

}
