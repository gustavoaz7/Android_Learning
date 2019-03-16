package com.gustavoaz7.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture[] birds;
	private Texture background;
	private Texture pipeBottom;
	private Texture pipeTop;
	private Random rand;
	private BitmapFont font;

	private int screenWidth;
	private int screenHeight;
	private int move = 0;
	private int fallSpeed = 0;
	private int birdX;
	private int birdY;
	private int pipeX;
	private int passageHeight;
	private int passagePositionY;
	private int score = 0;

	private float index = 0;
	private float deltaTime;

	private boolean gameStarted = false;
	private boolean scored = false;

	@Override
	public void create () {
		batch = new SpriteBatch();
		birds = new Texture[3];
		birds[0] = new Texture("bird1.png");
		birds[1] = new Texture("bird2.png");
		birds[2] = new Texture("bird3.png");
		background = new Texture("background.png");
		pipeBottom = new Texture("pipe_bottom_lg.png");
		pipeTop = new Texture("pipe_top_lg.png");
		rand = new Random();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(6);
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		birdX = (int) (screenWidth * 0.2);
		birdY = screenHeight / 2 -50;
		pipeX = screenWidth;
		passageHeight = 300;
	}

	@Override
	public void render () {
		deltaTime = Gdx.graphics.getDeltaTime();

		index += deltaTime * 9;
		if (index >= 3) {
			index = 0;
		}

		if (!gameStarted) {
			if (Gdx.input.justTouched()) {
				gameStarted = true;
			}
		} else {
			fallSpeed++;
			pipeX -= deltaTime * 600;



			if (Gdx.input.justTouched()) {
				fallSpeed = -20;
			}

			if (birdY > 0) {
				birdY -= fallSpeed;
			}

			if (pipeX < -pipeBottom.getWidth()) {
				pipeX = screenWidth;
				passagePositionY = rand.nextInt(400) - 200;
				scored = false;
			}

			if (pipeX < birdX) {
				if (!scored) {
					score++;
					scored = true;
				}
			}
		}

		batch.begin();
		batch.draw(background, 0, 0, screenWidth, screenHeight);
		batch.draw(pipeTop, pipeX, screenHeight / 2 + passageHeight / 2 + passagePositionY);
		batch.draw(pipeBottom, pipeX, 0 - passageHeight / 2 + passagePositionY);
		batch.draw(birds[(int) index], birdX, birdY);
		font.draw(batch, String.valueOf(score), screenWidth / 2, (int) (screenHeight - screenHeight * 0.1));
		batch.end();
	}

}
