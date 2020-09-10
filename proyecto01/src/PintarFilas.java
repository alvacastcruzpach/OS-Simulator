
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Andy
 */
public class PintarFilas extends DefaultTableCellRenderer {
    
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
       if(table.getValueAt(row,2).toString().equals("Listo")){
           setBackground(Color.CYAN);
           setForeground(Color.BLACK);
           
       }else{
           if(table.getValueAt(row,2).toString().equals("En ejecución")){
              setBackground(Color.YELLOW);
              setForeground(Color.BLACK);
           }else{
               if(table.getValueAt(row,2).toString().equals("Nuevo")){
                   setBackground(Color.GRAY);
                   setForeground(Color.BLACK);
               }else{
                   if(table.getValueAt(row,2).toString().equals("Terminado")){
                       setBackground(Color.GREEN);
                       setForeground(Color.BLACK);
                   }else{
                       setBackground(Color.RED);
                       setForeground(Color.WHITE);
                   }
               }
               
           }
           
       }
       
       return this;
   }
}
