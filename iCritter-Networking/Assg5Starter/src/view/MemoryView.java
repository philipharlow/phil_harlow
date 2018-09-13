package view;

import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.FXCollections;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import model.Owner;

import model.ICritterMemoryEvent;
//Written by Nick Forbes
public class MemoryView extends BorderPane implements Observer {
	private static final int VGAP = 2;
	ListView<ICritterMemoryEvent> memList;
	Slider satisfaction;
	Owner owner;
	GridPane memView;
	public MemoryView(Owner owner) {
			this.owner = owner;
			memView = new GridPane();
			memView.setVgap(VGAP);
			satisfaction = createSlider(owner.getCritter().getHappiness());
			memView.add(satisfaction, 0, 0);
			memView.add(createMems(),  0, 1);
			this.setCenter(memView);
	}
	
	ListView<ICritterMemoryEvent> createMems() {
		memList = new ListView<ICritterMemoryEvent>();
		memList.setCellFactory(new Callback<ListView<ICritterMemoryEvent>, ListCell<ICritterMemoryEvent>>(){

            @Override
            public ListCell<ICritterMemoryEvent> call(ListView<ICritterMemoryEvent> p) {
                
                ListCell<ICritterMemoryEvent> cell = new ListCell<ICritterMemoryEvent>(){

                    @Override
                    protected void updateItem(ICritterMemoryEvent t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.toString());
                        }
                    }

                };
                
                return cell;
            }

        });
		List<ICritterMemoryEvent> mems = new ArrayList<ICritterMemoryEvent>(owner.getCritter().getMemories());
		if(mems.size() < 8) {
			memList.setItems(FXCollections.observableArrayList(mems));
		}
		else {
			memList.setItems(FXCollections.observableArrayList(mems.subList
															(mems.size() - 8,mems.size())));
		}
		
		memList.setPrefSize(400, 400);
		return memList;
	}
	
	public Slider createSlider(double val) {
		Slider satisfaction = new Slider(-4, 4, val);
		satisfaction.setShowTickMarks(true);
		satisfaction.setShowTickLabels(true);
		satisfaction.setMajorTickUnit(1.0f);
		return satisfaction;
	}
	
	public void update(Observable o, Object arg) {
		owner = (Owner) o;
		memView.getChildren().remove(memList);
		memList = createMems();
		memView.add(memList, 0, 1);
		satisfaction.setValue(owner.getCritter().getHappiness());
	}
}


