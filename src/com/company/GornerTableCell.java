package com.company;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

class GornerTableCellRenderer implements TableCellRenderer {
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    private String needle = null;
    private String searchFrom=null;
    private String searchTo=null;
    private DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();

    GornerTableCellRenderer() {
        formatter.setMaximumFractionDigits(5);
        formatter.setGroupingUsed(false);
        DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
        panel.add(label);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        String formattedDouble = formatter.format(value);
        label.setText(formattedDouble);
        if((col==1||col==2) && needle!=null && needle.equals(formattedDouble)){
            panel.setBackground(Color.RED);
            label.setForeground(Color.BLACK);
        }
        else if(((col==1 || col==3) && row%2!=0) || ((col==0|| col==2) && row%2==0)){
            panel.setBackground(Color.BLACK);
            label.setForeground(Color.WHITE);
        }
        else {
            panel.setBackground(Color.WHITE);
            label.setForeground(Color.BLACK);
        }

        if(searchFrom!=null && searchTo!=null &&
                Double.parseDouble(formattedDouble) >= Double.parseDouble(searchFrom) &&
                Double.parseDouble(formattedDouble) <= Double.parseDouble(searchTo)) {
            panel.setBackground(Color.GREEN);
            if(((col==1 || col==3) && row%2!=0) || ((col==0|| col==2) && row%2==0))
                label.setForeground(Color.BLACK);
            else
                label.setForeground(Color.WHITE);
        }
        return panel;
    }

    void setNeedle(String needle) {
        this.needle = needle;
    }

    public void setdiap(String searchFrom, String searchTo) {
        this.searchFrom = searchFrom;
        this.searchTo = searchTo;
    }

}