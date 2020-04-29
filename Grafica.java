/*
 * Nombre Programa: TU STOCK
 * Version: 0.1
 * Programador: Marcos Morono calvo
 * Fecha inicio: 17/03/2020     Fecha fin:  /03/2020
 */
package tu_stock_v_0.pkg1;

import com.sun.java.swing.plaf.motif.MotifBorders;
import com.sun.scenario.effect.DelegateEffect;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.scene.control.MenuBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import oracle.jrockit.jfr.JFR;
import tu_stock_v_0.pkg1.Conexion_BD;

/**
 *
 * @author marcos
 */
public class Grafica extends JFrame {
    //DECLARACION DE DISTINTOS ELEMENTOS***************************************************************************

    //CONTENEDOR
    public static Container fondo;

    //IMAGENES
    public static Image imagen;

    //STRINGS
    public static String titulo_ventana;
    public static String cad_copyrigth;
    public static String opc_tabla[] = {"Codigo", "Producto", "Cantidad", "Mínimo", "Deseado"};
    public static Object[][] objecto = new Object[0][0];

    //VENTANAS
    public static JFrame ven_principal = new JFrame();
    public static JFrame ven_productos = new JFrame("OPCIONES PRODUCTOS");
    public static JFrame ven_stock = new JFrame("STOCK PRODUCTOS");
    public static JFrame ven_ventas = new JFrame("VENTAS - SALIDAS");
    public static JFrame ven_compras = new JFrame("COMPRAS - ENTRADAS");
    public static JFrame ven_variacion = new JFrame("VARIACION PEDIDO");
    public static JFrame ven_tabla = new JFrame("VISUALIZACION DE STOCK");

    //ETIQUETAS
    public static JLabel titulo, eti_productos, eti_stock, eti_ventas, eti_compras, eti_variacion, eti_tabla;
    public static JLabel copyrigth;

    //PANELES
    public static JPanel p1, p2;

    //SCROLL PANEL
    public static JScrollPane scroll_tabla;

    //TABLA
    public static DefaultTableModel modelo;
    public static JTable tabla_stock;

    //BOTONES MENU PRINCIPAL
    public static JButton productos, stock, ventas, compras, opciones, salir;

    //BOTONES PANTALLA PRODUCTOS
    public static JButton bot_crear, bot_eliminar, bot_inventario, bot_volver1;

    //BOTONES PANTALLA STOCK
    public static JButton bot_ver_stock, bot_minimo, bot_cantidad, bot_volver2;

    //BOTONES PANTALLA VENTAS
    public static JButton bot_ventas, bot_volver3;

    //BOTONES PANTALLA COMPRAS
    public static JButton bot_confirmar, bot_variacion, bot_volver4;

    //BOTONES PANTALLA VARIACION DE PEDIDO
    public static JButton bot_buscar, bot_volver5;

    //BOTONES EN PANTALLA TABLA STOCK
    public static JButton bot_volver6;

