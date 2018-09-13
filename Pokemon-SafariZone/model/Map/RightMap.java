package model.Map;

import java.util.Random;

public class RightMap extends Map {

	public RightMap(String name1, boolean multiplayer1) {
		super(name1, multiplayer1);
		buildTheMap();
	}

	@Override
	protected void buildTheMap() {
		//insert top and bottom walls
		int coulumn,row=0;
		for(coulumn=0;coulumn<50;coulumn++) {
			mapLayout[coulumn][0] = new Wall(coulumn,0,false);
			mapLayout[coulumn][49] = new Wall(coulumn,49,false);
		}
		//insert left walls
		for(row=1;row<49;row++) {
			//create an opening for trainer to walk through the map
			if(row==25) {
				continue;
			}
			mapLayout[0][row] = new Wall(0,row,false);
		}
		//insert right walls
		for(row=1;row<49;row++) {
			mapLayout[49][row] = new Wall(49,row,false);
		}
		//Place flowers
		for(coulumn=9;coulumn<35;coulumn++) {
			mapLayout[coulumn][15] = new Tree(coulumn,15,false);
			mapLayout[coulumn][35] = new Tree(coulumn,35,false);
		}
		//and the flower sides
		for(row=15;row<35;row++) {
			mapLayout[9][row] = new Tree(9,row,false);
			mapLayout[34][row] = new Tree(34,row,false);
		}
		

		//Line the inside of the pathway with rocks
		for(row=16;row<35;row++) {
			mapLayout[10][row] = new LeftFence(10, row, false);
			mapLayout[33][row] = new RightFence(33, row, false);
			if(row==25) {
				//mapLayout[10][row] = new ShortGrass(10, row, true);
				mapLayout[10][row] = new ShortGrass(10, row, true);
				continue;
			}
		}
		for(coulumn=11;coulumn<33;coulumn++) {
			mapLayout[coulumn][16] = new TopFence(coulumn, 16, false);
			mapLayout[coulumn][34] = new TopFence(coulumn, 34, false);
		}
		
		// end of inside lining of rocks
		//Form the shores
		//top left corner
		mapLayout[10][16] = new TopLeftFence(10, 16, false);
		
		mapLayout[33][16] = new TopRightFence(33, 16, false);
		
		mapLayout[33][34] = new BottomRightFence(33, 34, false);
		mapLayout[10][34] = new BottomLeftFence(10, 34, false);
		
		
		//Form the Top of the island
		mapLayout[18][21] = new TopLeftIsland(18, 21, false);
		mapLayout[26][21] = new TopRightIsland(26, 21, false);
		
		for(coulumn=19;coulumn<26;coulumn++) {
			mapLayout[coulumn][21] = new    TopIsland(coulumn, 21, false);
			mapLayout[coulumn][29] = new BottomIsland(coulumn, 29, false);
		}
		
		//form the sides of the island
		
		for(row=22;row<29;row++) {
			mapLayout[18][row] = new LeftIsland(18, row, false);
			mapLayout[26][row] = new RightIsland(26, row, false);
			
		}
		mapLayout[18][29] = new BottomLeftIsland(18, 29, false);
		mapLayout[26][29] = new BottomRightIsland(26, 29, false);
		
		//put a picket fencen in on the island
		for(coulumn=20;coulumn<25;coulumn++) {
			mapLayout[coulumn][22] = new TopFence(coulumn, 22, false);
			//actually this is the bottom fence...they just look exactly the same
			mapLayout[coulumn][28] = new TopFence(coulumn, 28, false);
			
			
			mapLayout[coulumn][24] = new TopFence(coulumn, 24, false);
			mapLayout[coulumn][26] = new TopFence(coulumn, 26, false);
						
		}
		
		
		for(row=22;row<29;row++) {
			//right side fence
			mapLayout[25][row] = new RightFence(25, row, false);
			//left side fence
			if(row==25) {
				mapLayout[24][row] = new RightFence(24, row, false);
				continue;
			}
			mapLayout[19][row] = new LeftFence(19, row, false);
		}
		//corners
		mapLayout[19][22] = new TopLeftFence(19, 22, false);
		mapLayout[25][22] = new TopRightFence(25, 22, false);
		mapLayout[25][28] = new BottomRightFence(25, 28, false);
		mapLayout[19][28] = new BottomLeftFence(19, 28, false);
		//innermost fence corners
		mapLayout[24][24] = new TopRightFence(24, 24, false);
		mapLayout[24][26] = new BottomRightFence(24, 26, false);
		
		mapLayout[19][26] = new TopLeftFence(19, 26, false);
		mapLayout[19][24] = new BottomLeftFence(19, 24, false);
		//end of inner most corners
		
		for(coulumn=20;coulumn<25;coulumn++) {
			mapLayout[coulumn][23] = new Rock(coulumn, 23, false);
			mapLayout[coulumn][27] = new Rock(coulumn, 27, false);
		}
		mapLayout[19][25] = new ShortGrass(19, 25, true);
		mapLayout[20][25] = new ShortGrass(20, 25, true);
		mapLayout[21][25] = new ShortGrass(21, 25, true);
		mapLayout[22][25] = new ShortGrass(22, 25, true);
		//water around the island
		for(coulumn=17;coulumn<28;coulumn++) {
			mapLayout[coulumn][20] = new Water(coulumn, 20, false);
			mapLayout[coulumn][30] = new Water(coulumn, 30, false);
			
			mapLayout[coulumn][19] = new    TopSide(coulumn, 19, false);
			mapLayout[coulumn][31] = new BottomSide(coulumn, 31, false);
		}
		for(row=20;row<31;row++) {
			mapLayout[17][row] = new Water(17, row, false);
			mapLayout[27][row] = new Water(27, row, false);
			mapLayout[28][row] = new RightSide(28, row, false);
			if(row==25) {
				//mapLayout[17][row] = new IslandGrass(17, row, true);
				//mapLayout[16][row] = new IslandGrass(16, row, true);
				//mapLayout[18][row] = new IslandGrass(18, row, true);
				continue;
			}	
			
			mapLayout[16][row] = new  LeftSide(16, row, false);
		}
		//Land Bridge
		row=24;
		//top of land bridge
		mapLayout[16][row] = new BottomLeftSide(16, row, false);
		mapLayout[17][row] = new BottomSide(17, row, false);
		mapLayout[18][row] = new BottomRightSide(18, row, false);
		//bottom of land bridge
		row=26;
		mapLayout[16][row] = new TopLeftSide(16, row, false);
		mapLayout[17][row] = new TopSide(17, row, false);
		mapLayout[18][row] = new TopRightSide(18, row, false);
		//insert sides around the water
		
		//corners
		mapLayout[16][19] = new TopLeftSide(16, 19, false);
		mapLayout[28][19] = new TopRightSide(28, 19, false);
		
		mapLayout[16][31] = new BottomLeftSide(16, 31, false);
		mapLayout[28][31] = new BottomRightSide(28, 31, false);
		//end of corners
		//put a pathway from the pathwaysquare to the edge of the map 
		for(coulumn=0;coulumn<19;coulumn++) {
			mapLayout[coulumn][25] = new Pathway(coulumn,25,true);
		}
		
		
		mapLayout[23][25] = new Key(23, 25, false);
		
		
		
		//fill in the rest with short/tall grass at a 2:3 ratio of tall grass
		//to short grass
		coulumn=0;
		row=0;
		Random generate = new Random();
		int randomNum;
		for(coulumn=0;coulumn<50;coulumn++) {
			for(row=0;row<50;row++) {
				if(mapLayout[coulumn][row]==null) {
					randomNum = generate.nextInt(11);
					if(randomNum<8) {
						mapLayout[coulumn][row] = new TallGrass(coulumn, row, true);
					}else {//(randomNum==10)
						mapLayout[coulumn][row] = new ShortGrass(coulumn, row, true);
					}
//					}else {
//						mapLayout[coulumn][row] = new Rock(coulumn, row, false);
//					}
				}
			}
		}
	}
}