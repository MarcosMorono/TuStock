/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tu_stock_v_0.pkg1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author marcos
 */
public class Conexion_BD {
    //Atributos.

    static Connection conn = null;

    //Método conexión.
    public static Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Mi_Stock", "root", "root");
            JOptionPane.showMessageDialog(null, "EXITO DE CONEXION CON LA BASE DE DATOS" );

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR DE CONEXION CON LA BASE DE DATOS" + e);
        }
        return conn;

    }

}
