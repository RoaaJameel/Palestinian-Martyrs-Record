package application;

public class LocationTreeNode {
	String locationName;
	MartyrDateTree date;
	LocationTreeNode left;
	LocationTreeNode right;

	public LocationTreeNode() {
		left = null;
		right = null;

	}
	

	public String getLocationName() {
		return locationName;
	}


	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}


	public MartyrDateTree getDate() {
		return date;
	}


	public void setDate(MartyrDateTree date) {
		this.date = date;
	}


	public LocationTreeNode getLeft() {
		return left;
	}


	public void setLeft(LocationTreeNode left) {
		this.left = left;
	}


	public LocationTreeNode getRight() {
		return right;
	}
	


	public void setRight(LocationTreeNode right) {
		this.right = right;
	}


	public LocationTreeNode(String locationName) {
		this.locationName = locationName;
		left = right = null;

	}

	public LocationTreeNode(String locationName, MartyrDateTree martyrDateTree) {
		super();
		this.locationName = locationName;
		this.date = martyrDateTree;
		this.left = null;
		this.right = null;

	}

	@Override
	public String toString() {
		return "LocationTreeNode [locationName=" + locationName + ", date=" + date + ", left=" + left + ", right="
				+ right + "]";
	}
	

}
