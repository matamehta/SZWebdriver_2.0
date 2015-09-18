package com.common;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class CompareFiles {

	public void compareTwoFiles() {

		try {
			FileInputStream fis = new FileInputStream("textfiles//FileExpected.txt");
			DataInputStream dis = new DataInputStream(fis);
			BufferedReader brExpected = new BufferedReader(
					new InputStreamReader(dis));

			FileInputStream fis2 = new FileInputStream("textfiles//FileActual.txt");
			DataInputStream dis2 = new DataInputStream(fis2);
			BufferedReader brActual = new BufferedReader(new InputStreamReader(
					dis2));

			int counter = 0;
			String line1 = null;
			String line2 = null;
			do {
				line1 = brExpected.readLine();
				line2 = brActual.readLine();
				counter++;
				if ((line1 != null) && (line2 != null) && (line1.equals(line2))) {
//					System.out.println("Line: ," + counter + ", Matched"
//							+ ",Actual:," + line2 + " ,Expected:," + line1);
				} else if ((line1 != null) || (line2 != null)) {
					System.out.println("Error:xxx  line: ," + counter
							+ ", Not matched" + ",Actual:," + line2
							+ " ,Expected:," + line1);
				}

			} while ((line1 != null) && (line2 != null));

			if ((line1 == null) && (line2 == null)) {
				System.out.println("SUCCESS::::: File length is matched");
			} else {
				System.out
						.println("Error : xxxxxxxxxxxxxxx   File length mismatch");
			}
			
			brExpected.close();
			brActual.close();

		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public static void main(String[] args) {
		CompareFiles obj = new CompareFiles();
		obj.compareTwoFiles();
	}
}