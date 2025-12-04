package miscTools.TestMethods;

public class AllKmers {
	
	public static void create() {
		
		NodeList nl = new NodeList();
		NodeList nl2 = new NodeList();
		
		nl2.add(new Node(2));
		nl2.add(new Node(3));
		nl2.add(new Node(1));
		
		for(int x = 0; x < 30; x++) {
			nl.add(new Node(0));
		}
		
		
		
		IO.println(nl.size());
		
		while(nl.sum() != 3) {
			nl.calc();
		}
		
		IO.println("NodeListTest:");
		for(int x = 0; x < nl.size(); x++) {
			IO.println(nl.get(x));
		}
		
		IO.println("sum: " + nl.sum());
		
		//generateAllKMers(30);
	}
	
	static final char[] BASES = {'A', 'C', 'G', 'T'};

	public static void generateAllKMers(int k) {
	    long total = (long) Math.pow(4, k);

	    char[] buf = new char[k];

	    for (long n = 0; n < total; n++) {
	        long x = n;
	        for (int i = k - 1; i >= 0; i--) {
	            buf[i] = BASES[(int)(x & 3)]; // x % 4
	            x >>= 2; // x = x / 4
	        }
	        System.out.println(new String(buf));
	    }
	}

	
	private static class NodeList{
		
		private Node head;
		private int size = 0;
		final private int cap = 4;
		
		public void add(Node toAdd) {
			if(head == null) {
				head = toAdd;
				size++;
			}
			else if(head.next == null) {
				head.setNext(toAdd);
				size++;
			}
			else {
				Node curr = head.getNext();
				
				while(curr.next != null) {
					curr = curr.getNext();
				}
				curr.setNext(toAdd);
				size++;
			}
		}
		
		public int  size() {
			return size;
		}
		
		public void calc() {
			
			head.setValue(head.getValue() + 1);
			
			if(head.getValue() == cap) {
				head.setValue(0);
				recCalc(head.getNext());
			}
		}
		
		private void recCalc(Node curr) {
			curr.setValue(curr.getValue() + 1);
			
			if(curr.getValue() == cap) {
				curr.setValue(0);
				recCalc(curr.getNext());
			}
		}
		
		public int get(int pos) {
			if(pos == 0) return head.getValue();
			
			Node curr = head.getNext();
			
			int counter = 1;
			
			while(counter != pos) {
				curr = curr.getNext();
				counter++;
			}
			
			return curr.getValue();
		}
		
		public int sum() {
			if(head == null) return 0;
			
			if(head.getNext() == null) return head.getValue();
			
			Node curr = head.getNext();
			
			int sum = curr.getValue() + head.getValue();
			
			while(curr.getNext() != null) {
				curr = curr.getNext();
				sum = sum + curr.getValue();
			}
			
			return sum;
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
