package sauvegarde;

import interpreter.IAI_Definitions;
import interpreter.IAutomaton;
import ricm3.parser.AutomataParser;
import ricm3.parser.Ast.AI_Definitions;

import java.io.Writer;
import java.util.ListIterator;
import java.io.Reader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Sauvegarde {

	public int tab_map[][];
	public IAutomaton tab_auto[];
	public String nom_fichier_auto;
	
	static int nb_cases_lig=48;
	static int nb_cases_col=64;
	static int nb_auto=12;

	public Sauvegarde(int map[][], IAutomaton autos[], String fichier) {
		tab_map = map;
		tab_auto = autos;
		nom_fichier_auto = fichier;
	}

	public void encode(String nom_fichier_sauv) { // Ecrit le fichier de sauvegarde

		Writer writer = null;

		try {

			File fichier = new File(nom_fichier_sauv);
			writer = new FileWriter(fichier);

			// Ecriture du tableau
			for (int i = 0; i < nb_cases_lig; i++) {
				for (int j = 0; j < nb_cases_col; j++) {
					writer.write(Integer.toString(tab_map[i][j]));
				}
			}
			writer.write("\n");

			// Ecriture du nom du fichier source
			writer.write(nom_fichier_auto);
			writer.write("\n");

			// Ecriture des noms d'automates
			for (int i = 0; i < tab_auto.length; i++) {
				writer.write(tab_auto[i].name());
				writer.write("\n");
			}

		} catch (IOException e) {
		}

		if (writer != null) {

			try {

				writer.close();

			} catch (IOException e) {

			}

		}
	}

	public static Sauvegarde decode(String nom_fich_sauvegarde) {

		FileReader fr = null;
		IAutomaton autos[] = new IAutomaton[nb_auto];
		int map[][] = new int[nb_cases_lig][nb_cases_col];
		String nom_fich_autos=null;

		try {

			fr = new FileReader(nom_fich_sauvegarde);
			// Flux de lecture de lignes
			char a[] = new char[1];

			for (int i = 0; i < nb_cases_lig; i++) {
				for (int j = 0; j < nb_cases_col; j++) {
					fr.read(a);
					map[i][j] = Integer.parseInt(String.valueOf(a[0]));
				}
			}
			fr.read(a);//Lecture du carac de fin de ligne
			
			// Flux de lecture de lignes
			LineNumberReader lnr = new LineNumberReader(fr);

			String line = null;

			// Lecture du nom fichier automate source
			line = lnr.readLine();
			nom_fich_autos= new String (line);
			AI_Definitions ai_def = ((AI_Definitions) AutomataParser.from_file(line));
			IAI_Definitions iai_def = ai_def.make();

			int i = 0;
			do {

				// lecture de nom d'automate
				line = lnr.readLine();

				IAutomaton rech = recherche_automate(line, iai_def);
				if (rech==null) {
					return null;
				}
				autos[i] = rech;
				i++;

			} while (line != null && i < nb_auto);

		} catch (FileNotFoundException e) {
			System.out.println("Sauvegarde: fichier non trouvé");
			return null;
		} catch (IOException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
		

		if (fr != null) {

			try {

				fr.close();

			} catch (IOException e) {
			}
		}
		
		Sauvegarde sauv=new Sauvegarde(map,autos,nom_fich_autos);
		return sauv;
	}
	
	public static IAutomaton recherche_automate(String autoname, IAI_Definitions def) {
		
		ListIterator<IAutomaton> iter = def.automatas.listIterator();
		while (iter.hasNext()) {
			IAutomaton aut = iter.next();
			if (aut.name().equals(autoname)) {
				return aut;
			}
		}
		System.out.println("Sauvegarde: automate "+autoname+" non trouvé");
		return null;
	}
}
