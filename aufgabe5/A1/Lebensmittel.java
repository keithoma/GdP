public abstract class Lebensmittel {
	
	protected String name;
	protected int menge; // in Milliliter bzw. Gramm
    protected String unit;

	public Lebensmittel(String name, int menge, String unit) {
		this.name = name;
		this.menge = menge;
		this.unit = unit;
	}

	public abstract boolean essen(int menge);

	public abstract boolean trinken(int menge);

    public String status() {
        return String.format("%s (%s, %d %s)", this.getClass().getName(), this.name, this.menge, this.unit);
    }
}