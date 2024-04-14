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

    public TableData(int id, String command, String explanation, String example) {
        this.id = id;
        this.command = command;
        this.explanation = explanation;
        this.example = example;
    }

    public TableData(String flowerName, String flowerOccasion, String flowerPrice
            , String flowerColor, String flowerMeaning) {
        this.flowerName = flowerName;
        this.flowerOccasion = flowerOccasion;
        this.flowerPrice = flowerPrice;
        this.flowerColor = flowerColor;
        this.flowerMeaning = flowerMeaning;
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
}
