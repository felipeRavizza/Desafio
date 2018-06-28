package desafioSenior;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;

public class CSVParser {
	

	public static void insertData(InputStream is,DefaultTableModel model) {

		Scanner scan = new Scanner(is);
		String[] array;
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			if (line.indexOf(",") > -1)
				array = line.split(",");
			else
				array = line.split("\t");
			Object[] data = new Object[array.length];
			for (int i = 0; i < array.length; i++)
				data[i] = array[i];

			model.addRow(data);
		}
	}

	public static Integer retornaIndexColuna(Object[] Cabecalho, String target) {
		Integer retorno = 0;
		for (int i = 0; i < Cabecalho.length; i++) {
			if (Cabecalho[i].equals(target)) {
				retorno = i;
			}
		}
		return retorno;
	}

	public static void imprimirCapitais(DefaultTableModel model, Integer indexCapital) {
		for (int x = 0; x < model.getRowCount(); x++) {
			if ("true".equals(model.getValueAt(x, indexCapital.intValue()))) {
				for (int y = 0; y < model.getColumnCount(); y++) {
					System.out.print(model.getValueAt(x, y) + " | ");
				}
				System.out.println();
			}
		}

	}

	public static void contarCidades(DefaultTableModel model) {
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {

		File f = new File("C:\\Users\\felip\\Downloads\\Desafio Cidades - Back End.csv");
		
		ArrayList<String> headers = new ArrayList<String>();

		Scanner lineScan = new Scanner(f);
		Scanner s = new Scanner(lineScan.nextLine());
		s.useDelimiter(",");
		while (s.hasNext()) {
			headers.add(s.next());
		}
		DefaultTableModel model = new DefaultTableModel(headers.toArray(), 0);
		
		InputStream is = new FileInputStream(f);
		CSVParser.insertData(is,model);
		CSVParser.imprimirCapitais(model, CSVParser.retornaIndexColuna(headers.toArray(), "capital"));
		CSVParser.contarCidades(model);

		// Imprimir todas as colunas da tabela, seguido de uma nova linha.
		for (int x = 0; x < model.getColumnCount(); x++) {
			System.out.print(model.getColumnName(x) + " | ");
		}
		System.out.println();

		// Imprimir todos os dados da tabela.

	}

}
