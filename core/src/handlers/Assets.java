package handlers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {

	public static final AssetManager manager = new AssetManager();

	// Waiter related resources
	public static final String WAITER_IDLE = "character_sprites/Waiter_Idle.atlas";
	public static final String WAITER_WALKING = "character_sprites/Waiter_Walking.atlas";
	public static final String WAITER_DYING = "character_sprites/Waiter_Dying.atlas";

	// Guest related resources
	public static final String GUEST1 = "character_sprites/Guest1.atlas";
	public static final String GUEST1_ANGRY = "character_sprites/Guest1_Kick.atlas";
	public static final String GUEST1_ORDERING = "character_sprites/Guest1_Jump.atlas";
	public static final String GUEST2 = "character_sprites/Guest2.atlas";
	public static final String GUEST2_ORDERING = "character_sprites/Guest2_Jump.atlas";
	public static final String GUEST2_ANGRY = "character_sprites/Guest2_Kick.atlas";
	public static final String GUEST3 = "character_sprites/Guest3.atlas";
	public static final String GUEST3_ANGRY = "character_sprites/Guest3_Kick.atlas";
	public static final String GUEST3_ORDERING = "character_sprites/Guest3_Jump.atlas";
	public static final String GUEST4 = "character_sprites/Guest4.atlas";
	public static final String GUEST4_ANGRY = "character_sprites/Guest4_Kick.atlas";
	public static final String GUEST4_ORDERING = "character_sprites/Guest4_Jump.atlas";
	public static final String GUEST5 = "character_sprites/Guest5.atlas";
	public static final String GUEST5_ANGRY = "character_sprites/Guest5_Kick.atlas";
	public static final String GUEST5_ORDERING = "character_sprites/Guest5_Jump.atlas";
	public static final String GUEST6 = "character_sprites/Guest6_Idle.atlas";
	public static final String GUEST6_ANGRY = "character_sprites/Guest6_Kick.atlas";
	public static final String GUEST6_ORDERING = "character_sprites/Guest6_Jump.atlas";
	public static final String GUEST7 = "character_sprites/Guest7_Idle.atlas";
	public static final String GUEST7_ANGRY = "character_sprites/Guest7_Kick.atlas";
	public static final String GUEST7_ORDERING = "character_sprites/Guest7_Jump.atlas";
	public static final String GUEST8 = "character_sprites/Guest8_Idle.atlas";
	public static final String GUEST8_ANGRY = "character_sprites/Guest8_Kick.atlas";
	public static final String GUEST8_ORDERING = "character_sprites/Guest8_Jump.atlas";
	public static final String GUEST9 = "character_sprites/Guest9_Idle.atlas";
	public static final String GUEST9_ANGRY = "character_sprites/Guest9_Kick.atlas";
	public static final String GUEST9_ORDERING = "character_sprites/Guest9_Jump.atlas";
	public static final String KING = "character_sprites/King.atlas";
	public static final String COOK = "character_sprites/Cook_Idle.atlas";
	public static final String KING_KICK = "character_sprites/King_Kick.atlas";
	public static final String KING_JUMP = "character_sprites/King_Jump.atlas";
    public static final String SKELETON_IDLE = "character_sprites/Skeleton_Idle.atlas";
    public static final String SKELETON_KICKING = "character_sprites/Skeleton_Kicking.atlas";
    public static final String SKELETON_JUMPING = "character_sprites/Skeleton_Jump.atlas";


	// dish related resources
    public static final String PIZZA = "Food/pizza.png";
	public static final String BEER = "Food/beer.png";
    public static final String POMMES = "Food/pommes.png";
    public static final String CHICKEN = "Food/chicken.png";
    public static final String FISH = "Food/fish.png";
    public static final String COUNTER = "Map/counter.png";
    public static final String COUNTER_TURNED = "Map/counterturned.png";
    public static final String SKULL = "skull.png";

	// map related resources
    public static final String TABLE = "Map/tablewithcandle.png";
    public static final String CHAIR = "Map/chair1.png";
	public static final String COIN = "coin.png";
	public static final String BUBBLE = "bubble.png";
    public static final String Level1 = "level1.png";
    public static final String Level2 = "level2.png";
    public static final String Level3 = "level3.png";
    public static final String Level4 = "level4.png";
    public static final String Level5 = "level5.png";

    //level files
    public static final String LEVEL1JSON = "Map/level1.json";
    public static final String LEVEL2JSON = "Map/level2.json";
    public static final String LEVEL3JSON = "Map/level3.json";

	// font related resources
	public static final String MONEYFONT = "goldfont.fnt";
    public static final String LEVELFONT = "levelfont.fnt";

	// screen related resources
	public static final String ARROWS = "arrowkeys.png";
	public static final String SKEY = "Keyboard_White_S.png";
	public static final String DKEY = "Keyboard_White_D.png";
	public static final String MOVE = "move.png";
	public static final String SERVE = "serve.png";
	public static final String DISCARD = "discard.png";
	public static final String LOADING = "loading.png";
	public static final String DONTLOSE = "dontlose.png";
	public static final String NEXTACTIVE = "nextactive.png";
	public static final String NEXTPASSIVE = "nextpassive.png";
	public static final String EXITACTIVE = "exitactive.png";
	public static final String EXITPASSIVE = "exitpassive.png";
	public static final String LEVELCLEARED = "levelcleared.png";
	public static final String LEVELFAILED = "levelfailed.png";
	public static final String ENTER = "continue.png";
	public static final String SCORE = "score.png";
	public static final String SKELETON = "skeleton.png";
	public static final String NOMONEY = "nomoney.png";
	public static final String BONUSLEVEL = "bonuslevel.png";
	public static final String KINGINFO = "kinginfo.png";
	public static final String SKELETONINFO = "skeletoninfo.png";
	public static final String BONUSINSTRUCTIONS = "bonusinstruction.png";
	public static final String LOGO = "getserved_logo.png";

	// sound realted resources
	public static final String BELL = "Audio/bell.wav";
	public static final String FANFARE = "Audio/fanfare.wav";
	public static final String PAY = "Audio/pay.wav";
	public static final String HI1 = "Audio/hi_alex.wav";
	public static final String YO1 = "Audio/yo_alex.wav";
	public static final String GREETINGS1 = "Audio/greetings_alex.wav";
	public static final String NO1 = "Audio/no_alex.wav";
	public static final String NOTFORME1 = "Audio/notforme_alex.wav";
	public static final String HELLO2 = "Audio/hello_ian.wav";
    public static final String HEY2 = "Audio/hey_ian.wav";
	public static final String WHATSUP2 = "Audio/whatsup_ian.wav";
	public static final String NO2 = "Audio/no_ian.wav";
	public static final String NOTFORME2 = "Audio/notforme_ian.wav";
	public static final String HELLO3 = "Audio/hello_sean.wav";
	public static final String HOWDY3 = "Audio/howdy_sean.wav";
	public static final String GREETINGS3 = "Audio/greetings_sean.wav";
	public static final String NO3 = "Audio/no_sean.wav";
	public static final String NOTFORME3 = "Audio/notforme_sean.wav";
	public static final String HEY4 = "Audio/hey_karen.wav";
	public static final String HEYA4 = "Audio/heya_karen.wav";
	public static final String NO4 = "Audio/no_karen.wav";
	public static final String NOTFORME4 = "Audio/notforme_karen.wav";
	public static final String GREETINGS5 = "Audio/greetings_meghan.wav";
	public static final String YO5 = "Audio/yo_meghan.wav";
	public static final String NO5 = "Audio/no_meghan.wav";
	public static final String NOTFORME5 = "Audio/notforme_meghan.wav";
	public static final String MUSIC1 = "Audio/music1.wav";
	public static final String MUSIC2 = "Audio/music2.wav";
	public static final String APPLAUSE = "Audio/applause.wav";
	public static final String BOO = "Audio/boo.wav";
	public static final String EVILLAUGH = "Audio/evillaugh.wav";
	


	public static void load() {
		manager.load(COUNTER, Texture.class);
        manager.load(COUNTER_TURNED, Texture.class);
		manager.load(PIZZA, Texture.class);
		manager.load(BEER, Texture.class);
		manager.load(FISH, Texture.class);
		manager.load(POMMES, Texture.class);
		manager.load(CHICKEN, Texture.class);
		manager.load(SKULL, Texture.class);
		manager.load(TABLE, Texture.class);
		manager.load(WAITER_IDLE, TextureAtlas.class);
		manager.load(WAITER_WALKING, TextureAtlas.class);
		manager.load(WAITER_DYING, TextureAtlas.class);
		manager.load(GUEST1, TextureAtlas.class);
		manager.load(GUEST2, TextureAtlas.class);
		manager.load(GUEST3, TextureAtlas.class);
		manager.load(GUEST4, TextureAtlas.class);
		manager.load(GUEST5, TextureAtlas.class);
		manager.load(GUEST6, TextureAtlas.class);
		manager.load(GUEST7, TextureAtlas.class);
		manager.load(GUEST8, TextureAtlas.class);
		manager.load(GUEST9, TextureAtlas.class);
		manager.load(GUEST1_ANGRY, TextureAtlas.class);
		manager.load(GUEST1_ORDERING, TextureAtlas.class);
		manager.load(GUEST6_ANGRY, TextureAtlas.class);
		manager.load(GUEST6_ORDERING, TextureAtlas.class);
		manager.load(GUEST7_ANGRY, TextureAtlas.class);
		manager.load(GUEST7_ORDERING, TextureAtlas.class);
		manager.load(GUEST8_ANGRY, TextureAtlas.class);
		manager.load(GUEST8_ORDERING, TextureAtlas.class);
		manager.load(GUEST9_ANGRY, TextureAtlas.class);
		manager.load(GUEST9_ORDERING, TextureAtlas.class);
		manager.load(GUEST2_ORDERING, TextureAtlas.class);
		manager.load(GUEST2_ANGRY, TextureAtlas.class);
		manager.load(GUEST3_ANGRY, TextureAtlas.class);
		manager.load(GUEST3_ORDERING, TextureAtlas.class);
		manager.load(GUEST4_ANGRY, TextureAtlas.class);
		manager.load(GUEST4_ORDERING, TextureAtlas.class);
		manager.load(GUEST5_ANGRY, TextureAtlas.class);
		manager.load(GUEST5_ORDERING, TextureAtlas.class);
		manager.load(KING_KICK, TextureAtlas.class);
		manager.load(KING_JUMP, TextureAtlas.class);
		manager.load(KING, TextureAtlas.class);
		manager.load(COOK, TextureAtlas.class);
        manager.load(SKELETON_IDLE, TextureAtlas.class);
        manager.load(SKELETON_KICKING, TextureAtlas.class);
        manager.load(SKELETON_JUMPING, TextureAtlas.class);
		manager.load(MONEYFONT, BitmapFont.class);
        manager.load(LEVELFONT, BitmapFont.class);
		manager.load(NEXTACTIVE, Texture.class);
		manager.load(NEXTPASSIVE, Texture.class);
		manager.load(EXITACTIVE, Texture.class);
		manager.load(EXITPASSIVE, Texture.class);
		manager.load(LEVELCLEARED, Texture.class);
		manager.load(LEVELFAILED, Texture.class);
		manager.load(SCORE, Texture.class);
		manager.load(BONUSINSTRUCTIONS, Texture.class);
		manager.load(BONUSLEVEL, Texture.class);
		manager.load(SKELETON, Texture.class);
		manager.load(NOMONEY, Texture.class);
		manager.load(CHAIR, Texture.class);
		manager.load(BUBBLE, Texture.class);
        manager.load(Level1, Texture.class);
        manager.load(Level2, Texture.class);
        manager.load(Level3, Texture.class);
        manager.load(Level4, Texture.class);
        manager.load(Level5, Texture.class);
		manager.load(BELL, Sound.class);
		manager.load(FANFARE, Sound.class);
		manager.load(PAY, Sound.class);
		manager.load(HI1, Sound.class);
		manager.load(YO1, Sound.class);
		manager.load(GREETINGS1, Sound.class);
		manager.load(NO1, Sound.class);
		manager.load(NOTFORME1, Sound.class);
		manager.load(HELLO2, Sound.class);
		manager.load(HEY2, Sound.class);
		manager.load(WHATSUP2, Sound.class);
		manager.load(NO2, Sound.class);
		manager.load(NOTFORME2, Sound.class);
		manager.load(HELLO3, Sound.class);
		manager.load(HOWDY3, Sound.class);
		manager.load(GREETINGS3, Sound.class);
		manager.load(NO3, Sound.class);
		manager.load(NOTFORME3, Sound.class);
		manager.load(HEYA4, Sound.class);
		manager.load(HEY4, Sound.class);
		manager.load(NO4, Sound.class);
		manager.load(NOTFORME4, Sound.class);
		manager.load(GREETINGS5, Sound.class);
		manager.load(YO5, Sound.class);
		manager.load(NO5, Sound.class);
		manager.load(NOTFORME5, Sound.class);
		manager.load(MUSIC1, Sound.class);
		manager.load(MUSIC2, Sound.class);
		manager.load(APPLAUSE, Sound.class);
		manager.load(BOO, Sound.class);
		manager.load(EVILLAUGH, Sound.class);
		manager.load(KINGINFO, Texture.class);
		manager.load(SKELETONINFO, Texture.class);

    }
	public static void loadingScreen() {
		manager.load(ARROWS, Texture.class);
		manager.load(SKEY, Texture.class);
		manager.load(DKEY, Texture.class);
		manager.load(MOVE, Texture.class);
		manager.load(SERVE, Texture.class);
		manager.load(DISCARD, Texture.class);
		manager.load(LOADING, Texture.class);
		manager.load(COIN, Texture.class);
		manager.load(DONTLOSE, Texture.class);
		manager.load(ENTER, Texture.class);
		manager.load(LOGO, Texture.class);
	}

	public static void dispose() {
		manager.dispose();
	}

}
