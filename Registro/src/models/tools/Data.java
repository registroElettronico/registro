/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.tools;

/**
 *
 * @author studente
 */
public class Data {
    public final int gg;
    public final int mm;
    public final int yyyy;

    public Data(int gg, int mm, int yyyy) {
        this.gg = gg;
        this.mm = mm;
        this.yyyy = yyyy;
    }
    
    public Data(String data) {
        this.yyyy = Integer.parseInt(data.substring(0, 4));
        this.mm = Integer.parseInt(data.substring(5, 7));
        this.gg = Integer.parseInt(data.substring(8, 10));
    }

    @Override
    public String toString() {
        String y = String.valueOf(yyyy);
        String m = String.valueOf(mm);
        String g = String.valueOf(gg);

        y = "0000".substring(y.length()) + y;
        m = "00".substring(m.length()) + m;
        g = "00".substring(g.length()) + g;

        return y + "/" + m + "/" + g;
    }
}
