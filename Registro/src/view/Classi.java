/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;



import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import controller.GestoreRegistro;
import java.util.Collections;
import models.Classe;
import models.Insegnante;
import models.Studente;


/**
 *
 * @author dential
 */
public class Classi extends Pagina {

    /**
     * Creates new form Classi
     */
    
    public Classi(GestoreRegistro gest) {
        initComponents();
        gestoreRegistro = gest;
        ArrayList<Classe> classi = ((Insegnante) gestoreRegistro.getUser()).getClassi();    //riceve le classi dell'Insegnante dal gestore

        for (Classe c: classi) {
            choice1.add(c.getSezione());        //riempie il primo menù a tendina con le classe
        }

        choice1.addItemListener(new ItemListener() {        //imposta un gestore degli eventi sul menù a tendina: quando un item è selezionato si attiva itemStateChanged
            @Override
            public void itemStateChanged(ItemEvent e) {
                list1.removeAll();    //svuota il secondo menù a tendina
                ArrayList<Studente> studenti = gestoreRegistro.getClasse(choice1.getSelectedItem()).getStudenti(); //gli studenti della data classe

                Collections.sort(studenti);

                for (Studente s: studenti) {      //riempie il menù a tendina con i nomi degli studenti
                    list1.add(s.getNome() + " " + s.getCognome() + " " + s.getDataDiNascita() );
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        choice1 = new java.awt.Choice();
        jLabel1 = new javax.swing.JLabel();
        list1 = new java.awt.List();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Classe");

        jButton1.setText("Indietro");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 90, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(90, 90, 90))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jButton1)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        RegistroInsegnante reg = new RegistroInsegnante(gestoreRegistro);
        reg.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Choice choice1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private java.awt.List list1;
    // End of variables declaration//GEN-END:variables
}
