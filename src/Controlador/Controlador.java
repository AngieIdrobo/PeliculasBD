
package Controlador;

import Modelo.Compania;
import Modelo.CompaniaDAO;
import Vista.vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Controlador implements ActionListener{
    CompaniaDAO dao = new CompaniaDAO();
    Compania compania = new Compania();
    vista vista = new vista();
    DefaultTableModel modelo = new DefaultTableModel();

    public Controlador(vista v){
    this.vista = v;
    this.vista.btnlistar.addActionListener(this);
    this.vista.btnagregar.addActionListener(this);
    this.vista.btneditar.addActionListener(this);
    this.vista.btnborrar.addActionListener(this);
    this.vista.btnactualizar.addActionListener(this);
    this.vista.btnnuevo.addActionListener(this);
    
    }

    
    void nuevo(){
        vista.txtidCompania.setText("");
        vista.txtnombreCompania.setText("");
        vista.txtdireccion.setText("");
        vista.txtnombreCompania.requestFocus();
        
    }
    
    void limpiarTabla(){
        for(int i=0; i<vista.tabla.getRowCount(); i++){
            modelo.removeRow(i);
            i=i-1;
        }
    }
    
    public void delete(){
        int fila = vista.tabla.getSelectedRow();
        if (fila ==1){
            JOptionPane.showMessageDialog(vista,"Seleccione una fila");
        }else{
            int id = Integer.parseInt((String) vista.tabla.getValueAt(fila, 0) .toString());
            dao.Delete(id);
            System.out.println("El resultado es: "+ id);
            JOptionPane.showMessageDialog(vista, "Usuario eliminado");
        }
        limpiarTabla();
    }
    
    public void add (){
        String nom = vista.txtnombreCompania.getText();
        String direccion = vista.txtdireccion.getText();
        compania.setNombre(nom);
        compania.setDireccion(direccion);
        int r = dao.agregar(compania);
        if (r == 1){
            JOptionPane.showMessageDialog(vista, "Compañía Agregada con éxito");
        }else{
            JOptionPane.showMessageDialog(vista, "Error");
        }
        limpiarTabla();
    }
    
    public void Actualizar(){
        if(vista.txtidCompania.getText().equals("")){
            JOptionPane.showMessageDialog(vista,"Se sugiere que el usuario seleccione " + 
                    "La opción editar para solucionar el problema");
        }else{
            int id = Integer.parseInt(vista.txtidCompania.getText());
            String nom = vista.txtnombreCompania.getText();
            String direccion = vista.txtdireccion.getText();
            compania.setIdCompania(id);
            compania.setNombre(nom);
            compania.setDireccion(direccion);
            int r = dao.Actualizar(compania);
            if (r == 1){
            JOptionPane.showMessageDialog(vista, "Compañía actualizada con éxito");
        }else{
            JOptionPane.showMessageDialog(vista, "Error");
        }
        limpiarTabla();
            
        }
    }
    
    void centrarCeldas(JTable tabla){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i=0; i<vista.tabla.getColumnCount (); i++){
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
                   
        }

    }    
    public void listar (JTable tabla){
        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Compania> lista = dao.listar();
        Object [] objeto =new Object[3];
        for(int i= 0; i <lista.size();i++){
            objeto[0] = lista.get(i).getIdCompania();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getDireccion();
            modelo.addRow(objeto);
            
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);
    }    
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()== vista.btnlistar){
            limpiarTabla();
            listar(vista.tabla);
            nuevo();    
        }
        if (e.getSource() == vista.btnagregar){
            add();
            listar(vista.tabla);
            nuevo();
        }
        if (e.getSource()== vista.btneditar){
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1){
                JOptionPane.showMessageDialog(vista, "Seleccione una fila");
                          
            }else{
                int id = Integer.parseInt((String)vista.tabla.getValueAt(fila, 0).toString());
                String nom = (String) vista.tabla.getValueAt(fila, 1);
                String direccion = (String) vista.tabla.getValueAt(fila, 2);
                vista.txtidCompania.setText("" + id);
                vista.txtnombreCompania.setText(nom);
                vista.txtdireccion.setText(direccion);
                
            }
        }
        if (e.getSource()== vista.btnactualizar){
            Actualizar();
            listar(vista.tabla);
            nuevo();
        
        }
        if (e.getSource()== vista.btnborrar){
            delete();
            listar(vista.tabla);
            nuevo();
            
        }
        if (e.getSource()== vista.btnnuevo){
            nuevo();
        
        }
        }
    
    
}
