package miscTools.TestMethods;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AllKmers {
	
	public static void create() {
		
		NodeList nl = new NodeList();
		StringBuilder sb = new StringBuilder("");
		int count = 0;
		
		for(int x = 0; x < 15; x++) {
			nl.add(new Node(0));
		}
		
		
		
		sb.append(">kmer" + count + "\n" + nl.toString() + "\n");
		count++;
		
		while(count != Math.powExact(4, 15)) {
			nl.calc();
			sb.append(">kmer" + count + "\n" + nl.toString() + "\n");
			count++;
			
			if(sb.length() > Integer.MAX_VALUE/4) {
				write(sb);
				sb.setLength(0);
			}
		}
		
		if(sb.length() > 0) {
			write(sb);
		}
		
		
	}
	
	private static void write(StringBuilder sb) {

		try (FileChannel channel = FileChannel.open(Paths.get("src/testFiles/AnalyseDir/AllKmer.fa"),
				StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND)) {

			ByteBuffer buffer = ByteBuffer.wrap(sb.toString().getBytes());
			channel.write(buffer);
			channel.force(true);
			channel.close();

		} catch (IOException e) {
			System.out.println("IO-Error");
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
		
		@SuppressWarnings("unused")
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
		
		@SuppressWarnings("unused")
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
		
		@SuppressWarnings("unused")
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
		
		public String toString() {
			if(head == null) return "";
			
			if(head.getNext() == null) return head.getNext().getValue() + "";
			
			Node curr = head.getNext();
			StringBuilder result = new StringBuilder(head.getValue() + "" + curr.getValue());
			
			while(curr.getNext() != null) {
				curr = curr.getNext();
				result.append(curr.getValue() + "");
			}
			
			return result.toString();
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
