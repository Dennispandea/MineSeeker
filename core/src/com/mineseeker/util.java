package com.mineseeker;

import java.text.DecimalFormat;

public class util {

    public static String memUsageCalc(long size) {
        String memCalc = null;

        double b = size;
        double k = size/1024.0;
        double m = ((size/1024.0)/1024.0);
        double g = (((size/1024.0)/1024.0)/1024.0);

        DecimalFormat dec = new DecimalFormat("0.00");

        if ( g>1 ) {
            memCalc = dec.format(g).concat(" GB");
        } else if ( m>1 ) {
            memCalc = dec.format(m).concat(" MB");
        } else if ( k>1 ) {
            memCalc = dec.format(k).concat(" KB");
        } else {
            memCalc = dec.format(b).concat(" Bytes");
        }

        return memCalc;
    }
}
