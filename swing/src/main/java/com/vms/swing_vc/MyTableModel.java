/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vms.swing_vc;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import model.Employees;

/**
 *
 * @author serg
 */
public class MyTableModel implements TableModel {

    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    private List<Employees> employees;

    public MyTableModel(List<Employees> employees) {
        this.employees = employees;
    }

    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0 || columnIndex == 7 || columnIndex == 8
                || columnIndex == 9) {
            return Long.class;
        } else if (columnIndex == 6) {
            return Date.class;
        }

        return String.class;
    }

    public int getColumnCount() {
        return 13;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "id";
            case 1:
                return "alias";
            case 2:
                return "name";
            case 3:
                return "firstName";
            case 4:
                return "secondName";
            case 5:
                return "lastName";
            case 6:
                return "createDate";
            case 7:
                return "originalid";
            case 8:
                return "refined";
            case 9:
                return "sortNo";
            case 10:
                return "hidden";
            case 11:
                return "password";
            case 12:
                return "salt";

        }
        return "";
    }

    public int getRowCount() {
        return employees.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Employees employee = employees.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return employee.getId();
            case 1:
                return employee.getAlias();
            case 2:
                return employee.getName();
            case 3:
                return employee.getFirstName();
            case 4:
                return employee.getSecondName();
            case 5:
                return employee.getLastName();
            case 6:
                return employee.getCreateDate();
            case 7:
                return employee.getOriginalid();
            case 8:
                return employee.getRefined();
            case 9:
                return employee.getSortNo();
            case 10:
                return employee.getHidden();
            case 11:
                return employee.getPassword();
            case 12:
                return employee.getSalt();
        }
        return "";
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Employees employee = employees.get(rowIndex);

        switch (columnIndex) {

            case 1:
                employee.setAlias((String) value);
                Run.ejb.update(employee);
                break;
            case 2:
                employee.setName((String) value);
                Run.ejb.update(employee);
                break;
            case 3:
                employee.setFirstName((String) value);
                Run.ejb.update(employee);
                break;
            case 4:
                employee.setSecondName((String) value);
                Run.ejb.update(employee);
                break;
            case 5:
                employee.setLastName((String) value);
                Run.ejb.update(employee);
                break;
            case 6:
                try {
                    Date w = new Date(Date.parse((String) value));
                    employee.setCreateDate(w);
                    Run.ejb.update(employee);
                } catch (NumberFormatException e) {
                    e.getMessage();
                } catch (IllegalArgumentException e){
                    e.getMessage();
                }
                break;
            case 7:
                try {
                    employee.setOriginalid(Long.parseLong((String) value));
                    Run.ejb.update(employee);
                } catch (NumberFormatException e) {
                    e.getMessage();
                }
                break;
            case 8:
                employee.setRefined((String) value);
                Run.ejb.update(employee);
                break;
            case 9:
                try {
                    employee.setSortNo(Long.parseLong((String) value));
                    Run.ejb.update(employee);
                } catch (NumberFormatException e) {
                    e.getMessage();
                }
                break;
            case 10:
                try {
                    employee.setHidden(Long.parseLong((String) value));
                    Run.ejb.update(employee);
                } catch (NumberFormatException e) {
                    e.getMessage();
                }
                break;
            case 11:
                employee.setPassword((String) value);
                Run.ejb.update(employee);
                break;
            case 12:
                employee.setSalt((String) value);
                Run.ejb.update(employee);
                break;

        }

    }
}
