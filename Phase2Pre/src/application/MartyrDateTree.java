package application;

import java.time.LocalDate;

public class MartyrDateTree {

	MartyrDateTreeNode root;

	public MartyrDateTree() {
		this.root = null;
	}

	// Method to insert a new martyr record
	public void insert1(LocalDate date, LinkedListMartyr martyrsLinkedList) {
		root = insertTo(root, date, martyrsLinkedList);
	}

	// Helper method for insertion
	private MartyrDateTreeNode insertTo(MartyrDateTreeNode root, LocalDate date, LinkedListMartyr martyrsLinkedList) {
		if (root == null) {
			root = new MartyrDateTreeNode(date, new LinkedListMartyr());

		} else {
			if (search(date) == true) {
				if (date.isBefore(root.date)) {
					root.left = insertTo(root.left, date, martyrsLinkedList);
				} else if (date.isAfter(root.date)) {
					root.right = insertTo(root.right, date, martyrsLinkedList);

				}
			} else if (search(date) == false) {
				if (date.isBefore(root.date)) {
					root.left = insertTo(root.left, date, new LinkedListMartyr());
				} else if (date.isAfter(root.date)) {
					root.right = insertTo(root.right, date, new LinkedListMartyr());

				}
			}
		}
		return root;
	}

	public void insert(LocalDate date) {
		root = insertTo(root, date);
	}

	// Helper method for insertion
	private MartyrDateTreeNode insertTo(MartyrDateTreeNode root, LocalDate date) {
		if (root == null) {
			root = new MartyrDateTreeNode(date);
			return root;
		}

		// Compare the dates properly
		if (date.isBefore(root.date)) {
			root.left = insertTo(root.left, date);
		} else if (date.isAfter(root.date)) {
			root.right = insertTo(root.right, date);
		}

		return root;
	}

	public void insert(MartyrDateTreeNode newNode) {
		root = insert(root, newNode);
	}

	private MartyrDateTreeNode insert(MartyrDateTreeNode node, MartyrDateTreeNode newNode) {
		if (node == null) {
			return newNode;
		}

		int comparison = newNode.getDate().compareTo(node.getDate());
		if (comparison < 0) {
			node.left = insert(node.left, newNode);
		} else if (comparison > 0) {
			node.right = insert(node.right, newNode);
		} else {
			// If the date already exists, update the martyr list (if necessary)
			if (node.getMartyrsLinkedList() == null) {
				node.setMartyrsLinkedList(newNode.getMartyrsLinkedList());
			} else {
				LinkedListMartyr existingList = node.getMartyrsLinkedList();
				LinkedListMartyr newList = newNode.getMartyrsLinkedList();
				existingList.merge(newList); // Merge the new list with the existing one
			}
		}

		return node;
	}

	public boolean search(LocalDate date) {
		return searchIn(root, date);
	}

	// Helper method for searching
	private boolean searchIn(MartyrDateTreeNode node, LocalDate date) {
		if (node == null) {
			return false; // Date not found
		}
		if (date.isEqual(node.date)) {
			return true; // Date found
		} else if (date.isBefore(node.date)) {
			return searchIn(node.left, date);
		} else {
			return searchIn(node.right, date);
		}
	}

	public MartyrDateTreeNode Search(LocalDate date) {
		return searchFor(root, date);
	}

	private MartyrDateTreeNode searchFor(MartyrDateTreeNode root, LocalDate date) {
		if (root == null || root.date.isEqual(date)) {
			return root;
		} else if (date.isBefore(root.date)) {
			return searchFor(root.left, date);
		} else {
			return searchFor(root.right, date);
		}

	}

	// Method to calculate the total number of martyrs
	public int getTotalMartyrs() {
		return getTotalMartyrs(root);
	}

	// Helper method for counting martyrs
	private int getTotalMartyrs(MartyrDateTreeNode node) {
		if (node == null) {
			return 0;
		}
		int leftTotal = getTotalMartyrs(node.left);
		int rightTotal = getTotalMartyrs(node.right);
		return leftTotal + rightTotal;
	}

	public void printTreeInorder() {
		printTreeInorder(root);

	}

	private void printTreeInorder(MartyrDateTreeNode root) {
		if (root != null) {
			printTreeInorder(root.left);
			System.out.println(root.date);
			printTreeInorder(root.right);
		}

	}

