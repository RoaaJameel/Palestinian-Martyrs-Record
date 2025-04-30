package application;

public class MyStack {
	StackNode head;
	int size;

	public MyStack() {

	}

	public void push(Object element) {
		StackNode newNode;
		newNode = new StackNode(element);
		newNode.next = head;
		head = newNode;
		size++;// update size
	}

	public Object pop() {
		if (!isEmpty()) {
			StackNode top = head;
			head = head.next;
			size--;
			return top.data;
		} else
			throw new IllegalStateException("Stack is empty");
	}

	public Object peek() {
		if (!isEmpty())
			return head.data;
		else
			throw new IllegalStateException("Stack is empty");
	}

	public int Size() {
		return size;
	}

	public boolean isEmpty() {
		return (head == null);
	}

	public Object get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		StackNode current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.data;
	}

	public void clear() {
		head = null;
		size = 0;
	}

}
