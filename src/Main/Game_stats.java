package Main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

// This public class has all important constants of the game;
// This is an OBSERVER of the Player because it changes by
// the state of it;
public class Game_stats extends Observer {
    // The general game panel where the image is render;
    public static GamePanel panel_game;

    // CONSTANTS FOR SIZES OF THE PLAY SCREEN
    public static int unit;
    public static int offset;
    public static int game_score_height;

    public static int number_per_width;
    public static int number_per_height;

    public static int default_x_position;
    public static int default_y_position;

    public static int game_width;
    public static int game_height;

	public static int game_score_heigth;

    // VARIABLES THAT CHANGE WITH THE GAME AND THEIR DEFAULT VALUES
    public static int default_score;
    public static int current_score;

    public static int default_time;
    public static int current_time;

    public static int default_enemies_number;
    public static int current_enemies_number;

    public static int default_points_number;
    public static int current_points_number;
    public static int point_value;

    // Lists of enemies and points that update with the game
    public static List<Enemy> enemies_list;
    public static List<Point> points_list;

    // Matrix with elements corresponding to the entities on the table
    public static char[][] entities_matrix;

    // Variable that defines how many tiles the player can jump
    public static int jumping_tiles;

    // Constructor to initialize the static variables
    public Game_stats() {
        unit = 30;
        offset = 10;
        game_score_height = (unit * 3) / 2;

        number_per_width = 35;
        number_per_height = 20;

        default_x_position = number_per_width / 2;
        default_y_position = number_per_height / 2;

        game_width = number_per_width * unit + (number_per_width + 1) * offset;
        game_height = game_score_height + number_per_height * unit + (number_per_height + 1) * offset;

        default_score = 0;
        current_score = 0;

        default_time = 30;
        current_time = 30;

        default_enemies_number = 50;
        current_enemies_number = 50;

        default_points_number = 5;
        current_points_number = 5;
        point_value = 10;
		game_score_heigth = (unit*3)/2;

        enemies_list = new ArrayList<>();
        points_list = new ArrayList<>();

        entities_matrix = new char[number_per_width][number_per_height];

        jumping_tiles = 1;
    }

    static {
        new Game_stats();
    }

    // Function that generates an integer number between min and max
    public static int randomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    @Override
    // The update logic of the stats, based on the Player state
    public void update() {
        // Possible Player states:
        // IDLE
        // COLLECTING_POINT
        // DYING;

        if (this.player.getState() == this.player.IDLE) {
            // Nothing because it's OK to move
        } else if (this.player.getState() == this.player.COLLECTING_POINT) {
            for (Point point : Game_stats.points_list) {
                // Search for the point that was collected
                if (point.x_tile == this.player.x_tile && point.y_tile == this.player.y_tile) {
                    // Remove it
                    points_list.remove(point);

                    // Add points and time for collecting a Point Entity
                    Game_stats.current_score += Game_stats.point_value;
                    break;
                }
            }
            this.player.setState(this.player.IDLE);
        } else if (this.player.getState() == this.player.DYING) {
			if(0 != current_score)
			{
				System.out.print(String.format("Score: %d\n" , current_score));
			}
			current_enemies_number = default_enemies_number;
            current_points_number = default_points_number;

            current_score = default_score;
            current_time = 0; // Default time

            this.player.x_tile = default_x_position;
            this.player.y_tile = default_y_position;

            this.player.setState(this.player.IDLE);
        } else if (this.player.getState() == this.player.JUMPING) {
            jumping_tiles = 2;
            this.player.color = Color.GREEN.darker();
        } else if (this.player.getState() == this.player.JUMPED) {
            jumping_tiles = 1;
            this.player.setState(this.player.IDLE);
            this.player.color = Color.GREEN;
        }
    }
}
