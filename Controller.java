package sdp_asignment2;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
//controller pattern
//adding 4 button to gui interface
public class Controller implements ActionListener {
    private final ItemTableDescrip itemTable;
    private final JFrame frame;
    private final JTable table;
    private final DefaultTableModel tableModel;
     
    private final JButton addButton;
    private final JButton deleteButton;
    private final JButton undoButton;
    private final JButton redoButton;

    public Controller(ItemTableDescrip itemTable) {
        this.itemTable = itemTable;
        //added name for interface
        frame = new JFrame("SDP_HW2 - Desktop Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // Create a border for the JFrame
        Border border = BorderFactory.createLineBorder(Color.black, 8);
        frame.getRootPane().setBorder(border);

        tableModel = new DefaultTableModel(new String[]{"ID", "Content"}, 0);
        table = new JTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        frame.add(scrollPane, BorderLayout.CENTER);

        addButton = createButton("Add", "add");
        deleteButton = createButton("Delete", "delete");
        undoButton = createButton("Undo", "undo");
        redoButton = createButton("Redo", "redo");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(redoButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        
        JTableHeader header = table.getTableHeader();
        Border headerBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
        header.setBorder(headerBorder);
    }

    public void run() {
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "add":
                showAddDialog();
                break;
            case "delete":
                deleteSelectedRow();
                break;
            case "undo":
                if (itemTable.canUndo()) {
                    itemTable.undo();
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(frame, "Nothing to undo", "Undo", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case "redo":
                if (itemTable.canRedo()) {
                    itemTable.redo();
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(frame, "Nothing to redo", "Redo", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            default:
                break;
        }
    }

    private JButton createButton(String text, String command) {
        JButton button = new JButton(text);
        button.setActionCommand(command);
        button.addActionListener(this);
        return button;
    }

    private void showAddDialog() {
        JTextField idField = new JTextField();
        JTextField contentField = new JTextField();
        Object[] fields = {"ID:", idField, "Content:", contentField};
        int option = JOptionPane.showConfirmDialog(frame, fields, "Add Item", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String id = idField.getText();
            String content = contentField.getText();
            ItemDescrip item = new ItemDescrip(id, content);
            itemTable.addItem(item);
            refreshTable();
        }
    }



    private void deleteSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            itemTable.deleteItem(selectedRow);
            refreshTable();
        }
    }

   

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<ItemDescrip> itemList = itemTable.getItemList();
        for (ItemDescrip item : itemList) {
            Object[] rowData = {item.getId(), item.getContent()};
            tableModel.addRow(rowData);
        }
    }
}
