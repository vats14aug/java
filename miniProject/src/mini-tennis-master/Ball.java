import java.awt.Graphics2D;
import java.awt.Rectangle;
public class Ball {
    private static final int DIAMETER = 30;
    private int x;
    private int y;
    private int xspd;
    int yspd;
    private MiniTennis game;
    public Ball(MiniTennis game) {
        this.game = game;
        xspd = 1;
        yspd = 1;
    }

    void move() {
        if (x + xspd < 0)
            xspd = game.speed;
        if (x + xspd > game.getWidth() - DIAMETER)
            xspd = -game.speed;
        if (y + yspd < 0)
            yspd = game.speed;
        if (y + yspd > game.getHeight() - DIAMETER) {
            game.speed = 1;
            game.gameOver();
        }
        if (collision()) {
            yspd = -game.speed;
            y = game.racquet.getY() - DIAMETER;
            game.speed++;
        }
        x += xspd;
        y += yspd;
    }

    public void paint(Graphics2D g) {
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }

    private boolean collision() {
        return game.racquet.getBounds().intersects(getBounds());
    }
}