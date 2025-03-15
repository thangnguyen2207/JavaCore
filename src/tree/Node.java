package tree;

public class Node {
	private int num;
	private Node left;
	private Node right;
	
	public Node(int num, Node left, Node right) {
		super();
		this.num = num;
		this.left = left;
		this.right = right;
	}

	public Node(int num) {
		super();
		this.num = num;
		this.left = null;
		this.right = null;
	}



	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
	
	
}
