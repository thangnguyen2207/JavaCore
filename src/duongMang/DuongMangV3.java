package duongMang;

import java.util.ArrayList;
import java.util.List;

public class DuongMangV3 {

	public static void main(String[] args) {
		int[] ipAddress = {172, 16, 0, 0};
		int numberOfComputerOrSubnet = 600;
		boolean isNumberOfSubnet = false;

		calculate(ipAddress, numberOfComputerOrSubnet, isNumberOfSubnet);
	}

	public static void calculate(int[] ipAddress, int numberOfComputerOrSubnet, boolean isNumberOfSubnet) {
		int subnets, computers, bitMask;

		//Tim x bang logarit cua 2
		int x = (int) Math.ceil(Math.log(numberOfComputerOrSubnet) / Math.log(2));

		//Kiem tra ip thuoc lop dia chi nao + lay gia tri host
		int ipClassValue = getIpClassValue(ipAddress[0]);

		// Tính toán số subnets, số máy tính/subnet và subnet mask
		if (!isNumberOfSubnet) {
			computers = (int) Math.pow(2, x) - 2;
			subnets = (int) Math.pow(2, ipClassValue - x);
			bitMask = 32 - x;
		} else {
			subnets = (int) Math.pow(2, x);
			computers = (int) Math.pow(2, ipClassValue - x) - 2;
			bitMask = 32 - (ipClassValue - x);
		}

		System.out.println("2^x = " + numberOfComputerOrSubnet + " => x = " + x +
			(!isNumberOfSubnet ? "\nComputers = 2^" + x + " - 2 = " + computers +
			"\nSubnets = 2^(" + ipClassValue + " - " + x + ") = " + subnets
			: "\nSubnets = 2^" + x + " = " + subnets +
			"\nComputers = 2^(" + ipClassValue + " - " + x + ") - 2 = " + computers) +
			"\nSubnet mask: " + convertBitToNetmask(bitMask) + " / " + bitMask);

		List<NetworkInterface> nInterfaces = new ArrayList<>();
		int[] tempIP = ipAddress.clone();

		// Tạo danh sách các đường mạng
		for (int subnet = 1; subnet <= subnets; subnet++) {
			NetworkInterface nInterface = new NetworkInterface();

			nInterface.setInterfaceAddress(tempIP.clone());

			tempIP[3]++;
			nInterface.setStartComputerIP(tempIP.clone());

			tempIP = findEndComputerIP(tempIP, computers).clone();
			nInterface.setEndComputerIP(tempIP.clone());

			tempIP[3]++;
			nInterface.setIpBroadcast(tempIP.clone());

			//Them thong tin subnet hoan chinh vao list
			nInterfaces.add(nInterface);

			//Cong them 1 vao ip de chuyen sang subnet tiep theo
			tempIP[3]++;
			tempIP = configIP(tempIP);
		}

		displayNetworkInterfaces(nInterfaces, isNumberOfSubnet, numberOfComputerOrSubnet);
	}

	private static void displayNetworkInterfaces(List<NetworkInterface> nInterfaces, boolean isNumberOfSubnet, int numberOfComputerOrSubnet) {
		// Hiển thị các đường mạng
		if (nInterfaces.size() < 20) {
			for (int i = 0; i < nInterfaces.size(); i++) {
				System.out.println((i + 1) + ") " + nInterfaces.get(i));
			}
		} else {
			// Hiển thị 5 dòng đầu
			for (int i = 0; i < 5; i++) {
			    System.out.println((i + 1) + ") " + nInterfaces.get(i));
			}
			System.out.println("------------------------------------");

			//Xác định phần tử bắt đầu và kết thúc
			//Nếu đề bài cho số subnet thì hiện 5 đường mạng từ số subnet đề cho đổ lại
			//Nếu đề bài cho số máy tính thì hiện hết 5 đường mạng cuối cùng
			int start = isNumberOfSubnet ? numberOfComputerOrSubnet - 5 : nInterfaces.size() - 5;
			int end = isNumberOfSubnet ? numberOfComputerOrSubnet : nInterfaces.size();

			// Hiển thị 5 dòng cuối
			for (int i = start; i < end; i++) {
			    System.out.println((i + 1) + ") " + nInterfaces.get(i));
			}
		}
	}

	//TInh chỉnh IP chưa config ví dụ 192.168.0.256 thành 192.168.1.0
	public static int[] configIP(int[] unConfigIP) {
	    int[] configIP = unConfigIP.clone();
	    for (int i = 3; i > 0; i--) {
	        if (configIP[i] > 255) {
	            configIP[i - 1] += configIP[i] / 256;
	            configIP[i] %= 256;
	        }
	    }
	    return configIP;
	}

	//Chuyển đổi Bitmask sang Subnet Mask
	public static String convertBitToNetmask(int number) {
	    int[] subnetMask = new int[4];
	    for (int i = 0; i < 4; i++) {
	        subnetMask[i] = (number > 8) ? 255 : (number > 0 ? (256 - (1 << (8 - number))) : 0);
	        number -= Math.min(number, 8);
	    }
	    return NetworkInterface.formatIPv4(subnetMask);
	}

	public static int getIpClassValue(int firstOctet) {
		if (firstOctet <= 127) {
			return 24;
		}
		if (firstOctet <= 191) {
			return 16;
		}
		if (firstOctet <= 223) {
			return 8;
		}

		return 0;
	}

	public static int[] findEndComputerIP(int[] startComputerIP, int numberOfComputer) {
	    int[] endComputerIP = startComputerIP.clone();
	    int remainingComputers = numberOfComputer - 1; // Trừ đi 1 vì IP bắt đầu từ 0 (dành cho máy đầu tiên)

	    // Phân phối số lượng máy còn lại vào các octet
	    for (int i = 3; i >= 0 && remainingComputers > 0; i--) {
	        int increment = remainingComputers % 256;
	        endComputerIP[i] += increment;
	        remainingComputers /= 256;
	    }

	    // Điều chỉnh lại IP nếu vượt quá giới hạn (ví dụ: 256+)
	    return configIP(endComputerIP);
	}
}
