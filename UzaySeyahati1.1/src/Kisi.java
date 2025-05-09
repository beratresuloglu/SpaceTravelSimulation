/**
 *
 * @author BERAT RESULOĞLU berat.resuloglu@ogr.sakarya.edu.tr
 * @since 2025
 *        <p>
 *        Kişi bilgilerini tutan sınıf
 *        </p>
 */
public class Kisi {
    private String isim;
    private int yas;
    private int kalanOmur;
    private String uzayAraciAdi;

    public Kisi(String isim, int yas, int kalanOmur, String uzayAraciAdi) {
        this.isim = isim;
        this.yas = yas;
        this.kalanOmur = kalanOmur;
        this.uzayAraciAdi = uzayAraciAdi;
    }

    public void saatGecir() {
        if (kalanOmur > 0) kalanOmur--;
    }

    public boolean yasiyorMu() {
        return kalanOmur > 0;
    }

    // Getter ve Setter'lar
    public String getIsim() { return isim; }
    public void setIsim(String isim) { this.isim = isim; }
    public int getYas() { return yas; }
    public void setYas(int yas) { this.yas = yas; }
    public int getKalanOmur() { return kalanOmur; }
    public void setKalanOmur(int kalanOmur) { this.kalanOmur = kalanOmur; }
    public String getUzayAraciAdi() { return uzayAraciAdi; }
    public void setUzayAraciAdi(String uzayAraciAdi) { this.uzayAraciAdi = uzayAraciAdi; }
}
