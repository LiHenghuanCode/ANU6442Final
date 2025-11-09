package DesignPattern.Factory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AnimalCreator {
    int ageConfig = 0;  // default `ageConfig` and `levelConfig`
    int levelConfig = 0;

    public AnimalCreator(Integer age, Integer level) {
        // TODO - Your code starts here
        if ((age != null && age < 0) || (level != null && level < 0)) {
            throw new IllegalArgumentException("Age and level must be non-negative");
        }
        // 默认不强制设定，由子类补充逻辑
        this.ageConfig = (age != null) ? age : 0;
        this.levelConfig = (level != null) ? level : 0;

        // TODO - Your code ends here
    }

    public abstract Animal createAnimal(String id, String name);

    public abstract String getNextName();

    /**
     * A template method used to create the ID, Name of an animal,
     * then returns the Animal created using these information.
     * <p>
     * 1) Create the ID using the string representation of `randomUUID()` from `UUID`.
     *    (Reference: https://docs.oracle.com/javase/8/docs/api/java/util/UUID.html#randomUUID--)
     * 2) Get the next name for the animal.
     *    (Use the appropriate helper method which should be implemented in the subclasses)
     * 3) Create the animal, passing the ID and Name
     *    (Hint: `createAnimal()` should be implemented in the subclasses)
     */
    public Animal createAnimalTemplate() {
        // TODO - Your code starts here
        String id = UUID.randomUUID().toString(); // 唯一标识
        String name = getNextName();              // 获取名字
        return createAnimal(id, name);            // 交给子类创建


        // TODO - Your code ends here
    }

    /**
     * Return a list of `number` animals.
     * @param number: number of animals to be created.
     */
    public List<Animal> createBatch(int number) {
        // TODO - Your code starts here
        List<Animal> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            list.add(createAnimalTemplate());
        }
        return list;
    }
        // TODO - Your code ends here
    }


