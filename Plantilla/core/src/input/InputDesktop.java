package input;

import java.util.ArrayList;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;

import actores.MyActor;
import interfaz.IObservable;
import interfaz.IObservador;

public class InputDesktop extends InputAdapter implements IObservable {

	private ArrayList<IObservador> observadores = new ArrayList<IObservador>();
	private boolean ctrl = false;
	private Actor actor;

	/**
	 * Si el teclado actua con el actor
	 * 
	 * @param actor
	 */
	public InputDesktop(Actor actor) {
		this.actor = actor;
	}

	public InputDesktop() {
	}

	@Override
	public boolean keyDown(int keycode) {
		comprobarCombinacion(keycode);
		if (keycode == Keys.UP)
			((MyActor) actor).moverArriba();
		if (keycode == Keys.DOWN)
			((MyActor) actor).moverAbajo();
		if (keycode == Keys.LEFT)
			((MyActor) actor).moverIzquierda();
		if (keycode == Keys.RIGHT)
			((MyActor) actor).moverDerecha();

		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.UP||keycode == Keys.DOWN||keycode == Keys.LEFT||keycode == Keys.RIGHT)
			((MyActor) actor).parar();
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return true;
	}

	public void comprobarCombinacion(int keycode) {
		if (ctrl) {
			if (keycode == Input.Keys.R) {
				notifyObservers();
			}
		}
		if (keycode == Input.Keys.CONTROL_LEFT)
			ctrl = true;
	}

	@Override
	public void addObserver(IObservador observador) {
		observadores.add(observador);
	}

	@Override
	public void removeObserver(IObservador observador) {
		observadores.remove(observador);
	}

	@Override
	public void notifyObservers() {
		for (IObservador observador : observadores) {
			observador.update();
		}
	}

}
