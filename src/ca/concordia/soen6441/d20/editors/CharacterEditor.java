package ca.concordia.soen6441.d20.editors;

import java.util.Scanner;

import ca.concordia.soen6441.d20.character.Character;
import ca.concordia.soen6441.d20.character.factory.PlayerFactory;
import ca.concordia.soen6441.d20.item.AbilityEnum;
/*
 * this is a characterEditor userInterface class
 */
public class CharacterEditor {

	private Scanner scanner;
	
	public CharacterEditor() {
		System.out.println("Character Edtior Section: ");
		System.out.println("Choose Section : ");
		System.out.println("EditCharacter:(Type e) , CreateCharacter : (Type C) " );
		scanner = new Scanner(System.in);
		String hitButton = scanner.nextLine();

		if(hitButton.equals("c")){
			createCharacter();
		}else if (hitButton.equals("e")){
			editCharacter();
		}else {
			System.out.println("Error");
		}
	}

	/**
	 * this a method for creating character
	 */
	private void createCharacter(){
		System.out.println("Please Enter Your Character Name : ");
		String charName = scanner.nextLine();
		System.out.println("Please Enter Your Character Type : ");
		String charType = scanner.nextLine();
		PlayerFactory PlayerFactory = new PlayerFactory();
		Character character = PlayerFactory.create(charType,charName);

		if(character != null){
			System.out.println("Do you want to save your character (yes or no )?");
			String result = scanner.nextLine();

			if(result.equals("yes")){
				// save the character into file  . you need to save character object into file @saman @negar.
				System.out.println("Character saved successfully");
			}else if (result.equals("no")){
				System.out.println("Character removed");
			}else {
				System.out.println("Error");
			}
		}else {
			System.out.println("Error null");
		}

		//		scanner.close();
	}


	/**
	 * this a method for editing character
	 */
	private void editCharacter(){
		System.out.println("Load the character:");
		System.out.println("Name:");
		String characterLoaded = scanner.nextLine();
		// here we need to call the load function to load character from file 
		// Character character = load(characterLoaded);
		System.out.println("Edit item :(Type i) , Edit Ability :(Type a) , Edit Attribute: (Type t)");
		String hitButton = scanner.nextLine();

		if(hitButton.equals("i")){
			//			editItem(character)
		}else if (hitButton.equals("a")){
			//editAbility(character)
				editAbility( new Character());
		}else if (hitButton.equals("t")){
			//			editAttribute(character);
		}else{
			System.out.println("Error");
		}
		//		Scanner.close();
	}
	
	private void editItem(Character character){
		
	}
	
	private void editAbility(Character character){
		System.out.println("Enter Ability Name:");
		String abilityName = scanner.nextLine();
		AbilityEnum abilityEnum = AbilityEnum.valueOf(abilityName.toUpperCase());
//		System.out.println("enum:" + abilityEnum + " value" + abilityEnum.getValue());
		character.getAbility(abilityEnum).show();
		

			System.out.println("Enter number to change Score:");

			try{
				String number = scanner.nextLine();
				int numberScore = Integer.parseInt(number);
				character.getAbility(abilityEnum).setScore(numberScore);
				character.getAbility(abilityEnum).synceModifier();
				character.getAbility(abilityEnum).show();

				System.out.println("Do you want to change another ability:(yes or no)");
				String answer = scanner.nextLine();
				
				if(answer.equals("yes")){
					editAbility(character);
				}else if (answer.equals("no")){
					System.out.println("Do you want to save character changes:(yes or no)");
					answer = scanner.nextLine();
					if(answer.equals("yes")){
						// save(character.name);
					}else {
						
					}
				}else {
					System.out.println("Error IO");
				}
				
			}catch(Exception e){
				System.out.println("Error not int");
			}
	}
	
	private void editAttribute(Character character){
		
	}
}


