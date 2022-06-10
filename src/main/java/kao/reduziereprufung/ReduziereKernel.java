package kao.reduziereprufung;

import com.aparapi.Kernel;

import static kao.reduziereprufung.Reduziere.F;
import static kao.reduziereprufung.Reduziere.N;

public class ReduziereKernel extends Kernel {
        public final int[] in;
        public final float[] out;

        public ReduziereKernel(int[] in, float[] out) {
            this.in = in;
            this.out = out;
        }

        @Override
        public void run() {
            int id = getGlobalId(0);

        for(int j = 0; j < N*N ; j+=N) {
            for (int i = j; i < F; i++) {
                out[id] += in[i + id * F];
                out[id] += in[(id * F) + i + N];
            }
        }
            out[id] = out[id] / (F * F);
        }
    }

