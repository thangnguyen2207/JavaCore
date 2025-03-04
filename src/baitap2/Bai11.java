package baitap2;

import java.util.Arrays;
import java.util.Scanner;

public class Bai11 {
	private static final String text = "ut81n2j59";
	private static char[] c = text.toCharArray();

	public static void main(String[] args) {
		Arrays.sort(c);
		System.out.println("Các phần tử trong mảng:\n" + Arrays.toString(c) + "\n");
		
		try (Scanner scan = new Scanner(System.in)) {
			int option = 0;
			do {
				System.out.println("Chọn hành động:\n1. Tìm kiếm\n2. Thêm\n3. Xóa");
				option = scan.nextInt();
				
				switch (option) {
					case 1: {
						System.out.println("Nhập 1 ký tự cần tìm kiếm:");
						char key = scan.next().charAt(0);
						
						int timkiem = timkiem(key);
						if (timkiem == -1)
							System.out.println("Không tìm thấy ký tự " + key + " trong mảng");
						else
							System.out.println("Ký tự \"" + key + "\" nằm ở vị trí thứ " + timkiem + " trong mảng\n");
						
						break;
					}
					case 2: {
						System.out.println("Nhập 1 ký tự cần thêm:");
						char key = scan.next().charAt(0);
						
						if (add(key)) {
							System.out.println("\nĐã thêm ký tự \"" + key +"\" vào mảng\n");
							System.out.println("Các phần tử trong mảng:\n" + Arrays.toString(c) + "\n");
						} else
							System.out.println("Không thể thêm ký tự vào mảng\n");;
						
						break;
					}
					case 3: {
						System.out.println("Nhập 1 ký tự cần xóa:");
						char key = scan.next().charAt(0);
						
						if (remove(key)) {
							System.out.println("\nĐã xóa ký tự \"" + key +"\" khỏi mảng\n");
							System.out.println("Các phần tử trong mảng:\n" + Arrays.toString(c) + "\n");
						} else
							System.out.println("Không thấy ký tự cần xóa\n");
						
						break;
					}
					default: {
						System.out.println("Kết thúc");
						break;
					}
				}
			} while (option > 0 && option < 4);
		}
	}
	
	//Tìm kiếm ký tự và trả về index, -1 nếu không tìm thấy. Sử dụng vòng lặp for
	public static int timkiem(char key) {
		for (int i = 0; i < c.length; i++) {
			if (c[i] == key) return i+1;
		}
		
		return -1;
	}
	
	//Thêm ký tự vào mảng. Sử dụng vòng lặp for và các phương thức của mảng
	public static boolean add(char key) {
		char[] newArray = new char[c.length+1];
		for (int i = 0; i < c.length; i++) {
			if (c[i] >= key) {
				newArray[i] = key;
				System.arraycopy(c, 0, newArray, 0, i);
				System.arraycopy(c, i, newArray, i+1, c.length - i);
				c = newArray.clone();
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean remove(char key) {
		char[] newArray = new char[c.length-1];
		for (int i = 0; i < c.length; i++) {
			if (c[i] == key) {
				System.arraycopy(c, 0, newArray, 0, i);
				System.arraycopy(c, i + 1, newArray, i, newArray.length - i);
				c = newArray.clone();
				return true;
			}
		}
		
		return false;
	}
}
