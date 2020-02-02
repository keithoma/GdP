public class SelectionSort
{
  // sortiere das Array a mit SelectionSort
  public static void sort(String[] a, char c)
  {
      // TODO: selection sort
  }
  
  public static void main(String[] argv) 
  {
    // ueberpruefe Eingabeparameter
    if(argv.length == 0 || argv[0].length() != 1) {
      System.out.println("Fehler: es wird ein Buchstabe als Parameter erwartet");
      System.exit(-1);
    }

    // den buchstaben aus dem Parameter extrahieren
    char c = argv[0].charAt(0);
    
    // alle Zeilen ueber die Konsole einlesen
    String[] a = StdIn.readAllLines();
    
    
    // Array sortieren
    sort(a, c);
    
    // TODO: sortierte Liste ausgeben
    
  }
}