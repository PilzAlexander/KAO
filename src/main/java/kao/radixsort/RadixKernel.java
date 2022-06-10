package kao.radixsort;

import com.aparapi.Kernel;
import com.aparapi.Range;

public class RadixKernel extends Kernel {
    private int[] input, output, flags, sums;
    private int offset;

    void setParameters(int[] input, int[] output,
                       int[] flags, int[] sums, int offset) {
        this.input = input;
        this.output = output;
        this.flags = flags;
        this.sums = sums;
        this.offset = offset;
    }

    @Override
    public void run() {
        int i = getGlobalId(0);
        if (flags[i] == 1)
            output[sums[i] - 1] = input[i];
        else
            output[i - sums[i] + offset] = input[i];
    }
}
