public class Getraenk extends Lebensmittel {
    private int chunk;

    public Getraenk(String name, int menge, int chunk) {
        super(name, menge, "ml");
        this.chunk = chunk;
    }

    public boolean essen (int menge) {
        return false;
    }

    public boolean trinken (int menge) {
        if (this.menge <= this.chunk) {
            this.menge = 0;
            return false;
        } else {
            this.menge -= menge;
            return true;
        }
        
    }
}