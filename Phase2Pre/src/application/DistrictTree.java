package application;

public class DistrictTree {
	DistrictTreeNode root;

	public DistrictTree() {
		this.root = null;
	}

	public DistrictTreeNode getCurrentNode(String districtName) {
		return searchDistrict1(root, districtName);
	}

	// Existing searchDistrict helper method
	private DistrictTreeNode searchDistrict1(DistrictTreeNode root, String districtName) {
		if (root == null || root.districtName.equalsIgnoreCase(districtName)) {
			return root;
		}
		int comparison = districtName.compareToIgnoreCase(root.districtName);
		if (comparison < 0) {
			return searchDistrict(root.left, districtName);
		} else {
			return searchDistrict(root.right, districtName);
		}
	}

	// Method to insert a new district record or update existing
	public void insert(String districtName, LocationTree locationTree) {
		root = insertTo(root, districtName, locationTree);
	}

	// Helper method for insertion
	private DistrictTreeNode insertTo(DistrictTreeNode root, String districtName, LocationTree locationTree) {
		if (root == null) {
			root = new DistrictTreeNode(districtName, locationTree); // Initialize with the provided LocationTree
		} else {
			int comparison = districtName.compareToIgnoreCase(root.districtName);
			if (comparison < 0) {
				root.left = insertTo(root.left, districtName, locationTree);
			} else if (comparison > 0) {
				root.right = insertTo(root.right, districtName, locationTree);
			}
			// If the district already exists, no need to modify its location tree
		}
		return root;
	}

	public void insert(String districtName) {
		root = insert(root, districtName);
	}

	// Helper method for insertion
	private DistrictTreeNode insert(DistrictTreeNode root, String districtName) {
		if (root == null) {
			root = new DistrictTreeNode(districtName);
			return root;
		}

		// Compare the district names properly
		int comparisonResult = districtName.compareToIgnoreCase(root.districtName);
		if (comparisonResult < 0) {
			root.left = insert(root.left, districtName);
		} else if (comparisonResult > 0) {
			root.right = insert(root.right, districtName);
		}
		return root;
	}

	// Method to search for a district record by name
	public DistrictTreeNode searchDistrict(String districtName) {
		return searchDistrict(root, districtName);
	}

	// Helper method for searching
	private DistrictTreeNode searchDistrict(DistrictTreeNode root, String districtName) {
		if (root == null || districtName == null || root.districtName.equalsIgnoreCase(districtName)) {
			//System.out.println("root: "  + root);
			return root;
		}
		int comparison = districtName.compareToIgnoreCase(root.districtName);
		if (comparison < 0) {
			return searchDistrict(root.left, districtName);
		} else {
			return searchDistrict(root.right, districtName);
		}
	}

	public boolean search(String districtName) {
		return search(root, districtName);
	}

	private boolean search(DistrictTreeNode root, String districtName) {
		if (root == null) {
			return false;
		}
		if (root.districtName.equalsIgnoreCase(districtName)) {
			return true;
		}
		if (root.districtName.compareToIgnoreCase(districtName) < 0) {
			return search(root.left, districtName); 
		}

		return search(root.right, districtName);
	}

	public void printTreeInorder() {
		printTreeInorder(root);

	}

	// In-order traversal for retrieving districts in sorted order

	private void printTreeInorder(DistrictTreeNode root) {
		if (root != null) {
			printTreeInorder(root.left);
			System.out.println(root.districtName + "  ");
			printTreeInorder(root.right);
		}

	}

	public void delete(String districtName) {
		root = deleteNode(root, districtName);
	}

	private DistrictTreeNode deleteNode(DistrictTreeNode root, String districtName) {
		if (root == null) {
			return null;
		}

		if (districtName.compareToIgnoreCase(root.districtName) < 0) {
			root.left = deleteNode(root.left, districtName);
		} else if (districtName.compareToIgnoreCase(root.districtName) > 0) {
			root.right = deleteNode(root.right, districtName);
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
			root.districtName = minValue(root.right);

			// Delete the inOrder successor
			root.right = deleteNode(root.right, root.districtName);
		}
		return root;
	}

	private String minValue(DistrictTreeNode root) { // Get the smallest value
		String minValue = root.districtName;
		while (root.left != null) {
			minValue = root.left.districtName;
			root = root.left;
		}
		return minValue;
	}

