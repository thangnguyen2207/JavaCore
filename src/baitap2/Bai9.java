package baitap2;

import java.util.Scanner;

public class Bai9 {
	
	// Hàm đệ quy để tạo các chuỗi nhị phân
    public static void generateBinaryStrings(int n, String prefix) {
        if (n == 0) {
            // Khi đã đạt độ dài mong muốn, in chuỗi nhị phân
            System.out.println(prefix);
            return;
        }
        // Đệ quy với thêm '0' vào chuỗi
        generateBinaryStrings(n - 1, prefix + "0");
        // Đệ quy với thêm '1' vào chuỗi
        generateBinaryStrings(n - 1, prefix + "1");
    }
    
    //Hàm tạo chuỗi nhị phân Sử dụng vòng lặp for
    public static void generateBinaryStrings(int n) {
    	// Tính tổng số chuỗi nhị phân có thể có (2^n)
        int total = (int) Math.pow(2, n);

        // Sử dụng vòng lặp để duyệt qua tất cả các số từ 0 đến (2^n - 1)
        for (int i = 0; i < total; i++) {
            // Chuyển số i sang nhị phân và thêm các số 0 phía trước cho đủ n bít
            String binaryString = String.format("%" + n + "s", Integer.toBinaryString(i)).replace(' ', '0');
            System.out.println(binaryString);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập số bít n từ người dùng
        System.out.print("Nhập số bít n: ");
        int n = scanner.nextInt();

        System.out.println("Các chuỗi nhị phân " + n + " bít là:");
        generateBinaryStrings(n);

        scanner.close();
    }
}
