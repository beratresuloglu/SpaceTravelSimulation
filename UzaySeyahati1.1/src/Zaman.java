/**
 *
 * @author BERAT RESULOĞLU berat.resuloglu@ogr.sakarya.edu.tr
 * @since 2025
 *        <p>
 *        Zaman işlemlerinin yapıldığı sınıf
 *        </p>
 */
public class Zaman {
    private int gun;
    private int ay;
    private int yil;
    private int saat;

    public Zaman(int gun, int ay, int yil) {
        this.gun = gun;
        this.ay = ay;
        this.yil = yil;
        this.saat = 0;
    }

    public void saatIlerle() {
        saat++;
        if (saat >= 24) {
            saat = 0;
            gun++;
            if (gun > 30) { // Basit bir 30 gün mantığı (isteğe göre 31 çekme yapılabilir)
                gun = 1;
                ay++;
                if (ay > 12) {
                    ay = 1;
                    yil++;
                }
            }
        }
    }

    public void gunEkle(int gunSayisi) {
        for (int i = 0; i < gunSayisi; i++) {
            gun++;
            if (gun > 30) {
                gun = 1;
                ay++;
                if (ay > 12) {
                    ay = 1;
                    yil++;
                }
            }
        }
    }
    
    public int compareTo(Zaman diger) {
        if (this.yil != diger.yil) {
            return this.yil - diger.yil;
        } else if (this.ay != diger.ay) {
            return this.ay - diger.ay;
        } else {
            return this.gun - diger.gun;
        }
    }


    @Override
    public String toString() {
        return String.format("%02d.%02d.%04d", gun, ay, yil);
    }

    // Getter ve Setter'lar
    public int getGun() { return gun; }
    public void setGun(int gun) { this.gun = gun; }
    public int getAy() { return ay; }
    public void setAy(int ay) { this.ay = ay; }
    public int getYil() { return yil; }
    public void setYil(int yil) { this.yil = yil; }
    public int getSaat() { return saat; }
    public void setSaat(int saat) { this.saat = saat; }
}
