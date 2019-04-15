package com.example.viewsandviewgroups;


import java.util.ArrayList;

/**
 * Demo_ViewsAndViewGroups
 *
 * A simple class that holds data about LEGO kits.
 * This class implements the builder pattern, which is popular both in Java
 * development in general and in Android development in particular.
 * @see <a href="https://www.geeksforgeeks.org/builder-pattern-in-java/">Builder pattern</a>
 */
public class LegoKit {

    private String id;
    private String title;
    private int numberOfPieces;

    public LegoKit(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfPieces() {
        return numberOfPieces;
    }

    public static class Builder {
        LegoKit kit;

        public Builder() {
            kit = new LegoKit("");
        }

        public Builder id(String id) {
            kit.id = id;
            return this;
        }

        public Builder title(String title) {
            kit.title = title;
            return this;
        }

        public Builder pieces(int num) {
            kit.numberOfPieces = num;
            return this;
        }

        public LegoKit build() {
            return kit;
        }
    }

    /**
     * Creates an array of pre-defined LegoKits
     * @return array of LegoKit objects
     */
    public static LegoKit[] initKits() {
        ArrayList<LegoKit> kitsArray = new ArrayList<>();

        // This is an example of using the "builder pattern."
        // It is super-common in Java development.
        kitsArray.add(new LegoKit.Builder()
                .id("9474-1")
                .title("The Battle of Helm's Deep")
                .pieces(1330)
                .build());

        kitsArray.add(new LegoKit.Builder()
                .id("75192-1")
                .title("Millenium Falcon--UCS")
                .pieces(7513)
                .build());

        kitsArray.add(new LegoKit.Builder()
                .id("10243-1")
                .title("Parisian Restaurant")
                .pieces(2449)
                .build());

        kitsArray.add(new LegoKit.Builder()
                .id("70810-1")
                .title("MetalBeard's Sea Cow")
                .pieces(2705)
                .build());

        kitsArray.add(new LegoKit.Builder()
                .id("6804-1")
                .title("Space Rover")
                .pieces(12)
                .build());

        kitsArray.add(new LegoKit.Builder()
                .id("75955-1")
                .title("Hogwarts Express")
                .pieces(776)
                .build());

        LegoKit[] kits = new LegoKit[kitsArray.size()];
        kits = kitsArray.toArray(kits);
        return kits;
    }

}
