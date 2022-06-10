package kao.matrixvektormultiplikation;

import com.aparapi.Kernel;
import com.aparapi.Range;
import kao.Devices;

import java.util.Arrays;
import java.util.Random;

class MatrixVektor {
    static final int M = 1 << 12, N = 1 << 14;

    public static void main(String[] args) {
        int[] A = new Random().ints(M * N).toArray();
        int[] x = new Random().ints(N).toArray();
        int[] y = new int[M];

        Range range = Range.create(Devices.selectDevice(), M);

        Kernel k = new MatrixVektorKernel(A, x, y);

        k.execute(range);

    }
}