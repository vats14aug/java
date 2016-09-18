/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
//package components;
/*
 * TableDemo.java requires no other files.
 */
//DB Dependencies
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.Dimension;
import java.awt.GridLayout;

/** 
 * TableDemo is just like SimpleTableDemo, except that it
 * uses a custom TableModel.
 */
public class TableDemo extends JPanel {


    public boolean DEBUG = false;

    public TableDemo() {

        super(new GridLayout(1, 0));
        //Import
        MiniTennis sync = new MiniTennis();
        //MiniTennis sync;
        //ResultSet score = sync.getScores();
        String name[] = new String[500];
        int scores[] = new int[500];
        name = sync.getNameArr();
        scores = sync.getScoreArr();
        //Import


        JTable table = new JTable(new MyTableModel(name, scores));
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    class MyTableModel extends AbstractTableModel {
        //Import
        //MiniTennis sync = new MiniTennis();
        //String name[] = new String[500];
        //int scores[] = new int[500];
        //name = sync.getNameArr();
        //scores = sync.getScoreArr();
        //Import
        public String[] columnNames = {
            "Name",
            "Score"
        };
        public Object[][] data = {
            {
                "Kathy",
                new Integer(5)
            },
            {
                "Kathy",
                new Integer(5)
            },
            {
                "Kathy",
                new Integer(5)
            },
            {
                "Kathy",
                new Integer(5)
            },
            {
                "Kathy",
                new Integer(5)
            }
        };
        public MyTableModel(String[] name, int[] scores) {
            columnNames[0] = "Name";
            columnNames[1] = "Score";

            for (int i = 0; i < 5; i++) {
                data[i][0] = name[i];
                data[i][1] = scores[i];
            }
            /*
			data[0][0] = "Vivek Kumar";
			data[0][1] = new Integer(50);
			*/
            /*
            {"John", "Doe", new Integer(3), new Boolean(true)},
            {"Sue", "Black", new Integer(2), new Boolean(false)},
            {"Jane", "White", new Integer(20), new Boolean(true)},
            {"Joe", "Brown",
            "Pool", new Boolean(false)}
            */
        }
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        /*
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
        */

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            /*
            if (col < 2) {
                return false;
            } else {
                return true;
            }
            */
            return false;
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i = 0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j = 0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Top 5 Scores");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Create and set up the content pane.
        TableDemo newContentPane = new TableDemo();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });


    }
    public void printConn() {
        //Import
        MiniTennis sync = new MiniTennis();
        //MiniTennis sync;
        //ResultSet score = sync.getScores();
        String name[] = new String[500];
        int scores[] = new int[500];
        try {
            name = sync.getNameArr();
            scores = sync.getScoreArr();

        } catch (Exception e) {
            System.out.println(e);
        }
        //Import

        try {

            for (int i = 0; i < 5; i++) {
                System.out.println("In TableDemo Name = " + name[i]);
                System.out.println("In TableDemo Score  = " + scores[i]);
            }

            //System.out.println("In TableDemo = " + name);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}