    //************************************************************************************************************************
    public Grafica() {

        //STRINGS
        titulo_ventana = "TU STOCK VERSION 0.1";
        cad_copyrigth = "By Tato Software Corporation ®";

        //ETIQUETAS
        titulo = new JLabel(titulo_ventana, 2);
        eti_productos = new JLabel("PRODUCTOS", 2);
        eti_stock = new JLabel("STOCK DE PRODUCTOS", 2);
        eti_ventas = new JLabel("VENTAS - SALIDAS", 2);
        eti_compras = new JLabel("COMPRAS - ENTRADAS", 2);
        eti_variacion = new JLabel("VARIACION PEDIDO", 2);
        eti_tabla = new JLabel("VISUSALIZACION DE STOCK", 2);
        copyrigth = new JLabel(cad_copyrigth, 2);

        //FONDO
        fondo = getContentPane();
        ven_principal.add(fondo);

        //BOTONES PANTALLA PRINCIPAL
        productos = new JButton("PRODUCTOS");
        stock = new JButton("STOCK");
        ventas = new JButton("VENTAS");
        compras = new JButton("COMPRAS");
        opciones = new JButton("OPCIONES");
        salir = new JButton("CERRAR");

        //ELEMENTOS PANTALLA PRINCIPAL******************************************************************************************
        setLayout(new GridLayout(1, 0));
        p1 = new JPanel();
        p2 = new JPanel();
        fondo.add(p1);
        fondo.add(p2);

        //Añadimos elementos y configuramos los paneles
        p1_image.add(productos);
        p1_image.add(stock);
        p1_image.add(ventas);
        p1_image.add(compras);
        p1_image.add(opciones);
        p1_image.add(salir);
        p1_image.add(titulo);

        p1.setBorder(new SoftBevelBorder(5, Color.lightGray, Color.yellow));
        p2.setBorder(new SoftBevelBorder(5, Color.lightGray, Color.yellow));

        p1.add(p1_image);
        p2.add(p2_image);
        p2_image.add(copyrigth);

        p1.setLayout(new GridLayout(1, 0));
        p2.setLayout(new GridLayout(1, 0));
        p1_image.setLayout(null);
        p2_image.setLayout(null);

        //configuracion de etiquetas en p1
        titulo.setBounds(195, 20, 400, 60);
        titulo.setFont(new Font("arial black", 1, 22));
        titulo.setForeground(Color.WHITE);

        //configuracion de etiquetas en p2
        copyrigth.setBounds(265, 650, 500, 60);
        copyrigth.setFont(new Font("comic sans", 3, 22));
        copyrigth.setForeground(Color.BLACK);

        //configuaracion de botones en p1
        productos.setBounds(265, 120, 150, 70);
        productos.setFont(new Font("arial black", 1, 14));
        productos.addActionListener(new AbrirProductos());

        stock.setBounds(265, 220, 150, 70);
        stock.setFont(new Font("arial black", 1, 14));
        stock.addActionListener(new AbrirStock());

        ventas.setBounds(265, 320, 150, 70);
        ventas.setFont(new Font("arial black", 1, 14));
        ventas.addActionListener(new AbrirVentas());

        compras.setBounds(265, 420, 150, 70);
        compras.setFont(new Font("arial black", 1, 14));
        compras.addActionListener(new AbrirCompras());

        opciones.setBounds(115, 560, 150, 70);
        opciones.setFont(new Font("arial black", 1, 14));

        salir.setBounds(415, 560, 150, 70);
        salir.setFont(new Font("arial black", 1, 14));
        salir.addActionListener(new AccionCerrar());
        //*********************************************************************************************************************

        //ELEMENTOS EN PANTALLA TABLA DE STOCK*********************************************************************************
        p8_image.setLayout(null);
        p8_image.add(eti_tabla);
        eti_tabla.setBounds(480, 0, 400, 60);
        eti_tabla.setFont(new Font("arial black", 1, 22));
        eti_tabla.setForeground(Color.WHITE);

        //inicializacion de elementos en pantalla de visualizacion de stock
        bot_volver6 = new JButton("VOLVER");
        p8_image.add(bot_volver6);
        bot_volver6.setBounds(630, 680, 120, 25);
        bot_volver6.setFont(new Font("arial black", 1, 14));
        bot_volver6.addActionListener(new AccionVolver6());

        modelo = new DefaultTableModel(opc_tabla, 0);
        tabla_stock = new JTable(modelo);// en las JTable se debe añadir siempre un JScrollbar para que se visualice correctamente
        tabla_stock.setFont(new Font("arial", 1, 12));
        //cambiar fuente de encabezado de la tabla
        JTableHeader tabla_titulos;
        tabla_titulos = tabla_stock.getTableHeader();
        Font letra_titulo = new Font("arial black", 1, 13);
        tabla_titulos.setFont(letra_titulo);
        //centrar celdas **
        DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
        centrar.setHorizontalAlignment(SwingConstants.CENTER);
        tabla_stock.getColumnModel().getColumn(0).setCellRenderer(centrar);
        tabla_stock.getColumnModel().getColumn(1).setCellRenderer(centrar);
        tabla_stock.getColumnModel().getColumn(2).setCellRenderer(centrar);
        tabla_stock.getColumnModel().getColumn(3).setCellRenderer(centrar);
        tabla_stock.getColumnModel().getColumn(4).setCellRenderer(centrar);
        //****
        scroll_tabla = new JScrollPane(tabla_stock, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        p8_image.add(scroll_tabla);
        scroll_tabla.setBounds(10, 100, 1345, 550);
        tabla_stock.setEnabled(false);
        //hacer transparente la tabla(en realidad es el scrollpane)
        scroll_tabla.getViewport().setOpaque(false);
        scroll_tabla.setBackground(new Color(0, 0, 0, 0));

        //*********************************************************************************************************************
        //ELEMENTOS PANTALLA VARIACION DE PEDIDO*******************************************************************************
        p7_image.setLayout(
                null);
        p7_image.setBorder(
                new EtchedBorder(5));
        p7_image.add(eti_variacion);

        eti_variacion.setBounds(
                90, 1, 400, 60);
        eti_variacion.setFont(
                new Font("arial black", 1, 22));
        eti_variacion.setForeground(Color.WHITE);

        //inicializacion de botones en pantalla variacion de pedido
        bot_variacion = new JButton("BUSCAR PRODUCTO");

        p7_image.add(bot_variacion);

        bot_variacion.setBounds( 110, 60, 240, 45);
        bot_variacion.setFont(
                new Font("arial black", 1, 14));
       // bot_variacion.addActionListener( new AccionBuscar());

        bot_volver5 = new JButton("VOLVER");

        p7_image.add(bot_volver5);

        bot_volver5.setBounds(165, 145, 120, 25);
        bot_volver5.setFont(new Font("arial black", 1, 14));
        bot_volver5.addActionListener(new AccionVolver5());

        //*********************************************************************************************************************
        //ELEMENTOS PANTALLA COMPRAS*******************************************************************************************
        p6_image.setLayout(
                null);
        p6_image.setBorder(
                new EtchedBorder(5));
        p6_image.add(eti_compras);

        eti_compras.setBounds(
                80, 1, 400, 60);
        eti_compras.setFont(
                new Font("arial black", 1, 22));
        eti_compras.setForeground(Color.WHITE);

        //inicilizacion de botones en pantalla compras
        bot_confirmar = new JButton("CONFIRMAR PEDIDO");

        p6_image.add(bot_confirmar);

        bot_confirmar.setBounds(
                110, 60, 240, 45);
        bot_confirmar.setFont(
                new Font("arial black", 1, 14));

        bot_variacion = new JButton("VARIACION ENTREGA");

        p6_image.add(bot_variacion);

        bot_variacion.setBounds(
                110, 125, 240, 45);
        bot_variacion.setFont(
                new Font("arial black", 1, 14));
        bot_variacion.addActionListener(
                new AbrirVariacion());

        bot_volver4 = new JButton("VOLVER");

        p6_image.add(bot_volver4);

        bot_volver4.setBounds(
                165, 195, 120, 25);
        bot_volver4.setFont(
                new Font("arial black", 1, 14));
        bot_volver4.addActionListener(
                new AccionVolver4());

        //*********************************************************************************************************************
        //ELEMENTOS EN PANTALLA VENTAS*****************************************************************************************
        p5_image.setLayout(null);
        p5_image.setBorder(new EtchedBorder(5));
        p5_image.add(eti_ventas);

        eti_ventas.setBounds(97, 1, 400, 60);
        eti_ventas.setFont(new Font("arial black", 1, 22));
        eti_ventas.setForeground(Color.WHITE);

        //inicilización y opciones de botones en pantalla de ventas
        bot_ventas = new JButton("VENTAS - SALIDAS");

        p5_image.add(bot_ventas);

        bot_ventas.setBounds(120, 70, 200, 55);
        bot_ventas.setFont(new Font("arial black", 1, 14));
        bot_ventas.addActionListener(new AccionVentas());

        bot_volver3 = new JButton("VOLVER");

        p5_image.add(bot_volver3);

        bot_volver3.setBounds(160, 165, 120, 25);
        bot_volver3.setFont(new Font("arial black", 1, 14));
        bot_volver3.addActionListener(new AccionVolver3());

        //*********************************************************************************************************************
        //ELEMENTOS EN PANTALLA STOCK******************************************************************************************
        p4_image.setLayout(null);
        p4_image.setBorder(new EtchedBorder(5));
        p4_image.add(eti_stock);

        eti_stock.setBounds(75, 1, 400, 60);
        eti_stock.setFont(new Font("arial black", 1, 22));
        eti_stock.setForeground(Color.WHITE);

        //inicilización y opciones de botones en pantalla de stock
        bot_ver_stock = new JButton("VER STOCK");

        p4_image.add(bot_ver_stock);

        bot_ver_stock.setBounds(60, 100, 140, 55);
        bot_ver_stock.setFont(new Font("arial black", 1, 14));
        bot_ver_stock.addActionListener(new AccionVerStock());

        bot_minimo = new JButton("Nº MINIMO");

        p4_image.add(bot_minimo);

        bot_minimo.setBounds(240, 100, 140, 55);
        bot_minimo.setFont(new Font("arial black", 1, 14));
        bot_minimo.addActionListener(new AccionMinimo());

        bot_cantidad = new JButton("CANTIDAD DESEADA");

        p4_image.add(bot_cantidad);

        bot_cantidad.setBounds(110, 185, 220, 55);
        bot_cantidad.setFont(new Font("arial black", 1, 14));
        bot_cantidad.addActionListener(new AccionCantidad());

        bot_volver2 = new JButton("VOLVER");

        p4_image.add(bot_volver2);

        bot_volver2.setBounds(
                160, 285, 120, 25);
        bot_volver2.setFont(
                new Font("arial black", 1, 14));
        bot_volver2.addActionListener(
                new AccionVolver2());

        //*********************************************************************************************************************
        //ELEMENTOS EN PANTALLA PRODUCTOS**************************************************************************************
        p3_image.setLayout(
                null);
        p3_image.setBorder(
                new EtchedBorder(5));
        p3_image.add(eti_productos);

        eti_productos.setBounds(
                135, 1, 400, 60);
        eti_productos.setFont(
                new Font("arial black", 1, 22));
        eti_productos.setForeground(Color.WHITE);

        //inicialización y opciones de botones en pantalla de productos**********************************************************
        bot_crear = new JButton("NUEVO PRODUCTO");
        p3_image.add(bot_crear);
        bot_crear.setBounds(95, 70, 250, 45);
        bot_crear.setFont(new Font("arial black", 1, 14));
        bot_crear.addActionListener(new NuevoProducto());

        bot_eliminar = new JButton("ELIMINAR PRODUCTO");
        p3_image.add(bot_eliminar);
        bot_eliminar.setBounds(95, 135, 250, 45);
        bot_eliminar.setFont(new Font("arial black", 1, 14));
        bot_eliminar.addActionListener(new AccionEliminar());

        bot_inventario = new JButton("CANTIDAD EN ALMACEN");
        p3_image.add(bot_inventario);
        bot_inventario.setBounds(95, 200, 250, 45);
        bot_inventario.setFont(new Font("arial black", 1, 14));
        bot_inventario.addActionListener(new AccionInventario());

        bot_volver1 = new JButton("VOLVER");
        p3_image.add(bot_volver1);
        bot_volver1.setBounds(160, 285, 120, 25);
        bot_volver1.setFont(new Font("arial black", 1, 14));
        bot_volver1.addActionListener(new AccionVolver1());

        //********************************************************************************************************************
        //PANTALLA PRINCIPAL
        ven_principal.setUndecorated(
                true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        ven_principal.setVisible(
                true);
        ven_principal.setSize(dim);

        ven_principal.setExtendedState(MAXIMIZED_BOTH);

        ven_principal.setTitle(titulo_ventana);

        ven_principal.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //********************************************************************************************************************

        //PANTALLA OPCIONES PRODUCTOS*****************************************************************************************
        ven_productos.add(p3_image);

        ven_productos.setUndecorated(
                true);
        ven_productos.setVisible(
                false);
        ven_productos.setSize(
                450, 350);
        ven_productos.setResizable(
                false);
        ven_productos.setLocationRelativeTo(
                null);
        ven_productos.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //*********************************************************************************************************************

        //PANTALLA STOCK*******************************************************************************************************
        ven_stock.add(p4_image);

        ven_stock.setUndecorated(
                true);
        ven_stock.setVisible(
                false);
        ven_stock.setSize(
                450, 350);
        ven_stock.setResizable(
                false);
        ven_stock.setLocationRelativeTo(
                null);
        ven_stock.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //*********************************************************************************************************************

        //PANTALLA VENTAS*******************************************************************************************************
        ven_ventas.add(p5_image);

        ven_ventas.setUndecorated(
                true);
        ven_ventas.setVisible(
                false);
        ven_ventas.setSize(
                450, 230);
        ven_ventas.setResizable(
                false);
        ven_ventas.setLocationRelativeTo(
                null);
        ven_ventas.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //**********************************************************************************************************************

        //PANTALLA COMPRAS******************************************************************************************************
        ven_compras.add(p6_image);

        ven_compras.setUndecorated(
                true);
        ven_compras.setVisible(
                false);
        ven_compras.setSize(
                450, 260);
        ven_compras.setResizable(
                false);
        ven_compras.setLocationRelativeTo(
                null);
        ven_compras.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //**********************************************************************************************************************

        //PANTALLA VARIACION DE PEDIDOS*****************************************************************************************
        ven_variacion.add(p7_image);

        ven_variacion.setUndecorated(
                true);
        ven_variacion.setVisible(
                false);
        ven_variacion.setSize(
                450, 200);
        ven_variacion.setResizable(
                false);
        ven_variacion.setLocationRelativeTo(
                null);
        ven_variacion.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        //**********************************************************************************************************************
        //PANTALLA TABLA VISUALIZACION DE STOCK
        ven_tabla.add(p8_image);

        ven_tabla.setUndecorated(
                true);
        ven_tabla.setVisible(
                false);
        ven_tabla.setSize(dim);

        ven_tabla.setExtendedState(MAXIMIZED_BOTH);

        //**********************************************************************************************************************
    }
//PANELES CON IMAGEN DE FONDO***********************************************************************************************

//En estas funciones se crean los paneles y se les inserta una imagen para poner como fondos de pantallas*******************
    public JPanel p2_image = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {

            //IMAGENES
            imagen = new ImageIcon("/home/marcos/Documentos/EjerciciosJava/Tu_Stock_V_0.1/dist/img/375719-svetik.jpg").getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

        }
    };

    public JPanel p1_image = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {

            //IMAGENES
            imagen = new ImageIcon("/home/marcos/Documentos/EjerciciosJava/Tu_Stock_V_0.1/dist/img/494628_960_600.jpg").getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

        }
    };
    public JPanel p3_image = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {

            //IMAGENES
            imagen = new ImageIcon("/home/marcos/Documentos/EjerciciosJava/Tu_Stock_V_0.1/dist/img/mosaic-2989079_640.jpg").getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

        }
    };

    public JPanel p4_image = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {

            //IMAGENES
            imagen = new ImageIcon("/home/marcos/Documentos/EjerciciosJava/Tu_Stock_V_0.1/dist/img/mosaic-2989079_640.jpg").getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

        }
    };

    public JPanel p5_image = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {

            //IMAGENES
            imagen = new ImageIcon("/home/marcos/Documentos/EjerciciosJava/Tu_Stock_V_0.1/dist/img/mosaic-2989079_640.jpg").getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

        }
    };

    public JPanel p6_image = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {

            //IMAGENES
            imagen = new ImageIcon("/home/marcos/Documentos/EjerciciosJava/Tu_Stock_V_0.1/dist/img/mosaic-2989079_640.jpg").getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

        }
    };

    public JPanel p7_image = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {

            //IMAGENES
            imagen = new ImageIcon("/home/marcos/Documentos/EjerciciosJava/Tu_Stock_V_0.1/dist/img/mosaic-2989079_640.jpg").getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

        }
    };

    public JPanel p8_image = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {

            //IMAGENES
            imagen = new ImageIcon("/home/marcos/Documentos/EjerciciosJava/Tu_Stock_V_0.1/dist/img/mosaic-2989079_640.jpg").getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

        }
    };
    //******************************************************************************************************************************

    //ACCIONES**********************************************************************************************************************
    public class AccionCerrar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                JOptionPane.showMessageDialog(null, "Saliendo del programa \n       Hasta pronto", "CERRAR PROGRAMA", 1);
                System.exit(0);
            } catch (Exception i) {
                JOptionPane.showMessageDialog(null, "El programa no se puede cerrar", "CERRAR PROGRAMA", 0);

            }
        }

    }

    public class AbrirProductos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ven_productos.setVisible(true);
            productos.setEnabled(true);
            stock.setEnabled(false);
            ventas.setEnabled(false);
            compras.setEnabled(false);
            opciones.setEnabled(false);

        }
    }

    public class AccionVolver1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ven_productos.setVisible(false);
            productos.setEnabled(true);
            stock.setEnabled(true);
            ventas.setEnabled(true);
            compras.setEnabled(true);
            opciones.setEnabled(true);
        }
    }

    public class AbrirStock implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ven_stock.setVisible(true);
            productos.setEnabled(false);
            stock.setEnabled(true);
            ventas.setEnabled(false);
            compras.setEnabled(false);
            opciones.setEnabled(false);
            bot_volver6.setVisible(true);
        }
    }

    public class AccionVolver2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ven_stock.setVisible(false);
            productos.setEnabled(true);
            stock.setEnabled(true);
            ventas.setEnabled(true);
            compras.setEnabled(true);
            opciones.setEnabled(true);
        }
    }

    public class AbrirVentas implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ven_ventas.setVisible(true);
            productos.setEnabled(false);
            stock.setEnabled(false);
            ventas.setEnabled(true);
            compras.setEnabled(false);
            opciones.setEnabled(false);
        }
    }

    public class AccionVolver3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ven_ventas.setVisible(false);
            productos.setEnabled(true);
            stock.setEnabled(true);
            ventas.setEnabled(true);
            compras.setEnabled(true);
            opciones.setEnabled(true);
        }
    }

    public class AbrirCompras implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ven_compras.setVisible(true);
            productos.setEnabled(false);
            stock.setEnabled(false);
            ventas.setEnabled(false);
            compras.setEnabled(true);
            opciones.setEnabled(false);
        }
    }

    public class AccionVolver4 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ven_compras.setVisible(false);
            productos.setEnabled(true);
            stock.setEnabled(true);
            ventas.setEnabled(true);
            compras.setEnabled(true);
            opciones.setEnabled(true);
        }
    }

    public class AbrirVariacion implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ven_variacion.setVisible(true);
            productos.setEnabled(false);
            stock.setEnabled(false);
            ventas.setEnabled(false);
            compras.setEnabled(true);
            opciones.setEnabled(false);
            bot_confirmar.setEnabled(false);
            bot_volver4.setEnabled(false);
        }
    }

    public class AccionVolver5 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ven_variacion.setVisible(false);
            productos.setEnabled(false);
            stock.setEnabled(false);
            ventas.setEnabled(false);
            compras.setEnabled(true);
            opciones.setEnabled(false);
            bot_confirmar.setEnabled(true);
            bot_volver4.setEnabled(true);
        }
    }

