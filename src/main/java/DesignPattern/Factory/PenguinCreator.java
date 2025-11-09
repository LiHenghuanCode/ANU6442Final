package DesignPattern.Factory;

import java.util.List;
import java.util.Random;


public class PenguinCreator extends AnimalCreator {
    private static final List<String> NAMES = List.of("Ace", "Alaska", "Aurora", "Bam-Bam", "Banjo");
    private static int nextNameIndex = 0;

    public String getNextName() {
        // TODO - Your code starts here
        String name = NAMES.get(nextNameIndex);
        nextNameIndex = (nextNameIndex + 1) % NAMES.size();
        return name;
        // TODO - Your code ends here
    }

    public String getRandomName() {
        return NAMES.get(new Random().nextInt(NAMES.size()));
    }

    public PenguinCreator(Integer age, Integer level) {
        // TODO - Your code starts here
        super(
                (age != null) ? age : 1,
                (level != null) ? level : 2
        );
        // TODO - Your code ends here
    }

    @Override
    public Animal createAnimal(String id, String name) {
        // TODO - Your Code Starts Here
        return new Penguin(id, name, ageConfig, levelConfig);

        // TODO - Your Code Ends Here
    }

}
