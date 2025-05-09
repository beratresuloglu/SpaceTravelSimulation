import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author BERAT RESULOĞLU berat.resuloglu@ogr.sakarya.edu.tr
 * @since 2025
 *        <p>
 *        Dosyaların okunduğu sınıf
 *        </p>
 */
public class DosyaOkuma {
	public static ArrayList<String> dosyaOku(String dosyaAdi) {
		ArrayList<String> satirlar = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(dosyaAdi))) {
			String satir;
			while ((satir = br.readLine()) != null) {
				satirlar.add(satir);
			}
		} catch (IOException e) {
			System.out.println("Dosya okunamadı: " + dosyaAdi);
			e.printStackTrace();
		}
		return satirlar;
	}
}
