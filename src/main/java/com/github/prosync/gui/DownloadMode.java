package com.github.prosync.gui;

import com.github.prosync.domain.Camera;
import com.github.prosync.logic.CameraController;
import com.github.prosync.logic.GUIServices;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;
import com.github.prosync.domain.Constants;

/**
 * Created by oystein on 30.11.2014.
 */
public class DownloadMode extends JPanel {
    GUIServices services = new GUIServices();


    public DownloadMode() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {

                }

                setSize(800, 600);
                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.anchor = GridBagConstraints.WEST;
                gbc.weightx = 1;
                gbc.gridwidth = 2;
                gbc.gridy = 0;
                gbc.gridx = 0;

                gbc.fill = GridBagConstraints.HORIZONTAL;

                System.out.println(gbc.gridx + " " + gbc.gridy);

                final DTMDownloadMode dtmDownload = new DTMDownloadMode();

                JTable files = new JTable(dtmDownload);

                JScrollPane jspFiles = new JScrollPane(files);

                ArrayList<String> list = new ArrayList<String>();
                list.add("Test1.MP4");
                list.add("Test2.MP4");
                list.add("Test3.MP4");
                list.add("Test4.JPEG");

                dtmDownload.addRows(services.getDownloadableFiles());


                add(jspFiles, gbc);

                JButton chooseAll = new JButton("Velg alle");
                JButton chooseNone = new JButton("Velg ingen");
                JButton download = new JButton("Last ned");

                chooseAll.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dtmDownload.checkAll(true);
                    }
                });
                chooseNone.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dtmDownload.checkAll(false);
                    }
                });
                download.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser chooser = new JFileChooser();
                        chooser.setCurrentDirectory(new java.io.File("."));
                        chooser.setDialogTitle("Velg lagringsplass");
                        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        chooser.setAcceptAllFileFilterUsed(false);

                        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                            System.out.println(dtmDownload.getChecked() + " " +String.valueOf(chooser.getSelectedFile()));

                            services.downloadFiles(dtmDownload.getChecked(), String.valueOf(chooser.getSelectedFile()));
                        } else {
                            System.out.println("No Selection ");
                        }

                    }
                });

                gbc.gridwidth = 1;

                gbc.gridy++;
                add(chooseAll, gbc);

                gbc.gridx++;
                add(download, gbc);

                gbc.gridx--;
                gbc.gridy++;
                add(chooseNone, gbc);

            }
        });
    }


    private class DTMDownloadMode extends DefaultTableModel{



        public DTMDownloadMode(){
            addColumn("Navn");
            addColumn("Filtype");
            addColumn("Dato");
            addColumn("Last ned");
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            if (column == Constants.DOWNLOAD_COLUMN) return true;
                return false;
        }

        @Override
        public Class<?> getColumnClass(int column) {
            switch (column) {
                case  Constants.NAME_COLUMN:
                    return String.class;
                case  Constants.FILETYPE_COLUMN:
                    return String.class;
                case  Constants.DATE_COLUMN:
                    return String.class;
                case  Constants.DOWNLOAD_COLUMN:
                    return Boolean.class;
            }
            return null;
        }


        public void checkAll(boolean bol){
            for(int i = 0; i < getRowCount(); i++){
                setValueAt(bol, i,  Constants.DOWNLOAD_COLUMN);
            }
        }

        public ArrayList<String> getChecked(){
            ArrayList<String> checkedRows = new ArrayList<String>();
            for(int i = 0; i < getRowCount(); i++){
                Vector rowVector = (Vector) dataVector.elementAt(i);
                if ((Boolean) rowVector.elementAt( Constants.DOWNLOAD_COLUMN)) {
                    checkedRows.add((String)rowVector.elementAt(Constants.NAME_COLUMN));
                }
            }
            return checkedRows;
        }

        public void addRows(ArrayList<String> list){
            for(String s:list){
                if(s.contains("JPEG"))
					addRow(new Object[]{s, ".JPEG", "ToBeImplemented(Maybe)", false});
				else if(s.contains("MP4"))
					addRow(new Object[]{s, ".MP4", "ToBeImplemented(Maybe)", false});
                else
					addRow(new Object[]{s, "Unknown", "ToBeImplemented(Maybe)", false});
            }
        }
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Kofigurer");
                frame.setPreferredSize(new Dimension(800, 600));
                JTabbedPane config = new JTabbedPane();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                config.add(new DownloadMode());
                frame.add(config);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
