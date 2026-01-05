package app.HIBFDir;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import com.clearspring.analytics.stream.cardinality.CardinalityMergeException;
import com.clearspring.analytics.stream.cardinality.HyperLogLog;

import app.records.Alphabet;
import app.records.Genom;
import app.records.HashObject;
import hashFunctionCollection.HashFunction;
import hashFunctionCollection.methods.BoundedInteger;
import hashFunctionCollection.methods.ToHash;

public class HIBFCreator {

	public static void create(ArrayList<ArrayList<Genom>> genomList, int kmerSize, ArrayList<HashFunction> hfList,
			Alphabet alphabet) {

		int[] capArr = new int[genomList.size()];
		ArrayList<HashObject> hashes = new ArrayList<>();

		// i = tech bins, j = user bins
		int i = calcTBMax(genomList.size());
		int j = genomList.size();
		int tMax = i;

		// M = tracks the maximum technical bin size
		// L = tracks the space consumption estimate of all lower level IBFs that need
		// to be
		// created for each merged bin given the structure imposed by M
		double[][] M = new double[i][j];
		double[][] L = new double[i][j];

		double falsPositiveRate = 0.01;

		// Berechnen der caps
		for (int x = 0; x < genomList.size(); x++) {

			ArrayList<Genom> genom = genomList.get(x);

			int n = 0;

			for (Genom g : genom) {
				n = n + g.sequence().length - kmerSize + 1;
			}

			capArr[x] = BoundedInteger.newCap(n);
		}

		// Berechnen der Hashes
		for (int x = 0; x < genomList.size(); x++) {

			ArrayList<Genom> genom = genomList.get(x);

			ArrayList<Integer> intList = new ArrayList<>();

			for (Genom g : genom) {

				switch (alphabet) {
				case Alphabet.DNA -> {
					if (g.sequence().length < 30)
						continue;
				}
				default -> {
					if (g.sequence().length < 5)
						continue;
				}
				}

				for (HashFunction hashFc : hfList) {

					int[] intArr = (ToHash.hash(g, hashFc, capArr[x]));

					for (int currInt : intArr) {
						intList.add(currInt);
					}
				}

			}

			hashes.add(new HashObject(intList, capArr[x]));
		}

		// HyperLogLog sketches
		HyperLogLog[] HLLs = new HyperLogLog[hashes.size()];

		for (int x = 0; x < hashes.size(); x++) {
			HyperLogLog temp = new HyperLogLog(14);

			for (int currInt : hashes.get(x).hash()) {
				temp.offer(currInt);
			}

			HLLs[x] = temp;
		}

		// Sortieren nach groesse
		//hashes.sort((HashObject h1, HashObject h2) -> Integer.compare(h2.hash().size(), h1.hash().size()));

		// Init M[i][0]
		for (int currI = 0; currI < i; currI++) {

			double temp = (HLLs[0].cardinality() / (currI + 1));

			M[currI][0] = temp * fCorrection(currI + 1, falsPositiveRate, hfList.size());
		}

		// Init L[i][0]
		for (int currI = 0; currI < i; currI++) {
			L[currI][0] = 0.0;
		}

		// Init M[0][j]
		for (int currJ = 1; currJ < j; currJ++) {
			M[0][currJ] = calcU(HLLs, currJ);
		}

		// Init L[0][j]

		for (int currJ = 0; currJ < j; currJ++) {

		    int l = (int) Math.ceil(
		                Math.log(currJ + 1) / Math.log(tMax)
		            );

		    int ubCount = currJ + 1;

		    L[0][currJ] = l * ubCount;
		}

		IO.println(Math.log(j + 1));
		
		print(M);
		IO.println("------------------------");
		print(L);

	}

	private static double calcU(HyperLogLog[] HLLs, int j) {

		double U0j = 0;

		try {
			HyperLogLog tempUnion = HyperLogLog.Builder.build(HLLs[0].getBytes());

			for (int currJ = 1; currJ < j; currJ++) {
				tempUnion.addAll(HLLs[currJ]);
			}

			U0j = tempUnion.cardinality();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CardinalityMergeException e) {
			e.printStackTrace();
		}

		return U0j;
	}

	// s = anzahl der tech bins in welche wir einen user bin spalten
	// h = anzahl der hashfunktionen
	private static double fCorrection(int s, double falsePos, int h) {

		double pCorr = 1.0 - Math.pow((1 - falsePos), 1.0 / s);

		double temp1 = Math.log(1 - Math.pow(falsePos, 1.0 / h));
		double temp2 = Math.log(1 - Math.pow(pCorr, 1.0 / h));

		return temp1 / temp2;
	}

	private static int calcTBMax(int UserBinsSize) {

		return (int) (Math.ceil(Math.sqrt(UserBinsSize) / 64.0) * 64.0);
	}

	private static long expandToLong(int h) {
		long x = h & 0xffffffffL;
		x ^= x >>> 33;
		x *= 0xff51afd7ed558ccdL;
		x ^= x >>> 33;
		x *= 0xc4ceb9fe1a85ec53L;
		x ^= x >>> 33;
		return x;
	}

	private static void print(double[][] Matrix) {

		for (int i = 0; i < Matrix.length; i++) {
			for (int j = 0; j < Matrix[0].length; j++) {
				IO.print(Matrix[i][j] + "|");
			}
			IO.println();
		}

	}

}
