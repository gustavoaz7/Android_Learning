package com.gustavoaz7.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture[] birds;
	private Texture background;
	private Texture pipeBottom;
	private Texture pipeTop;
	private Texture gameOverText;
	private Random rand;
	private BitmapFont font;
	private BitmapFont restartMessage;
	private Circle birdCircle;
	private Rectangle pipeBottomRect;
	private Rectangle pipeTopRect;

	private int screenWidth;
	private int screenHeight;
	private int fallSpeed = 0;
	private int birdX;
	private int birdY;
	private int pipeX;
	private int passageHeight;
	private int passagePositionY;
	private int score = 0;

	private float index = 0;

	private boolean gameStarted = false;
	private boolean gameOver = false;
	private boolean scored = false;

	private OrthographicCamera camera;
	private Viewport viewport;
	private final int VIRTUAL_WIDTH = 768;
	private final int VIRTUAL_HEIGHT = 1024;

	@Override
	public void create () {
		batch = new SpriteBatch();
		birds = new Texture[3];
		birds[0] = new Texture("bird1.png");
		birds[1] = new Texture("bird2.png");
		birds[2] = new Texture("bird3.png");
		background = new Texture("background.png");
		pipeBottom = new Texture("pipe_bottom.png");
		pipeTop = new Texture("pipe_top.png");
		gameOverText = new Texture("game_over.png");
		rand = new Random();
		birdCircle = new Circle();
		pipeBottomRect = new Rectangle();
		pipeTopRect = new Rectangle();

		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(6);

		restartMessage = new BitmapFont();
		restartMessage.setColor(Color.WHITE);
		restartMessage.getData().setScale(4);

		camera = new OrthographicCamera();
		camera.position.set(VIRTUAL_WIDTH / 2, VIRTUAL_HEIGHT / 2, 0);
		viewport = new StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);

		screenWidth = VIRTUAL_WIDTH;
		screenHeight = VIRTUAL_HEIGHT;

		birdX = (int) (screenWidth * 0.2);
		birdY = screenHeight / 2 - birds[0].getHeight() / 2;
		pipeX = screenWidth;
		passageHeight = 300;
	}

	@Override
	public void render () {

		camera.update();

		// Clear previous frames
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		float deltaTime = Gdx.graphics.getDeltaTime();

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
			if (birdY > 0) {
				birdY -= fallSpeed;
			}

			if (!gameOver) {
				pipeX -= deltaTime * 600;

				if (Gdx.input.justTouched()) {
					fallSpeed = -15;
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
			} else {
                if (Gdx.input.justTouched()) {
                    gameOver = false;
                    gameStarted = false;
                    score = 0;
                    fallSpeed = 0;
                    birdY = screenHeight / 2 - birds[0].getHeight() / 2;
                    pipeX = screenWidth;
                }
			}

		}

		int pipeBottomY = - passageHeight / 2 + passagePositionY;
		int pipeTopY = screenHeight / 2 + passageHeight / 2 + passagePositionY;
		int centerWidth = screenWidth / 2;
		int centerHeight = screenHeight / 2;

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(background, 0, 0, screenWidth, screenHeight);
		batch.draw(pipeTop, pipeX, pipeTopY);
		batch.draw(pipeBottom, pipeX, pipeBottomY );
		batch.draw(birds[(int) index], birdX, birdY);
		font.draw(batch, String.valueOf(score), centerWidth, (int) (screenHeight - screenHeight * 0.1));
		if (gameOver) {
			batch.draw(gameOverText, (float) (centerWidth - gameOverText.getWidth() / 2), centerHeight);
			restartMessage.draw(batch, "Touch to restart the game",  centerWidth - 320, centerHeight - 30);
		}
		batch.end();

		birdCircle.set(birdX + (float) birds[0].getWidth() / 2, (float) (birdY + birds[0].getHeight() / 2), (float) birds[0].getWidth() / 2);
		pipeBottomRect.set(pipeX, pipeBottomY, pipeBottom.getWidth(), pipeBottom.getHeight());
		pipeTopRect.set(pipeX, pipeTopY, pipeTop.getWidth(), pipeTop.getHeight());


		if (Intersector.overlaps(birdCircle, pipeBottomRect) || Intersector.overlaps(birdCircle, pipeTopRect)
				|| birdY <= 0 || birdY >= screenHeight) {
			gameOver = true;
		}
	}

	@Override
    public void resize(int width, int height) {
	    viewport.update(width, height);
    }

}
