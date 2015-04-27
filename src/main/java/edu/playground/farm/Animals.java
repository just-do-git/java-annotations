package edu.playground.farm;

import static edu.playground.farm.annotations.Animal.Family.CANIDAE;
import static edu.playground.farm.annotations.Animal.Family.FELIDAE;
import static edu.playground.farm.annotations.Canidae.SpeciesCanidae.DOG;
import static edu.playground.farm.annotations.Canidae.SpeciesCanidae.FOX;
import static edu.playground.farm.annotations.Felidae.SpeciesFelidae.CAT;
import edu.playground.farm.annotations.Animal;
import edu.playground.farm.annotations.Canidae;
import edu.playground.farm.annotations.Felidae;

/**
 * Try-out of annotations, they are evaluated at JUnit test.
 */
public class Animals {

	public static final String DOMESTIC_CAT = "Don't go. Leave me alone. There is a mouse in your bed.";
	public static final String MAINE_COON_CAT = " I am very fluffy so you have more to cuddle. But where is my comb?";
	public static final String HUSKY_DOG = "Wanna play? How about catching some cat?";
	public static final String RED_FOX = "I like to jump around and I can be friendly to people.";
	
	
	@Animal(family = FELIDAE)
	@Felidae(species = CAT)
	public String getDomesticCat() {
		return DOMESTIC_CAT;
	}
	
	
	@Animal(family = FELIDAE)
	@Felidae(species = CAT)
	public String getMaineCoonCat() {
		return MAINE_COON_CAT;
	}
	
	
	@Animal(family = CANIDAE)
	@Canidae(species = DOG)
	public String getHuskyDog() {
		return HUSKY_DOG;
	}
	
	
	@Animal(family = CANIDAE)
	@Canidae(species = FOX)
	public String getFox() {
		return RED_FOX;
	}
}
