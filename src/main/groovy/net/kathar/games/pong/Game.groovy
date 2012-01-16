package net.kathar.games.pong

import org.newdawn.slick.BasicGame
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Color

class Game extends BasicGame {

    def height = 640
    def width = 800
    Paddle leftPaddle
    Paddle rightPaddle
    Ball ball
    def leftScore = 0
    def rightScore = 0

    public Game() {
        super("Pong")
    }

    @Override
    void render(GameContainer container, Graphics g) {
        renderBackground g

        renderCenterLine g

        renderScore g

        leftPaddle.render g
        rightPaddle.render g
        ball.render g
    }

    void renderScore(Graphics g) {
        g.setColor(Color.lightGray)
        g.drawString("Score: ${leftScore} : ${rightScore}", 200f, 15f)

    }

    void renderCenterLine(Graphics g) {
        //draw a bunch of faint blocky stripes down the center
        g.setColor(Color.gray)
        float increment = height / 9
        0.upto(10) {
            g.fillRect((width / 2) - 5 as float, increment * it as float, 10f, 30f)
        }
    }

    private void renderBackground(Graphics g) {
        g.setColor(Color.darkGray)
        g.fillRect(0, 0, width, height)


    }

    @Override
    void init(GameContainer container) {
        startRound()
    }

    void startRound() {
        leftPaddle = new Paddle(centerX: 15, centerY: 200, color: Color.lightGray)
        rightPaddle = new Paddle(centerX: 785, centerY: 200, color: Color.lightGray)
        ball = new Ball(centerX: 400, centerY: 200, velocityX: -95, velocityY: 100)
    }

    @Override
    void update(GameContainer container, int delta) {
        leftPaddle.update delta
        rightPaddle.update delta
        ball.update delta

        if (ball.touchesTopOrBottom()) {
            ball.bounceOffTopOrBottom()
        }

        if (ball.touchesPaddle(leftPaddle)) {
            ball.bounceOffPaddle()
        } else if (ball.touchesPaddle(rightPaddle)) {
            ball.bounceOffPaddle()
        }

        if (ball.isLeftOfBoard()) {
            rightWinsRound()
        } else if (ball.isRightOfBoard()) {
            leftWinsRound()
        }
    }

    void leftWinsRound() {
        leftScore++
        startRound()
    }

    void rightWinsRound() {
        rightScore++
        startRound()
    }

    void keyPressed(int key, char c) {
        if (c == 'w') {
            leftPaddle.moveUp()
        } else if (c == 's') {
            leftPaddle.moveDown()
        } else if (c == 'o') {
            rightPaddle.moveUp()
        } else if (c == 'l') {
            rightPaddle.moveDown()
        }
    }

    void keyReleased(int keyValue, char c) {
        if (keyValue == 1) {
            println "bye"
            System.exit(0);
        }

        if (c == 'w') {
            leftPaddle.stopMovingUp()
        } else if (c == 's') {
            leftPaddle.stopMovingDown()
        } else if (c == 'o') {
            rightPaddle.stopMovingUp()
        } else if (c == 'l') {
            rightPaddle.stopMovingDown()
        }
    }
}
