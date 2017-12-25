package bericht;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Einstellung;

public class DatenLeser {

    private BufferedReader breaderBericht, zweiteRunde;
    private BufferedReader breaderDaten;
    private BufferedWriter bWriter;
    List berichtFelder = new ArrayList();
    List datenFelder = new ArrayList();
    Map<String, String> feldUndDaten = new HashMap<>();

    public String leseDatei() {
        String everything = "";
        try {
            breaderBericht = new BufferedReader(new FileReader(Einstellung.BERICHTVORLAGE));
            zweiteRunde = new BufferedReader(new FileReader(Einstellung.BERICHTVORLAGE));;
            breaderDaten = new BufferedReader(new FileReader(Einstellung.TAETIGKEITEN));
            bWriter = new BufferedWriter(new FileWriter(Einstellung.SENKE_DATEI));
            String line, line2, lineDaten = "";
            while ((lineDaten = breaderDaten.readLine()) != null) {
                // Speichere in eine Liste
                leseDatenfelderEin(lineDaten);
            }
            while ((line = breaderBericht.readLine()) != null) {
                leseBerichtfelderEin(line);
            }
            while ((line2 = zweiteRunde.readLine()) != null) {
                String str = leseBerichtfelderNochmalEin(line2);
                for (int i = 0; i < berichtFelder.size(); i++) {
                    if (str.equals(berichtFelder.get(i))) {
                        line2 = line2.replace(str, datenFelder.get(i).toString());
                    }
                }
//                System.err.println((line2));
                bWriter.write((line2 + "\n"));
            }
        } catch (IOException es) {
        } finally {
            try {
                breaderBericht.close();
                bWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(DatenLeser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return everything;
    }

    public String leseBerichtfelderEin(String str) {
        Pattern pattern = Pattern.compile(":::(.*?)::");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            berichtFelder.add(matcher.group());
        }
        return str;
    }

    public String leseBerichtfelderNochmalEin(String str) {
        String feld = "";
        Pattern pattern = Pattern.compile(":::(.*?)::");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            feld = matcher.group();
        }
        return feld;
    }

    public String leseDatenfelderEin(String str) {
        Pattern pattern = Pattern.compile("::(.*?):::");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            str += datenFelder.add(matcher.group(1));
        }
        return str;
    }

    public List getBerichtfelder() {
        return berichtFelder;
    }

    public void setBerichtfelder(List eintraege) {
        this.berichtFelder = eintraege;
    }

    public List getDatenFelder() {
        return datenFelder;
    }

    public void setDatenFelder(List datenFelder) {
        this.datenFelder = datenFelder;
    }

    public Map wandeleInMapUm(List daten, List bericht) {
        if (daten.size() == bericht.size()) {
            for (int i = 0; i < daten.size(); i++) {
                feldUndDaten.put(daten.get(i).toString(), bericht.get(i).toString());
            }
            for (Map.Entry<String, String> entry : feldUndDaten.entrySet()) {
            }
        } else {
            System.err.println("Die Listen (daten und bericht) sind unterschiedlich groß.");
        }
        return feldUndDaten;
    }
}
