package net.kathar.games.pong

import org.newdawn.slick.AppGameContainer

class Pong {

    //TODO: splash screen and a break between rounds perhaps?
    public static void main(args) {
        println "playing pong"

        AppGameContainer appGameContainer = new AppGameContainer(new Game())
        appGameContainer.setDisplayMode(800, 640, false)
        appGameContainer.start()
    }
}
