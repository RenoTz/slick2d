package game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

public class WindowGame extends BasicGame {

	private GameContainer container;
	private TiledMap map;

	private float xPos = 100;
	private float yPos = 100;

	private int direction = 0;
	private boolean moving = false;
	private final Animation[] animations = new Animation[8];

	public WindowGame() {

		super("Lesson 1 :: WindowGame");
	}

	public static void main(String[] args) throws SlickException {

		new AppGameContainer(new WindowGame(), 640, 480, false).start();
	}

	@Override
	public void init(GameContainer container) throws SlickException {

		this.container = container;

		// initialisation de la carte
		this.map = new TiledMap("map/exemple3.tmx");

		// initialisation des animations du personnage
		final SpriteSheet spriteSheet = new SpriteSheet("sprites/character.png", 64, 64);
		this.animations[0] = this.loadAnimation(spriteSheet, 0, 1, 0);
		this.animations[1] = this.loadAnimation(spriteSheet, 0, 1, 1);
		this.animations[2] = this.loadAnimation(spriteSheet, 0, 1, 2);
		this.animations[3] = this.loadAnimation(spriteSheet, 0, 1, 3);
		this.animations[4] = this.loadAnimation(spriteSheet, 1, 9, 0);
		this.animations[5] = this.loadAnimation(spriteSheet, 1, 9, 1);
		this.animations[6] = this.loadAnimation(spriteSheet, 1, 9, 2);
		this.animations[7] = this.loadAnimation(spriteSheet, 1, 9, 3);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {

		// suivi de la camera
		g.translate(container.getWidth() / 2 - this.xPos, container.getHeight() / 2 - this.yPos);
		// affichage de la carte
		this.map.render(0, 0);
		// affiche du personnage (animations) et d'une ellipse en dessous de lui
		g.setColor(new Color(0, 0, 0, .5f));
		g.fillOval(this.xPos - 16, this.yPos - 8, 32, 16);
		g.drawAnimation(this.animations[this.direction + (this.moving ? 4 : 0)], this.xPos - 32, this.yPos - 60);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {

		// deplacement du personnage
		if (this.moving) {
			switch (this.direction) {
			case 0:
				this.yPos -= .1f * delta;
				break;
			case 1:
				this.xPos -= .1f * delta;
				break;
			case 2:
				this.yPos += .1f * delta;
				break;
			case 3:
				this.xPos += .1f * delta;
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void keyPressed(int key, char c) {

		// touches de deplacement du personnage
		switch (key) {
		case Input.KEY_Z:
			this.direction = 0;
			this.moving = true;
			break;
		case Input.KEY_Q:
			this.direction = 1;
			this.moving = true;
			break;
		case Input.KEY_S:
			this.direction = 2;
			this.moving = true;
			break;
		case Input.KEY_D:
			this.direction = 3;
			this.moving = true;
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(int key, char c) {

		// le personnage s'arrete quand le joueur est inactif
		this.moving = false;

		// touche ECHAP pour quitter le jeu
		if (Input.KEY_ESCAPE == key) {
			this.container.exit();
		}
	}

	// ----------------
	// METHODES PRIVEES
	// ----------------

	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {

		final Animation animation = new Animation();
		for (int x = startX; x < endX; x++) {
			animation.addFrame(spriteSheet.getSprite(x, y), 100);
		}
		return animation;
	}

}
