package sqdance.g6;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import sqdance.sim.Player;
import sqdance.sim.Point;

public class UltimatePlayer implements Player {
	private static double DANCER_DIS = 0.5;
	private static double OFFSET = 0.002;
	private static double REST_DIS = 0.1;

	private int d = -1;
	private int room_side = -1;

	private List<Point> danceTable = null;
	private int[] playerAtPosition = null;
	private int[] posOfPlayer = null;
	private int danceAreaColNum = -1;
	private int restAreaColNum = -1;
	private double dancerAreaEnd = -1;
	private int dancerPerCol = -1;
	private int restPersonPerCol = -1;
	private int[][] E = null;
	private int timer = -1;

	@Override
	public void init(int d, int room_side) {
		// TODO Auto-generated method stub
		this.d = d;
		this.room_side = room_side;
		this.danceTable = new LinkedList<>();
		this.playerAtPosition = new int[d];
		this.posOfPlayer = new int[d];
		this.timer = 0;

		E = new int[d][d];
		for (int i = 0; i < d; ++i) {
			for (int j = 0; j < d; ++j) {
				E[i][j] = i == j ? 0 : -1;
			}
		}

		divideDanceTable();
		drawGrid();
	}

	@Override
	public Point[] generate_starting_locations() {
		// TODO Auto-generated method stub
		Point[] L = new Point[d];
		for (int i = 0; i < danceTable.size(); i++) {
			L[i] = danceTable.get(i);
			posOfPlayer[i] = i;
			playerAtPosition[i] = i;
			//System.out.println(L[i].x + "," + L[i].y);
		}
		return L;
	}

	@Override
	public Point[] play(Point[] dancers, int[] scores, int[] partner_ids, int[] enjoyment_gained) {
		// TODO Auto-generated method stub
		Point[] instructions = new Point[d];

		// Default move: stay chill
		for (int i = 0; i < d; ++i)
			instructions[i] = new Point(0, 0);
		// move
		if (timer > 0 && timer % 20 == 0) {
			for (int i = 0; i < dancers.length; i++) {
				int pos = (posOfPlayer[i] + 1) % danceTable.size();
				posOfPlayer[i] = pos;
				playerAtPosition[pos] = i;
				Point target = danceTable.get(pos);
				instructions[i] = direction(subtract(target, dancers[i]));
			}
		}
		timer++;

		return instructions;
	}

	private void drawGrid() {
		List<Point[][]> cols = new ArrayList<>();
		double y_end = 0.0;
		// generate dancer columns
		for (double Y = 0; Y < dancerAreaEnd; Y += DANCER_DIS * 2 + OFFSET) {
			Point[][] currentCol = new Point[dancerPerCol][2];
			int i = 0;
			for (double X = 0; X < room_side && i < dancerPerCol; X += DANCER_DIS + OFFSET, i++) {
				currentCol[i][0] = new Point(X, Y);
				currentCol[i][1] = new Point(X, Y + DANCER_DIS);
			}
			cols.add(currentCol);
			y_end = Y;
		}
		// int dancerColNum = cols.size();
		// generate rest columns
		for (double Y = y_end + DANCER_DIS + OFFSET; Y < room_side; Y += 2 * REST_DIS) {
			Point[][] currentCol = new Point[restPersonPerCol][2];
			int i = 0;
			for (double X = 0; X < room_side && i < restPersonPerCol; X += REST_DIS, i++) {
				currentCol[i][0] = new Point(X, Y);
				currentCol[i][1] = new Point(X, Y + REST_DIS);
			}
			cols.add(currentCol);
		}
		// int totalColNum = cols.size();
		List<Point> set1 = new LinkedList<>();
		List<Point> set2 = new LinkedList<>();

		boolean topDown = true;
		for (Point[][] pointPairs : cols) {
			if (topDown) {
				for (int i = 0; i < pointPairs.length; i++) {
					set1.add(pointPairs[i][0]);
					set2.add(pointPairs[i][1]);
				}
				topDown = false;
			} else {
				for (int i = pointPairs.length - 1; i >= 0; i--) {
					set1.add(pointPairs[i][1]);
					set2.add(pointPairs[i][0]);
				}
				topDown = true;
			}
		}
		for (Point p : set1)
			danceTable.add(p);
		for (int i = set2.size() - 1; i >= 0; i--)
			danceTable.add(set2.get(i));
	}

	// calculate the estimated number of dancing points
	private void divideDanceTable() {
		int x = 41 - (d / 960);
		int y = 200 - 5 * x;
		if (y < 0)
			y = 1;
		dancerAreaEnd = x * DANCER_DIS;
		
		dancerPerCol = (int) (room_side / (DANCER_DIS + OFFSET));
		restPersonPerCol = (int) (room_side / REST_DIS);
		danceAreaColNum = x;
		restAreaColNum = y;
	}

	private Point direction(Point a) {
		double l = Math.hypot(a.x, a.y);
		if (l <= 1 + 1e-8)
			return a;
		else
			return new Point(a.x / l, a.y / l);
	}

	private Point subtract(Point a, Point b) {
		return new Point(a.x - b.x, a.y - b.y);
	}

}
