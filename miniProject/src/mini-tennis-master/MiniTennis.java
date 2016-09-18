import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//button
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

//DB Dependencies
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MiniTennis extends JPanel {
    Racquet racquet = new Racquet(this);
    Ball ball = new Ball(this);
    int speed = 1;
    int highScore = 0;
    int logScore = 0;
    static ResultSet resultSet;
    int i = 0;
    static int saveScore[] = new int[500];
    static String name[] = new String[500];
    static TableDemo tbl = new TableDemo();

    private int getScore() {
        return speed - 1;
    }
    public MiniTennis() {
        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }
            public void keyPressed(KeyEvent e) {
                racquet.keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                racquet.keyReleased(e);
            }
        });
        setFocusable(true);
    }

    public static void main(String[] args) throws InterruptedException {

        // TODO Auto-generated method stub
        MiniTennis game = new MiniTennis();
        JFrame frame = new JFrame();
        frame.add(game);
        frame.setSize(300, 400);
        /*
		//Options
				Object[] options = {"Retry",
		                    "Exit",
		                    "Scores"};
		int n = JOptionPane.showOptionDialog(frame,
		    "Would you like some green eggs to go "
		    + "with that ham?",
		    "GAME OVER",
		    JOptionPane.YES_NO_CANCEL_OPTION,
		    JOptionPane.QUESTION_MESSAGE,
		    null,
		    options,
		    options[2]);
		    if(n == JOptionPane.CANCEL_OPTION)
		    {
		    	
		      //Table
			//TableDemo tbl = new TableDemo();
			    javax.swing.SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		                tbl.createAndShowGUI();
		            }
		        });
		     
		    //Table	
		    }
    //options
    */
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(10);
        }

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paint(g2d);
        racquet.paint(g2d);

        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.drawString(String.valueOf(getScore()), 10, 30);
        highScore = greaterOf(getScore(), highScore);
        g2d.drawString("High : " + String.valueOf(highScore), 50, 30);
        logScore = getScore();
    }

    private void move() {
        ball.move();
        racquet.move();
    }

    public int greaterOf(int a, int b) {
        if (a > b)
            return a;
        else
            return b;
    }

    public void gameOver() {


        //DB Connection
        //Class.forName("org.sqlite.JDBC");

        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.


            //statement.executeUpdate("DROP TABLE IF EXISTS score");
            //statement.executeUpdate("CREATE TABLE score (score INTEGER, name STRING)");
            //statement.executeUpdate("CREATE TABLE scores (score INTEGER, name STRING, unique (score, name))");

            //statement.executeUpdate("DROP TABLE IF EXISTS scores");
            //statement.executeUpdate("CREATE TABLE scores (score INTEGER, name STRING, unique (score, name))");

            /*
            int ids [] = {1,2,3,4,5};
            String names [] = {"Peter","Pallar","William","Paul","James Bond"};

            for(int i=0;i<ids.length;i++){
                 statement.executeUpdate("INSERT INTO person values(' "+ids[i]+"', '"+names[i]+"')");   
            }
            */

            //statement.executeUpdate("UPDATE person SET name='Peter' WHERE id='1'");
            //statement.executeUpdate("DELETE FROM person WHERE id='1'");
            String inputValue = "";
            while (inputValue.isEmpty()) {
                inputValue = JOptionPane.showInputDialog("Please Enter Your Name");
            }
            System.out.println("input = " + inputValue);
            /*
            ResultSet resultSet1 = statement.executeQuery("SELECT * FROM scores WHERE score="+logScore+" AND name = "+inputValue+" ");
            if(resultSet != null)
            */
            try {
                statement.executeUpdate("INSERT INTO scores values(' " + logScore + " ', ' " + inputValue + " ')");
            } catch (Exception e) {
                System.out.println(e);
            }

            //resultSet1 = null;
            //resultSet = statement.executeQuery("SELECT * from score");
            resultSet = statement.executeQuery("SELECT * FROM scores ORDER BY score DESC LIMIT 5");
            //tbl.printConn();
            i = 0;
            while (resultSet.next()) {
                // iterate & read the result set
                /*
              System.out.println("name = " + resultSet.getString("name"));
              System.out.println("id = " + resultSet.getInt("id"));
              */
                System.out.println("score = " + resultSet.getInt("score"));
                System.out.println("name = " + resultSet.getString("name"));
                saveScore[i] = resultSet.getInt("score");
                name[i] = resultSet.getString("name");
                //System.out.println("saveScore = " + saveScore[i]);
                i++;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();

                getScoreArr();
                tbl.printConn();
                //printScore();
            } catch (SQLException e) { // Use SQLException class instead.          
                System.err.println(e);
            }

        }



        //DB Updated
        //Table
        //TableDemo tbl = new TableDemo();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //tbl.createAndShowGUI();
            }
        });

        //Table
        /*
		int n = JOptionPane.showConfirmDialog(
			    this,
			    "Would you like to play again?",
			    "Game Over",
			    JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			ball.yspd = -1;
		} else {
			System.exit(ABORT);
		}
	*/
        //Options
        Object[] options = {
            "Retry",
            "Exit",
            "Scores"
        };
        int n = JOptionPane.showOptionDialog(this,
            "What would you like to do",
            "Game Over",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[2]);
        if (n == JOptionPane.YES_OPTION) {
            ball.yspd = -1;
        }

        if (n == JOptionPane.CANCEL_OPTION) {

            //Table
            //TableDemo tbl = new TableDemo();
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    tbl.createAndShowGUI();
                    //Thread.sleep(20);
                }
            });

            //Table
            try {
                Thread.sleep(150000);
            } catch (InterruptedException e) {
                System.out.println("got interrupted!");
            }
        }

        if (n == JOptionPane.NO_OPTION) {
            System.exit(ABORT);
        }
        //options




    }

    public static ResultSet getScores() {
        return resultSet;
    }

    public static String[] getNameArr() {
        //System.out.println("get Name = " + name[0]);
        return name;
    }
    public static int[] getScoreArr() {
            return saveScore;
        }
        /*
        public void printScore()
        {
        	System.out.println("get Scored = " + saveScore[0]);
        	System.out.println("get Named = " + name[0]);
        }
        */
}