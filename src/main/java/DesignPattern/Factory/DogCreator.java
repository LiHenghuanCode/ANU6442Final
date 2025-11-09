package DesignPattern.Factory;

import java.util.List;
import java.util.Random;


public class DogCreator extends AnimalCreator {
    public static final List<String> NAMES = List.of("Rex", "Buddy", "Max", "Rocky");
    private int nextNameIndex = 0;

    /**
     *  See README.
     *  Hint: you may use `nextNameIndex`
     */
    public String getNextName() {
        // TODO - Your code starts here
        String name = NAMES.get(nextNameIndex);
        nextNameIndex = (nextNameIndex + 1) % NAMES.size(); // 循环取名
        return name;
        // TODO - Your code ends here
    }

    public String getRandomName() {
        return NAMES.get(new Random().nextInt(NAMES.size()));
    }

    public DogCreator(Integer age, Integer level) {
        // TODO - Your code starts here
        super(age, level);


        // TODO - Your code ends here
    }

    @Override
    public Animal createAnimal(String id, String name) {
        // TODO - Your Code Starts Here
        return new Dog(id, name, ageConfig, levelConfig);

        // TODO - Your Code Ends Here
    }

}

