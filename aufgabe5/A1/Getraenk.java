public class Getraenk extends Lebensmittel {
    private int chunk;

    public Getraenk(String name, int menge, int chunk) {
        super(name, menge, "ml");
        this.chunk = chunk;
    }

    public boolean essen () {
        return false;
    }

    public boolean trinken () {
        if (this.menge <= this.chunk) {
            this.menge = 0;
            return false;
        } else {
            this.menge -= this.chunk;
            return true;
        }
        
    }
}