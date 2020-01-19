public class Speise extends Lebensmittel {
    private int chunk;

    public Speise(String name, int menge, int chunk) {
        super(name, menge, "g");
        this.chunk = chunk;
    }

    public boolean essen (int menge) {
        if (this.menge <= this.chunk) {
            this.menge = 0;
            return false;
        } else {
            this.menge -= menge;
            return true;
        }
    }

    public boolean trinken (int menge) {
        return false;
    }
}