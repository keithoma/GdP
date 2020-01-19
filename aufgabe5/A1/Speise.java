public class Speise extends Lebensmittel {
    private int chunk;

    public Speise(String name, int menge, int chunk) {
        super(name, menge, "g");
        this.chunk = chunk;
    }

    public boolean essen () {
        if (this.menge <= this.chunk) {
            this.menge = 0;
            return false;
        } else {
            this.menge -= this.chunk;
            return true;
        }
    }

    public boolean trinken () {
        return false;
    }
}