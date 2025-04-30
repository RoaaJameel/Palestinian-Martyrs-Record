package application;

public class LinkedListMartyr {
	SingleMartyrNode head;

	public LinkedListMartyr() {
		// head = null; // Initialize head to null
	}

	public void insert(Martyr martyr) {
		// String name, LocalDate dateOfDeath, int age, String district, String
		// location, char gender
		// Martyr martyr = new Martyr(name, date, age, district, location, gender);
		SingleMartyrNode martyrNode = new SingleMartyrNode(martyr);

		if (head == null) { // Case 1: Add to an empty list
			head = martyrNode;
		} else {
			SingleMartyrNode current = head;
			SingleMartyrNode prev = null;

			while (current != null && current.martyr.getAge() < martyr.getAge()) {
				prev = current;
				current = current.next;
			}

			// If the martyr has the same age, sort by gender
			while (current != null && current.martyr.getAge() == martyr.getAge()
					&& current.martyr.getGender() <= martyr.getGender()) {
				prev = current;
				current = current.next;
			}

			// If the martyr should be inserted at the beginning
			if (prev == null) {
				martyrNode.next = head;
				head = martyrNode;
			} else {
				prev.next = martyrNode;
				martyrNode.next = current;
			}
		}
	}

	public SingleMartyrNode getHead() {
		return head;
	}

	public void setHead(SingleMartyrNode head) {
		this.head = head;
	}

	public void delete(String name) {
		if (head == null) {// this means that the list is empty
			return;
		}

		if (head.martyr.getName().equalsIgnoreCase(name)) {
			head = head.next;
			return;
		}

		SingleMartyrNode current = head;
		SingleMartyrNode prev = null;

		while (current != null && !current.martyr.getName().equalsIgnoreCase(name)) {
			prev = current;
			current = current.next;
		}

		if (current == null) {
			return;
		}

		prev.next = current.next;
	}

	public boolean search(String name) {
		SingleMartyrNode current = head;

		while (current != null) {
			if (current.martyr.getName().equalsIgnoreCase(name)) {
				return true;
			}
			current = current.next;
		}

		return false;
	}

	public Martyr searchMartyr(String name) {
		SingleMartyrNode current = head;

		while (current != null) {
			if (current.martyr.getName().equalsIgnoreCase(name)) {
				return current.martyr;
			}
			current = current.next;
		}

		return null; // Martyr not found
	}

	public void printMartyrsLinkedList() {
		SingleMartyrNode temp = head;
		while (temp != null) {
			System.out.println(temp.martyr);
			temp = temp.next;
		}
	}

	@Override
	public String toString() {
		return "LinkedListMartyr [head=" + head + "]";
	}

	public void merge(LinkedListMartyr newList) {
		if (newList == null || newList.head == null) {
			return; // Nothing to merge
		}

		if (head == null) {
			head = newList.head; // If the current list is empty, assign the new list's head to it
			return;
		}

		SingleMartyrNode current = head;
		while (current.next != null) {
			current = current.next; // Move to the end of the current list
		}

		// Concatenate the new list to the end of the current list
		current.next = newList.head;
	}

	public SingleMartyrNode searchForMartyr(String name) {
		SingleMartyrNode current = head;

		while (current != null) {
			if (current.martyr.getName().equalsIgnoreCase(name)) {
				return current;
			}
			current = current.next;
		}

		return null; // Martyr not found
	}

	public int size() {
		int count = 0;
		SingleMartyrNode current = head;

		while (current != null) {
			count++;
			current = current.next;
		}

		return count;
	}

}
