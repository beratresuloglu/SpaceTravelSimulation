/**
 *
 * @author BERAT RESULOĞLU berat.resuloglu@ogr.sakarya.edu.tr
 * @since 2025
 *        <p>
			Bir Gezegeni temsil eden sınıf
 *        </p>
 */
public class Gezegen {
    private String ad;
    private int gunSaat; // bir gün kaç saat
    private Zaman tarih;

    public Gezegen(String ad, int gunSaat, Zaman tarih) {
        this.ad = ad;
        this.gunSaat = gunSaat;
        this.tarih = tarih;
    }

    public void saatGecir() {
        tarih.saatIlerle();
    }

    // Getter ve Setter'lar
    public String getAd() { return ad; }
    public void setAd(String ad) { this.ad = ad; }
    public int getGunSaat() { return gunSaat; }
    public void setGunSaat(int gunSaat) { this.gunSaat = gunSaat; }
    public Zaman getTarih() { return tarih; }
    public void setTarih(Zaman tarih) { this.tarih = tarih; }
}
