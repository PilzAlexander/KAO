package kao.arraypacking;

import com.aparapi.Kernel;

public class ArraypackingKernel extends Kernel {
    public final char[] alphabet;
    public final char[] output;
    public final int[] vokalFlags;
    public final int[] vokalSummen;

    public ArraypackingKernel(char[] alphabet, char[] output, int[] vokalFlags, int[] vokalSummen) {
        this.alphabet = alphabet;
        this.output = output;
        this.vokalFlags = vokalFlags;
        this.vokalSummen = vokalSummen;
    }

    @Override
    public void run() {
        int i = getGlobalId(0);

        if(vokalFlags[i] == 1) {
            output[vokalSummen[i] - 1] = alphabet[i];
        }


    }
}
