package Progetto_Java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Scanner;

import javax.swing.*;
import com.fazecast.jSerialComm.SerialPort;

import org.jfree.ui.RefineryUtilities;




public class Main  {

    //Variabile per porta seriale
    static SerialPort chosenPort;

    public static void main(String[] args) {

        //creazione finestra
        JFrame window = new JFrame();
        window.setTitle("Lettura dati scheda Arduino");
        window.setSize(300,300);
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //componente con bottone e lista e modificabile drop down
        JComboBox<String> portList = new JComboBox<>();
        JButton connectButton = new JButton("Connect");
        JPanel Panel = new JPanel();
        Panel.add(portList);
        Panel.add(connectButton);
        window.add(Panel, BorderLayout.CENTER);
        

        //vettore di porte serial per lettura porte,aggiungiamo le porte con add item
        SerialPort[] portNames = SerialPort.getCommPorts();
        for(int i=0; i<portNames.length; i++) {
            portList.addItem(portNames[i].getSystemPortName());
        }
        //lettura bottone
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(connectButton.getText().equals("Connect")) {
                    chosenPort = SerialPort.getCommPort(portList.getSelectedItem().toString());
                    
                    if(chosenPort.openPort()) {
                        connectButton.setText("Disconnect");
                        portList.setEnabled(false);
                    }
                    //thread che gestisce l'arrivo dei dati e stampa i dati
                    Thread thread = new Thread() {
                        @Override 
                        public void run() {
                            Scanner scanner = new Scanner(chosenPort.getInputStream());
                            while(scanner.hasNextLine()) {
                                try {
                                    String line = scanner.nextLine();
                                    //torna valori finchè verranno inviati dati,analizza la stringa come numero decimale
                                    System.out.println(line);
                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                            scanner.close();
                        }
                    };
                    thread.start();
                }else {
                    chosenPort.closePort();
                    portList.setEnabled(true);
                    connectButton.setText("Connect");
                }
            }

        });
        window.setVisible(true);


        //creazione grafico 
        Graphics chart = new Graphics("Temperature" ,"Temperature comparison in May(Catania)");
        
        //si adatta al layout
         chart.pack( );
         RefineryUtilities.centerFrameOnScreen( chart );
         chart.setVisible( true );


    }




    
    
        
}