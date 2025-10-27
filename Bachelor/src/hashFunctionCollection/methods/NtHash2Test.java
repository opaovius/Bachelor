package hashFunctionCollection.methods;

import java.util.ArrayList;

import hashFunctionCollection.IHashFunction;

public class NtHash2Test implements IHashFunction{
	
	private ArrayList<Block> blocks = new ArrayList<>();
	private ArrayList<Monomer> monos = new ArrayList<>();
	private long[][] look;
	
	public NtHash2Test() {
		this.look = NtHash2LookupTable.load();
	}
	
	public long[] toHash(String genom, int kmerSize) {
	
		parseSeed("1111110101101011100111011001111");
		
		long[] temp = baseHash(genom.substring(0,kmerSize),kmerSize);
		
		long[] kmerArray = new long[genom.length() - kmerSize + 1];
		
		kmerArray[0] = temp[0];
		
		int i = 1;
		
		for(int counter = kmerSize; counter < genom.length()-kmerSize; counter++) {
			kmerArray[i] = slideHash(genom.substring(counter,counter+kmerSize),kmerSize,temp);
			temp[1] = kmerArray[i-1];
			temp[0] = kmerArray[i];
			i++;
		}
		
		
		return new long[2];
	}

	//Erstellen der Bloecke und Monomere
	private void parseSeed(String seed) {
		int start = 0;
		boolean isBlock = true;
		
		for(int i = 0; i < seed.length(); i++) {
			
			//Start eines Blocks, oder eines Monomers
			if((seed.charAt(i) == '1' ) && !isBlock) {
				isBlock = true;
				start = i;
			}
			//Speichern, bei mehreren 1 als Block, bei einer als Monomer
			else if(((seed.charAt(i) == '0') && isBlock) || i == seed.length() - 1) {
				
				/*
				 * Erster Fall: Block und Ende des Seeds
				 * Zweiter Fall: Block
				 * Dritter Fall: Monomer und Ende des Seeds
				 * Vierter Fall: Monomer 
				 */
				if((i - start > 1) && (i == seed.length() - 1)) {
					blocks.add(new Block(start,i));
				}
				else if(i - start > 1) {
					blocks.add(new Block(start,i));
				}
				else if(i == seed.length()- 1) {
					monos.add(new Monomer(i));
				}
				else {
					monos.add(new Monomer(i));
				}
				isBlock = false;
			}
			
		}
		
	}
	
	private long[] baseHash(String sequence, int kmerSize) {
	
		/*
		 * [0] = H
		 * [1] = H_b
		 */
		long[] hashResults = {0,0};
		
		
		//Jeden Block durchgehen von Start nach Ende
		for(Block b: blocks) {
			int p = b.start();
			int q = b.end();
			
			//Start -> Ende
			for(int i = p; i < q; i++) {
				
				hashResults[0] = hashResults[0] ^ srol(sequence.charAt(i),kmerSize-i-1);
				
			}
			
			hashResults[1] = hashResults[0];
			
		}
		
		//Jedes Monomer durchgehen
		for(Monomer m: monos) {
			
			int i = m.i();
			
			hashResults[0] = hashResults[0] ^ srol(sequence.charAt(i), kmerSize-i-1);
			
		}
		
		return hashResults;
		
	}
	
	private long slideHash(String sequence, int kmerSize, long[] hashResults) {
		
		hashResults[1] = srol(hashResults[1]);
		
		for(Block b: blocks) {
			int p = b.start();
			int q = b.end()-1;
			
			hashResults[1] = hashResults[1] ^ srol(sequence.charAt(p),kmerSize-p-1) ^ srol(sequence.charAt(q),kmerSize-q);
		}
		
		hashResults[0] = hashResults[1];
		
		for(Monomer m: monos) {
			int i = m.i();
			
			hashResults[0] = hashResults[0] ^ srol(sequence.charAt(i),kmerSize-i-1);
		}
		
		return hashResults[0];
	}
	
	private long srol(char c, int i) {
		long kmer = 0;
		
		switch(c) {
		case 'A':
			kmer = look[0][i];
			break;
		case 'C':
			kmer = look[1][i];
			break;
		case 'G':
			kmer = look[2][i];
			break;
		case 'T':
			kmer = look[3][i];
			break;
		case 'N':
			kmer = look[4][i];
			break;
		default:
			IO.println("Fehler: NtHash2TEST: srol");
			break;
		}
		
		return kmer;
	}
	
	private long srol(long curr) {
		
		//Bit 0 mit Bit 32 und Bit 33 mit Bit 63 tauschen und dann shiften
		long m = ((curr & 0x8000000000000000L) >>> 30) | ((curr & 0x100000000L) >>> 32) ;
		
		return ((curr << 1) & 0xFFFFFFFDFFFFFFFFL) | m;
	}
	

}

record Block(int start, int end){
	
}

record Monomer(int i){
	
}
