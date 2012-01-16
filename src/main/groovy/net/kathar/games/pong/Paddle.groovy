package net.kathar.games.pong

import org.newdawn.slick.Color
import org.newdawn.slick.Graphics
import org.newdawn.slick.geom.Shape
import org.newdawn.slick.geom.Rectangle

class Paddle {

    final float width = 10
    final float height = 75
    float centerX //left or right, really
    float centerY //height
    float velocityY //in pixels per second
    Color color

    void moveUp() {
        velocityY = -150
    }

    void moveDown() {
        velocityY = 150
    }

    void stop() {
        velocityY = 0
    }

    void render(Graphics g) {
        g.setColor(color)
        g.fill(shape())
    }

    void update(long delta) {
        float time = delta / 1000.0f
        centerY += velocityY * time

        if (centerY < 25 || centerY > 595) {
            stop()
        }
    }

    def shape = {->
        new Rectangle(centerX - 10 as float, centerY - 20 as float, width, height)
    }


    void stopMovingDown() {
        if (velocityY > 0) {
            stop()
        }
    }

    void stopMovingUp() {
        if (velocityY < 0) {
            stop()
        }
    }
}
