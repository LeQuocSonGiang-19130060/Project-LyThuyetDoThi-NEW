package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import algorithm.tuan1.Graph;

public class ReadFile {

	static int i;

	public static Graph readFile(String path) {
		Graph matrix = new Graph();
		i = 0;// dong
		try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {																						// utf8
			stream.forEach(line -> {
				matrix.addPoint();
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
			stream.forEach(line -> {
				StringTokenizer token = new StringTokenizer(line, " ");
				int j = 0;
				while (token.hasMoreTokens()) {
					matrix.readFile(i, j, Integer.parseInt(token.nextToken()));
					j++;
				}
				i++;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matrix;
	}
	
	public static void wirteFile(String path, Graph matrix) {
		File file = new File(path);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			writer.write(matrix.printMatrix());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Graph matrix = ReadFile.readFile("C:\\Users\\SONGIANG-PRIME\\eclipse-workspace\\NewProject-LyThuyetDoThi\\file.txt");
	//	System.out.println(matrix.printMatrix());
		Graph g = new Graph();
		g.addPoint();
		g.addPoint();
		g.addPoint();
		g.addPoint();
		g.addPoint();
		System.err.println(g.printMatrix());
		ReadFile.wirteFile("C:\\Users\\SONGIANG-PRIME\\eclipse-workspace\\NewProject-LyThuyetDoThi\\file.txt", g);
	}
}
