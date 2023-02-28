package com.app.apisvtes.utils;

public class Util {

    public final static String  caracteresEspeciales        = "ທົດລອງ";

    public final static byte[]  codigoCaracteresEspeciales  = new byte[] {(byte) 0xDC, (byte) 0xFC, (byte) 0xC1, (byte) 0xE1, (byte) 0xC9, (byte) 0xE9,
            (byte) 0xCD, (byte) 0xED, (byte) 0xD3, (byte) 0xF3, (byte) 0xDA, (byte) 0xFA, (byte) 0xD1, (byte) 0xF1, (byte) 0xC5, (byte) 0xC6, (byte) 0xD8,
            (byte) 0xE5, (byte) 0xE6, (byte) 0xF8           };

    public static byte[] stringABytes(String s)
    {
        int i, l, i_especial;
        byte b;
        byte[] b_arr;
        String s_sub;

        if(s == null)
            return null;
        if((l= s.length()) < 1)
            return new byte[0];

        // convertimos a byte carácter por carácter
        b_arr= new byte[l];
        for(i= 0; i < l; i++)
        {
            s_sub= s.substring(i, i + 1);
            i_especial= Util.caracteresEspeciales.indexOf(s_sub);
            if(i_especial < 0)
                b= (s_sub.getBytes())[0];
            else
                b= Util.codigoCaracteresEspeciales[i_especial];
            b_arr[i]= b;
        }

        return b_arr;
    }
}
