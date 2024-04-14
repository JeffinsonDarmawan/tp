package florizz.objects;

public class TableData {
    private int id;
    private String command;
    private String explanation;
    private String example;
    private String flowerName;
    private String flowerOccasion;
    private String flowerPrice;
    private String flowerColor;
    private String flowerMeaning;
    private String type;

    public TableData(int id, String command, String explanation, String example) {
        this.id = id;
        this.command = command;
        this.explanation = explanation;
        this.example = example;
    }

    public TableData(int id, String flowerName, String flowerColor, String flowerOccasion,
                     String flowerMeaning, String flowerPrice, String type) {
        this.id = id;
        this.flowerName = flowerName;
        this.flowerOccasion = flowerOccasion;
        this.flowerColor = flowerColor;
        this.flowerMeaning = flowerMeaning;
        this.flowerPrice = flowerPrice;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getCommand() {
        return command;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getExample() {
        return example;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public String getFlowerOccasion() {
        return flowerOccasion;
    }

    public String getFlowerPrice() {
        return flowerPrice;
    }

    public String getFlowerColor() {
        return flowerColor;
    }

    public String getFlowerMeaning() {
        return flowerMeaning;
    }
    public String getType() {
        return type;
    }
}
