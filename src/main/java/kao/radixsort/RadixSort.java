package kao.radixsort;

import com.aparapi.Kernel;
import com.aparapi.Range;

import java.util.Arrays;

public class RadixSort {

    private static final int N = 1 << 5;
    static int bitNr;
    private static int[] input = new java.util.Random()
            .ints(N, 0, Integer.MAX_VALUE).toArray();
    private static int[] output = new int[N];
    private static int[] flags = new int[N];
    private static int[] sums = new int[N];

    private static int[] flags1 = new int[N];
    private static int[] sums1 = new int[N];

    public static void main(String[] args) {

        Range range = Range.create(kao.Devices.selectDevice(), N);
        RadixKernel kernel = new RadixKernel();

        for (bitNr = 0; bitNr < 31; bitNr++) {
            Arrays.parallelSetAll(flags,
                    i -> 1 - (input[i] >> bitNr) % 2);
            sums = flags.clone();
            Arrays.parallelPrefix(sums, Integer::sum);
        }

        for (int j = 0; j < 31; j++) {
            for (int i = 0; i < N; i++) {

                if ((input[i] & (1 << j)) == 0) {
                    flags1[i] = 1;
                } else {
                    flags1[i] = 0;
                }
            }

            for (int k = 0; k < N; k++) {
                if (k == 0) {
                    sums1[k] = flags1[k];

                } else {
                    sums1[k] = sums1[k - 1] + flags1[k];
                }
            }

            kernel.setParameters(input, output, flags, sums, sums[N-1]);
            kernel.execute(range);
            input = output.clone();
        }

        System.out.println("FLAGS" + Arrays.toString(flags));
        System.out.println("SUMS" + Arrays.toString(sums));

        System.out.println("FLAGS1" + Arrays.toString(flags1));
        System.out.println("SUMS1" + Arrays.toString(sums1));

    }
}