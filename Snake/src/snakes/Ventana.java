package snakes;


import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import javazoom.jl.decoder.JavaLayerException;

/**
 * Ventana del juego CLASE JFRAME
 *
 * @author https://www.youtube.com/watch?v=Fh5fFE5h8tw
 */
public class Ventana extends javax.swing.JFrame {

   
    public ImageSnake serpiente;
    public static int numCuadros ;
    public Hilo3 musica;
    Thread thread2;
    Reproductor player;
    static int puntuacion = 0;
    static String titulo = "SNAKE";
    static int nivel;
  
 

    /**
     * Constructor de Ventana
     */
    public Ventana() throws FileNotFoundException, JavaLayerException {

        initComponents();

        this.setLocation(330, 0);
        this.setSize(635, 680);
        this.getContentPane().setLayout(null);
        this.setResizable(false);

        
    
        

            while (numCuadros < 20 || numCuadros > 60) {
        try {
            
                numCuadros = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el tamaño del tablero (20-60 Cuadros)"));
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Introduzca un valor entre 20 y 60");
        }
    }
    
 while (nivel != 1 && nivel != 2 && nivel != 3) {
        try {
           
                nivel = Integer.parseInt(JOptionPane.showInputDialog("Nivel || 1 || 2 || 3 ||"));
                if (nivel == 1) {
                    Hilo2.pausa = 120;
                }
                else if (nivel == 2) {
                    Hilo2.pausa = 80;
                }
                else if (nivel == 3) {
                    Hilo2.pausa = 40;
                } else {
                    JOptionPane.showMessageDialog(this, "Introduzca 1, 2 o 3");
                }
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Introduzca 1, 2 o 3");
        }
 }
             
            this.setTitle(titulo + "  || Nivel:" + nivel + " || Tablero:" + numCuadros + " cuadros");
            //instanciamos a la serpiente (600pixels, 30 cuadros)

            serpiente = new ImageSnake(600, numCuadros);
            serpiente.setLayout(null);
            serpiente.setBounds(15, 15, 600, 600);//https://docs.oracle.com/javase/8/docs/api/java/awt/Component.html#setBounds-int-int-int-int-
            serpiente.setOpaque(false);//el panel de la serpiente tapa el panel de fondo. Lo hacemos transparente.
            //https://docs.oracle.com/javase/8/docs/api/javax/swing/JComponent.html#setOpaque-boolean-
            this.add(serpiente);//agregamos la serpiente a la "this" clase
            serpiente.setVisible(true);
            //instanciar fondo (600pixels, 30 cuadros)
//            fondo = new Fondo(600, numCuadros);
//
//            fondo.setBounds(15, 15, 600, 600);//https://docs.oracle.com/javase/8/docs/api/java/awt/Component.html#setBounds-int-int-int-int-
//            this.add(fondo);//agregamos el fondo a la "this" clase
//            fondo.setVisible(true);

            this.requestFocus(true);
            System.out.println("Jpanels Cargados");
            musica = new Hilo3(player);//instanciamos objeto de clase runnable y le pasamos el Reproductor
            System.out.println("Instanciamos objeto de clase runnable y le pasamos el Reproductor");
            thread2 = new Thread(musica);//instaciomos objeto de clase Thread y le pasamos el objeto tipo runnable como parametro
            System.out.println("Instaciomos objeto de clase Thread y le pasamos el objeto tipo runnable como parametro");
            thread2.start();//iniciamos el hilo
            System.out.println("Musica cargada");
            this.validate();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(java.awt.Color.black);
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:

            case KeyEvent.VK_LEFT:
                if (!"derecha".equals(serpiente.direccion)) {
                    serpiente.cambiarDireccion("izquierda");
                    break;
                }
            case KeyEvent.VK_RIGHT:
                if (!"izquierda".equals(serpiente.direccion)) {
                    serpiente.cambiarDireccion("derecha");
                    break;
                }
            case KeyEvent.VK_DOWN:
                if (!"abajo".equals(serpiente.direccion)) {
                    serpiente.cambiarDireccion("arriba");
                    break;
                }
            case KeyEvent.VK_UP:
                if (!"arriba".equals(serpiente.direccion)) {
                    serpiente.cambiarDireccion("abajo");
                    break;
                }
            default:
                break;
        }

    }//GEN-LAST:event_formKeyPressed

    private void formPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_formPropertyChange

    }//GEN-LAST:event_formPropertyChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws FileNotFoundException, JavaLayerException {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        Ventana ventana=new Ventana();
        ventana.setVisible(true);
        
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
