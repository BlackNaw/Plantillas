package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import control.Game;
import global.Constantes;

public class MyGdxGame extends ApplicationAdapter {

	World world;

	Game game;

	@Override
	public void create() {
		world = new World(new Vector2(Constantes.GRAVEDAD_X, Constantes.GRAVEDAD_Y), true);
		game = new Game(world);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.step(1f / 60f, 6, 2);

		// Actua
		game.act();

		// Dibuja
		game.render();
	}

	@Override
	public void dispose() {
		world.dispose();
		game.dispose();
	}
}
