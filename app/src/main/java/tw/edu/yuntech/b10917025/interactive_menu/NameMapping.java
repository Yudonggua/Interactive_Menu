package tw.edu.yuntech.b10917025.interactive_menu;

public class NameMapping {

    private String name;
    private String price;
    private String element;
    private String introduction;
    private String glb;

    public NameMapping(String name, String price) {
        this.name = name;
        this.price = price;
        this.element = element;
        this.introduction = introduction;
        this.glb = glb;
    }
    public NameMapping(String name, String price, String element, String introduction, String glb) {
        this.name = name;
        this.price = price;
        this.element = element;
        this.introduction = introduction;
        this.glb = glb;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getElement() {
        return element;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getGlb() {
        return glb;
    }
}
