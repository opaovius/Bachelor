package miscTools.TestMethods;

public class AllKmers {
	
	public static void create() {
		
		
		
		
	}
	
	private static int sum(Node[] nodes) {
		
		int sum = 0;
		
		for(Node n : nodes) {
			sum = sum + n.getValue();
		}
		return sum;
	}
	
	private static class NodeList{
		
		Node head;
		
		public void add(Node toAdd) {
			if(head == null) {
				head = toAdd;
			}
			else if(head.next == null) {
				head.setNext(toAdd);
			}
			else {
				Node curr = head.getNext();
				
				while(curr.next != null) {
					curr = curr.getNext();
				}
				curr.setNext(toAdd);
				
			}
		}
		
	}
	
	private static class Node {
		
		private int value;
		private Node next;
		
		public Node(int value) {
			this.value = value;
		}
		
		public void setValue(int value) {
			this.value = value;
		}
		
		public void setNext(Node next) {
			this.next = next;
		}
		
		public int getValue() {
			return value;
		}
		
		public Node getNext() {
			return next;
		}
		
	}

}
