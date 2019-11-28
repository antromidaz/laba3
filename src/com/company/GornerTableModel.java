package com.company;
import javax.swing.table.AbstractTableModel;

public class GornerTableModel extends AbstractTableModel {

    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;


    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    public int getColumnCount() {
        return 4;
    }
    public int getRowCount(){

        // Вычислить количество точек между началом и концом отрезка
        // исходя из шага табулирования
        return new Double(Math.ceil((to-from)/step)).intValue()+1;
    }




    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Значение X";
            case 1:
                return "Значение многочлена";
            case 2:
                return "Значение многочлена при обратных коэффициентах";
            default:
                return "Разница 2 и 3 столбцов";

        }
    }
        public Class<?> getColumnClass(int col) {
            // И в 1-ом и во 2-ом столбце находятся значения типа Double
            return Double.class;
        }


    public Object getValueAt(int row, int col) {
        double x = from + step * row;
        if (col == 0) {
            return x;
        } else if (col == 1) {
            Double result = 0.0;
            for (int i = 0; i < coefficients.length; i++) {
                double ren = 1;
                for (int j = coefficients.length - 1 - i; j >= 1; j--) {
                    ren *= x;
                }
                result += ren * coefficients[i];
            }
            return result;
        } else if (col == 2) {
            Double result = 0.0;
            for (int i = 0; i < coefficients.length; i++) {
                double ren = 1;
                for (int j = coefficients.length - 1 - i; j >= 1; j--) {
                    ren *= x;
                }
                result += ren * coefficients[coefficients.length - 1 - i];
            }
            return result;
        } else {
            Double result1 = 0.0;
            for (int i = 0; i < coefficients.length; i++) {
                double ren = 1;
                for (int j = coefficients.length - 1 - i; j >= 1; j--) {
                    ren *= x;
                }
                result1 += ren * coefficients[i];
            }

            Double result2 = 0.0;
            for (int i = 0; i < coefficients.length; i++) {
                double ren = 1;
                for (int j = coefficients.length - 1 - i; j >= 1; j--) {
                    ren *= x;
                }
                result2 += ren * coefficients[coefficients.length - 1 - i];
            }

            return result1 - result2;
        }
    }
}
