/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModels;

import entityclasses.Islem;
import java.util.Date;
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
public class IslemTableModel {
    public DefaultTableModel islemTableModel = new DefaultTableModel();
    private String[] columnNames = {"ID", "Musteri Adı", "Müşteri Soyadı", "Tarih", "Yapılan İşlem", "Odenen", "Döviz Türü"};
    private static EntityManagerFactory factory;
    EntityManager em;

    public IslemTableModel() {
        factory = Persistence.createEntityManagerFactory("TeknikServis2PU");
        em = factory.createEntityManager();
        em.getTransaction().begin();
    }
    
    public void getIslemler(JTable islemTbl){
        clearRows();
        List<Islem> data = em.createNamedQuery("Islem.findAll").getResultList();
        islemTableModel.setColumnIdentifiers(columnNames);
        for (Islem i : data) {
            islemTableModel.addRow(
                    new Object[]{i.getId(), i.getMusteriID().getFName(), i.getMusteriID().getLName() , i.getTarih(), i.getYapilanIslem(), i.getOdenen(), i.getDovizTuru().getDovizName()});
        }
        islemTbl.setModel(islemTableModel);
    }
    public void addIslem(Islem i, JTable islemTbl){
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        em.getTransaction().begin();
        em.persist(i);
        em.getTransaction().commit();
        islemTableModel.addRow(
              new Object[]{i.getId(), i.getMusteriID().getFName(), i.getMusteriID().getLName() , i.getTarih(), i.getYapilanIslem(), i.getOdenen(), i.getDovizTuru().getDovizName()});
        islemTbl.setModel(islemTableModel);
    }
    public void findIslem(JRootPane rootPane, String selectedParam, String input, JTable islemTbl){
        List<Islem> iList = null;
        if (selectedParam.equals("ID")) {
            int id = Integer.parseInt(input);
            iList = em.createNamedQuery("Islem.findBy" + selectedParam).setParameter(selectedParam, id).getResultList();
        }else{
            if (selectedParam.equals("Fiyat")){
                iList = em.createNamedQuery("Islem.findBy" + selectedParam).setParameter(selectedParam, Double.parseDouble(input)).getResultList();
            }
            else if(selectedParam.equals("Iskonto")){
                iList = em.createNamedQuery("Islem.findBy" + selectedParam).setParameter(selectedParam, Double.parseDouble(input)).getResultList();
            }else{
                iList = em.createNamedQuery("Islem.findBy" + selectedParam).setParameter(selectedParam, input).getResultList();
            }
            
        }
        if (iList.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Eleman bulunamadı!");
        }
        clearRows();
        fillTable(islemTbl, iList);
    }
    public void clearRows(){
        if (islemTableModel.getRowCount() > 0) {
            for (int i = islemTableModel.getRowCount() - 1; i > -1; i--) {
                islemTableModel.removeRow(i);
            }
        }
    }
    public void fillTable(JTable islemTbl, List<Islem> data){
        for (Islem i : data) {
            islemTableModel.addRow(
                    new Object[]{i.getId(), i.getMusteriID().getFName(), i.getMusteriID().getLName() , i.getTarih(), i.getYapilanIslem(), i.getOdenen(), i.getDovizTuru().getDovizName()});
        }
        islemTbl.setModel(islemTableModel);
    }
    
    public List<Islem> findIslemler(JRootPane rootPane, JTable islemTbl, Date tarih1, Date tarih2){
        List<Islem> iList = null;
        iList = em.createNamedQuery("Islem.findByTarihler").setParameter("tarih1", tarih1).setParameter("tarih2", tarih2).getResultList();
        if (iList.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Eleman bulunamadı!");
        }
        else{
            clearRows();
            fillTable(islemTbl, iList);
        }
        return iList;
    }
    public String deleteIslem(JTable islemTbl, String id){
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        em.getTransaction().begin();
        Islem t2 = null;
        t2 = em.find(Islem.class, Integer.parseInt(id));
        if (t2 == null) {
            return "Silinecek eleman bulunamadı!";
        }
        em.remove(t2);
        em.getTransaction().commit();
        clearRows();
        getIslemler(islemTbl);
        return t2.getYapilanIslem();
    }
}
