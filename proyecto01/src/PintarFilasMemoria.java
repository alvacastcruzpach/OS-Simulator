
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Andy
 */
public class PintarFilasMemoria extends DefaultTableCellRenderer {
    
   @Override
   public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col){
       super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);
       
       /*switch(table.getValueAt(row,1).toString()){
           case "Listo":
               setBackground(Color.CYAN);
           case "En ejecucion":
               setBackground(Color.YELLOW);
           case "Nuevo":
               setBackground(Color.GRAY);
           case "Terminado":
               setBackground(Color.GREEN);
           case "Bloqueado":
               setBackground(Color.RED);
       
       }
               */
       if(table.getValueAt(row,4).toString().equals("Lleno")){
           setBackground(Color.CYAN);
           setForeground(Color.BLACK);
           
       }else{
              setBackground(Color.WHITE);
              setForeground(Color.BLACK);
           
           }
           
       
      
       
       return this;
   }
}
