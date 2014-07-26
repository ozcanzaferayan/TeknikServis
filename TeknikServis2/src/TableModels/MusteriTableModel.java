/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModels;

import entityclasses.Musteri;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author -ZaferAYAN-
 */
public class MusteriTableModel {
    public DefaultTableModel musteriTableModel = new DefaultTableModel();
    private String[] columnNames = {"ID", "First Name", "Last Name", "Birth Date", "Gender", "Job"};
    private static EntityManagerFactory factory;
    EntityManager em;

    public MusteriTableModel() {
        factory = Persistence.createEntityManagerFactory("TeknikServis2PU");
        em = factory.createEntityManager();
        em.getTransaction().begin();
    }
    
    public void getMusteriler(JTable musterilertbl){
        clearRows();
        List<Musteri> data = em.createNamedQuery("Musteri.findAll").getResultList();
        musteriTableModel.setColumnIdentifiers(columnNames);
        for (Musteri m : data) {
            musteriTableModel.addRow(
                    new Object[]{m.getId(), m.getFName(), m.getLName(), m.getBDate(), m.getGender(), m.getJob()});
        }
        musterilertbl.setModel(musteriTableModel);
    }
    public void addMusteri(Musteri m, JTable musterilertbl){
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        em.getTransaction().begin();
        em.persist(m);
        em.getTransaction().commit();
        
        
        musteriTableModel.addRow(
              new Object[]{m.getId(), m.getFName(), m.getLName(), m.getBDate(), m.getGender(), m.getJob()});
        musterilertbl.setModel(musteriTableModel);
    }
    public void findMusteri(JRootPane rootPane, String selectedParam, String input, JTable musterilertbl){
        List<Musteri> mList = null;
        if (selectedParam.equals("ID")) {
            int id = Integer.parseInt(input);
            mList = em.createNamedQuery("Musteri.findBy" + selectedParam).setParameter(selectedParam, id).getResultList();
        }
        else {
            mList = em.createNamedQuery("Musteri.findBy" + selectedParam).setParameter(selectedParam, input).getResultList();
        }
        if (mList.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Aranan eleman bulunamadı");
            return;
        }
        clearRows();
        fillTable(musterilertbl, mList);
    }
    public Musteri findByMusteriFnameLname(String ad, String soyad){
        Musteri m = null;
        m = (Musteri) em.createNamedQuery("Musteri.findByAdSoyad").setParameter("Ad", ad).setParameter("Soyad", soyad).getSingleResult();
        return m;
    }
    
    public List<String> findByMusteriFname(String input){
        List<Musteri> mList = null;
        List<String> lastNames = new ArrayList<String>();
        mList = em.createNamedQuery("Musteri.findBy" + "Ad").setParameter("Ad", input).getResultList();
        for (Musteri m : mList) {
            lastNames.add(m.getLName());
        }
        return lastNames;
    }
    public void clearRows(){
        if (musteriTableModel.getRowCount() > 0) {
            for (int i = musteriTableModel.getRowCount() - 1; i > -1; i--) {
                musteriTableModel.removeRow(i);
            }
        }
    }
    public void fillTable(JTable musterilertbl, List<Musteri> data){
        for (Musteri m : data) {
            musteriTableModel.addRow(
                    new Object[]{m.getId(), m.getFName(), m.getLName(), m.getBDate(), m.getGender(), m.getJob()});
        }
        musterilertbl.setModel(musteriTableModel);
    }
    public String deleteMusteri(JTable musteritbl, String id){
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        em.getTransaction().begin();
        Musteri m2 = null;
        m2 = em.find(Musteri.class, Integer.parseInt(id));
        if (m2 == null) {
            return "Silinecek eleman bulunamadı!";
        }
        em.remove(m2);
        em.getTransaction().commit();
        clearRows();
        getMusteriler(musteritbl);
        return m2.getFName();
    }
}
