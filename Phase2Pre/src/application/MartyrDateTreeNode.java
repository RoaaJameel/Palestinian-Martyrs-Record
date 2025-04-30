package application;

import java.time.LocalDate;

public class MartyrDateTreeNode {
	LocalDate date;
	LinkedListMartyr martyrsLinkedList;
	MartyrDateTreeNode left;
	MartyrDateTreeNode right;
	
	
	

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LinkedListMartyr getMartyrsLinkedList() {
		return martyrsLinkedList;
	}

	public void setMartyrsLinkedList(LinkedListMartyr martyrsLinkedList) {
		this.martyrsLinkedList = martyrsLinkedList;
	}

	public MartyrDateTreeNode getLeft() {
		return left;
	}

	public void setLeft(MartyrDateTreeNode left) {
		this.left = left;
	}

	public MartyrDateTreeNode getRight() {
		return right;
	}

	public void setRight(MartyrDateTreeNode right) {
		this.right = right;
	}

	public MartyrDateTreeNode() {
		left = null;
		right = null;
	}

	public MartyrDateTreeNode(LocalDate date) {
		this.date = date;
		left = right = null;
	}

	public MartyrDateTreeNode(LocalDate date, LinkedListMartyr martyrsLinkedList) {
		this.date = date;
		this.martyrsLinkedList = martyrsLinkedList;

	}

	@Override
	public String toString() {
		return "MartyrDateTreeNode [date=" + date + ", martyrsLinkedList=" + martyrsLinkedList;
	}

}
