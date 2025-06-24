package main;

import pokemon.PokemonManager;
import state.GameStateManager;


import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Screen Settings
    final int originalTileSize = 16;
    final int scale =3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    int FPS = 60;

    //Class Settings
    public KeyHandler keyH = new KeyHandler();
    private final GameStateManager stateManager = new GameStateManager(this);
    public PokemonManager pokemonManager = new PokemonManager();
    Thread gameThread;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        stateManager.setState("Overworld");
        stateManager.setupState();
        pokemonManager.generateNewPokemon(25);
    }
    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
            if(timer >= 1000000000){
                //System.out.println("FPS:" + drawCount);
                timer = 0;
            }
        }
    }

    public void update() {
        if(keyH.Bpressed)
        {
            stateManager.setState("Battle");
        }
        stateManager.update();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if(stateManager.getCurrentState() != null){stateManager.draw(g2);}

        g2.dispose();
    }
}
