package seu.vCampus.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class SetTableColor {
	public static void makeFace(JTable table) {

        try
        {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer()
            {
              public Component getTableCellRendererComponent(JTable table,
                  Object value, boolean isSelected, boolean hasFocus,
                  int row, int column)
              {
                if(row%2 == 1)
                  setBackground(Color.white); //���������е�ɫ
                else if(row%2 == 0)
                  setBackground(new Color(232,242,254));  //����ż���е�ɫ
                return super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column); }
            };
                for(int i = 0; i < table.getColumnCount(); i++) {
                  table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
          }
        }
        catch (Exception ex)
        {
          ex.printStackTrace();
        }

}
}
