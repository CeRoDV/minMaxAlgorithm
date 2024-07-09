import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App
{
    static int duracion; //Duración del programa únicamente

    static int[][] interrupciones; //Se guardan las interrupciones, en [] se guarda la prioridad de la interrupción, en [][1] el segundo de esta, en [][2] los segundos que dura y en [][3] el índice

    static int[] segundos; //Se guarda la prioridad; por lo tanto, la interrupción, actual
    
    static int[][] segundoPlano = new int[30][2]; //Se guardan las interrupciones que han sido interrumpidas, su prioridad en [], los segundos restantes en [][1] y el índice en [][2]
    
    static String[] interrupcionesNombre = new String[20];
    static int[] interrupcionesPrioridades = new int[20];

    
    public static void main(String[] args)
    {
        //Se ordena el arreglo de interrupciones por los segundos

        Segundo(0, 0, 16, duracion, 19);
    }

    public static void Segundo(int seg, int indiceInterrupcion, int prioridad, int segRestantes, int indice)
    {
        if(interrupciones[indiceInterrupcion][1] == seg - 1)
        {
            if(interrupciones[indiceInterrupcion][0] > prioridad)
            {
                //Guardar el proceso actual a segundo plano

                if(segRestantes > 0)
                {
                    int x = 0;
                    while(segundoPlano[x][0] == 0)
                    {
                        x += 1;
                    }

                    segundoPlano[x][0] = prioridad;
                    segundoPlano[x][1] = segRestantes;
                    segundoPlano[x][2] = indice;
                }

                prioridad = interrupciones[indiceInterrupcion][0];
                segRestantes = interrupciones[indiceInterrupcion][2];
                indice = interrupciones[indiceInterrupcion][3];
            }
            else
            {
                //Guarda la interrupción en segundo plano
                int x = 0;
                while(segundoPlano[x][0] == 0)
                {
                    x += 1;
                }
                segundoPlano[x][0] = interrupciones[indiceInterrupcion][0];
                segundoPlano[x][1] = interrupciones[indiceInterrupcion][2];
            }
            indiceInterrupcion += 1;
        }
        else
        {
            //Se ordena el arreglo de segundo plano según la prioridad de manera ascendente

            if(segundoPlano[0][0] < prioridad)
            {
                prioridad = segundoPlano[0][0];
                segRestantes = segundoPlano[0][1];
                segundoPlano[0][0] = 0;
                segundoPlano[0][1] = 0;
            }
        }
        segRestantes -= 1;
        segundos[seg] = prioridad;
        if(seg < segundos.length)
        {
            Segundo(seg + 1, indiceInterrupcion, prioridad, segRestantes, indice);
        }
    }

    private static DefaultTableModel Mtabla;
    private static JTable tabla;
    
    public void Frame(){

        //NOMBRE DE PRIORIDADES
        interrupcionesNombre[0] = "Reloj del Sistema";
        interrupcionesNombre[1] = "Teclado";
        interrupcionesNombre[2] = "Reloj (tics) en tiempo real CMOS";
        interrupcionesNombre[3] = "Red";
        interrupcionesNombre[4] = "sonido";
        interrupcionesNombre[5] = "puerto SCSI";
        interrupcionesNombre[6] = "Libre";
        interrupcionesNombre[7] = "Libre";
        interrupcionesNombre[8] = "PS-Mouse";
        interrupcionesNombre[9] = "Co-Pocesador Matematico";
        interrupcionesNombre[10] = "Canal IDE primario (Disco)";
        interrupcionesNombre[11] = "Libre (otros adaptadores)";
        interrupcionesNombre[12] = "COM2";
        interrupcionesNombre[13] = "COM4";
        interrupcionesNombre[14] = "COM1";
        interrupcionesNombre[15] = "COM3";
        interrupcionesNombre[16] = "Libre";
        interrupcionesNombre[17] = "Controlador Floppy - Diskette";
        interrupcionesNombre[18] = "Puerto Paralelo - Impresora";
        interrupcionesNombre[19] = "Programa";
        
        //PRIORIDAD DE INTERRUPCIONES
        interrupcionesPrioridades[0] = 1;
        interrupcionesPrioridades[1] = 2;
        interrupcionesPrioridades[2] = 3;
        interrupcionesPrioridades[3] = 4;
        interrupcionesPrioridades[4] = 4;
        interrupcionesPrioridades[5] = 4;
        interrupcionesPrioridades[6] = 5;
        interrupcionesPrioridades[7] = 6;
        interrupcionesPrioridades[8] = 7;
        interrupcionesPrioridades[9] = 8;
        interrupcionesPrioridades[10] = 9;
        interrupcionesPrioridades[11] = 10;
        interrupcionesPrioridades[12] = 11;
        interrupcionesPrioridades[13] = 11;
        interrupcionesPrioridades[14] = 12;
        interrupcionesPrioridades[15] = 12;
        interrupcionesPrioridades[16] = 13;
        interrupcionesPrioridades[17] = 14;
        interrupcionesPrioridades[18] = 15;
        interrupcionesPrioridades[19] = 16;


        JFrame frame = new JFrame("Gestion de interrupciones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocation(0,0);

        JPanel panel = new JPanel();

        frame.add(panel);

        JLabel lbl = new JLabel("Aniadir una interrupcion");
        lbl.setVisible(true);

        panel.add(lbl);

        final JComboBox<String> cb = new JComboBox<String>(interrupcionesNombre);
        cb.setVisible(true);
        panel.add(cb);

        JButton button = new JButton("Aniadir");
        button.setBounds(50, 50, 200, 30);
        
    }
}
