
package application;

public class SingleMartyrNode {
	Martyr martyr;
	SingleMartyrNode next;

	public SingleMartyrNode() {

	}

	public SingleMartyrNode(Martyr martyr) {
		super();
		this.martyr = martyr;
	}

	public SingleMartyrNode(Martyr martyr, SingleMartyrNode next) {
		super();
		this.martyr = martyr;
		this.next = next;
	}

	@Override
	public String toString() {
		return "SingleMartyrNode [martyr=" + martyr;
	}

	public Martyr getMartyr() {
		// TODO Auto-generated method stub
		return null;
	}

}
