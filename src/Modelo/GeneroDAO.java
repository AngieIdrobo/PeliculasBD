
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class GeneroDAO {
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Genero g = new Genero();
    
    public List listar(){
        List<Genero> datos = new ArrayList <>();
        try{
            con = conectar.getConnection();
            ps = con.prepareStatement("Select * from genero");
            rs = ps.executeQuery();
            while(rs.next()){
                Genero p = new Genero();
                p.setIdGenero(rs.getInt(1));
                p.setNombre(rs.getString(2));
                datos.add(p);
            }
              
        }catch(Exception e){
        
        }
        return datos;
    }
    
    public int agregar (Genero per){
        int r = 0;
        String sql = "insert into genero(idGenero, nombre)Values(?,?)";
        
        try{
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, per.getIdGenero());
            ps.setString(2, per.getNombre());
            r = ps.executeUpdate();
            if (r == 1){
                return 1;
            }else{
                return 0;
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        return r;
    }  
    
    public int Actualizar (Genero per){
        int r = 0;
        String sql = "update genero set nombre=? where idGenero=?";
        try{
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, per.getNombre());
            ps.setInt(2, per.getIdGenero());
            r = ps.executeUpdate();
            if (r == 1){
                return 1;
            }else{
                return 0;
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        return r;
    }
    public int Delete (int idGenero){
        int r=0;
        String sql = "delete from genero where idGenero=" + idGenero;
        
        try{
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();        
        }catch (Exception e){
            System.out.println(e);
        }
        return r;
    }
}
