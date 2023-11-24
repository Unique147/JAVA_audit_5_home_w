package org.example;

import com.github.javafaker.Faker;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        File jsonFile = new File("characters.json");
        Character[] characters;

        if (jsonFile.exists()) {
            characters = loadCharactersFromJSON(jsonFile);
            System.out.println("\nПерсонажи, загруженные из JSON:");

            for (Character character : characters) {
                System.out.println(character.getName() +
                        ", Class: " + character.getGameClass() +
                        ", Level: " + character.getLevel() +
                        ", Attack: " + character.getAttack() +
                        ", Armor: " + character.getArmor() +
                        ", Money: " + character.getMoney());
            }

        } else {
            characters = generateRandomCharacters();
            saveCharactersToJSON(jsonFile, characters);
        }
        Faker faker = new Faker();
        Character[] charactersnew = new Character[15];

        for (int i = 0; i < charactersnew.length; i++) {
            String name = faker.name().fullName();
            String gameClass = faker.gameOfThrones().house();
            int level = (int) (Math.random() * 51);
            int attack = (int) (Math.random() * 51);
            int armor = (int) (Math.random() * 51);
            int money = (int) (Math.random() * 51);

            charactersnew[i] = new Character(name, gameClass, level, attack, armor, money);
        }
        System.out.println("\nГенерирующиеся персонажи:");
        for (Character character : charactersnew) {
            System.out.println(character.getName() +
                    ", Class: " + character.getGameClass() +
                    ", Level: " + character.getLevel() +
                    ", Attack: " + character.getAttack() +
                    ", Armor: " + character.getArmor() +
                    ", Money: " + character.getMoney());
        }
        //1
        Character highestLevelCharacter = Arrays.stream(charactersnew)
                .max(Comparator.comparing(Character::getLevel))
                .orElse(null);
        if (highestLevelCharacter != null) {
            System.out.println("\nСамый высокий уровень у персонажа:");
            System.out.println(highestLevelCharacter.getName() +
                    ", Class: " + highestLevelCharacter.getGameClass() +
                    ", Level: " + highestLevelCharacter.getLevel() +
                    ", Attack: " + highestLevelCharacter.getAttack() +
                    ", Armor: " + highestLevelCharacter.getArmor() +
                    ", Money: " + highestLevelCharacter.getMoney());
        } else {
            System.out.println("Нет персонажей для анализа.");
        }
        charactersnew = Arrays.stream(charactersnew)
                .map(character -> {
                    int newAttack = (int) (character.getAttack() * 1.5);
                    character.setAttack(newAttack);
                    return character;
                })
                .toArray(Character[]::new);
        //2
        System.out.println("\nДанные всех героев после изменения уровня атаки в 1.5 раза:");

        for (Character character : charactersnew) {
            System.out.println(character.getName() +
                    ", Class: " + character.getGameClass() +
                    ", Level: " + character.getLevel() +
                    ", Attack: " + character.getAttack() +
                    ", Armor: " + character.getArmor() +
                    ", Money: " + character.getMoney());
        }
        //3
        Random random = new Random();
        int numHeroesToIncreaseMoney = random.nextInt(5);
        Character richestCharacterBefore = Arrays.stream(charactersnew)
                .max(Comparator.comparing(Character::getMoney))
                .orElse(null);
        Arrays.stream(charactersnew)
                .filter(character -> random.nextBoolean())
                .limit(numHeroesToIncreaseMoney)
                .forEach(character -> character.setMoney(character.getMoney() * 2));


        if (richestCharacterBefore != null) {
            System.out.println("\nСамый богатый герой до увеличения:");
            System.out.println(richestCharacterBefore.getName() +
                    ", Class: " + richestCharacterBefore.getGameClass() +
                    ", Level: " + richestCharacterBefore.getLevel() +
                    ", Attack: " + richestCharacterBefore.getAttack() +
                    ", Armor: " + richestCharacterBefore.getArmor() +
                    ", Money: " + richestCharacterBefore.getMoney());
        } else {
            System.out.println("Нет героев для анализа.");
        }

        System.out.println("\nСлучайным героям было увеличено количество золота в 2 раза.");


        Character richestCharacterAfter = Arrays.stream(charactersnew)
                .max(Comparator.comparing(Character::getMoney))
                .orElse(null);

        if (richestCharacterAfter != null) {
            System.out.println("\nСамый богатый герой после увеличения:");
            System.out.println(richestCharacterAfter.getName() +
                    ", Class: " + richestCharacterAfter.getGameClass() +
                    ", Level: " + richestCharacterAfter.getLevel() +
                    ", Attack: " + richestCharacterAfter.getAttack() +
                    ", Armor: " + richestCharacterAfter.getArmor() +
                    ", Money: " + richestCharacterAfter.getMoney());
        } else {
            System.out.println("Нет героев для анализа.");
        }
        // 4
        charactersnew = Arrays.stream(charactersnew)
                .map(character -> {
                    int newArmor = character.getLevel() + character.getArmor();
                    character.setArmor(newArmor);
                    return character;
                })
                .toArray(Character[]::new);

        System.out.println("\nНовые данные всех героев после привязки показателя защиты к уровню:");

        for (Character character : charactersnew) {
            System.out.println(character.getName() +
                    ", Class: " + character.getGameClass() +
                    ", Level: " + character.getLevel() +
                    ", Attack: " + character.getAttack() +
                    ", Armor: " + character.getArmor() +
                    ", Money: " + character.getMoney());
        }
        // 5
        System.out.println("\nРаспределение по классам:");
        Map<String, String> classToHeroesMap = Arrays.stream(charactersnew)
                .collect(HashMap::new,
                        (map, character) -> map.merge(character.getGameClass(), character.getName(),
                                (existingNames, newName) -> existingNames + ", " + newName),
                        HashMap::putAll);


        classToHeroesMap.forEach((gameClass, heroNames) -> {
            System.out.println(gameClass + " : " + heroNames);
        });

    }
    private static Character[] generateRandomCharacters() {
        Character[] characters = new Character[15];
        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < characters.length; i++) {
            String name = faker.name().fullName();
            String gameClass = faker.gameOfThrones().house();
            int level = random.nextInt(51);
            int attack = random.nextInt(51);
            int armor = random.nextInt(51);
            int money = random.nextInt(51);

            characters[i] = new Character(name, gameClass, level, attack, armor, money);
        }

        return characters;
    }

    private static void saveCharactersToJSON(File jsonFile, Character[] characters) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, characters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Character[] loadCharactersFromJSON(File jsonFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonFile, new TypeReference<Character[]>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new Character[0];
        }

    }
}

