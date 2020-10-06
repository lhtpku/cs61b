package hw3.hash;

import java.util.List;
//import java.util.Collections;
import java.util.Arrays;
public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /*
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        double high = oomages.size() / 2.5;
        double low = oomages.size() / 50;
        int[] buckets = new int[M];
        for (Oomage t : oomages) {
            buckets[(t.hashCode() & 0x7FFFFFFF) % M] += 1;
        }

        if (Arrays.stream(buckets).min().getAsInt()< low || Arrays.stream(buckets).max().getAsInt()>high) {
            return false;
        }
        return true;
    }
}
