package View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Map.Barrier;
import model.Map.BotDirt;
import model.Map.BotLeftDirt;
import model.Map.BotRightDirt;
import model.Map.BottomIsland;
import model.Map.BottomLeftFence;
import model.Map.BottomLeftIsland;
import model.Map.BottomLeftSide;
import model.Map.BottomRightFence;
import model.Map.BottomRightIsland;
import model.Map.BottomRightSide;
import model.Map.BottomSide;
import model.Map.BottomStatue;
import model.Map.Dirt;
import model.Map.DirtPath;
import model.Map.DirtPathBot;
import model.Map.DirtPathBotLeft;
import model.Map.DirtPathBotRight;
import model.Map.DirtPathLeft;
import model.Map.DirtPathRight;
import model.Map.DirtPathTop;
import model.Map.DirtPathTopLeft;
import model.Map.DirtPathTopRight;
import model.Map.DirtRock;
import model.Map.IslandGrass;
import model.Map.Key;
import model.Map.LeftDirt;
import model.Map.LeftFence;
import model.Map.LeftGate;
import model.Map.LeftGateFence;
import model.Map.LeftIsland;
import model.Map.LeftSide;
import model.Map.Map;
import model.Map.MapObjects;
import model.Map.Pathway;
import model.Map.RightDirt;
import model.Map.RightFence;
import model.Map.RightGate;
import model.Map.RightGateFence;
import model.Map.RightIsland;
import model.Map.RightSide;
import model.Map.Rock;
import model.Map.Stairs;
import model.Map.TallGrass;
import model.Map.TopDirt;
import model.Map.TopFence;
import model.Map.TopIsland;
import model.Map.TopLeftCorner;
import model.Map.TopLeftDirt;
import model.Map.TopLeftFence;
import model.Map.TopLeftIsland;
import model.Map.TopLeftSide;
import model.Map.TopRightCorner;
import model.Map.TopRightDirt;
import model.Map.TopRightFence;
import model.Map.TopRightIsland;
import model.Map.TopRightSide;
import model.Map.TopSide;
import model.Map.TopStatue;
import model.Map.Tree;
import model.Map.VertPathWay;
import model.Map.Water;
import model.Trainer.Trainer;

public class MapView extends Pane {
	//use a gridmap
	private GridPane theGrid;
	private Trainer trainer;
	
