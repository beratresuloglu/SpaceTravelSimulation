import java.util.ArrayList;

/**
*
* @author BERAT RESULOĞLU berat.resuloglu@ogr.sakarya.edu.tr
* @since 2025
*        <p>
			Uzay araçlarını temsil eden sınıf
*        </p>
*/
public class UzayAraci {
    private String ad;
    private String cikisGezegeni;
    private String varisGezegeni;
    private Zaman cikisTarihi;
    private int mesafeSaat;
    private ArrayList<Kisi> yolcular;
    private boolean imhaDurumu = false;
    private int kalanSaat;
    private Zaman varisTarihi; // VARIŞ TARİHİ sabit olacak

    public UzayAraci(String ad, String cikisGezegeni, String varisGezegeni, Zaman cikisTarihi, int mesafeSaat) {
        this.ad = ad;
        this.cikisGezegeni = cikisGezegeni;
        this.varisGezegeni = varisGezegeni;
        this.cikisTarihi = cikisTarihi;
        this.mesafeSaat = mesafeSaat;
        this.kalanSaat = mesafeSaat;
        this.yolcular = new ArrayList<>();
        this.varisTarihi = hesaplaVarisTarihi(); // varış tarihi ilk başta hesaplanıyor
    }

    private Zaman hesaplaVarisTarihi() {
        // Çıkış tarihi üzerinden mesafe saatine göre varış tarihi hesapla
        Gezegen varisGezegen = Simulasyon.gezegenBulStatik(varisGezegeni);
        if (varisGezegen == null) {
            return new Zaman(cikisTarihi.getGun(), cikisTarihi.getAy(), cikisTarihi.getYil());
        }

        Zaman sonuc = new Zaman(cikisTarihi.getGun(), cikisTarihi.getAy(), cikisTarihi.getYil());
        int gunSaat = varisGezegen.getGunSaat();
        int gunArtis = mesafeSaat / gunSaat;
        sonuc.gunEkle(gunArtis);
        return sonuc;
    }

    public void saatGecir() {
        if (kalanSaat > 0) {
            kalanSaat--;
        }
    }

    public void yolcuEkle(Kisi kisi) {
        yolcular.add(kisi);
    }

    public boolean imhaKontrol() {
        for (Kisi kisi : yolcular) {
            if (kisi.yasiyorMu()) {
                return false;
            }
        }
        imhaDurumu = true;
        return true;
    }

    // Getter ve Setter'lar
    public String getAd() { return ad; }
    public void setAd(String ad) { this.ad = ad; }
    public String getCikisGezegeni() { return cikisGezegeni; }
    public void setCikisGezegeni(String cikisGezegeni) { this.cikisGezegeni = cikisGezegeni; }
    public String getVarisGezegeni() { return varisGezegeni; }
    public void setVarisGezegeni(String varisGezegeni) { this.varisGezegeni = varisGezegeni; }
    public Zaman getCikisTarihi() { return cikisTarihi; }
    public void setCikisTarihi(Zaman cikisTarihi) { this.cikisTarihi = cikisTarihi; }
    public int getMesafeSaat() { return mesafeSaat; }
    public void setMesafeSaat(int mesafeSaat) { this.mesafeSaat = mesafeSaat; }
    public ArrayList<Kisi> getYolcular() { return yolcular; }
    public void setYolcular(ArrayList<Kisi> yolcular) { this.yolcular = yolcular; }
    public boolean isImhaDurumu() { return imhaDurumu; }
    public void setImhaDurumu(boolean imhaDurumu) { this.imhaDurumu = imhaDurumu; }
    public int getKalanSaat() { return kalanSaat; }
    public void setKalanSaat(int kalanSaat) { this.kalanSaat = kalanSaat; }
    public Zaman getVarisTarihi() { return varisTarihi; }
    public void setVarisTarihi(Zaman varisTarihi) { this.varisTarihi = varisTarihi; }
}
