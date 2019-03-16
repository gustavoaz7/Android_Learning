package com.gustavoaz7.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture[] birds;
	private Texture background;

	private int screenWidth;
	private int screenHeight;
	private int move = 0;
	private int fallSpeed = 0;
	private int x;
	private int y;

	private float delta = 0;


	@Override
	public void create () {
		batch = new SpriteBatch();
		birds = new Texture[3];
		birds[0] = new Texture("bird1.png");
		birds[1] = new Texture("bird2.png");
		birds[2] = new Texture("bird3.png");
		background = new Texture("background.png");
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		x = screenWidth / 2 - 50;
		y = screenHeight / 2 -50;
	}

	@Override
	public void render () {
		delta += Gdx.graphics.getDeltaTime() * 3;
		if (delta >= 3) {
			delta = 0;
		}

		if (Gdx.input.justTouched()) {
			fallSpeed = -20;
		}

		fallSpeed++;
		y -= fallSpeed;

		batch.begin();
		batch.draw(background, 0, 0, screenWidth, screenHeight);
		batch.draw(birds[(int) delta], x, y);
		batch.end();
	}

}