	public void delete(LocalDate date) {
		root = deleteNode(root, date);
	}

	private MartyrDateTreeNode deleteNode(MartyrDateTreeNode root, LocalDate date) {
		if (root == null) {
			return null;
		}

		if (date.isBefore(root.date)) {
			root.left = deleteNode(root.left, date);
		} else if (date.isAfter(root.date)) {
			root.right = deleteNode(root.right, date);
		} else {

			// Node to delete found

			// Case 1: Node with no children or one child
			if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			}

			// Case 2: Node with two children
			// Get the inOrder successor (smallest in the right subtree)
			root.date = minValue(root.right);

			// Delete the inOrder successor
			root.right = deleteNode(root.right, root.date);
		}
		return root;
	}

	private LocalDate minValue(MartyrDateTreeNode root) { // Get the smallest value
		LocalDate minValue = root.date;
		while (root.left != null) {
			minValue = root.left.date;
			root = root.left;
		}
		return minValue;
	}

	public MartyrDateTreeNode getCurrentNode(LocalDate date) {
		return searchDate1(root, date);
	}

	// Existing searchDistrict helper method
	private MartyrDateTreeNode searchDate1(MartyrDateTreeNode root, LocalDate date) {
		if (root == null || root.date.equals(date)) {
			return root;
		}
		if (date.isBefore(root.date)) {
			return searchDate1(root.left, date);
		} else {
			return searchDate1(root.right, date);
		}
	}

	@Override
	public String toString() {
		return "MartyrDateTree [root=" + root + "]";
	}

	public void insert2(MartyrDateTreeNode node) {
		root = insertTo(node);
	}

	// Helper method for insertion
	private MartyrDateTreeNode insertTo(MartyrDateTreeNode root) {
		if (root == null) {
			root = new MartyrDateTreeNode();
		}
		if (root.date.isBefore(root.date)) {
			root.left = insertTo(root.left);
		} else if (root.date.isAfter(root.date)) {
			root.right = insertTo(root.right);
		}

		return root;
	}

	public void insert2(LocalDate date, LinkedListMartyr martyrList) {
		root = insert(root, date, martyrList);
	}

	private MartyrDateTreeNode insert(MartyrDateTreeNode node, LocalDate date, LinkedListMartyr martyrList) {
		if (node == null) {
			return new MartyrDateTreeNode(date, martyrList);
		}

		int comparison = date.compareTo(node.date);
		if (comparison < 0) {
			node.left = insert(node.left, date, martyrList);
		} else if (comparison > 0) {
			node.right = insert(node.right, date, martyrList);
		} else {
			// If the date already exists, update the martyrList (if necessary)
			node.martyrsLinkedList = martyrList;
		}

		return node;
	}

	public MartyrDateTreeNode getRoot() {
		return root;
	}

	public void setRoot(MartyrDateTreeNode root) {
		this.root = root;
	}

	public void printDatesAndMartyrs() {
		// Check if the root is null before starting the traversal
		if (root == null) {
			System.out.println("Tree is empty.");
			return;
		}

		// Start the recursive printing from the root
		printDatesAndMartyrsRecursive(root);
	}

	private void printDatesAndMartyrsRecursive(MartyrDateTreeNode node) {
		// Base case: stop recursion if the current node is null
		if (node == null) {
			return;
		}

		// Recursively print the left subtree
		printDatesAndMartyrsRecursive(node.getLeft());

		// Print the data of the current node
		System.out.println("Date: " + node.getDate());
		System.out.println("Martyrs:");
		if (node.getMartyrsLinkedList() != null) {
			node.getMartyrsLinkedList().printMartyrsLinkedList();
		} else {
			System.out.println("No martyrs linked list found.");
		}

		// Recursively print the right subtree
		printDatesAndMartyrsRecursive(node.getRight());
	}

	public SingleMartyrNode getHead() {
		if (root != null) {
			MartyrDateTreeNode currentNode = root;
			LinkedListMartyr martyrsLinkedList = currentNode.getMartyrsLinkedList();
			if (martyrsLinkedList != null) {
				return martyrsLinkedList.getHead();
			}
		}
		return null;
	}

}
