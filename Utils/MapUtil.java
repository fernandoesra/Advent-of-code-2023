package Utils;

public class MapUtil {

	public static <T> boolean inBounds(T[][] map, int mapY, int mapX) {
		boolean inBounds = true;
		int realHeight = map.length;
		int realWidth = map[0].length;
		if ((mapY < 0 || mapY >= realHeight) || (mapX < 0 || mapX >= realWidth))
			inBounds = false;
		return inBounds;
	}

	public static <T> boolean exists(T[][] map, T object) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j].equals(object))
					return true;
			}
		}
		return false;
	}

	public static <T> T getAtPosition(T[][] map, int mapY, int mapX) {
		T position = null;
		if (inBounds(map, mapY, mapX))
			position = map[mapY][mapX];
		return position;
	}

	public static <T> T getAtPosition(T[][] map, Coordinates coordinates) {
		T position = null;
		if (inBounds(map, coordinates.getMapY(), coordinates.getMapX()))
			position = map[coordinates.getMapY()][coordinates.getMapX()];
		return position;
	}

	public static <T> Coordinates findCoordinates(T[][] map, T object) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j].equals(object))
					return new Coordinates(i, j);
			}
		}
		return null;
	}

	public static class Coordinates {
		int mapY;
		int mapX;

		public Coordinates(int mapY, int mapX) {
			this.mapY = mapY;
			this.mapX = mapX;
		}

		public int getMapY() {
			return mapY;
		}

		public void setMapY(int mapY) {
			this.mapY = mapY;
		}

		public int getMapX() {
			return mapX;
		}

		public void setMapX(int mapX) {
			this.mapX = mapX;
		}

	}

}
