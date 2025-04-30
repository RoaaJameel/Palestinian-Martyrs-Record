package application;

public class LocationTree {
	LocationTreeNode root;

	public LocationTree() {
		this.root = null;
	}

	// Method to insert a new location record
	public void insert(String location, MartyrDateTree martyrDateTree) {
		root = insertTo(root, location, martyrDateTree);
	}

	// Helper method for insertion
	private LocationTreeNode insertTo(LocationTreeNode root, String locationName, MartyrDateTree martyrDateTree) {
		if (root == null) {
			root = new LocationTreeNode(locationName, martyrDateTree);
		} else {
			int comparison = locationName.compareToIgnoreCase(root.locationName);
			if (comparison < 0) {
				root.left = insertTo(root.left, locationName, martyrDateTree);
			} else if (comparison > 0) {
				root.right = insertTo(root.right, locationName, martyrDateTree);
			} else {
				// If the location name already exists, update the martyrDateTree
				root.date = martyrDateTree;
			}
		}
		return root;
	}

	// Method to print locations along with their dates
	public void printLocationsAndDates() {
		printLocationsAndDates(root);
	}

	// Helper method to traverse the location tree and print locations along with
	// their dates
	private void printLocationsAndDates(LocationTreeNode locationNode) {
		if (locationNode != null) {
			// Print the location name
			System.out.println("Location: " + locationNode.getLocationName());

			// Get the date tree of the current location node
			MartyrDateTree dateTree = locationNode.getDate();
			if (dateTree != null) {
				// Print the dates associated with this location
				printDates(dateTree.getRoot());
			}

			// Recursively traverse left and right subtrees
			printLocationsAndDates(locationNode.getLeft());
			printLocationsAndDates(locationNode.getRight());
		}
	}

	// Helper method to traverse the date tree and print dates
	private void printDates(MartyrDateTreeNode dateNode) {
		if (dateNode != null) {
			// Print the date
			System.out.println("Date: " + dateNode.getDate());

			// Recursively traverse left and right subtrees
			printDates(dateNode.getLeft());
			printDates(dateNode.getRight());
		}
	}

	public void insert(String locationName) {
		root = insert(root, locationName);
	}

	// Helper method for insertion
	private LocationTreeNode insert(LocationTreeNode root, String locationName) {
		if (root == null) {
			root = new LocationTreeNode(locationName);
			return root;
		}

		// Compare the location names properly
		int comparisonResult = locationName.compareToIgnoreCase(root.locationName);
		if (comparisonResult < 0) {
			root.left = insert(root.left, locationName);
		} else if (comparisonResult > 0) {
			root.right = insert(root.right, locationName);
		}
		return root;
	}

	// Method to calculate the total number of martyrs
	public int getTotalMartyrs() {
		return getTotalMartyrs(root);
	}

	// Helper method for counting martyrs recursively
	private int getTotalMartyrs(LocationTreeNode node) {
		if (node == null) {
			return 0;
		}
		int leftTotal = getTotalMartyrs(node.getLeft());
		int rightTotal = getTotalMartyrs(node.getRight());
		return leftTotal + rightTotal + 1; // Add 1 for the current node
	}

	// Method to search for a location record by name
	public LocationTreeNode searchLocation(String locationName) {
		return searchLocation(root, locationName);
	}

	// Helper method for searching
	private LocationTreeNode searchLocation(LocationTreeNode root, String locationName) {
		if (root == null || root.locationName.equalsIgnoreCase(locationName)) {
			return root;
		}

		if (locationName.compareToIgnoreCase(root.locationName) < 0) {
			return searchLocation(root.left, locationName);
		} else {
			return searchLocation(root.right, locationName);
		}
	}

	public void printTreeInorder() {
		printTreeInorder(root);

	}

	private void printTreeInorder(LocationTreeNode root) {
		if (root != null) {
			printTreeInorder(root.left);
			System.out.println(root.locationName);
			printTreeInorder(root.right);
		}

	}

	@Override
	public String toString() {
		return "LocationTree{" + "root=" + (root != null ? root.toString() : "null") + '}';
	}

	public void delete(String districtName) {
		root = deleteNode(root, districtName);
	}

	private LocationTreeNode deleteNode(LocationTreeNode root, String locationName) {
		if (root == null) {
			return null;
		}

		if (locationName.compareToIgnoreCase(root.locationName) < 0) {
			root.left = deleteNode(root.left, locationName);
		} else if (locationName.compareToIgnoreCase(root.locationName) > 0) {
			root.right = deleteNode(root.right, locationName);
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
			root.locationName = minValue(root.right);

			// Delete the inOrder successor
			root.right = deleteNode(root.right, root.locationName);
		}
		return root;
	}

	private String minValue(LocationTreeNode root) { // Get the smallest value
		String minValue = root.locationName;
		while (root.left != null) {
			minValue = root.left.locationName;
			root = root.left;
		}
		return minValue;
	}

	public void insert(LocationTreeNode locationNode) {
		root = insertTo(locationNode);
	}

	// Helper method for insertion
	private LocationTreeNode insertTo(LocationTreeNode root) {
		if (root == null) {
			root = new LocationTreeNode();
		}
//		} else {
//			if (search(root, districtName) == true) {
		if (root.locationName.compareToIgnoreCase(root.locationName) < 0) {
			root.left = insertTo(root.left);
		} else if (root.locationName.compareToIgnoreCase(root.locationName) > 0) {
			root.right = insertTo(root.right);
		}

		return root;
	}

	public LocationTreeNode getRoot() {
		return root;
	}

	public void setRoot(LocationTreeNode root) {
		this.root = root;
	}

	public MyStack getAllLocations() {
		MyStack stack = new MyStack();
		inOrderTraversal(root, stack);
		return stack;
	}

	private void inOrderTraversal(LocationTreeNode node, MyStack stack) {
		if (node != null) {
			inOrderTraversal(node.left, stack);
			stack.push(node.getLocationName());
			inOrderTraversal(node.right, stack);
		}
	}
	

}
