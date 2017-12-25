


public class FormatterTest {

    public String[] zerlegeStringInZeilen(String str) {

        String array[] = str.split("[:\\r?\\n]+"); // nach Zeilen oder dem Zeichen ":" splitten.
        //  String array[] = str.split("[:]+"); // nach Zeilen oder dem Zeichen ":" splitten.

        for (int i = 0; i < array.length; i++) {

            if ("Lesen".equals(array[i])) {
                array[i] = "Hallooooooooooooooooooo"; // das funktioniert.
            }
            String arra = array[i].trim();
//            System.out.println(i + " " + arra);
        }
        return array;
    }

    public String[] bestimmeTagDerWoche(String[] strArray) {
        String inhalt[] = new String[strArray.length];
        String tage[] = new String[strArray.length * 2];
        int inhaltIndex = 0;
        for (int i = 0; i < strArray.length; i++) {
            //    System.out.println(ermittleTagDerWoche(strArray[i]));
            if (ermittleTagDerWoche(strArray[i])) {
                inhalt[i]=strArray[i];
                 System.out.println((inhalt[i]));
            }
        }
//        for (int j = 0; j < tage.length; j++) {
//            String hilfe = strArray[j];
//            tage[j] = hilfe;
//            if (j % 2 == 0) { // gerade und ungerade index bestimmen.
//                inhalt[inhaltIndex] = tage[j];
//                inhaltIndex++;
//            }
//            System.out.println(tage.length + " " + inhalt[inhaltIndex]);
//        }
        return inhalt;
    }

    private boolean ermittleTagDerWoche(String str) {
        str = str.toLowerCase();
        if (str.startsWith("montag")) {
            System.out.println("montag ");
            return true;
        } else if (str.startsWith("dienstag")) {
            System.out.println("dienstag ");
            return true;
        } else if (str.startsWith("mittwoch")) {
            System.out.println("mittwoch ");

            return true;
        } else if (str.startsWith("donnerstag")) {
            System.out.println("donnerstag ");
            return true;
        } else {
            return str.startsWith("freitag");

        }
    }
}


