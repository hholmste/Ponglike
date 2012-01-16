package net.kathar.games.pong

import org.newdawn.slick.Graphics
import org.newdawn.slick.Color
import org.newdawn.slick.geom.Shape
import org.newdawn.slick.geom.Circle

public class Ball {

    float centerX
    float centerY

    //velocity = pixels per seconds... i guess
    float velocityX
    float velocityY

    void render(Graphics g) {
        g.setColor(Color.lightGray)
        g.fill(shape())
    }

    void update(long delta) {
        float time = delta / 1000.0f
        centerX += velocityX * time
        centerY += velocityY * time
    }

    boolean isLeftOfBoard() {
        return centerX < 0
    }

    boolean isRightOfBoard() {
        return centerX > 800
    }

    boolean touchesTopOrBottom() {
        return centerY <= 3 || centerY >= 637
    }

    void bounceOffTopOrBottom() {
        velocityY *= -1.075
    }

    void bounceOffPaddle() {
        velocityX *= -1.075
    }

    Shape shape() {
        new Circle(centerX, centerY, 6f)
    }

    boolean touchesPaddle(Paddle paddle) {
       paddle.shape().intersects(this.shape())
    }
}
