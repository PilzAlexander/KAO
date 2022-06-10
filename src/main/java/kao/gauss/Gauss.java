package kao.gauss;


import com.aparapi.Kernel;
import com.aparapi.Range;

class Gauss {
    public static void main(String[] args) {
        double[] a = { 2, 8, 1,
                4, 4, -1,
                -1, 2, 12 };

        print(a); gauss(a); print(a);
    }

    private static void print(double[] a) {
        int n = (int) Math.round(Math.sqrt(a.length));
        for (int z = 0; z < n; z++) {
            for (int s = 0; s < n; s++)
                System.out.printf("%5.1f ", a[z * n + s]);
            System.out.println();
        }
        System.out.println();
    }

    private static void gauss(double[] a) {
        int n = (int) Math.round(Math.sqrt(a.length));
        double[] c = new double[n]; // Koeffizienten

        class Zeilensubtraktion extends Kernel {
            double[] c;
            int i;

            void setC(double[] c) { this.c = c; }
            void setI(int i) { this.i = i; }

            @Override public void run() {
                int j = getGlobalId(0); // Zeilenindex
                int k = getGlobalId(1); // Spaltenindex
                a[j * n + k] -= c[j] * a[i * n + k];
            }
        }

        Zeilensubtraktion kernel = new Zeilensubtraktion();
        Range range = Range.create2D(kao.Devices.selectDevice(), n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                c[j] = a[j * n + i] / a[i * n + i];
            c[i] = 0; // vermeidet Fallunterscheidung im Kernel
            kernel.setI(i); kernel.setC(c);
            kernel.execute(range);
        }
    }
}