package tree;

import java.util.ArrayList;
import java.util.List;

public class TreeDemo {

	public static void main(String[] args) {
		Node n1 = new Node(7);
		Node n2 = new Node(5);
		Node n3 = new Node(12);
		Node n4 = new Node(3);
		Node n5 = new Node(6);
		Node n6 = new Node(9);
		Node n7 = new Node(15);
		Node n8 = new Node(1);
		Node n9 = new Node(4);
		Node n10 = new Node(8);
		Node n11 = new Node(10);
		Node n12 = new Node(13);
		Node n13 = new Node(17);
		
		n1.setLeft(n2);
		n1.setRight(n3);
		n2.setLeft(n4);
		n2.setRight(n5);
		n3.setLeft(n6);
		n3.setRight(n7);
		n4.setLeft(n8);
		n4.setRight(n9);
		n6.setLeft(n10);
		n6.setRight(n11);
		n7.setLeft(n12);
		n7.setRight(n13);
		
		System.out.println("NRL: " + nrl(n1, new ArrayList<Integer>()));
		System.out.println("NLR: " + nlr(n1, new ArrayList<Integer>()));
		System.out.println("LNR: " + lnr(n1, new ArrayList<Integer>()));
		System.out.println("LRN: " + lrn(n1, new ArrayList<Integer>()));
		System.out.println("RNL: " + rnl(n1, new ArrayList<Integer>()));
		System.out.println("RLN: " + rln(n1, new ArrayList<Integer>()));
		System.out.println("Total node:" + countLeafNodes(n1));
		
		insert(n1, 2);
		insert(n1, 11);
		insert(n1, 14);
		insert(n1, 16);
		
		System.out.println("LNR: " + lnr(n1, new ArrayList<Integer>()));
	}

	public static List<Integer> nrl(Node node, List<Integer> orderList) {
		if (node == null) return orderList;
		
		orderList.add(node.getNum());
		
		nrl(node.getRight(), orderList);
		nrl(node.getLeft(), orderList);
		
		return orderList;
	}
	
	public static List<Integer> nlr(Node node, List<Integer> orderList) {	
		if (node == null) return orderList;
		
		orderList.add(node.getNum());
		
		nlr(node.getLeft(), orderList);
		nlr(node.getRight(), orderList);
		
		return orderList;
	}
	
	public static List<Integer> lnr(Node node, List<Integer> orderList) {	
		if (node == null) return orderList;
		
		lnr(node.getLeft(), orderList);
		orderList.add(node.getNum());
		lnr(node.getRight(), orderList);
		
		return orderList;
	}
	
	public static List<Integer> lrn(Node node, List<Integer> orderList) {	
		if (node == null) return orderList;
		
		lrn(node.getLeft(), orderList);
		lrn(node.getRight(), orderList);
		orderList.add(node.getNum());
		
		return orderList;
	}
	
	public static List<Integer> rnl(Node node, List<Integer> orderList) {	
		if (node == null) return orderList;
		
		rnl(node.getRight(), orderList);
		orderList.add(node.getNum());
		rnl(node.getLeft(), orderList);
		
		return orderList;
	}
	
	public static List<Integer> rln(Node node, List<Integer> orderList) {	
		if (node == null) return orderList;
		
		rln(node.getRight(), orderList);
		rln(node.getLeft(), orderList);
		orderList.add(node.getNum());
		
		return orderList;
	}
	
	public static Node insert(Node node, int value) {
		if (node == null)
			return new Node(value);
		
		if (value < node.getNum()) {
	        node.setLeft(insert(node.getLeft(), value));
	    } else if (value > node.getNum()) {
	        node.setRight(insert(node.getRight(), value));
	    }
		
		return node;
	}
	
	public static int countLeafNodes(Node node) {
		 if (node == null) return 0;

		 if (node.getLeft() == null && node.getRight() == null) 
			 return 1;
		 
		 return countLeafNodes(node.getLeft()) + countLeafNodes(node.getRight());
	}
}
