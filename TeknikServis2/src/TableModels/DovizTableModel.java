/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModels;

import entityclasses.Doviz;
import entityclasses.Malzeme;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author -ZaferAYAN-
 */
public class DovizTableModel {
    public DefaultTableModel dovizTableModel = new DefaultTableModel();
    private String[] columnNames = {"ID", "Döviz Adı", "Döviz Kuru"};
    private static EntityManagerFactory factory;
    EntityManager em;

    public DovizTableModel() {
        factory = Persistence.createEntityManagerFactory("TeknikServis2PU");
        em = factory.createEntityManager();
        em.getTransaction().begin();
    }
    
    public void getDovizler(JTable dovizTable){
        clearRows();
        List<Doviz> data = em.createNamedQuery("Doviz.findAll").getResultList();
        dovizTableModel.setColumnIdentifiers(columnNames);
        for (Doviz d : data) {
            dovizTableModel.addRow(
                    new Object[]{d.getDid(), d.getDovizName(), d.getDovizKur()});
        }
        dovizTable.setModel(dovizTableModel);
    }
    public void addDoviz(Doviz d, JTable dovizTable){
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        em.getTransaction().begin();
        em.persist(d);
        em.getTransaction().commit();
        dovizTableModel.addRow(
               new Object[]{d.getDid(), d.getDovizName(), d.getDovizKur()});
        dovizTable.setModel(dovizTableModel);
    }
    public void findDoviz(String selectedParam, String input, JTable dovizTable){
        List<Doviz> mList = null;
        if (selectedParam.equals("ID")) {
            int id = Integer.parseInt(input);
            mList = em.createNamedQuery("Doviz.findBy" + selectedParam).setParameter(selectedParam, id).getResultList();
        }else if (selectedParam.equals("Kur")){
                mList = em.createNamedQuery("Doviz.findBy" + selectedParam).setParameter(selectedParam, Double.parseDouble(input)).getResultList();
            }
        else{
            mList = em.createNamedQuery("Doviz.findBy" + selectedParam).setParameter(selectedParam, input).getResultList();
        }
        clearRows();
        fillTable(dovizTable, mList);
    }
    public Doviz findDoviz(String input){
        Doviz d = null;
        d = (Doviz) em.createNamedQuery("Doviz.findByAd").setParameter("Ad", input).getSingleResult();
        return d;
    }
    public void clearRows(){
        if (dovizTableModel.getRowCount() > 0) {
            for (int i = dovizTableModel.getRowCount() - 1; i > -1; i--) {
                dovizTableModel.removeRow(i);
            }
        }
    }
    public void fillTable(JTable dovizTable, List<Doviz> data){
        for (Doviz d : data) {
            dovizTableModel.addRow(
                    new Object[]{d.getDid(), d.getDovizName(), d.getDovizKur()});
        }
        dovizTable.setModel(dovizTableModel);
    }
    public String deleteDoviz(JTable dovizTable, String id){
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        em.getTransaction().begin();
        Doviz t2 = null;
        t2 = em.find(Doviz.class, Integer.parseInt(id));
        if (t2 == null) {
            return "Silinecek eleman yok!";
        }
        em.remove(t2);
        em.getTransaction().commit();
        clearRows();
        getDovizler(dovizTable);
        return t2.getDovizName();
    }
}
