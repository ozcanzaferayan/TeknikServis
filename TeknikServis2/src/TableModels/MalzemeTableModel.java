/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModels;

import entityclasses.Malzeme;
import entityclasses.TeknikServis;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author -ZaferAYAN-
 */
public class MalzemeTableModel {
    public DefaultTableModel malzemeTableModel = new DefaultTableModel();
    private String[] columnNames = {"ID", "Malzeme Adı", "Malzeme Fiyatı", "Üretici", "Iskonto", "Döviz"};
    private static EntityManagerFactory factory;
    EntityManager em;

    public MalzemeTableModel() {
        factory = Persistence.createEntityManagerFactory("TeknikServis2PU");
        em = factory.createEntityManager();
        em.getTransaction().begin();
    }
    
    public void getMalzemeler(JTable malzemeTbl){
        clearRows();
        List<Malzeme> data = em.createNamedQuery("Malzeme.findAll").getResultList();
        malzemeTableModel.setColumnIdentifiers(columnNames);
        for (Malzeme m : data) {
            malzemeTableModel.addRow(
                    new Object[]{m.getMid(), m.getMalzemeName(), m.getMalzemeFiyat(), m.getUretici(), m.getIskonto(), m.getFiyatTuru()});
        }
        malzemeTbl.setModel(malzemeTableModel);
    }
    public void addMalzeme(Malzeme m, JTable malzemeTbl){
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        em.getTransaction().begin();
        em.persist(m);
        em.getTransaction().commit();
        malzemeTableModel.addRow(
              new Object[]{m.getMid(), m.getMalzemeName(), m.getMalzemeFiyat(), m.getUretici(), m.getIskonto(), m.getFiyatTuru()});
        malzemeTbl.setModel(malzemeTableModel);
    }
    public void findMalzeme(JRootPane rootPane, String selectedParam, String input, JTable malzemeTbl){
        List<Malzeme> mList = null;
        if (selectedParam.equals("ID")) {
            int id = Integer.parseInt(input);
            mList = em.createNamedQuery("Malzeme.findBy" + selectedParam).setParameter(selectedParam, id).getResultList();
        }else{
            if (selectedParam.equals("Fiyat")){
                mList = em.createNamedQuery("Malzeme.findBy" + selectedParam).setParameter(selectedParam, Double.parseDouble(input)).getResultList();
            }
            else if(selectedParam.equals("Iskonto")){
                mList = em.createNamedQuery("Malzeme.findBy" + selectedParam).setParameter(selectedParam, Double.parseDouble(input)).getResultList();
            }else{
                mList = em.createNamedQuery("Malzeme.findBy" + selectedParam).setParameter(selectedParam, input).getResultList();
            }
            
        }
        if (mList.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Eleman bulunamadı!");
        }
        clearRows();
        fillTable(malzemeTbl, mList);
    }
    public void clearRows(){
        if (malzemeTableModel.getRowCount() > 0) {
            for (int i = malzemeTableModel.getRowCount() - 1; i > -1; i--) {
                malzemeTableModel.removeRow(i);
            }
        }
    }
    public void fillTable(JTable malzemeTbl, List<Malzeme> data){
        for (Malzeme m : data) {
            malzemeTableModel.addRow(
                    new Object[]{m.getMid(), m.getMalzemeName(), m.getMalzemeFiyat(), m.getUretici(), m.getIskonto(), m.getFiyatTuru()});
        }
        malzemeTbl.setModel(malzemeTableModel);
    }
    public String deleteMalzeme(JTable malzemeTbl, String id){
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        em.getTransaction().begin();
        Malzeme t2 = null;
        t2 = em.find(Malzeme.class, Integer.parseInt(id));
        if (t2 == null) {
            return "Silinecek eleman bulunamadı!";
        }
        em.remove(t2);
        em.getTransaction().commit();
        clearRows();
        getMalzemeler(malzemeTbl);
        return t2.getMalzemeName();
    }
}
