package app.records;

import java.util.ArrayList;
import java.util.BitSet;

public record IBFObject(String name, BitSet vector, int cap, ArrayList<String> IDs, int filterCount) {

}
