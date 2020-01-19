public class Brot extends Speise {
    private static String bread_name[] = {
        "Weißbrot",
        "Schwarzbrot",
        "Mischbrot",
        "Spezialbrot"
    };

    public Brot(int bread_type, int menge) {
        super(bread_name[bread_type >= 0 && bread_type <= 2 ? bread_type : 3], menge, 50);
    }

}