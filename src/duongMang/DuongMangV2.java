package duongMang;

import java.util.ArrayList;
import java.util.List;

public class DuongMangV2 {

	public static void main(String[] args) {
		int[] ipAddress = {80, 0, 0, 0};
		int numberOfComputerOrSubnet = 1100;
		boolean isNumberOfSubnet = true;		
		
		calculate(ipAddress, numberOfComputerOrSubnet, isNumberOfSubnet);
	}
	
	public static void calculate(int[] ipAddress, int numberOfComputerOrSubnet, boolean isNumberOfSubnet) {
		int subnets = 0;
		int computers = 0;
		
		//Tim x bang logarit cua 2
		int x = (int) Math.ceil(Math.log(numberOfComputerOrSubnet) / Math.log(2));
		
		//Kiem tra ip thuoc lop dia chi nao + lay gia tri host
		String[] ipClass = defindIPClass(ipAddress).clone();
		
		//De tim subnet mask
		int s;
		
		//Tinh toan subnets, may tinh / subnet va gia tri s
		if (!isNumberOfSubnet) {
			computers += Math.pow(2, x) - 2;
			subnets += Math.pow(2, Integer.valueOf(ipClass[1]) - x);
			s = 32 - x;	
		} else {
			subnets += Math.pow(2, x);
			computers += Math.pow(2, Integer.valueOf(ipClass[1]) - x) - 2;
			s = 32 - (Integer.valueOf(ipClass[1]) - x);
		}
		
		System.out.println("2^x = " + numberOfComputerOrSubnet + " => x = " + x + 
				(!isNumberOfSubnet ? "\nComputers = 2^" + x + " - 2 = " + computers + 
				"\nSubnets = 2^(" + Integer.valueOf(ipClass[1]) + " - " + x + ") = " + subnets 
				: "\nSubnets = 2^" + x + " = " + subnets + 
				"\nComputers = 2^(" + Integer.valueOf(ipClass[1]) + " - " + x + ") - 2 = " + computers) +
				"\nSubnet mask: " + calculateSubnetMask(s) + " / " + s);
		
		int[] tempIP = ipAddress.clone();
		
		List<NetworkInterface> listNetworkInts = new ArrayList<>();
		
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
			listNetworkInts.add(nInterface);
			
			//Cong them 1 vao ip de chuyen sang subnet tiep theo
			tempIP[3] += 1;
			tempIP = configIP(tempIP).clone();
		}
		
		//Hien thi cac duong mang
		if (listNetworkInts.size() < 20) {
			for (int i = 0; i < listNetworkInts.size(); i++) {
				System.out.println((i+1) + ") " + listNetworkInts.get(i));
			}
		} else {
			for (int i = 0; i < 5; i++) {
				System.out.println((i+1) + ") " + listNetworkInts.get(i));
			}
			System.out.println("------------------------------------");
			if (!isNumberOfSubnet) {
				for (int i = listNetworkInts.size() - 5; i < listNetworkInts.size(); i++) {
					System.out.println((i+1) + ") " + listNetworkInts.get(i));
				}
			} else {
				for (int i = numberOfComputerOrSubnet - 5; i < numberOfComputerOrSubnet; i++) {
					System.out.println((i+1) + ") " + listNetworkInts.get(i));
				}
			}
		}
	
	}
	
	public static int[] configIP (int[] unConfigIP) {
		int[] configIP = unConfigIP.clone();
		
		for (int i = 3; i > 0; i--) {
			if (configIP[i] > 255) {
				configIP[i] -= 256;
				configIP[i - 1]++;
			}
		}
		
		return configIP;
	}
	
	public static String calculateSubnetMask(int number) {
		int[] subnetMask = {0, 0, 0, 0};
		int num = number;
		int count = 0;
		
		while (num > 8) {
			num -= 8;
			subnetMask[count] = 255;
			count++;
		}
		
		int bitValue = 128;
		int value = 0;
		for (int i = 1; i <= num; i++) {
			value += bitValue;
			bitValue /= 2;
		}		
		subnetMask[count] = value;
		count++;
		
		return subnetMask[0] + "." + subnetMask[1] + "." + subnetMask[2] + "." + subnetMask[3];
	}
	
	public static String[] defindIPClass(int[] ipAddress) {
		String[] ipClass = new String[2];

		if (ipAddress[0] >= 1 && ipAddress[0] <= 127) {
			ipClass[0] = "A";
			ipClass[1] = "24";
		} else if (ipAddress[0] >= 128 && ipAddress[0] <= 191) {
			ipClass[0] = "B";
			ipClass[1] = "16";
		} else if (ipAddress[0] >= 192 && ipAddress[0] <= 223) {
			ipClass[0] = "C";
			ipClass[1] = "8";
		}
		
		return ipClass;
	}
	
	public static int[] findEndComputerIP(int[] startComputerIP, int numberOfComputer) {
		int[] endComputerIP = startComputerIP.clone();
		int curComputer = numberOfComputer;
		int idx = 0;
		
		for (int i = 3; i >= 1; i--) {
			int max = (int) Math.pow(256, i);
			int rs = (int) Math.floor(curComputer / max);
			endComputerIP[idx] += rs;
			curComputer -= rs * max;
			idx++; 
		}
		
		if (curComputer > 0) {
			endComputerIP[3] += curComputer - 1;
			curComputer = 0;
		}
		
		endComputerIP = configIP(endComputerIP).clone();
		
		return endComputerIP;
	}
}
