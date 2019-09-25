package com.example.ssd.fboauthapp.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log {

	private final String FILENAME = "logs.txt";

	public void writeToFile(String data) {

		File file = new File(FILENAME);
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
				System.out.println("File not_found. Created_new_log_file");
			} else {
				System.out.println("File_is_already_existing");
			}

			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);

			data = data + "\n---------------------------------------------------------------------------------------\n";

			bw.write(data);

		} catch (Exception e) {

			System.out.println("Error_occured : " + e.getMessage());

		} finally {

			try {
				bw.close();
			} catch (IOException e) {
				
			}
			try {
				fw.close();
			} catch (IOException e) {
				
			}
		}

	}

}
