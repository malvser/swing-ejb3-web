/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vms.swing_vc;

import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.table.TableModel;
import model.Employees;
import services.EmployeeBeanImpl;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import services.EmployeeBean;

/**
 *
 * @author serg
 */
public class Run extends JFrame {

    private static Context context;
    static EmployeeBean ejb;
    private JTable table;
    private JScrollPane jsPane;

    private JPanel dialogPanel;
    private JTextField tf[];
    private JLabel lbl[];
    private JPanel panel;

    public Run() {

        initComponents();
    }

    private void initComponents() {
        try {
            createInitialContext();
            setEjb(lookupRemoteEJB());
        } catch (NamingException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        List<Employees> employees = new ArrayList();

        employees = ejb.getAll();

        TableModel model = new MyTableModel(employees);
        table = new JTable(model);
        //add the table to the frame
        jsPane = new JScrollPane(table);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() >= 2) {

                    int x = e.getX();
                    int y = e.getY();
                    int row = table.rowAtPoint(new Point(x, y));
                    //int col = table.columnAtPoint(new Point(x, y));

                    String array[] = new String[table.getColumnCount()];
                    for (int i = 0; i < array.length; i++) {
                        array[i] = "" + table.getValueAt(row, i);
                    }
                    populateTextField(array);
                    JOptionPane.showMessageDialog(null, dialogPanel,
                            "Edit Employee", JOptionPane.INFORMATION_MESSAGE);

                    for (int i = 0; i < tf.length; i++) {
                        model.setValueAt(tf[i].getText(), row, i);
                    }

                }

            }
        });

        panel = new JPanel(new BorderLayout());

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

        panel.add(panel1, BorderLayout.NORTH);
        panel.add(jsPane, BorderLayout.CENTER);
        getContentPane().add(panel);

        this.setTitle("Employees List");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(1060, 1020));
        prepareDialogPanel();
        this.pack();
        this.setVisible(true);
    }

    public void createInitialContext() throws NamingException {
        Properties jndiProperties = new Properties();
        jndiProperties.put("jboss.naming.client.ejb.context", true);
        jndiProperties.put("useUnicode", "true");
        jndiProperties.put("charSet", "UTF8");
        String resourceName = "jndi.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            jndiProperties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        context = new InitialContext(jndiProperties);
    }

    private void populateTextField(String[] s) {
        for (int i = 0; i < s.length; i++) {
            tf[i].setText(s[i]);

        }
    }

    private void prepareDialogPanel() {

        dialogPanel = new JPanel();
        int col = table.getColumnCount();
        dialogPanel.setLayout(new GridLayout(col, 1));
        tf = new JTextField[col];
        lbl = new JLabel[col];

        for (int i = 0; i < col; i++) {
            lbl[i] = new JLabel(table.getColumnName(i));
            tf[i] = new JTextField(10);

            dialogPanel.add(lbl[i]);
            dialogPanel.add(tf[i]);

        }
    }

    public EmployeeBean lookupRemoteEJB() throws NamingException {

        String appName = "test-ejb3-ear-1.0-SNAPSHOT";
        String moduleName = "test-ejb3-ejb-1.0-SNAPSHOT";
        String distinctName = "";
        String beanName = EmployeeBeanImpl.class.getSimpleName();

        String viewClassName = EmployeeBean.class.getName();
        System.out.println("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
        return (EmployeeBean) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);

    }

    public static EmployeeBean getEjb() {
        return ejb;
    }

    public static void setEjb(EmployeeBean ejb) {
        Run.ejb = ejb;
    }

    public static void main(String[] args) {
        try {
            javax.swing.SwingUtilities.invokeLater(() -> {
                JFrame.setDefaultLookAndFeelDecorated(true);
                new Run();
            });

        } finally {
            try {
                if (context != null) {
                    context.close();
                }

            } catch (NamingException ex) {
                Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
