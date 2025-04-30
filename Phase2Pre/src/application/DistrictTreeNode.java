package application;

public class DistrictTreeNode {
	String districtName;
	LocationTree locationTree;
	DistrictTreeNode left, right;

	public DistrictTreeNode() {
		left = null;
		right = null;
	}

	public DistrictTreeNode(String districtName) {
		this.districtName = districtName;
		left=right=null;
	}

	public DistrictTreeNode(String districtName, LocationTree locationTree) {
		this.districtName = districtName;
		this.locationTree = locationTree;

	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public LocationTree getLocationTree() {
		return locationTree;
	}

	public void setLocationTree(LocationTree locationTree) {
		this.locationTree = locationTree;
	}

	public DistrictTreeNode getLeft() {
		return left;
	}

	public void setLeft(DistrictTreeNode left) {
		this.left = left;
	}

	public DistrictTreeNode getRight() {
		return right;
	}

	public void setRight(DistrictTreeNode right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "DistrictTreeNode [districtName=" + districtName + ", locationTree=" + locationTree + ", left=" + left
				+ ", right=" + right + "]";
	}

}
