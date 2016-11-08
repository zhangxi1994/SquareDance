package sqdance.g6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import sqdance.sim.Point;

public class Player implements sqdance.sim.Player {
	// Dancer's relationship map
	// map.get(1) for soulmate
	// map.get(2) for friends
	// map.get(3) for strangers
	private List<HashMap<Integer, Set<Integer>>> dancerRelationship = null;
	private int[][] Enjoyment = null;
	private int[] idleTurns = null;
	private int dancerNumber;
	private int roomSide;
	private Random random;

	@Override
	public void init(int d, int room_side) {
		// TODO Auto-generated method stub
		// There are d total participants. Each participant has f friends.
		dancerNumber = d;
		roomSide = room_side;
		random = new Random();
		Enjoyment = new int[d][d];
		idleTurns = new int[d];
		dancerRelationship = new LinkedList<HashMap<Integer, Set<Integer>>>();

		for (int i = 0; i < d; i++) {
			idleTurns[i] = 0;
			for (int j = 0; j < d; j++) {
				Enjoyment[i][j] = i == j ? 0 : -1;
			}
		}
	}

	@Override
	public Point[] generate_starting_locations() {
		// TODO Auto-generated method stub
		Point[] L = new Point[dancerNumber];
		for (int i = 0; i < dancerNumber; i++) {
			int b = 1000 * 1000 * 1000;
			double x = random.nextInt(b + 1) * roomSide / b;
			double y = random.nextInt(b + 1) * roomSide / b;
			L[i] = new Point(x, y);
			dancerRelationship.add(new HashMap<Integer, Set<Integer>>());
		}
		return L;
	}

	@Override
	// dancers: array of locations of the dancers
	// scores: cumulative score of the dancers
	// partner_ids: index of the current dance partner. -1 if no dance partner
	// enjoyment_gained: integer amount (-5,0,3,4, or 6) of enjoyment gained in
	// the most recent 6-second interval
	public Point[] play(Point[] dancers, int[] scores, int[] partner_ids, int[] enjoyment_gained) {
		// TODO Auto-generated method stub
		Point[] Instructions = new Point[dancerNumber];

		return null;
	}

	public void updateDancerInfoFindTargets(Point[] dancers, int[] scores, int[] partner_ids, int[] enjoyment_gained) {
		for (int i = 0; i < dancerNumber; i++) {
			Point self = dancers[i];
			int j = partner_ids[i];
			HashMap<Integer, Set<Integer>> relationshipMap = dancerRelationship.get(i);
			if (enjoyment_gained[i] > 0) { // previously had a dance partner
				idleTurns[i] = 0;
				if (Enjoyment[i][j] == -1) {
					Enjoyment[i][j] = total_enjoyment(enjoyment_gained[i]) - enjoyment_gained[i];
				} else
					Enjoyment[i][j] -= enjoyment_gained[i];
				if (!relationshipMap.containsKey(enjoyment_gained[i]))
					relationshipMap.put(enjoyment_gained[i], new HashSet<>());
				relationshipMap.get(enjoyment_gained[i]).add(j);
				// relationshipMap.put(enjoyment_gained[i], value)
			}
			
		}
	}

	private int total_enjoyment(int enjoyment_gained) {
		switch (enjoyment_gained) {
		case 3:
			return 60; // stranger
		case 4:
			return 200; // friend
		case 6:
			return 10800; // soulmate
		default:
			throw new IllegalArgumentException("Not dancing with anyone...");
		}
	}

}
