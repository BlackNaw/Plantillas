package control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import comun.MyCamera;
import comun.MyDebug;
import global.Constantes;
import global.MakingACage;
import input.InputDesktop;
import input.InputMovil;

public class Game {

	Stage stage;
	SpriteBatch batch;
	World world;
	InputAdapter input;
	MyCamera myCamera;
	MyDebug myDebug;
	GestorActores gestor;
	Contacto contacto;
	Logica logica;

	public Game(World world) {
		this.world = world;
		batch = new SpriteBatch();
		myDebug = new MyDebug();

		// Aqui el actor en caso de querer que la camara siga al actor
		myCamera = new MyCamera();
		stage = new Stage(myCamera.viewport, batch);
		gestor = new GestorActores(world, stage);

		////////////////////////////
		if (Constantes.ANDROID) {
			input = new InputMovil();
		} else {
			input = new InputDesktop(gestor.getActorUno());
			((InputDesktop)input).addObserver(myDebug);
		}
		/////////////////////////////
		Gdx.input.setInputProcessor(input);
		contacto = new Contacto();
		this.world.setContactListener(contacto);
		new MakingACage(world, 0, Gdx.graphics.getWidth()/Constantes.PIXELS_TO_METERS, Gdx.graphics.getHeight()/Constantes.PIXELS_TO_METERS, 1);
		logica=new Logica(gestor);
	}

	/**
	 * Se encarga de actuar
	 */
	public void act() {
		stage.act();
		logica.act();
		if (Constantes.ANDROID)
			((InputMovil)input).act();
	}

	/**
	 * Para actualizar y dibujar Primero actualizar siempre y luego dibujar
	 */
	public void render() {
		myCamera.update();
		myCamera.draw(batch);
		stage.draw();
		myDebug.draw(world, batch);
	}

	/**
	 * Elimina los elementos de memoria
	 */
	public void dispose() {
		batch.dispose();
		stage.dispose();
		myDebug.dispose();
	}
}
