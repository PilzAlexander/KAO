package kao.matrixvektormultiplikation;

import com.aparapi.Kernel;

import java.util.Arrays;

import static kao.matrixvektormultiplikation.MatrixVektor.N;

public class MatrixVektorKernel extends Kernel {
        public final int[] A, x, y;

        MatrixVektorKernel(int[] A, int[] x, int[] y) {
            this.A = A;
            this.x = x;
            this.y = y;
        }

        @Override
        public void run() {
            int id = getGlobalId(0);
            int summe = 0;

            for (int i = 0; i < N; i++) {
                summe += A[id * N + i] * x[i];
            }
            y[id] = summe;


        }
    }
