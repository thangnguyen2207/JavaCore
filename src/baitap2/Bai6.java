package baitap2;

import java.util.Arrays;
import java.util.Scanner;

public class Bai6 {

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
        double sum = 0;

        // Nhập mảng số nguyên
        for (int i = 0; i < nTotal; i++) {
            System.out.print("Nhập số phần tử thứ " + (i + 1) + ": ");
            numbers[i] = scan.nextInt();
            sum += numbers[i];
        }
        scan.close();

        // Sắp xếp mảng từ bé đến lớn
        Arrays.sort(numbers);

        // Tính giá trị trung bình
        double avg = sum / nTotal;

        // Đếm số lượng lớn hơn và nhỏ hơn trung bình
        int belowAvg = 0, aboveAvg = 0;
        for (int num : numbers) {
            if (num < avg) {
                belowAvg++;
            } else if (num > avg) {
                aboveAvg++;
            }
        }

        // Hiển thị kết quả
        System.out.println("\nMax: " + numbers[numbers.length - 1]);
        System.out.println("Min: " + numbers[0]);
        System.out.println("Average: " + String.format("%.3f", avg));
        System.out.println("Số lượng lớn hơn trung bình: " + aboveAvg);
        System.out.println("Số lượng nhỏ hơn trung bình: " + belowAvg);
    }
}