package com.wings4.core.panel;

import com.jidesoft.converter.ConverterContext;
import com.jidesoft.converter.DoubleConverter;
import com.jidesoft.grid.*;
import com.jidesoft.hssf.HssfTableScrollPaneUtils;
import com.jidesoft.hssf.HssfTableUtils;
import com.jidesoft.swing.PartialLineBorder;
import com.jidesoft.swing.PartialSide;
import com.nepxion.swing.action.JSecurityAction;
import com.nepxion.swing.border.BorderManager;
import com.nepxion.swing.layout.filed.FiledLayout;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import com.wings4.core.toggle.GeneralToggleActionButton;
import com.wings4.core.toggle.InventoryCreateTogglePanel;
import com.wings4.core.toggle.ProductCreateTogglePanel;
import com.wings4.dao.MaterialDao;
import com.wings4.model.InventoryRegister;
import com.wings4.util.InventoryConstants;
import com.wings4.util.PrintUtilities;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/29/12
 * Time: 10:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class InventoryButtonPanel extends JPanel {

    private JScrollPane inventoryListHolder;
    private JTable inventoryTable;

    private JToolBar inventoryToolBar;
    private JButton createInventoryButton;
    private JButton reportInventoryButton;

    //For Group Header
    protected MultiTableModel _totalModel;
    protected MultiTableModel _subHeaderModel;
    protected MultiTableModel _model;
    public TableScrollPane _pane;
    private String _tablePref;
    private String _lastDirectory = ".";
    private static final long serialVersionUID = -5850105228695796397L;
    protected Color COLOR_MAIN = new Color(255, 254, 203);
    protected Color COLOR_HEADER = new Color(233, 254, 203);
    protected Color COLOR_CORNER = new Color(32, 32, 32);
    private static CellStyle SALES_STYLE = new CellStyle();
    private static CellStyle PROFITS_STYLE = new CellStyle();
    private static CellStyle BOLD_STYLE = new CellStyle();

    private static CellStyle FOOTER_STYLE = new CellStyle();

    static {
        SALES_STYLE.setForeground(new Color(0, 128, 0));
        SALES_STYLE.setHorizontalAlignment(SwingConstants.CENTER);
        PROFITS_STYLE.setForeground(Color.blue);
        PROFITS_STYLE.setHorizontalAlignment(SwingConstants.CENTER);
        BOLD_STYLE.setFontStyle(Font.BOLD);
        BOLD_STYLE.setHorizontalAlignment(SwingConstants.CENTER);

        FOOTER_STYLE.setForeground(Color.YELLOW);
    }



    public InventoryButtonPanel() {
        setLayout(new FiledLayout(FiledLayout.COLUMN, FiledLayout.FULL, 0));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new InventoryListPanel());
    }

    public class InventoryListPanel extends JPanel {

        class DummyTableModel extends AbstractMultiTableModel implements ColumnIdentifierTableModel,
                HeaderStyleModel, StyleModel {
            private static final long serialVersionUID = 7142342324546147914L;
            private Object[][] values;
            private ArrayList<TableModelListener> listeners = new ArrayList<TableModelListener>();

            List<InventoryRegister> inventories = MaterialDao.findAllInventories();

            public CellStyle getHeaderStyleAt(int rowIndex, int columnIndex) {
                if (columnIndex >= 4 && columnIndex <= 7) {
                    return SALES_STYLE;
                }
                else if (columnIndex >= 8 && columnIndex <= 10) {
                    return PROFITS_STYLE;
                }
                return BOLD_STYLE;
            }

            public boolean isHeaderStyleOn() {
                return true;
            }

            public CellStyle getCellStyleAt(int rowIndex, int columnIndex) {
                if (columnIndex >= 4 && columnIndex <= 7) {
                    return SALES_STYLE;
                }
                else if (columnIndex >= 8 && columnIndex <= 10) {
                    return PROFITS_STYLE;
                }
                return null;
            }

            public boolean isCellStyleOn() {
                return true;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0:
                        return "SL No.";
                    case 1:
                        return "Name of Items (REACTIVE STARFIX)";
                    case 2:
                        return "Substitute Name";
                    case 3:
                        return "Opening Balance";
                    case 4:
                        return "From Local Supplier";
                    case 5:
                        return "Import by LC";
                    case 6:
                        return "Sales Return";
                    case 7:
                        return "Loan Return";
                    case 8:
                        return "Sales";
                    case 9:
                        return "Sample";
                    case 10:
                        return "Loan";
                    case 11:
                        return "Closing Balance@rows:1:1:1";
                }
                return "";
            }

            public Object getColumnIdentifier(int column) {
                switch (column) {
                    case 0:
                        return "SL No.";
                    case 1:
                        return "Name of Items (REACTIVE STARFIX)";
                    case 2:
                        return "Substitute Name";
                    case 3:
                        return "Opening Balance";
                    case 4:
                        return "Received Quantity From Local Supplier";
                    case 5:
                        return "Received Quantity Import by LC";
                    case 6:
                        return "Received Quantity Sales Return";
                    case 7:
                        return "Received Quantity Loan Return";
                    case 8:
                        return "Delivered Quantity Sales";
                    case 9:
                        return "Delivered Quantity Sample";
                    case 10:
                        return "Delivered Quantity Loan";
                    case 11:
                        return "Closing Balance";
                }
                return "";
            }

            public int getColumnCount() {
                return 13;
            }

            public int getRowCount() {
                return 50;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                /*if (rowIndex > 10) {
                    if (columnIndex == 0) {
                        return "";
                    }
                    else {
                        return null;
                    }
                }*/
                if (values == null) {
                    values = new Object[inventories.size()][8];

                    /*values = new Object[MaterialDao.findAllInventories().size()][8];
                    for(int i = 0; i < MaterialDao.findAllInventories().size(); i++){
                        System.out.println("values[i].length = " + values[i].length);
                        for(int j = 0; j < values[i].length; j++){
                            if(i == 0)
                                values[j][0] = MaterialDao.findAllInventories().get(j).getId();
                            else if(i == 1)
                                values[j][1] = MaterialDao.findAllInventories().get(j).getProductName();
                        }
                    }*/
                    /*values[0][0] = 1398695d;
                    values[0][1] = 1430276d;
                    values[0][2] = 1447157d;
                    values[0][3] = 1490965d;
                    values[0][4] = 110934d;
                    values[0][5] = 107759d;
                    values[0][6] = 124032d;
                    values[0][7] = 135152d;
                    values[1][0] = 1241294d;
                    values[1][1] = 1226253d;
                    values[1][2] = 1289095d;
                    values[1][3] = 1353997d;
                    values[1][4] = 36559d;
                    values[1][5] = 49946d;
                    values[1][6] = 95282d;
                    values[1][7] = 104617d;
                    values[2][0] = 1623674d;
                    values[2][1] = 1687223d;
                    values[2][2] = 1661264d;
                    values[2][3] = 1400121d;
                    values[2][4] = 120311d;
                    values[2][5] = 99284d;
                    values[2][6] = 120320d;
                    values[2][7] = -73490d;
                    values[3][0] = 1456498d;
                    values[3][1] = 1494618d;
                    values[3][2] = 1521356d;
                    values[3][3] = 1585911d;
                    values[3][4] = 120354d;
                    values[3][5] = 126496d;
                    values[3][6] = 76348d;
                    values[3][7] = 119686d;
                    values[4][0] = 1445585d;
                    values[4][1] = 1454570d;
                    values[4][2] = 1452573d;
                    values[4][3] = 1428549d;
                    values[4][4] = 122653d;
                    values[4][5] = 112943d;
                    values[4][6] = 122834d;
                    values[4][7] = 111922d;
                    values[5][0] = 1300210d;
                    values[5][1] = 1322831d;
                    values[5][2] = 1373056d;
                    values[5][3] = 1412359d;
                    values[5][4] = 92180d;
                    values[5][5] = 97277d;
                    values[5][6] = 105470d;
                    values[5][7] = 106082d;
                    values[6][0] = 1182609d;
                    values[6][1] = 1222192d;
                    values[6][2] = 1243017d;
                    values[6][3] = 1284205d;
                    values[6][4] = 77185d;
                    values[6][5] = 85249d;
                    values[6][6] = 87432d;
                    values[6][7] = 97998d;
                    values[7][0] = 1101612d;
                    values[7][1] = 1073429d;
                    values[7][2] = 1103876d;
                    values[7][3] = 1114595d;
                    values[7][4] = 59496d;
                    values[7][5] = 48201d;
                    values[7][6] = 50763d;
                    values[7][7] = 78128d;
                    values[8][0] = 1024201d;
                    values[8][1] = 1048687d;
                    values[8][2] = 1064492d;
                    values[8][3] = 1077936d;
                    values[8][4] = 25422d;
                    values[8][5] = 36375d;
                    values[8][6] = 38694d;
                    values[8][7] = 39239d;
                    values[9][0] = 1114705d;
                    values[9][1] = 1093520d;
                    values[9][2] = 1058748d;
                    values[9][3] = 1029272d;
                    values[9][4] = -133d;
                    values[9][5] = 14493d;
                    values[9][6] = 8353d;
                    values[9][7] = 13126d;
                    values[10][0] = 1119619d;
                    values[10][1] = 1127677d;
                    values[10][2] = 1143982d;
                    values[10][3] = 1122837d;
                    values[10][4] = 77113d;
                    values[10][5] = 69331d;
                    values[10][6] = 71194d;
                    values[10][7] = 52707d;*/
                }
                if(inventories.size() > 0){

                    if(rowIndex == 0){
                        switch (columnIndex) {
                            case 0:
                                return inventories.get(0).getId();
                            case 1:
                                return inventories.get(0).getProductName();
                            case 2:
                                return inventories.get(0).getProductName();
                            case 3:
                                return inventories.get(0).getOnHand();
                            case 4:
                                return inventories.get(0).getOnHandFromLocalSupplier();
                            case 5:
                                return inventories.get(0).getOnHandImportByLC();
                            case 6:
                                return inventories.get(0).getOnSalesReturn();
                            case 7:
                                return inventories.get(0).getOnLoanReturn();
                            case 8:
                                return inventories.get(0).getUnavailableFromSales();
                            case 9:
                                return inventories.get(0).getUnavailableFromSample();
                            case 10:
                                return inventories.get(0).getUnavailableFromLoan();
                            case 11:
                                return inventories.get(0).getUnavailable();
                        }
                    } else if(columnIndex == 0 && rowIndex > 0 && inventories.size() > 1){
                        long id = inventories.get(0).getId();
                        while (rowIndex == inventories.size())
                        return id + rowIndex;
                    } else if(columnIndex == 1 && rowIndex > 0){

                    }

                }

                
//                if(columnIndex == 0)
//                    return MaterialDao.findAllInventories().get(0).getId();
                /*if (columnIndex == 0) {
                    return 2011 - 1 - rowIndex;
                }
                else if (columnIndex <= 8 && rowIndex <= 10) {
                    return values[rowIndex][columnIndex - 1];
                }*/
                /*else if (columnIndex == 9) {
                    Double summary = 0d;
                    for (int i = 1; i <= 4; i++) {
                        Object value = getValueAt(rowIndex, i);
                        if (value instanceof Double) {
                            summary += (Double) value;
                        }
                    }
                    return summary;
                }
                else if (columnIndex == 10) {
                    Double summary = 0d;
                    for (int i = 5; i <= 8; i++) {
                        Object value = getValueAt(rowIndex, i);
                        if (value instanceof Double) {
                            summary += (Double) value;
                        }
                    }
                    return summary;
                }*/
                return null;
            }

            /*public void setValueAt(Object aValue, int row, int column) {


            }*/

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            public int getTableIndex(int columnIndex) {
                return 0;
            }

            public int getColumnType(int column) {
                if (column < 1) {
                    return HEADER_COLUMN;
                }
                else if (column >= getColumnCount() - 2) {
                    return FOOTER_COLUMN;
                }
                else {
                    return REGULAR_COLUMN;
                }
            }

            @Override
            public Class<?> getCellClassAt(int row, int column) {
                return getColumnClass(column);
            }

            @Override
            public ConverterContext getConverterContextAt(int row, int column) {
                return column >= 1 ? DoubleConverter.CONTEXT_FRACTION_NUMBER : null;
            }
        }

        class DummyFooterTableModel extends AbstractMultiTableModel implements ColumnIdentifierTableModel, StyleModel {
            TableModel _model;
            private static final long serialVersionUID = -9132647394140127017L;

            public DummyFooterTableModel(TableModel model) {
                _model = model;
            }

            public CellStyle getCellStyleAt(int rowIndex, int columnIndex) {
                return FOOTER_STYLE;
            }

            public boolean isCellStyleOn() {
                return true;
            }

            @Override
            public String getColumnName(int column) {
                return _model.getColumnName(column);
            }

            public Object getColumnIdentifier(int columnIndex) {
                return ((ColumnIdentifierTableModel) _model).getColumnIdentifier(columnIndex);
            }

            public int getColumnCount() {
                return _model.getColumnCount();
            }

            public int getRowCount() {
                return 1;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return _model.getColumnClass(columnIndex);
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                if (columnIndex == 0) {
                    return "Average";
                }
                else {
                    Object summary = "";
                    for (int i = 0; i < _model.getRowCount(); i++) {
                        Object value = _model.getValueAt(i, columnIndex);
                        summary = value;
                    }
                    return summary;
                }
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            public int getColumnType(int column) {
                return ((MultiTableModel) _model).getColumnType(column);
            }

            public int getTableIndex(int columnIndex) {
                return 0;
            }

            @Override
            public Class<?> getCellClassAt(int row, int column) {
                return getColumnClass(column);
            }

            @Override
            public ConverterContext getConverterContextAt(int row, int column) {
                return column >= 1 ? DoubleConverter.CONTEXT_FRACTION_NUMBER : null;
            }
        }

        class DummyHeaderTableModel extends AbstractMultiTableModel implements ColumnIdentifierTableModel, StyleModel {
            TableModel _model;
            private static final long serialVersionUID = -9132647394140127017L;

            public DummyHeaderTableModel(TableModel model) {
                _model = model;
            }

            public CellStyle getCellStyleAt(int rowIndex, int columnIndex) {
                return ((StyleModel) _model).getCellStyleAt(0, columnIndex);
            }

            public boolean isCellStyleOn() {
                return true;
            }

            @Override
            public String getColumnName(int column) {
                return _model.getColumnName(column);
            }

            public Object getColumnIdentifier(int columnIndex) {
                return ((ColumnIdentifierTableModel) _model).getColumnIdentifier(columnIndex);
            }

            public int getColumnCount() {
                return _model.getColumnCount();
            }

            public int getRowCount() {
                return 1;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return _model.getColumnClass(columnIndex);
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                /*if (columnIndex == 0) {
                    return "2011";
                }
                else if (columnIndex == 1) {
                    return 1596666d;
                }
                else if (columnIndex == 5) {
                    return 144463d;
                }
                else if (columnIndex == 10) {
                    Double summary = 0d;
                    for (int i = 2; i <= 5; i++) {
                        Object value = getValueAt(rowIndex, i);
                        if (value instanceof Double) {
                            summary += (Double) value;
                        }
                    }
                    return summary;
                }
                else if (columnIndex == 11) {
                    Double summary = 0d;
                    for (int i = 7; i <= 9; i++) {
                        Object value = getValueAt(rowIndex, i);
                        if (value instanceof Double) {
                            summary += (Double) value;
                        }
                    }
                    return summary;
                }*/
                return null;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            public int getColumnType(int column) {
                return ((MultiTableModel) _model).getColumnType(column);
            }

            public int getTableIndex(int columnIndex) {
                return 0;
            }

            @Override
            public Class<?> getCellClassAt(int row, int column) {
                return getColumnClass(column);
            }

            @Override
            public ConverterContext getConverterContextAt(int row, int column) {
                return column >= 1 ? DoubleConverter.CONTEXT_FRACTION_NUMBER : null;
            }
        }

        public InventoryListPanel() {

            //For Group Header
            _model = new DummyTableModel();
            _totalModel = new DummyFooterTableModel(_model);
            _subHeaderModel = new DummyHeaderTableModel(_model);
            _pane = new TableScrollPane(_model, _subHeaderModel, _totalModel, true);

            //_pane.setCorner(JScrollPane.UPPER_LEFT_CORNER, new JButton());
//            _pane.setColumnHeaderView(columnLabel);
//            _pane.setRowHeaderView(rowLabel);

            ((JideTable) _pane.getMainTable()).setNestedTableHeader(true);
            TableColumnModel columnModel = _pane.getMainTable().getColumnModel();
            TableColumnGroup sales = new TableColumnGroup("Received Quantity@rows");
            sales.add(columnModel.getColumn(3));
            sales.add(columnModel.getColumn(4));
            sales.add(columnModel.getColumn(5));
            sales.add(columnModel.getColumn(6));
            TableColumnGroup profits = new TableColumnGroup("Delivered Quantity@rows");
            profits.add(columnModel.getColumn(7));
            profits.add(columnModel.getColumn(8));
            profits.add(columnModel.getColumn(9));

            if (_pane.getMainTable().getTableHeader() instanceof NestedTableHeader) {
                NestedTableHeader header = (NestedTableHeader) _pane.getMainTable().getTableHeader();
                header.addColumnGroup(sales);
                header.addColumnGroup(profits);
            }

            _pane.getRowHeaderTable().setBackground(COLOR_MAIN);
            _pane.getMainTable().setBackground(COLOR_MAIN);
            _pane.getRowFooterTable().setBackground(COLOR_MAIN);

            _pane.getColumnHeaderTable().setBackground(COLOR_HEADER);
            _pane.getRowHeaderColumnHeaderTable().setBackground(COLOR_HEADER);
            _pane.getRowFooterColumnHeaderTable().setBackground(COLOR_HEADER);

            _pane.getColumnFooterTable().setBackground(COLOR_CORNER);
            _pane.getRowHeaderColumnFooterTable().setBackground(COLOR_CORNER);
            _pane.getRowFooterColumnFooterTable().setBackground(COLOR_CORNER);

            Border border = new PartialLineBorder(Color.DARK_GRAY, 2, PartialSide.SOUTH);
            _pane.getRowHeaderColumnHeaderTable().setBorder(border);
            _pane.getColumnHeaderTable().setBorder(border);
            _pane.getRowFooterColumnHeaderTable().setBorder(border);


            TableHeaderPopupMenuInstaller installer = new TableHeaderPopupMenuInstaller(_pane.getMainTable()) {
                @Override
                protected void customizeMenuItems(final JTableHeader header, final JPopupMenu popup, final int clickingColumn) {
                    super.customizeMenuItems(header, popup, clickingColumn);

                    addSeparatorIfNecessary(popup);

                    final JMenuItem export = new JMenuItem(new AbstractAction("Export to Excel 2003 format") {
                        private static final long serialVersionUID = 2581042425782595535L;

                        public void actionPerformed(ActionEvent e) {
                            _pane.putClientProperty(HssfTableUtils.CLIENT_PROPERTY_EXCEL_OUTPUT_FORMAT, HssfTableUtils.EXCEL_OUTPUT_FORMAT_2003);
                            if (!HssfTableUtils.isHssfInstalled()) {
                                JOptionPane.showMessageDialog((Component) e.getSource(), "Export to Excel feature is disabled because POI-HSSF jar is missing in the classpath.");
                                return;
                            }
                            outputToExcel(e);
                        }
                    });

                    final JMenuItem export2007 = new JMenuItem(new AbstractAction("Export to Excel 2007 format") {
                        private static final long serialVersionUID = 2581042425782595535L;

                        public void actionPerformed(ActionEvent e) {
                            _pane.putClientProperty(HssfTableUtils.CLIENT_PROPERTY_EXCEL_OUTPUT_FORMAT, HssfTableUtils.EXCEL_OUTPUT_FORMAT_2007);
                            if (!HssfTableUtils.isXssfInstalled()) {
                                JOptionPane.showMessageDialog((Component) e.getSource(), "Export to Excel 2007 feature is disabled because one or several POI-XSSF dependency jars are missing in the classpath. Please include all the jars from poi release in the classpath and try to run again.");
                                return;
                            }
                            outputToExcel(e);
                        }
                    });

                    final JMenuItem exportToCsv = new JMenuItem(new AbstractAction("Export to CSV format") {
                        private static final long serialVersionUID = 2581042425782595535L;

                        public void actionPerformed(ActionEvent e) {
                            outputToCsv(e);
                        }
                    });
                    popup.add(export);
                    popup.add(export2007);
                    popup.add(exportToCsv);
                }
            };

            installer.addTableHeaderPopupMenuCustomizer(new AutoResizePopupMenuCustomizer());
            installer.addTableHeaderPopupMenuCustomizer(new TableColumnChooserPopupMenuCustomizer());

            TableHeaderPopupMenuInstaller installer2 = new TableHeaderPopupMenuInstaller(_pane.getRowFooterTable()) {
                @Override
                protected void customizeMenuItems(final JTableHeader header, final JPopupMenu popup, final int clickingColumn) {
                    super.customizeMenuItems(header, popup, clickingColumn);

                    addSeparatorIfNecessary(popup);

                    final JMenuItem export = new JMenuItem(new AbstractAction("Export to Excel 2003 format") {
                        private static final long serialVersionUID = 2581042425782595535L;

                        public void actionPerformed(ActionEvent e) {
                            _pane.putClientProperty(HssfTableUtils.CLIENT_PROPERTY_EXCEL_OUTPUT_FORMAT, HssfTableUtils.EXCEL_OUTPUT_FORMAT_2003);
                            if (!HssfTableUtils.isHssfInstalled()) {
                                JOptionPane.showMessageDialog((Component) e.getSource(), "Export to Excel feature is disabled because POI-HSSF jar is missing in the classpath.");
                                return;
                            }
                            outputToExcel(e);
                        }
                    });

                    final JMenuItem export2007 = new JMenuItem(new AbstractAction("Export to Excel 2007 format") {
                        private static final long serialVersionUID = 2581042425782595535L;

                        public void actionPerformed(ActionEvent e) {
                            _pane.putClientProperty(HssfTableUtils.CLIENT_PROPERTY_EXCEL_OUTPUT_FORMAT, HssfTableUtils.EXCEL_OUTPUT_FORMAT_2007);
                            if (!HssfTableUtils.isXssfInstalled()) {
                                JOptionPane.showMessageDialog((Component) e.getSource(), "Export to Excel 2007 feature is disabled because one or several POI-XSSF dependency jars are missing in the classpath. Please include all the jars from poi release in the classpath and try to run again.");
                                return;
                            }
                            outputToExcel(e);
                        }
                    });

                    final JMenuItem exportToCsv = new JMenuItem(new AbstractAction("Export to CSV format") {
                        private static final long serialVersionUID = 2581042425782595535L;

                        public void actionPerformed(ActionEvent e) {
                            outputToCsv(e);
                        }
                    });
                    popup.add(export);
                    popup.add(export2007);
                    popup.add(exportToCsv);
                }
            };

            installer2.addTableHeaderPopupMenuCustomizer(new AutoResizePopupMenuCustomizer());
            installer2.addTableHeaderPopupMenuCustomizer(new TableColumnChooserPopupMenuCustomizer());

            TableHeaderPopupMenuInstaller installer3 = new TableHeaderPopupMenuInstaller(_pane.getRowHeaderTable()) {
                @Override
                protected void customizeMenuItems(final JTableHeader header, final JPopupMenu popup, final int clickingColumn) {
                    super.customizeMenuItems(header, popup, clickingColumn);

                    addSeparatorIfNecessary(popup);

                    final JMenuItem export = new JMenuItem(new AbstractAction("Export to Excel 2003 format") {
                        private static final long serialVersionUID = 2581042425782595535L;

                        public void actionPerformed(ActionEvent e) {
                            _pane.putClientProperty(HssfTableUtils.CLIENT_PROPERTY_EXCEL_OUTPUT_FORMAT, HssfTableUtils.EXCEL_OUTPUT_FORMAT_2003);
                            if (!HssfTableUtils.isHssfInstalled()) {
                                JOptionPane.showMessageDialog((Component) e.getSource(), "Export to Excel feature is disabled because POI-HSSF jar is missing in the classpath.");
                                return;
                            }
                            outputToExcel(e);
                        }
                    });

                    final JMenuItem export2007 = new JMenuItem(new AbstractAction("Export to Excel 2007 format") {
                        private static final long serialVersionUID = 2581042425782595535L;

                        public void actionPerformed(ActionEvent e) {
                            _pane.putClientProperty(HssfTableUtils.CLIENT_PROPERTY_EXCEL_OUTPUT_FORMAT, HssfTableUtils.EXCEL_OUTPUT_FORMAT_2007);
                            if (!HssfTableUtils.isXssfInstalled()) {
                                JOptionPane.showMessageDialog((Component) e.getSource(), "Export to Excel 2007 feature is disabled because one or several POI-XSSF dependency jars are missing in the classpath. Please include all the jars from poi release in the classpath and try to run again.");
                                return;
                            }
                            outputToExcel(e);
                        }
                    });

                    final JMenuItem exportToCsv = new JMenuItem(new AbstractAction("Export to CSV format") {
                        private static final long serialVersionUID = 2581042425782595535L;

                        public void actionPerformed(ActionEvent e) {
                            outputToCsv(e);
                        }
                    });
                    popup.add(export);
                    popup.add(export2007);
                    popup.add(exportToCsv);
                }
            };
            installer3.addTableHeaderPopupMenuCustomizer(new AutoResizePopupMenuCustomizer());
            installer3.addTableHeaderPopupMenuCustomizer(new TableColumnChooserPopupMenuCustomizer());

            //For Group Header

            setLayout(new BorderLayout());
            setBorder(BorderManager.createComplexTitledBorder("Inventory List"));

            inventoryListHolder = new JScrollPane();
            inventoryTable = new JTable();


            inventoryToolBar = new JToolBar();
            createInventoryButton = new JButton();
            reportInventoryButton = new JButton();

            createInventoryButton.setIcon(new ImageIcon(getClass().
                    getResource(InventoryConstants.resourceDirectory.
                            concat("list-add.png"))));
            createInventoryButton.setFocusable(false);
            createInventoryButton.setHorizontalTextPosition(SwingConstants.CENTER);
            createInventoryButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            inventoryToolBar.add(createInventoryButton);

            createInventoryButton.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
                    GeneralToggleActionButton inventoryCreateButton = new GeneralToggleActionButton(new
                            InventoryCreateTogglePanel());
                    inventoryCreateButton.doClick();
                }
            });

            reportInventoryButton.setIcon(new ImageIcon(getClass().
                    getResource(InventoryConstants.resourceDirectory.
                            concat("document-print.png"))));
            reportInventoryButton.setFocusable(false);
            reportInventoryButton.setHorizontalTextPosition(SwingConstants.CENTER);
            reportInventoryButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            reportInventoryButton.addActionListener(new JSecurityAction() {
                @Override
                public void execute(ActionEvent actionEvent) {
                    PrintUtilities.printComponent(_pane);
                }
            });
            inventoryToolBar.add(reportInventoryButton);



            inventoryListHolder.setViewportView(inventoryTable);

            add(inventoryToolBar, BorderLayout.PAGE_START);
            add(_pane, BorderLayout.CENTER);
        }

        private void outputToExcel(ActionEvent e) {
            JFileChooser chooser = new JFileChooser() {
                @Override
                protected JDialog createDialog(Component parent) throws HeadlessException {
                    JDialog dialog = super.createDialog(parent);
                    dialog.setTitle("Export the content to an Excel file");
                    return dialog;
                }
            };
            chooser.setCurrentDirectory(new File(_lastDirectory));
            int result = chooser.showDialog(((JMenuItem) e.getSource()).getTopLevelAncestor(), "Export");
            if (result == JFileChooser.APPROVE_OPTION) {
                _lastDirectory = chooser.getCurrentDirectory().getAbsolutePath();
                try {
                    HssfTableScrollPaneUtils.export(_pane, chooser.getSelectedFile().getAbsolutePath(), "TreeTableScrollPane", false);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        private void outputToCsv(ActionEvent e) {
            JFileChooser chooser = new JFileChooser() {
                @Override
                protected JDialog createDialog(Component parent) throws HeadlessException {
                    JDialog dialog = super.createDialog(parent);
                    dialog.setTitle("Export the content to a CSV file");
                    return dialog;
                }
            };
            chooser.setCurrentDirectory(new File(_lastDirectory));
            int result = chooser.showDialog(((JMenuItem) e.getSource()).getTopLevelAncestor(), "Export");
            if (result == JFileChooser.APPROVE_OPTION) {
                _lastDirectory = chooser.getCurrentDirectory().getAbsolutePath();
                try {
                    CsvTableScrollPaneUtils.export(_pane, chooser.getSelectedFile().getAbsolutePath());
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
