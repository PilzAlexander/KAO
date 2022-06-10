package kao.arraypacking;

import com.aparapi.Kernel;
import com.aparapi.Range;


import java.util.Arrays;
import java.util.List;

public class Arraypacking {
    public static void main(String[] args) {

        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        //char[] vocals = {'a', 'e', 'i', 'o', 'u'};
        var vocals = List.of('a', 'e', 'i', 'o', 'u');

        var n = alphabet.length;
        var vokalFlags = new int[n];
        //var vokalSummen = new int[n];

        var output = new char[n];

        Arrays.parallelSetAll(vokalFlags, i -> vocals.contains(alphabet[i]) ? 1 : 0);

        System.out.println("alphabet" + Arrays.toString(alphabet));

        /*for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (alphabet[i] == vocals[j]) {
                    vokalFlags[i] = 1;
                    break;
                } else {
                    vokalFlags[i] = 0;
                }
            }
        }*/

        System.out.println("vokalFlags" + Arrays.toString(vokalFlags));

        var vokalSummen = vokalFlags.clone();
        Arrays.parallelPrefix(vokalSummen, Integer::sum);

        /*for (int i = 0; i < n; i++) {

            if(i == 0) {
                vokalSummen[i] = vokalFlags[i];
            }else {
                vokalSummen[i] = vokalSummen[i-1] + vokalFlags[i];
            }
        }*/

        System.out.println("vokalSummen" + Arrays.toString(vokalSummen));


        Range range = Range.create(kao.Devices.selectDevice(), n);
        Kernel k = new ArraypackingKernel(alphabet, output, vokalFlags, vokalSummen).execute(range);

        System.out.println("output" + Arrays.toString(output));

    }
}
