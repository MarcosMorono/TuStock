/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tu_stock_v_0.pkg1;

import com.sun.xml.internal.fastinfoset.algorithm.IntegerEncodingAlgorithm;
import java.awt.HeadlessException;
import static java.lang.Math.log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static tu_stock_v_0.pkg1.Conexion_BD.conectar;
import static tu_stock_v_0.pkg1.Conexion_BD.conn;
import static tu_stock_v_0.pkg1.Grafica.bot_crear;

/**
 *
 * @author marcos
 */
class Acciones_BD {

    static Connection con;
    static int cod;

    public static void AñadirProducto() {

        String cad_nuevoProducto = (String) JOptionPane.showInputDialog(bot_crear, "Introduce el nombre del nuevo producto",
                "NUEVO PRODUCTO", 3, null, null, "Introduce aquí");
        String query;
        query = "INSERT INTO MiStock (Producto) VALUES (?)";

        try {
            con = Conexion_BD.conectar();

            PreparedStatement pst;
            pst = con.prepareStatement(query);
            pst.setString(1, cad_nuevoProducto);
            pst.executeUpdate();

            pst.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Acciones_BD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO AÑADIR PRODUCTO A LA BASE DE DATOS", "ERROR", 0);
        }

    }

    public static int ConseguirCodigo() {
        con = Conexion_BD.conectar();
        String cad_eliminar = (String) JOptionPane.showInputDialog(Grafica.bot_eliminar, "Introduce el nombre del producto a buscar",
                "BUSCAR PRODUCTO", 3, null, null, "Introduce Producto existente");
        String query = "SELECT Codigo FROM MiStock WHERE Producto=" + "'" + cad_eliminar + "'";
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(query);
            ResultSet res = pst.executeQuery(query);
            while (res.next()) {
                Acciones_BD.cod = res.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Acciones_BD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Acciones_BD.cod;
    }

    public static void EliminarProducto() {
        con = Conexion_BD.conectar();
        int cod = ConseguirCodigo();

        try {

            String query = "DELETE FROM MiStock WHERE Codigo=(?)";

            PreparedStatement pst;
            pst = con.prepareStatement(query);
            pst.setInt(1, cod);
            pst.executeUpdate();
            pst.close();
            con.close();
            JOptionPane.showMessageDialog(Grafica.bot_eliminar, "El producto con codigo " + cod + " se ha eliminado", "ELIMINAR PRODUCTO", 1);
            if (cod == 0) {
                JOptionPane.showMessageDialog(bot_crear, "El nombre del Producto introducido no es correcto. Intentelo de nuevo",
                        "ERROR AL INTRODUCIR DATO", 0);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Acciones_BD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR PRODUCTO DE LA BASE DE DATOS", "ERROR", 0);

        }
        Grafica.bot_crear.setEnabled(true);
        Grafica.ven_tabla.setVisible(false);
        Grafica.bot_volver6.setVisible(true);

    }

    public static void RellenarTabla() {

        con = Conexion_BD.conectar();
        String query = "SELECT * FROM MiStock";

        Statement st;
        ResultSet rs;
        try {

            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                Object[] columnas = new Object[5];
                for (int i = 0; i < 5; i++) {
                    columnas[i] = rs.getObject(i + 1);

                }
                Grafica.modelo.addRow(columnas);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Acciones_BD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void limpiar_tabla() {
        for (int i = 0; i < Grafica.tabla_stock.getRowCount(); i++) {
            Grafica.modelo.removeRow(i);
            i -= 1;
        }
    }

    public static void AñadirMinimo() {
        con = Conexion_BD.conectar();
        Acciones_BD.cod = ConseguirCodigo();
        String cad_minimo = (String) JOptionPane.showInputDialog(Grafica.bot_minimo, "Introduce la cantidad mínima con la cual el programa avisará "
                + "para realizar el pedido",
                "CANTIDAD MINIMA", 3, null, null, "Introduce cantidad");
        int minimo = Integer.parseInt(cad_minimo);

        try {

            String query = "UPDATE MiStock SET Mínimo= (" + minimo + ") WHERE Codigo=(?)";

            PreparedStatement pst;
            pst = con.prepareStatement(query);
            pst.setInt(1, Acciones_BD.cod);
            pst.executeUpdate();
            pst.close();
            con.close();

            if (Acciones_BD.cod == 0) {
                JOptionPane.showMessageDialog(Grafica.bot_minimo, "El nombre del Producto introducido no es correcto. Intentelo de nuevo",
                        "ERROR AL INTRODUCIR DATO", 0);
            } else {
                JOptionPane.showMessageDialog(Grafica.bot_minimo, "Se ha añadido cantidad mínima para aviso para el producto con código "
                        + Acciones_BD.cod + "", "CANTIDAD MINIMA ESTABLECIDA", 1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Acciones_BD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO AÑADIR CANTIDAD MINIMA AL PRODUCTO EN LA BASE DE DATOS", "ERROR", 0);

        }
        Grafica.ven_tabla.setVisible(false);
        Grafica.bot_volver6.setVisible(true);

    }

    public static void AñadirCantidad() {
        con = Conexion_BD.conectar();
        Acciones_BD.cod = ConseguirCodigo();
        JOptionPane.showMessageDialog(bot_crear, Acciones_BD.cod);
        String cad_deseada = (String) JOptionPane.showInputDialog(Grafica.bot_minimo, "Introduce la cantidad total que se desea tener en almacén "
                + "después de realizar el pedido",
                "CANTIDAD DESEADA", 3, null, null, "Introduce cantidad");
        int deseada = Integer.parseInt(cad_deseada);
        JOptionPane.showMessageDialog(bot_crear, deseada);

        try {

            String query = "UPDATE MiStock SET Deseado= (" + deseada + ") WHERE Codigo=(?)";

            PreparedStatement pst;
            pst = con.prepareStatement(query);
            pst.setInt(1, Acciones_BD.cod);
            pst.executeUpdate();
            pst.close();
            con.close();

            if (Acciones_BD.cod == 0) {
                JOptionPane.showMessageDialog(Grafica.bot_minimo, "El nombre del Producto introducido no es correcto. Intentelo de nuevo",
                        "ERROR AL INTRODUCIR DATO", 0);
            } else {
                JOptionPane.showMessageDialog(Grafica.bot_minimo, "Se ha añadido cantidad deseada para realizar el pedido del producto con código "
                        + Acciones_BD.cod + "", "CANTIDAD DESEADA ESTABLECIDA", 1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Acciones_BD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO AÑADIR CANTIDAD DESEADA AL PRODUCTO EN LA BASE DE DATOS", "ERROR", 0);

        }
        Grafica.ven_tabla.setVisible(false);
        Grafica.bot_volver6.setVisible(true);
    }

    public static void invetario() {
        con = Conexion_BD.conectar();
        Acciones_BD.cod = ConseguirCodigo();
        JOptionPane.showMessageDialog(bot_crear, Acciones_BD.cod);
        String cad_inventario = (String) JOptionPane.showInputDialog(Grafica.bot_inventario, "Introduce la cantidad que hay en almacén actualmente",
                "CANTIDAD EN ALMACEN", 3, null, null, "Introduce cantidad");
        int inventario = Integer.parseInt(cad_inventario);
        JOptionPane.showMessageDialog(bot_crear, inventario);

        try {

            String query = "UPDATE MiStock SET Cantidad= (" + inventario + ") WHERE Codigo=(?)";

            PreparedStatement pst;
            pst = con.prepareStatement(query);
            pst.setInt(1, Acciones_BD.cod);
            pst.executeUpdate();
            pst.close();
            con.close();

            if (Acciones_BD.cod == 0) {
                JOptionPane.showMessageDialog(Grafica.bot_minimo, "El nombre del Producto introducido no es correcto. Intentelo de nuevo",
                        "ERROR AL INTRODUCIR DATO", 0);
            } else {
                JOptionPane.showMessageDialog(Grafica.bot_minimo, "Se ha añadido cantidad existente actualmente par el producto con código "
                        + Acciones_BD.cod + "", "CANTIDAD EXISTENTE ESTABLECIDA", 1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Acciones_BD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO AÑADIR CANTIDAD EXISTENTE AL PRODUCTO EN LA BASE DE DATOS", "ERROR", 0);

        }
        Grafica.ven_tabla.setVisible(false);
        Grafica.bot_volver6.setVisible(true);
    }

    public static int obtenerCantidad() {

        con = Conexion_BD.conectar();

        int cantidad = 0;
        int cod= ConseguirCodigo();
        String query = "SELECT Cantidad FROM MiStock WHERE Codigo = ("+cod+")";

        PreparedStatement pst;

        try {
            pst = con.prepareStatement(query);
            ResultSet res = pst.executeQuery(query);
            while (res.next()) {
                cantidad = res.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Acciones_BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(bot_crear, "ESTOY EN CANTIDAD" + cantidad);

        return cantidad;
    }

    public static void accionVentas() {
        con = Conexion_BD.conectar();
        int cantidad = obtenerCantidad();

        String cad_venta = (String) JOptionPane.showInputDialog(Grafica.bot_ventas, "Introduce la cantidad de producto que se ha vendido",
                "VENTAS", 3, null, null, "Introduce cantidad");
        int venta = Integer.parseInt(cad_venta);
        JOptionPane.showMessageDialog(bot_crear, venta);

        JOptionPane.showMessageDialog(bot_crear, cantidad);

        int codi = Acciones_BD.cod;
        JOptionPane.showMessageDialog(bot_crear, "ESTOY EN VENTAS"+codi);

        int res = cantidad - venta;

        JOptionPane.showMessageDialog(bot_crear, res);

        String query = "UPDATE  MiStock SET Cantidad = (" + res + ") where Codigo=(?)";

        PreparedStatement pst;

        try {
            pst = con.prepareStatement(query);
            pst.setInt(1, codi);
            pst.executeUpdate();
            pst.close();
            con.close();
            if (Acciones_BD.cod == 0) {
                JOptionPane.showMessageDialog(Grafica.bot_minimo, "El nombre del Producto introducido no es correcto. Intentelo de nuevo",
                        "ERROR AL INTRODUCIR DATO", 0);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Acciones_BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        Grafica.ven_tabla.setVisible(false);
        Grafica.bot_volver6.setVisible(true);
    }

}