//    public class AccionBuscar implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String cad_buscar = new String();
//            cad_buscar = (String) JOptionPane.showInputDialog(bot_buscar, "Introduce el nombre de producto", "BUSCAR PRODUCTO", 3, null, null, "Introduce aqui");
//            JOptionPane.showMessageDialog(null, cad_buscar);
//        }
//    }
    public class AccionVerStock implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ven_tabla.setVisible(true);
            productos.setEnabled(false);
            stock.setEnabled(true);
            ventas.setEnabled(false);
            compras.setEnabled(false);
            opciones.setEnabled(false);
            bot_minimo.setEnabled(false);
            bot_cantidad.setEnabled(false);
            bot_volver2.setEnabled(false);
            //llamada al metodo
            Acciones_BD.limpiar_tabla();
            Acciones_BD.RellenarTabla();

        }
    }

    public class AccionVolver6 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ven_tabla.setVisible(false);
            productos.setEnabled(false);
            stock.setEnabled(true);
            ventas.setEnabled(false);
            compras.setEnabled(false);
            opciones.setEnabled(false);
            bot_minimo.setEnabled(true);
            bot_cantidad.setEnabled(true);
            bot_volver2.setEnabled(true);
        }
    }

    public class NuevoProducto implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //llamamos al metodo
            Acciones_BD.AñadirProducto();
        }

    }

    public class AccionEliminar implements ActionListener {

        @Override

        public void actionPerformed(ActionEvent e) {
            bot_crear.setEnabled(false);
            ven_tabla.setVisible(true);
            productos.setEnabled(true);
            stock.setEnabled(false);
            ventas.setEnabled(false);
            compras.setEnabled(false);
            opciones.setEnabled(false);
            bot_volver1.setEnabled(true);
            bot_volver6.setVisible(false);

            //llamada al metodo
            Acciones_BD.limpiar_tabla();
            Acciones_BD.RellenarTabla();
            Acciones_BD.EliminarProducto();
        }
    }

    public class AccionMinimo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            productos.setEnabled(false);
            stock.setEnabled(true);
            ventas.setEnabled(false);
            compras.setEnabled(false);
            opciones.setEnabled(false);
            bot_volver2.setEnabled(true);
            bot_minimo.setEnabled(true);
            ven_tabla.setVisible(true);
            bot_volver6.setVisible(false);
            //llamada a metodos
            Acciones_BD.limpiar_tabla();
            Acciones_BD.RellenarTabla();
            Acciones_BD.AñadirMinimo();
        }
    }

    public class AccionCantidad implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            productos.setEnabled(false);
            stock.setEnabled(true);
            ventas.setEnabled(false);
            compras.setEnabled(false);
            opciones.setEnabled(false);
            bot_volver2.setEnabled(true);
            bot_minimo.setEnabled(true);
            ven_tabla.setVisible(true);
            bot_volver6.setVisible(false);
            //llamada a metodos
            Acciones_BD.limpiar_tabla();
            Acciones_BD.RellenarTabla();
            Acciones_BD.AñadirCantidad();
        }
    }

    public class AccionInventario implements ActionListener {

        @Override

        public void actionPerformed(ActionEvent e) {
            productos.setEnabled(true);
            stock.setEnabled(false);
            ventas.setEnabled(false);
            compras.setEnabled(false);
            opciones.setEnabled(false);
            bot_volver1.setEnabled(true);
            ven_tabla.setVisible(true);
            bot_volver6.setVisible(false);
            //llamada a metodos
            Acciones_BD.limpiar_tabla();
            Acciones_BD.RellenarTabla();
            Acciones_BD.invetario();

        }

    }

    public class AccionVentas implements ActionListener {

        @Override

        public void actionPerformed(ActionEvent e) {
            productos.setEnabled(false);
            stock.setEnabled(false);
            ventas.setEnabled(true);
            compras.setEnabled(false);
            opciones.setEnabled(false);
            ven_tabla.setVisible(true);
            bot_volver6.setVisible(false);
            bot_volver3.setEnabled(true);
            //llamada a metodos
            Acciones_BD.limpiar_tabla();
            Acciones_BD.RellenarTabla();
            Acciones_BD.accionVentas();

        }

    }
//******************************************************************************************************************************
}
