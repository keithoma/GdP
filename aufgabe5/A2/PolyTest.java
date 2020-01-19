class Basis {
	protected String s = "basis";
	protected String t = "basisT";
	public String s() { return s; }
	public String t() { return t; }
}
class Ableitung extends Basis {
	protected String s = "ableitung";
	protected String t = "ableitungT";
	public Ableitung() {}
	public String s() { return s; }
}
public class PolyTest {
	static void out(Object o) { System.out.println(o); }
	public static void main(String[] args) {
		Ableitung a = new Ableitung();
		out(a.s);     // ableitung
		Basis b = a;
		out(b.s);     // basis
		out(a.s());   // ableitung
		out(b.s());   // ableitung
		out(a.t());   // basisT
		out(b.t());   // basisT
	}
}