	public void insert(DistrictTreeNode districtNode) {
		root = insertNode(root, districtNode);
	}

	private DistrictTreeNode insertNode(DistrictTreeNode root, DistrictTreeNode newNode) {
		if (root == null) {
			return newNode;
		}

		// Compare the district names properly
		int comparisonResult = newNode.getDistrictName().compareToIgnoreCase(root.getDistrictName());
		if (comparisonResult < 0) {
			root.left = insertNode(root.left, newNode);
		} else if (comparisonResult > 0) {
			root.right = insertNode(root.right, newNode);
		}

		return root;
	}

	// Method to print districts along with their locations
	public void printDistrictsAndLocations() {
		printDistrictsAndLocations(root);
	}

	// Helper method to traverse the district tree and print districts along with
	// their locations
	private void printDistrictsAndLocations(DistrictTreeNode node) {
		if (node != null) {
			// Recursively traverse left subtree
			printDistrictsAndLocations(node.left);

			// Print the district name
			System.out.println("District: " + node.getDistrictName());

			// Get the location tree of the current district node
			LocationTree locationTree = node.getLocationTree();
			if (locationTree != null) {
				// Print the locations associated with this district
				System.out.print("Locations:  ");

				locationTree.printTreeInorder();
				System.out.println();
			}

			// Recursively traverse right subtree
			printDistrictsAndLocations(node.right);
		}
	}

	public MyStack getDistrictNamesInStack() {
		MyStack stack = new MyStack();
		getDistrictNamesInStack(root, stack);
		return stack;
	}

	private void getDistrictNamesInStack(DistrictTreeNode node, MyStack stack) {
		if (node != null) {
			getDistrictNamesInStack(node.left, stack);
			stack.push(node.getDistrictName());
			getDistrictNamesInStack(node.right, stack);
		}
	}

	// New method to count martyrs in a district
	public int countMartyrsInDistrict(String districtName) {
		DistrictTreeNode districtNode = searchDistrict(districtName);
		if (districtNode == null) {
			return 0; // District not found
		}
		LocationTree locationTree = districtNode.getLocationTree();
		if (locationTree == null) {
			return 0; // No locations in this district
		}
		return countMartyrsInLocationTree(locationTree.getRoot());
	}

	private int countMartyrsInLocationTree(LocationTreeNode locationNode) {
		if (locationNode == null) {
			return 0;
		}
		
		int count = countMartyrsInLinkedList(locationNode.getDate().getHead());
		count += countMartyrsInLocationTree(locationNode.getLeft());
		count += countMartyrsInLocationTree(locationNode.getRight());
		return count;
	}

	private int countMartyrsInLinkedList(SingleMartyrNode martyrNode) {
		int count = 0;
		while (martyrNode != null) {
			count++;
			martyrNode = martyrNode.next;
		}
		return count;
	}
	public void updateDistrictName(String oldDistrictName, String newDistrictName) {
	    // Search for the node containing the old district name
	    DistrictTreeNode nodeToUpdate = searchDistrict(root, oldDistrictName);
	    System.out.println(nodeToUpdate);
	    
	    if (nodeToUpdate != null) {
	        // Check if the new district name already exists
	        if (!search(newDistrictName)) {
	            // Update the district name
	            nodeToUpdate.setDistrictName(newDistrictName);
	    	    System.out.println(nodeToUpdate);
	            System.out.println("District name updateddd successfully.");
	        } else {
	            System.out.println("Error: The new district name already exists.");
	        }
	    } else {
	        System.out.println("Error: District '" + oldDistrictName + "' not found.");
	    }
	}
	public void updateDistrict(String oldDistrictName, String newDistrictName) {
	    DistrictTreeNode districtNode = searchDistrict(oldDistrictName);
	    if (districtNode != null) {
	        districtNode.setDistrictName(newDistrictName);
	        System.out.println("District updated successfully.");
	    } else {
	        System.out.println("District not found.");
	    }
	}
	
	public MyStack getLocationsForDistrict(String district, DistrictTree districtTree) {
	    MyStack locations = new MyStack();
	    DistrictTreeNode districtNode = districtTree.searchDistrict(district);

	    if (districtNode != null) {
	        locations = districtNode.getLocationTree().getAllLocations();
	    }
	    return locations;
	}

	

}