	public MapView(Map theMap) {
		this.trainer = trainer;
		theGrid = new GridPane();
		
		Image imageRock   =new Image("File:images/rock.png");
		Image imageTree   =new Image("File:images/tree.png");
		Image imagePath   =new Image("File:images/pathway.png");
		Image imageTGrass =new Image("File:images/tallgrass_bush.png");
		Image imageVpath  =new Image("File:images/Vertpathway.png");
		Image imageTree2  =new Image("File:images/Trees.png");
		//island
		Image imageTopLeft     =new Image("File:images/topleftIsland.png");
		Image imageTop         =new Image("File:images/topIsland.png");
		Image imageTopRight    =new Image("File:images/toprightIsland.png");
		Image imageRight       =new Image("File:images/rightIsland.png");
		Image imageBottomRight =new Image("File:images/bottomrightIsland.png");
		Image imageBottom      =new Image("File:images/bottomIsland.png");
		Image imageBottomLeft  =new Image("File:images/bottomleftIsland.png");
		Image imageLeft        =new Image("File:images/leftIsland.png");
		Image imageIslandGrass =new Image("File:images/islandGrass2.png");
		//sides and water
		Image imageBottomleftside  =new Image("File:images/bottomleftSide.png");
		Image imageTopside         =new Image("File:images/topSide.png");
		Image imageTopRightside    =new Image("File:images/toprightSide.png");
		Image imageTopLeftside     =new Image("File:images/topleftSide.png");
		Image imageLeftside        =new Image("File:images/leftSide.png");
		Image imageRightside       =new Image("File:images/rightSide.png");
		Image imageBottomside      =new Image("File:images/bottomSide.png");
		Image imageBottomrightside =new Image("File:images/bottomrightSide.png");
		Image imageWater           =new Image("File:images/water.png");
		//Fence
		Image imageTopFence        =new Image("File:images/topfence.png");
		Image imageTopRightFence   =new Image("File:images/toprightfence.png");
		Image imageTopLeftFence    =new Image("File:images/topleftfence.png");
		Image imageLeftFence       =new Image("File:images/leftfence.png");
		Image imageRightFence      =new Image("File:images/rightfence.png");
		Image imageBottomRightFence=new Image("File:images/bottomrightfence.png");
		Image imageBottomLeftFence =new Image("File:images/bottomleftfence.png");
		//Desert boss stage
		Image imageTopDirt        =new Image("File:images/topdirt.png");
		Image imageBotDirt        =new Image("File:images/botdirt.png");
		Image imageLeftDirt       =new Image("File:images/leftdirt.png");
		Image imageRightDirt      =new Image("File:images/rightdirt.png");
		Image imageTopLeftDirt    =new Image("File:images/topleftdirt.png");
		Image imageTopRightDirt   =new Image("File:images/toprightdirt.png");
		Image imageBotLeftDirt    =new Image("File:images/botleftdirt.png");
		Image imageBotRightDirt   =new Image("File:images/botrightdirt.png");
		Image imageStairsDirt     =new Image("File:images/stairs.png");
		Image imageDirt           =new Image("File:images/dirt.png");
		//gate and fence
		Image imageLeftGate       =new Image("File:images/leftgate.png");
		Image imageRightGate      =new Image("File:images/rightgate.png");
		Image imageLeftFenceGate  =new Image("File:images/leftfencegate.png");
		Image imageRightFenceGate =new Image("File:images/rightfencegate.png");
		
		Image imageTopRightCorner =new Image("File:images/toprightcorner.png");
		Image imageTopLeftCorner  =new Image("File:images/topleftcorner.png");
		Image imageDirtRock		  =new Image("File:images/dirtrock.png");
		
		Image imageTopStatue      =new Image("File:images/topstatue.png");
		Image imageBottomStatue   =new Image("File:images/bottomstatue.png");
		Image imageKey            =new Image("File:images/key.png");
		//sand pathway
		Image imageDirtPath		      =new Image("File:images/dirtpathway.png");
		Image imageDirtPathTop		  =new Image("File:images/dirtpathwaytop.png");
		Image imageDirtPathBot		  =new Image("File:images/dirtpathwaybot.png");
		Image imageDirtPathTopRight   =new Image("File:images/dirtpathwaytopright.png");
		Image imageDirtPathTopLeft	  =new Image("File:images/dirtpathwaytopleft.png");
		Image imageDirtPathBotRight	  =new Image("File:images/dirtpathwaybotright.png");
		Image imageDirtPathBotLeft	  =new Image("File:images/dirtpathwaybotleft.png");
		Image imageDirtPathLeft		  =new Image("File:images/dirtpathwayleft.png");
		Image imageDirtPathRight	  =new Image("File:images/dirtpathwayright.png");
		int maxRow    = theMap.getXdimension();
		int maxColumn = theMap.getYdimension();
		
		ImageView[] photo = new ImageView[maxRow*maxColumn];
		int k=0;
		
		for(int i=0;i<maxRow;i++ ) {
			for(int j=0;j<maxColumn;j++) {
				//init()
				photo[k]= new ImageView();
				//figure out whats stored in theMap[i][j]
				if(theMap.getSingleMapObject(i, j) instanceof Rock){
					photo[k].setImage(imageRock);
				}else if(theMap.getSingleMapObject(i, j) instanceof TallGrass){
					photo[k].setImage(imageTGrass);
				}else if(theMap.getSingleMapObject(i, j) instanceof Pathway){
					photo[k].setImage(imagePath);
				}else if(theMap.getSingleMapObject(i, j) instanceof VertPathWay){
					photo[k].setImage(imageVpath);
				}else if(theMap.getSingleMapObject(i, j) instanceof IslandGrass){
					photo[k].setImage(imageIslandGrass);
				}else if(theMap.getSingleMapObject(i, j) instanceof TopLeftIsland){
					photo[k].setImage(imageTopLeft);
				}else if(theMap.getSingleMapObject(i, j) instanceof TopIsland){
					photo[k].setImage(imageTop);
				}else if(theMap.getSingleMapObject(i, j) instanceof TopRightIsland){
					photo[k].setImage(imageTopRight);
				}else if(theMap.getSingleMapObject(i, j) instanceof RightIsland){
					photo[k].setImage(imageRight);
				}else if(theMap.getSingleMapObject(i, j) instanceof BottomRightIsland){
					photo[k].setImage(imageBottomRight);
				}else if(theMap.getSingleMapObject(i, j) instanceof BottomIsland){
					photo[k].setImage(imageBottom);
				}else if(theMap.getSingleMapObject(i, j) instanceof BottomLeftIsland){
					photo[k].setImage(imageBottomLeft);
				}else if(theMap.getSingleMapObject(i, j) instanceof LeftIsland){
					photo[k].setImage(imageLeft);
				}else if(theMap.getSingleMapObject(i, j) instanceof TopRightSide){
					photo[k].setImage(imageTopRightside);
				}else if(theMap.getSingleMapObject(i, j) instanceof TopLeftSide){
					photo[k].setImage(imageTopLeftside);
				}else if(theMap.getSingleMapObject(i, j) instanceof BottomLeftSide){
					photo[k].setImage(imageBottomleftside);
				}else if(theMap.getSingleMapObject(i, j) instanceof TopSide){
					photo[k].setImage(imageTopside);
				}else if(theMap.getSingleMapObject(i, j) instanceof LeftSide){
					photo[k].setImage(imageLeftside);
				}else if(theMap.getSingleMapObject(i, j) instanceof RightSide){
					photo[k].setImage(imageRightside);
				}else if(theMap.getSingleMapObject(i, j) instanceof BottomSide){
					photo[k].setImage(imageBottomside);
				}else if(theMap.getSingleMapObject(i, j) instanceof BottomRightSide){
					photo[k].setImage(imageBottomrightside);
				}else if(theMap.getSingleMapObject(i, j) instanceof Water){
					photo[k].setImage(imageWater);
				}else if(theMap.getSingleMapObject(i, j) instanceof Key){
					photo[k].setImage(imageKey);
				}else if(theMap.getSingleMapObject(i, j) instanceof TopFence){
					photo[k].setImage(imageTopFence);
				}else if(theMap.getSingleMapObject(i, j) instanceof RightFence){
					photo[k].setImage(imageRightFence);
				}else if(theMap.getSingleMapObject(i, j) instanceof LeftFence){
					photo[k].setImage(imageLeftFence);
				}else if(theMap.getSingleMapObject(i, j) instanceof TopLeftFence){
					photo[k].setImage(imageTopLeftFence);
				}else if(theMap.getSingleMapObject(i, j) instanceof TopRightFence){
					photo[k].setImage(imageTopRightFence);
				}else if(theMap.getSingleMapObject(i, j) instanceof BottomRightFence){
					photo[k].setImage(imageBottomRightFence);
				}else if(theMap.getSingleMapObject(i, j) instanceof BottomLeftFence){
					photo[k].setImage(imageBottomLeftFence);
				}else if(theMap.getSingleMapObject(i, j) instanceof TopStatue){
					photo[k].setImage(imageTopStatue);
				}else if(theMap.getSingleMapObject(i, j) instanceof BottomStatue){
					photo[k].setImage(imageBottomStatue);
				}else if(theMap.getSingleMapObject(i, j) instanceof Tree){
					photo[k].setImage(imageTree2);
				}else if(theMap.getSingleMapObject(i, j) instanceof TopDirt){
					photo[k].setImage(imageTopDirt);
				}else if(theMap.getSingleMapObject(i, j) instanceof Dirt){
					photo[k].setImage(imageDirt);
				}else if(theMap.getSingleMapObject(i, j) instanceof BotDirt){
					photo[k].setImage(imageBotDirt);				
				}else if(theMap.getSingleMapObject(i, j) instanceof TopRightDirt){
					photo[k].setImage(imageTopRightDirt);
				}else if(theMap.getSingleMapObject(i, j) instanceof TopLeftDirt){
					photo[k].setImage(imageTopLeftDirt);
				}else if(theMap.getSingleMapObject(i, j) instanceof BotRightDirt){
					photo[k].setImage(imageBotRightDirt);
				}else if(theMap.getSingleMapObject(i, j) instanceof BotLeftDirt){
					photo[k].setImage(imageBotLeftDirt);
				}else if(theMap.getSingleMapObject(i, j) instanceof Stairs){
					photo[k].setImage(imageStairsDirt);
				}else if(theMap.getSingleMapObject(i, j) instanceof LeftDirt){
					photo[k].setImage(imageLeftDirt);
				}else if(theMap.getSingleMapObject(i, j) instanceof RightDirt){
					photo[k].setImage(imageRightDirt);
				}else if(theMap.getSingleMapObject(i, j) instanceof LeftGate){
					photo[k].setImage(imageLeftGate);
				}else if(theMap.getSingleMapObject(i, j) instanceof RightGate){
					photo[k].setImage(imageRightGate);
				}else if(theMap.getSingleMapObject(i, j) instanceof RightGateFence){
					photo[k].setImage(imageRightFenceGate);
				}else if(theMap.getSingleMapObject(i, j) instanceof LeftGateFence){
					photo[k].setImage(imageLeftFenceGate);
				}else if(theMap.getSingleMapObject(i, j) instanceof TopRightCorner){
					photo[k].setImage(imageTopRightCorner);
				}else if(theMap.getSingleMapObject(i, j) instanceof TopLeftCorner){
					photo[k].setImage(imageTopLeftCorner);
				}else if(theMap.getSingleMapObject(i, j) instanceof DirtRock){
					photo[k].setImage(imageDirtRock);
				}else if(theMap.getSingleMapObject(i, j) instanceof DirtPathTop){
					photo[k].setImage(imageDirtPathTop);
				}else if(theMap.getSingleMapObject(i, j) instanceof DirtPathBot){
					photo[k].setImage(imageDirtPathBot);
				}else if(theMap.getSingleMapObject(i, j) instanceof DirtPathLeft){
					photo[k].setImage(imageDirtPathLeft);
				}else if(theMap.getSingleMapObject(i, j) instanceof DirtPathRight){
					photo[k].setImage(imageDirtPathRight);
				}else if(theMap.getSingleMapObject(i, j) instanceof DirtPath){
					photo[k].setImage(imageDirtPath);
				}else if(theMap.getSingleMapObject(i, j) instanceof DirtPathBotRight){
					photo[k].setImage(imageDirtPathBotRight);
				}else if(theMap.getSingleMapObject(i, j) instanceof DirtPathBotLeft){
					photo[k].setImage(imageDirtPathBotLeft);
				}else if(theMap.getSingleMapObject(i, j) instanceof DirtPathTopLeft){
					photo[k].setImage(imageDirtPathTopLeft);
				}else if(theMap.getSingleMapObject(i, j) instanceof DirtPathTopRight){
					photo[k].setImage(imageDirtPathTopRight);
				}
				
				
				
				
				
				
				
				
				else if(theMap.getSingleMapObject(i, j) instanceof Barrier){
					photo[k].setImage(imageTree);
				}

				photo[k].setFitHeight(20);
		    	photo[k].setFitWidth(20);
				theGrid.add(photo[k], i, j);
				k++;
			}
		}
	}

	public GridPane getGetGridPane() {
		return theGrid; 
	}
	
}