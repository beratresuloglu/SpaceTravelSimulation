import java.util.ArrayList;

/**
 *
 * @author BERAT RESULOĞLU berat.resuloglu@ogr.sakarya.edu.tr
 * @since 2025
 *        <p>
 *        Simulasyonun kontrol edildiği , main fonksiyonu içeren sınıf
 *        </p>
 */
public class Simulasyon {
	private static ArrayList<Gezegen> gezegenler;
	private ArrayList<UzayAraci> uzayAraclari;
	private ArrayList<Kisi> kisiler;

	public Simulasyon() {
		this.gezegenler = new ArrayList<>();
		this.uzayAraclari = new ArrayList<>();
		this.kisiler = new ArrayList<>();
	}

	public static Gezegen gezegenBulStatik(String ad) {
		for (Gezegen gezegen : gezegenler) {
			if (gezegen.getAd().equals(ad)) {
				return gezegen;
			}
		}
		return null;
	}

	public void baslat() {
		while (!tumUzayAraclariVardiMi()) {
			saatIlerle();
			ekraniGuncelle();
			try {
				Thread.sleep(50); // 0.5 saniyede bir ekran güncelle
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		ekraniGuncelle();
		System.out.println("\nSimulasyon tamamlandı!");
	}

	public void verileriYukle() {
		// Kişileri yükle
		ArrayList<String> kisiSatirlari = DosyaOkuma.dosyaOku("Kisiler.txt");
		for (String satir : kisiSatirlari) {
			String[] parcalar = satir.split("#");
			if (parcalar.length == 4) {
				String isim = parcalar[0];
				int yas = Integer.parseInt(parcalar[1]);
				int kalanOmur = Integer.parseInt(parcalar[2]);
				String uzayAraciAdi = parcalar[3];
				Kisi kisi = new Kisi(isim, yas, kalanOmur, uzayAraciAdi);
				kisiler.add(kisi);
			}
		}

		// Gezegenleri yükle
		ArrayList<String> gezegenSatirlari = DosyaOkuma.dosyaOku("Gezegenler.txt");
		for (String satir : gezegenSatirlari) {
			String[] parcalar = satir.split("#");
			if (parcalar.length == 3) {
				String ad = parcalar[0];
				int gunSaat = Integer.parseInt(parcalar[1]);
				String[] tarihParca = parcalar[2].split("\\.");
				int gun = Integer.parseInt(tarihParca[0]);
				int ay = Integer.parseInt(tarihParca[1]);
				int yil = Integer.parseInt(tarihParca[2]);
				Zaman tarih = new Zaman(gun, ay, yil);
				Gezegen gezegen = new Gezegen(ad, gunSaat, tarih);
				gezegenler.add(gezegen);
			}
		}

		// Uzay araçlarını yükle
		ArrayList<String> aracSatirlari = DosyaOkuma.dosyaOku("Araclar.txt");
		for (String satir : aracSatirlari) {
			String[] parcalar = satir.split("#");
			if (parcalar.length == 5) {
				String ad = parcalar[0];
				String cikisGezegeni = parcalar[1];
				String varisGezegeni = parcalar[2];
				String[] tarihParca = parcalar[3].split("\\.");
				int gun = Integer.parseInt(tarihParca[0]);
				int ay = Integer.parseInt(tarihParca[1]);
				int yil = Integer.parseInt(tarihParca[2]);
				Zaman cikisTarihi = new Zaman(gun, ay, yil);
				int mesafeSaat = Integer.parseInt(parcalar[4]);
				UzayAraci uzayAraci = new UzayAraci(ad, cikisGezegeni, varisGezegeni, cikisTarihi, mesafeSaat);

				// Yolcuları ekle
				for (Kisi kisi : kisiler) {
					if (kisi.getUzayAraciAdi().equals(ad)) {
						uzayAraci.yolcuEkle(kisi);
					}
				}
				uzayAraclari.add(uzayAraci);
			}
		}

		System.out.println("Veriler başarıyla yüklendi.");
	}

	public void ekraniGuncelle() {
		clearScreen();

		System.out.println("Gezegenler:\n");

		int toplamGezegen = gezegenler.size();
		int satirSayisi = (int) Math.ceil(toplamGezegen / 6.0); // Kaç grup 8'lik var

		for (int i = 0; i < satirSayisi; i++) {
			// 1. Satır: Adlar
			for (int j = i * 6; j < Math.min((i + 1) * 6, toplamGezegen); j++) {
				System.out.printf("--- %-10s ---\t", gezegenler.get(j).getAd());
			}
			System.out.println();

			// 2. Satır: Tarihler
			for (int j = i * 6; j < Math.min((i + 1) * 6, toplamGezegen); j++) {
				System.out.printf("%-18s\t", gezegenler.get(j).getTarih().toString());
			}
			System.out.println();

			// 3. Satır: Nüfuslar
			for (int j = i * 6; j < Math.min((i + 1) * 6, toplamGezegen); j++) {
				System.out.printf("%-18d\t", gezegenNufusHesapla(gezegenler.get(j)));
			}
			System.out.println("\n"); // 6'lik bloktan sonra ekstra boşluk bırak
		}

		// Uzay Araçları kısmı devam ediyor...
		System.out.println("Uzay Araçları:");
		System.out.printf("%-10s %-10s %-10s %-10s %-20s %-20s\n", "Araç Adı", "Durum", "Çıkış", "Varış",
				"Hedefe Kalan Saat", "Varış Tarihi");
		for (UzayAraci arac : uzayAraclari) {
			String durum;
			if (arac.isImhaDurumu()) {
				durum = "İMHA";
			} else if (arac.getKalanSaat() == 0) {
				durum = "Vardı";
			} else if (gezegenBul(arac.getCikisGezegeni()).getTarih().compareTo(arac.getCikisTarihi()) < 0) {
				durum = "Bekliyor";
			} else {
				durum = "Yolda";
			}

			String kalanSaatYazdir = (arac.getKalanSaat() == 0 || arac.isImhaDurumu()) ? "-"
					: String.valueOf(arac.getKalanSaat());
			String varisTarihYazdir = (arac.getKalanSaat() == 0 || arac.isImhaDurumu()) ? "-"
					: arac.getVarisTarihi().toString();

			System.out.printf("%-10s %-10s %-10s %-10s %-20s %-20s\n", arac.getAd(), durum, arac.getCikisGezegeni(),
					arac.getVarisGezegeni(), kalanSaatYazdir, varisTarihYazdir);
		}
	}

	private static void clearScreen() {
		try {
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (Exception e) {
			for (int i = 0; i < 50; i++) {
				System.out.println();
			}
		}
	}

	private boolean tumUzayAraclariVardiMi() {
		for (UzayAraci arac : uzayAraclari) {
			if (!arac.isImhaDurumu() && arac.getKalanSaat() > 0) {
				return false;
			}
		}
		return true;
	}

	private void saatIlerle() {
		for (Gezegen gezegen : gezegenler) {
			gezegen.saatGecir();
		}

		for (UzayAraci arac : uzayAraclari) {
			if (!arac.isImhaDurumu()) {
				Gezegen cikisGezegen = gezegenBul(arac.getCikisGezegeni());
				if (cikisGezegen.getTarih().compareTo(arac.getCikisTarihi()) >= 0 && arac.getKalanSaat() > 0) {
					arac.saatGecir();
				}
			}
		}

		for (Kisi kisi : kisiler) {
			if (kisi.yasiyorMu()) {
				kisi.saatGecir();
			}
		}

		for (UzayAraci arac : uzayAraclari) {
			if (!arac.isImhaDurumu()) {
				arac.imhaKontrol();
			}
		}
	}

	private int gezegenNufusHesapla(Gezegen gezegen) {
		int sayac = 0;
		for (UzayAraci arac : uzayAraclari) {
			if (arac.getKalanSaat() == 0 && arac.getVarisGezegeni().equals(gezegen.getAd())) {
				for (Kisi kisi : arac.getYolcular()) {
					if (kisi.yasiyorMu()) {
						sayac++;
					}
				}
			} else if (arac.getCikisGezegeni().equals(gezegen.getAd()) && arac.getKalanSaat() == arac.getMesafeSaat()) {
				for (Kisi kisi : arac.getYolcular()) {
					if (kisi.yasiyorMu()) {
						sayac++;
					}
				}
			}
		}
		return sayac;
	}

	private Gezegen gezegenBul(String ad) {
		for (Gezegen gezegen : gezegenler) {
			if (gezegen.getAd().equals(ad)) {
				return gezegen;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Simulasyon simulasyon = new Simulasyon();
		simulasyon.verileriYukle();
		simulasyon.baslat();
	}
}
