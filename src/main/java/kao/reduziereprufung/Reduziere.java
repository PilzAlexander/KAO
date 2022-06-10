package kao.reduziereprufung;

import com.aparapi.Kernel;
import com.aparapi.Range;

import java.util.Arrays;

public class Reduziere {

    static final int N = 6;
    static final int F = 2; // N % F == 0


    public static void main(String[] args) {
        //var in = new Random().ints(N * N, 1, 10).toArray();
        int[] in = {8, 4, 2, 7, 5, 8, 4, 4, 6, 8, 5, 5, 4, 5, 1, 4, 5, 5, 4, 2, 5, 7, 2, 9, 7, 6, 6, 1, 5, 4, 5, 9, 1, 5, 3, 1};
        var out = new float[N / F * N / F];

        reduziere(in, out);

        System.out.println(Arrays.toString(out));

    }

    static void reduziere(int[] in, float[] out) {

        ReduziereKernel k = new ReduziereKernel(in, out);
        Range range = Range.create(kao.Devices.selectDevice(), (N / F) * (N / F));
        k.execute(range);

    }


}


