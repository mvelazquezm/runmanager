package es.mvm.runman.gui;

import javax.swing.table.TableModel;

import es.mvm.runman.beans.Category;
import es.mvm.runman.beans.Competitor;

import es.mvm.runman.beans.Race;
import es.mvm.runman.core.ApplicationManager;
import es.mvm.runman.util.Constants;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class CategoryTableModel implements TableModel, Comparator  {
        
    private List<Category> categoryList;
    private List<TableModelListener> listenersList;
    
    protected   int         currCol;
    protected   Vector      ascendCol;  // this vector stores the state (ascending or descending) of each column
    protected   Integer     one         = new Integer(1);
    protected   Integer     minusOne    = new Integer(-1);
    
    private ApplicationManager manager;
    
    private Race race;
    
    public CategoryTableModel() {
        super();
        
        categoryList = new ArrayList<Category>();
        listenersList = new ArrayList<TableModelListener>();
        ascendCol = new Vector();
        race = null;
        manager = null;
        
        ascendCol.add(one);
        ascendCol.add(one);
        ascendCol.add(one);
        ascendCol.add(one);
        ascendCol.add(one);
    }

    @Override
    public int getRowCount() {
        return categoryList.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Nombre"; 
            case 1: return "Edad Min";
            case 2: return "Edad Max";
            case 3: return "Sexo"; 
            case 4: return "Inscritos";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return String.class;
            case 1: return Integer.class;
            case 2: return Integer.class; 
            case 3: return Constants.TSex.class; 
            case 4: return Integer.class;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Category cat = categoryList.get(rowIndex);
        switch (columnIndex) {
            case 0: //Nombre
                return cat.getName();
            case 1: //Edad Min
                return new Integer(cat.getIniAge());
            case 2: //Edad Max
                return new Integer(cat.getEndAge());
            case 3: //Fin
                return cat.getSex();
            case 4:
                int value = calculateNumComp(cat);
                return new Integer(value);
        }
        return null;
    }
    
    public Object getObjectValue(Object cat, int columnIndex) {
        Category category = (Category)cat;
        switch (columnIndex) {
            case 0: //Nombre
                return category.getName();
            case 1: //Edad Min
                return new Integer(category.getIniAge());
            case 2: //Edad Max
                return new Integer(category.getEndAge());
            case 3: //Fin
                return category.getSex();
            case 4:
                int value = calculateNumComp(category);
                return new Integer(value);
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listenersList.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listenersList.remove(l);
    }
    
    public void setRace(Race race) {
        this.race = race;
    }
    
    private int calculateNumComp(Category cat) {
        int value = 0;
        if (race != null) {
            for (Competitor comp : race.getCompetitors()) {
                if (comp.getCategory().equals(cat))
                    value++;
            }
        }
        
        return value;
    }

    public void setCategoryList (List<Category> list) {
        TableModelEvent evento = new TableModelEvent (this, this.getRowCount(), this.getRowCount(), TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE);
        TableModelEvent evento2 = new TableModelEvent (this, this.getRowCount(), this.getRowCount(), TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        TableModelEvent evento3 = new TableModelEvent (this, this.getRowCount(), this.getRowCount(), TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
        avisaListeners (evento2);
        avisaListeners (evento3);
        avisaListeners (evento);
        categoryList = list;
        evento = new TableModelEvent (this, this.getRowCount(), this.getRowCount(), TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE);
        evento2 = new TableModelEvent (this, this.getRowCount(), this.getRowCount(), TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        evento3 = new TableModelEvent (this, this.getRowCount(), this.getRowCount(), TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
        avisaListeners (evento2);
        avisaListeners (evento3);
        avisaListeners (evento);
        
    }

    public void addCategory (Category category) {
        categoryList.add(category);
        TableModelEvent evento = new TableModelEvent (this, this.getRowCount(), this.getRowCount(), TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        avisaListeners (evento);
    }

    public void removeCategory (Category category) {
        categoryList.remove(category);
        TableModelEvent evento = new TableModelEvent (this, this.getRowCount(), this.getRowCount(), TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
        avisaListeners (evento);
    }
    
    private void avisaListeners(TableModelEvent evento) {
        for (TableModelListener aux : listenersList) {
            aux.tableChanged(evento);
        }
    }

    @Override
    public int compare(Object v1, Object v2) {
        // the comparison is between 2 vectors, each representing a row
        // the comparison is done between 2 objects from the different rows that are in the column that is being sorted

        int ascending = ((Integer) ascendCol.get(currCol)).intValue();
        if (v1 == null && v2 == null) {
            return 0;
        } else if (v2 == null) { // Define null less than everything.
            return 1 * ascending;
        } else if (v1 == null) {
            return -1 * ascending;
        }

        Object o1 = getObjectValue(v1, currCol);
        Object o2 = getObjectValue(v2, currCol);

        // If both values are null, return 0.
        if (o1 == null && o2 == null) {
            return 0;
        } else if (o2 == null) { // Define null less than everything.
            return 1 * ascending;
        } else if (o1 == null) {
            return -1 * ascending;
        }

        if (o1 instanceof Number && o2 instanceof Number) {
            Number n1 = (Number) o1;
            double d1 = n1.doubleValue();
            Number n2 = (Number) o2;
            double d2 = n2.doubleValue();

            if (d1 == d2) {
                return 0;
            } else if (d1 > d2) {
                return 1 * ascending;
            } else {
                return -1 * ascending;
            }

        } else if (o1 instanceof Boolean && o2 instanceof Boolean) {
            Boolean bool1 = (Boolean) o1;
            boolean b1 = bool1.booleanValue();
            Boolean bool2 = (Boolean) o2;
            boolean b2 = bool2.booleanValue();

            if (b1 == b2) {
                return 0;
            } else if (b1) {
                return 1 * ascending;
            } else {
                return -1 * ascending;
            }
            
        } else if (o1 instanceof Constants.TSex && o2 instanceof Constants.TSex) {
        
                if (o1 == Constants.TSex.Masculino)
                    return 1 * ascending;
                else if (o1 == Constants.TSex.Femenino && o2 == Constants.TSex.Indiferente)
                    return 1 * ascending;
                else return -1 * ascending;

        } else {
            // default case
            if (o1 instanceof Comparable && o2 instanceof Comparable) {
                Comparable c1 = (Comparable) o1;
                Comparable c2 = (Comparable) o2; // superflous cast, no need for it!

                try {
                    return c1.compareTo(c2) * ascending;
                } catch (ClassCastException cce) {
                    // forget it... we'll deal with them like 2 normal objects below.
                }
            }

            String s1 = o1.toString();
            String s2 = o2.toString();
            return s1.compareTo(s2) * ascending;
        }
    }
    
    public void sort() {
        Collections.sort(categoryList, this);
        Integer val = (Integer) ascendCol.get(currCol);
        ascendCol.remove(currCol);
        if(val.equals(one)) // change the state of the column
            ascendCol.add(currCol, minusOne);
        else
            ascendCol.add(currCol, one);
    }

    public void sortByColumn(int column) {
        this.currCol = column;
        sort();
        //fireTableChanged(new TableModelEvent(this));
        avisaListeners(new TableModelEvent(this));
    }
    
    // Add a mouse listener to the Table to trigger a table sort
    // when a column heading is clicked in the JTable.
    public void addMouseListenerToHeaderInTable(JTable table) {
        final CategoryTableModel sorter = this;
        final JTable tableView = table;
        tableView.setColumnSelectionAllowed(false);
        MouseAdapter listMouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                TableColumnModel columnModel = tableView.getColumnModel();
                int viewColumn = columnModel.getColumnIndexAtX(e.getX());
                int column = tableView.convertColumnIndexToModel(viewColumn);
                if (e.getClickCount() == 1 && column != -1) {
                    int shiftPressed = e.getModifiers()&InputEvent.SHIFT_MASK;
                    boolean ascending = (shiftPressed == 0);
                    sorter.sortByColumn(column);
                }
            }
        };
        JTableHeader th = tableView.getTableHeader();
        th.addMouseListener(listMouseListener);
    }
    
    public void setManager(ApplicationManager appMan) {
        manager = appMan;
    }
    
    public void updateCategotry(int index) {
        
        Category cat = categoryList.get(index);
        
        CategoryForm catForm = new CategoryForm(manager, cat);
        catForm.setVisible(true);
        
        avisaListeners(new TableModelEvent(this));
    }

}
