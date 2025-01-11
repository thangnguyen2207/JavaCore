package duongMang;

public class NetworkInterface {
	private int[] interfaceAddress;
	private int[] startComputerIP;
	private int[] endComputerIP;
	private int[] ipBroadcast;



	public NetworkInterface() {
		this.interfaceAddress = new int[4];
		this.startComputerIP = new int[4];
		this.endComputerIP = new int[4];
		this.ipBroadcast = new int[4];
	}



	public NetworkInterface(int[] interfaceAddress, int[] startComputerIP, int[] endComputerIP, int[] ipBroadcast) {
		super();
		this.interfaceAddress = interfaceAddress;
		this.startComputerIP = startComputerIP;
		this.endComputerIP = endComputerIP;
		this.ipBroadcast = ipBroadcast;
	}



	public int[] getInterfaceAddress() {
		return interfaceAddress;
	}



	public void setInterfaceAddress(int[] interfaceAddress) {
		this.interfaceAddress = interfaceAddress;
	}



	public int[] getStartComputerIP() {
		return startComputerIP;
	}



	public void setStartComputerIP(int[] startComputerIP) {
		this.startComputerIP = startComputerIP;
	}



	public int[] getEndComputerIP() {
		return endComputerIP;
	}



	public void setEndComputerIP(int[] endComputerIP) {
		this.endComputerIP = endComputerIP;
	}



	public int[] getIpBroadcast() {
		return ipBroadcast;
	}



	public void setIpBroadcast(int[] ipBroadcast) {
		this.ipBroadcast = ipBroadcast;
	}


	@Override
	public String toString() {
	    return formatIPv4(interfaceAddress) +
	           ": " + formatIPv4(startComputerIP) +
	           " - " + formatIPv4(endComputerIP) +
	           " / " + formatIPv4(ipBroadcast);
	}

	// Phương thức phụ để định dạng IPv4 từ mảng
	public static String formatIPv4(int[] address) {
	    return String.join(".", 
	        String.valueOf(address[0]), 
	        String.valueOf(address[1]), 
	        String.valueOf(address[2]), 
	        String.valueOf(address[3])
	    );
	}


}
