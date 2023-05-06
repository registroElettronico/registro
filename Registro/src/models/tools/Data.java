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
    private final int gg;
    private final int mm;
    private final int yyyy;

    public Data(int gg, int mm, int yyyy) {
        this.gg = gg;
        this.mm = mm;
        this.yyyy = yyyy;
    }
    
    public Data(String data) {
        this.yyyy = Integer.parseInt(data.substring(0, 3));
        this.mm = Integer.parseInt(data.substring(5, 6));
        this.gg = Integer.parseInt(data.substring(8, 9));
    }

    @Override
    public String toString() {
        return "[ " + yyyy + "/" + mm + "/" + gg + "]";
    }
}
