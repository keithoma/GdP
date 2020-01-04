public class KochSchneeflocke {

    public static void main(String[] args) {
    	// 1. Parameter ist die gewünschte Tiefe
        int depth = Integer.parseInt(args[0]);
         
        // Initialisiere Zeichenfläche, sodass die Schneeflocke
        //  gut sichtbar ist
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setScale(-0.3, 1.3);
        
        // Definiere die Punkte eines gleichschenkligen Dreiecks ... 
        double AX = 0.0;
        double AY = 0.0;
        
        double BX = 0.5;
        double BY = Math.sqrt(3) / 2;
        
        double CX = 1.0;
        double CY = 0.0;

        // ... rufe für jede Seite des Dreiecks die rekursive Funktion
        // zum Zeichnen der Kochkurve auf. Hierdurch entsteht dann eine
        // Schneeflocke
        zeichneKochKurve(depth, AX, AY, BX, BY);
        zeichneKochKurve(depth, BX, BY, CX, CY);
        zeichneKochKurve(depth, CX, CY, AX, AY);
    }

    private static double sqrt3 = 1.73205080757; // precomputed value of: sqrt(3)

    /**
     * Zeichnet eine Kochkurve mit der übergebenen Tiefe im Linienabschnitt von (x1,y1) nach (x5, y5).
     * 
     * Für tiefe=0 zeichnet die Funktion einfach eine Linie von (x1,y1) nach (x5,y5) (Rekursionsanker). 
     * Für tiefe>0 ruft sich die Funktion viermal selbst mit tiefe-1 und den entsprechenden Koordinaten 
     * für die 4 Teilstücke wieder auf (Rekursionsschritt). 
     * 
     * @param tiefe - Aktuelle Tiefe der Kochkurve
     * @param x1 - x-Koordinate des Beginns des akutellen Linienabschnitts 
     * @param y1 - y-Koordinate des Beginns des aktuellen Linienabschnitts
     * @param x5 - x-Koordinate des Endes des aktuellen Linienabschnitts
     * @param y5 - y-Koordinate des Endes des aktuellen Linienabschnitts
     */
    public static void zeichneKochKurve(int tiefe, double x1, double y1, double x5, double y5) {
        if (tiefe > 0)
        {
            double x2 = x1 + (x5 - x1) / 3;
            double y2 = y1 + (y5 - y1) / 3;

            double x3 = (x1 + x5) / 2 + (y1 - y5) * sqrt3 / 6;
            double y3 = (y1 + y5) / 2 + (x5 - x1) * sqrt3 / 6;

            double x4 = x1 + (x5 - x1) * 2 / 3;
            double y4 = y1 + (y5 - y1) * 2 / 3;

            zeichneKochKurve(tiefe - 1, x1, y1, x2, y2);
            zeichneKochKurve(tiefe - 1, x2, y2, x3, y3);
            zeichneKochKurve(tiefe - 1, x3, y3, x4, y4);
            zeichneKochKurve(tiefe - 1, x4, y4, x5, y5);
        }
        else
            StdDraw.line(x1, y1, x5, y5);
    }
}