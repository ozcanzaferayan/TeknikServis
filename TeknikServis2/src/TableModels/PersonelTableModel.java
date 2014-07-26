/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModels;

import entityclasses.Musteri;
import entityclasses.Personel;
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
public class PersonelTableModel {
    public DefaultTableModel personelTableModel = new DefaultTableModel();
    private String[] columnNames = {"ID", "First Name", "Last Name", "Birth Date", "Gender", "Salary", "Nüfus"};
    private static EntityManagerFactory factory;
    EntityManager em;

    public PersonelTableModel() {
        factory = Persistence.createEntityManagerFactory("TeknikServis2PU");
        em = factory.createEntityManager();
        em.getTransaction().begin();
    }
    
    public void getPersoneller(JTable personeltbl){
        clearRows();
        List<Personel> data = em.createNamedQuery("Personel.findAll").getResultList();
        personelTableModel.setColumnIdentifiers(columnNames);
        for (Personel p : data) {
            personelTableModel.addRow(
                    new Object[]{p.getPid(), p.getPFName(), p.getPLName(), p.getPBDate(), p.getPGender(), p.getPSalary(), p.getPNufus()});
        }
        personeltbl.setModel(personelTableModel);
    }
    public void addPersonel(Personel p, JTable personeltbl){
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        personelTableModel.addRow(
              new Object[]{p.getPid(), p.getPFName(), p.getPLName(), p.getPBDate(), p.getPGender(), p.getPSalary(), p.getPNufus()});
        personeltbl.setModel(personelTableModel);
    }
    public void findPersonel(JRootPane rootPane, String selectedParam, String input, JTable personeltbl){
        List<Personel> pList = null;
        if (selectedParam.equals("ID")) {
            int id = Integer.parseInt(input);
            pList = em.createNamedQuery("Personel.findBy" + selectedParam).setParameter(selectedParam, id).getResultList();
        }else{
            if (selectedParam.equals("Maas")){
                pList = em.createNamedQuery("Personel.findBy" + selectedParam).setParameter(selectedParam, Double.parseDouble(input)).getResultList();
            }
            else if(selectedParam.equals("Nufus")){
                pList = em.createNamedQuery("Personel.findBy" + selectedParam).setParameter(selectedParam, Integer.parseInt(input)).getResultList();
            }else{
            pList = em.createNamedQuery("Personel.findBy" + selectedParam).setParameter(selectedParam, input).getResultList();
            }
        }
        if (pList.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Aranan eleman bulunamadı");
            return;
        }
        clearRows();
        fillTable(personeltbl, pList);
    }
    public void clearRows(){
        if (personelTableModel.getRowCount() > 0) {
            for (int i = personelTableModel.getRowCount() - 1; i > -1; i--) {
                personelTableModel.removeRow(i);
            }
        }
    }
    public void fillTable(JTable personeltbl, List<Personel> data){
        for (Personel p : data) {
            personelTableModel.addRow(
                    new Object[]{p.getPid(), p.getPFName(), p.getPLName(), p.getPBDate(), p.getPGender(), p.getPSalary(), p.getPNufus()});
        }
        personeltbl.setModel(personelTableModel);
    }
    public String deletePersonel(JTable personelTbl, String id){
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        em.getTransaction().begin();
        Personel p2 = null;
        p2 = em.find(Personel.class, Integer.parseInt(id));
        if (p2 == null) {
            return "Silinecek eleman bulunamadı!";
        }
        em.remove(p2);
        em.getTransaction().commit();
        clearRows();
        getPersoneller(personelTbl);
        return p2.getPFName();
    }
}
