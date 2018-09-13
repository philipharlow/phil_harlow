package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;

import Controller.PokemonController;
import model.Map.LeftMap;
import model.Map.Map;
import model.Map.RightMap;
import model.Pokemon.CommonPokemon;
import model.Pokemon.Pokemon;
import model.Pokemon.RarePokemon;
import model.Pokemon.UncommonPokemon;
import model.Trainer.Trainer;

public class PokemonTest {
	private Trainer ash;
	private Map pokeMap;
	private Map rightMap;
	
	/**
	 * <b>testTrainer</b>
	 * <p>
	 * Will test some functions from the Trainer class
	 * 
	 * @author Marcos Ayon
	 */
	@Test
	void testTrainer() {
		//run controller
		//PokemonController.main(null);
		rightMap = new RightMap("right map", false);
		pokeMap = new LeftMap("safari map", false);
		assertEquals("safari map", pokeMap.getName());
		ash = new Trainer("Ash", pokeMap);
		
		//test creation of trainer
		assertEquals("Ash", ash.getName());
		assertEquals(500, ash.getSteps());
		assertEquals(100, ash.getHp());
		assertEquals(25, ash.getXPos());
		assertEquals(25, ash.getYPos()); 
		assertFalse(ash.outOfSafariBalls());
		
		//test moving in directions
		ash.moveDown();
		ash.moveDown();
		assertEquals(27, ash.getYPos());
		ash.moveUp();
		assertEquals(26, ash.getYPos());
		ash.moveRight();
		assertEquals(26, ash.getXPos());
		ash.moveLeft();
		assertEquals(25, ash.getXPos());
		
		for(int i = 0; i <= 25; i++) {
			ash.moveRight();
		}
		for(int i = 0; i <= 25; i++) {
			ash.moveDown();
		}
		for(int i = 0; i <= 50; i++) {
			ash.moveLeft();
		}
		for(int i = 0; i <= 50; i++) {
			ash.moveUp();
		}
		
		//test adding pokemon and pokemon list
		RarePokemon newPoke = new RarePokemon("Mewtwo", ash, "Psychic");
		while(true) {
			ash.throwPokeball(newPoke);
			if(ash.outOfSafariBalls()) {
				break;
			}
		}
		
	}
	
	/**
	 * <b>testPokemon</b>
	 * <p>
	 * Will test some functions from the Pokemon class and sub classes
	 * 
	 * @author Marcos Ayon
	 */
	@Test
	void testPokemon() {
		ash = new Trainer("Ash", pokeMap);
		CommonPokemon common = new CommonPokemon("Commie", ash, "Fire");
		assertEquals("Commie", common.getName());
		UncommonPokemon uncommon = new UncommonPokemon("unCommie", ash, "Fire");
		assertEquals("unCommie", uncommon.getName());
		RarePokemon rare = new RarePokemon("Mewtwo", ash, "Psychic");
		assertEquals("Mewtwo", rare.getName());
		
		assertEquals(ash, common.getTrainer());
		assertEquals(ash, uncommon.getTrainer());
		assertEquals(ash, rare.getTrainer());	
		
		common.runAway();
		uncommon.runAway();
		rare.runAway();
		
		ash.throwRock(common);
		ash.throwRock(uncommon);
		ash.throwRock(rare);
	}
	
	/**
	 * <b>testTrainerVsPokemon</b>
	 * <p>
	 * Will "simulate" a battle between a trainer and a Pokemon
	 * 
	 * @author Marcos Ayon
	 */
	@Test
	void testTrainerVsPokemon() {
		ash = new Trainer("Ash", pokeMap);
		CommonPokemon opponent = new CommonPokemon("op", null, "Fire");
		boolean added = false;
		while(true) {
			ash.throwBait(opponent);
			boolean caught = ash.throwPokeball(opponent);
			if(caught) {
				ash.addPokemon(opponent);
				added = true;
				break;
			}
		}
		if(added) {
			assertEquals(opponent, ash.getPokemon().get(0));
		}
		
		while(true) {
			ash.throwRock(opponent);
			if(opponent.runAway() == true) {
				break;
			}
		}
		
	}
}
