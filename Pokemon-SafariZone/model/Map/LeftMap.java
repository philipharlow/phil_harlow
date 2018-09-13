package model.Map;

import java.io.Serializable;
import java.util.Random;

public class LeftMap extends Map implements Serializable{

	public LeftMap(String name1, boolean multiplayer1) {
		super(name1, multiplayer1);
		buildTheMap();

	}

	@Override
	protected void buildTheMap() {
		//insert top and bottom walls
		int coulumn,row=0;
		for(coulumn=0;coulumn<50;coulumn++) {
			mapLayout[coulumn][0] = new BotDirt(coulumn,0,false);
			mapLayout[coulumn][49] = new TopDirt(coulumn,49,false);
		}//insert side walls
		for(row=1;row<49;row++) {
			mapLayout[0][row] = new RightDirt(0,row,false);
			//create an opening for trainer to walk through the map
			if(row==25) {
				continue;
			}
			mapLayout[49][row] = new LeftDirt(49,row,false);
		}//End of Border walls
		mapLayout[0][0] = new TopLeftCorner(0,0,false);
		mapLayout[49][0] = new TopRightCorner(49,0,false);
		//Boss Stage
		//corners
		mapLayout[22][1] = new TopLeftDirt(22,1,false);
		for(coulumn=23;coulumn<27;coulumn++) {
			mapLayout[coulumn][1] = new TopDirt(coulumn,1,false);
			mapLayout[coulumn][2] = new Dirt(coulumn,2,true);
			mapLayout[coulumn][3] = new Dirt(coulumn,3,true);
			mapLayout[coulumn][4] = new Dirt(coulumn,4,true);
			mapLayout[coulumn][5] = new Dirt(coulumn,5,true);
			mapLayout[coulumn][6] = new BotDirt(coulumn,6,false);
		}
		mapLayout[27][1] = new TopRightDirt(27,1,false);
		mapLayout[27][6] = new BotRightDirt(27,6,false);
		mapLayout[22][6] = new BotLeftDirt(22,6,false);
		//sides
		for(row=2;row<6;row++) {
			mapLayout[22][row] = new LeftDirt(22,row,false);
			mapLayout[27][row] = new RightDirt(27,row,false);
		}
		//stairs
		mapLayout[24][6] = new Stairs(24,6,false);
		mapLayout[25][6] = new Stairs(25,6,false);
		//gates
		mapLayout[24][6] = new LeftGate(24,6,false);
		mapLayout[25][6] = new RightGate(25,6,false);
		mapLayout[23][6] = new LeftGateFence(23,6,false);
		//mapLayout[22][7] = new LeftGateFence(22,7,false);
		mapLayout[26][6] = new RightGateFence(26,6,false);
		//mapLayout[27][7] = new RightGateFence(27,7,false);
		
		//pathway
		for(coulumn=28;coulumn<50;coulumn++) {
			mapLayout[coulumn][25] = new Pathway(coulumn,25,true);
			if(coulumn==49)
				break;
			mapLayout[coulumn][26] = new DirtRock(coulumn,26,false);
		}
		
		for(coulumn=28;coulumn<49;coulumn++) {
			for(row=26;row<49;row++) {
				mapLayout[coulumn][row] = new DirtRock(coulumn,row,false);	
			}
		}
		//desert
		mapLayout[2][25] = new DirtPathTopLeft(2,25,true);
		mapLayout[21][25] = new DirtPathTopRight(21,25,true);
		mapLayout[2][47] = new DirtPathBotLeft(2,47,true);
		mapLayout[21][47] = new DirtPathBotRight(21,47,true);
		
		for(row=26;row<47;row++) {
			for(coulumn=3;coulumn<21;coulumn++) {
				mapLayout[coulumn][row] = new DirtPath(coulumn,row,true);
			}
		}
		for(row=26;row<47;row++) {
			mapLayout[2][row] = new DirtPathLeft(2,row,true);
			mapLayout[21][row] = new DirtPathRight(21,row,true);
		}
		for(coulumn=3;coulumn<21;coulumn++) {
			mapLayout[coulumn][25] = new DirtPathTop(coulumn,25,true);
			mapLayout[coulumn][47] = new DirtPathBot(coulumn,47,true);
		}
		
//		mapLayout[coulumn][row] = new DirtPathTop(coulumn,row,true);
//		mapLayout[coulumn][row] = new DirtPathBot(coulumn,row,true);
//		mapLayout[coulumn][row] = new DirtPathLeft(coulumn,row,true);
//		mapLayout[coulumn][row] = new DirtPathRight(coulumn,row,true);
//		mapLayout[coulumn][row] = new DirtPath(coulumn,row,true);
//		mapLayout[coulumn][row] = new DirtPathBotRight(coulumn,row,true);
//		mapLayout[coulumn][row] = new DirtPathBotLeft(coulumn,row,true);
//		mapLayout[coulumn][row] = new DirtPathTopLeft(coulumn,row,true);
//		mapLayout[coulumn][row] = new DirtPathTopRight(coulumn,row,true);
		
		
		
		//cluster of rocks
		for(coulumn=1;coulumn<22;coulumn++) {
			if(coulumn==6||coulumn==19)
				continue;
			for(row=1;row<21;row++) {
				if(row==16||row==20||row==3)
					continue;
				mapLayout[coulumn][row] = new DirtRock(coulumn,row,false);
			}
		}
		//dirtmountain
		//base level
		mapLayout[28][1] = new TopLeftDirt(28,1,false);
		mapLayout[48][1] = new TopRightDirt(48,1,false);
		mapLayout[48][24] = new BotRightDirt(48,24,false);
		mapLayout[28][24] = new BotLeftDirt(28,24,false);
		
		for(coulumn=29;coulumn<48;coulumn++) {
			mapLayout[coulumn][1] = new TopDirt(coulumn,1,false);
			mapLayout[coulumn][24] = new BotDirt(coulumn,24,false);
		}
		for(row=2;row<24;row++) {
			mapLayout[28][row] = new LeftDirt(28,row,false);
			mapLayout[48][row] = new RightDirt(48,row,false);
		}
		//2nd level
		mapLayout[29][2] = new TopLeftDirt(29,2,false);
		mapLayout[47][2] = new TopRightDirt(47,1,false);
		mapLayout[47][20] = new BotRightDirt(47,20,false);
		mapLayout[29][20] = new BotLeftDirt(29,20,false);
		
		
		
		for(coulumn=30;coulumn<47;coulumn++) {
			mapLayout[coulumn][2] = new TopDirt(coulumn,2,false);
			mapLayout[coulumn][20] = new BotDirt(coulumn,20,false);
		}
		//dividers
		mapLayout[32][3]  = new TopLeftDirt(29,3,false);
		mapLayout[33][3]  = new TopRightDirt(30,3,false);
		mapLayout[33][17] = new BotRightDirt(29,17,false);
		mapLayout[32][17] = new BotLeftDirt(30,17,false);
		
		for(row=4;row<17;row++) {
			mapLayout[32][row] = new LeftDirt(32,row,false);
			mapLayout[33][row] = new RightDirt(33,row,false);
		}
		
		
		for(row=3;row<20;row++) {
			mapLayout[29][row] = new LeftDirt(29,row,false);
			mapLayout[47][row] = new RightDirt(47,row,false);
		}//end of 2nd level
		mapLayout[29][24] = new Stairs(29,24,true);
		mapLayout[39][20] = new Stairs(39,20,true);
		
		
		//fill in the rest randomly with rocks & grass

		coulumn=0;
		row=0;
		Random generate = new Random();
		int randomNum;
		for(coulumn=0;coulumn<50;coulumn++) {
			for(row=0;row<50;row++) {
				if(mapLayout[coulumn][row]==null) {
					randomNum = generate.nextInt(10);//generate numbers 0-9
					if(randomNum<7 ) {//0-6 will be tall grass
						mapLayout[coulumn][row] = new Dirt(coulumn,row,true);
						//mapLayout[coulumn][row] = new TallGrass(coulumn, row, true);
					}else if(randomNum==7||randomNum==8) {// 8 shortgrss
						mapLayout[coulumn][row] = new Dirt(coulumn,row,true);
						//mapLayout[coulumn][row] = new ShortGrass(coulumn, row, true);
					}else {//9 will be a rock
						mapLayout[coulumn][row] = new Dirt(coulumn,row,true);
						
						//mapLayout[coulumn][row] = new Rock(coulumn, row, false);
					}
				}
			}
		}
	}
}
