/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModels;

import entityclasses.Personel;
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
public class TeknikServisTableModel {
    public DefaultTableModel serviceTableModel = new DefaultTableModel();
    private String[] columnNames = {"ID", "Service Name", "Address", "Tel"};
    private static EntityManagerFactory factory;
    EntityManager em;

    public TeknikServisTableModel() {
        factory = Persistence.createEntityManagerFactory("TeknikServis2PU");
        em = factory.createEntityManager();
        em.getTransaction().begin();
    }
    
    public void getServisler(JTable servisTbl){
        clearRows();
        List<TeknikServis> data = em.createNamedQuery("TeknikServis.findAll").getResultList();
        serviceTableModel.setColumnIdentifiers(columnNames);
        for (TeknikServis t : data) {
            serviceTableModel.addRow(
                    new Object[]{t.getSid(), t.getServiceName(), t.getServiceAddress(), t.getServiceTel()});
        }
        servisTbl.setModel(serviceTableModel);
    }
    public void addServis(TeknikServis t, JTable servisTbl){
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        serviceTableModel.addRow(
              new Object[]{t.getSid(), t.getServiceName(), t.getServiceAddress(), t.getServiceTel()});
        servisTbl.setModel(serviceTableModel);
    }
    public void findServis(JRootPane rootPane, String selectedParam, String input, JTable servisTbl){
        List<TeknikServis> tList = null;
        if (selectedParam.equals("ID")) {
            int id = Integer.parseInt(input);
            tList = em.createNamedQuery("TeknikServis.findBy" + selectedParam).setParameter(selectedParam, id).getResultList();
        }else{
            tList = em.createNamedQuery("TeknikServis.findBy" + selectedParam).setParameter(selectedParam, input).getResultList();
        }
        if (tList.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Aranan eleman bulunamadı");
            return;
        }
        clearRows();
        fillTable(servisTbl, tList);
    }
    public void clearRows(){
        if (serviceTableModel.getRowCount() > 0) {
            for (int i = serviceTableModel.getRowCount() - 1; i > -1; i--) {
                serviceTableModel.removeRow(i);
            }
        }
    }
    public void fillTable(JTable servisTbl, List<TeknikServis> data){
        for (TeknikServis t : data) {
            serviceTableModel.addRow(
                    new Object[]{t.getSid(), t.getServiceName(), t.getServiceAddress(), t.getServiceTel()});
        }
        servisTbl.setModel(serviceTableModel);
    }
    public String deleteServis(JTable servisTbl, String id){
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        em.getTransaction().begin();
        TeknikServis t2 = null;
        t2 = em.find(TeknikServis.class, Integer.parseInt(id));
        if (t2 == null) {
            return "Silinecek eleman bulunamadı!";
        }
        em.remove(t2);
        em.getTransaction().commit();
        clearRows();
        getServisler(servisTbl);
        return t2.getServiceName();
    }
}
