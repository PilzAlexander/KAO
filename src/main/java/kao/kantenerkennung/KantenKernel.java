package kao.kantenerkennung;

import com.aparapi.Kernel;
import com.aparapi.Range;

public class KantenKernel extends Kernel{
    private int[] in;
    private int[] out;
    private Range range;
    @Local private int[] r, g, b;


    public KantenKernel(int[] in, int[] out, Range range) {
        this.in = in;
        this.out = out;
        this.range = range;

        int w = range.getLocalSize(0) + 1;
        int h = range.getLocalSize(1) + 2;

    }

    public void run() {
        int x = getGlobalId(0);
        int y = getGlobalId(1);

        // zugriffs index auf pixel
        int index = y * getGlobalSize(0) + x;

        // increment

    }

}
