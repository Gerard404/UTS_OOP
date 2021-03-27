package com.UTSFernando;


/*Baris diatas akan mengambil data yang ada di package com.UTSFernando untuk digunakan sebagai jvm di converter di file dengan ekstensi .main*/

class Player {
    /*Pada baris ini akan dibuat konstruktor sebagai property setiap bagian sehingga pada nantinya jika kita membutuhkan fungsi tersebut kita tidak perlu membuat nya lagi, dan akan terbagi atas variabel nama, daya tahan, daya serang, sampai dengan keterangan jika kendaraan masih bisa digunakan. */
    private String name;
    private int baseHealth;
    private int baseAttack;
    private int incrementHealth;
    private int incrementAttack;
    private int level;
    private int totalDamage;
    private boolean isAlive;

    // Ini adalah object member
    private Armor armor;
    private Weapon weapon;
    /*Baris diatas menggunakan teknik reference private model sehingga user tidak bisa merubah armor ataupun senjata yang digunakan, dan akan di set private sehingga tidak bisa terlihat oleh user. */

    public Player(String name) {
        /*Baris di bawah akan memanggil fungsi public yang memiliki parameter nama yang akan digunakan sebagai perhitungan dari setiap serangan dan pengurangan armor dengan memanggil konstruktor yang menyimpan variabel yang sudah di buat di konstruktor. */
        this.name = name;
        /*Baris di atas akan memanggil nama yang ada di konstruktor dan akan dijadikan sebagai variabel local di fungsi ini dan akan dijadikan variabel yang bisa diguakan di fungsi ini, demikian pula variabel yang ada di baris selanjutnya. */
        this.baseHealth = 100;
        this.baseAttack = 100;
        this.level = 1;
        this.incrementHealth = 20;
        this.incrementAttack = 20;
        this.isAlive = true;
    }

    public String getName() {
        return this.name;
        /* Baris diatas akan mengembalikan isi dari variabel nama, yang nantinya akan digunakan dalam pembuatan serangan. */
    }

    public int getHealth() {
        return this.maxHealth() - this.totalDamage;
        /*Baris diatas akan memanggil variabel daya tahan yang ada di konstruktor yang akan digunakan di dalam keterangan penyerangan. */
    }

    public void display() {
        /*Baris diatas memiliki kembalian kosong/hampa/void, baris di bawah juga akan memanggil variabel yang ada dikonstruktor untuk dipakai dalam keterangan penyerangan. */
        System.out.println("Player\t\t: " + this.name);
        System.out.println("Level\t\t: " + this.level);
        System.out.println("Health\t\t: " + this.getHealth() + "/" + this.maxHealth());
        System.out.println("Attack\t\t: " + this.getAttackPower());
        System.out.println("Alive\t\t: " + this.isAlive + "\n");
    }
    /*Baris diatas akan manampilkan ketarangan berupa nama pemain sampai keterangan hidup/rusak. */

    public void attack(Player opponent) {
        /*Fungsi ini akan memulai keterangan dalam penyerangan dengan memberikan bahwa pemain 1 akanmenyerang pemain lainnya dengan memanggil variabel yang telah di set di konstruktor. Serta akan melihat bagaimana serangan bisa di hitung di fungsi selanjutnya dengan menggunaka kemablian fungsi yang ada dibaris 27. */
        // hitung damage
        int damage = this.getAttackPower();
        // print event
        System.out.println(this.name + " is attacking " + opponent.getName() + " with " + damage);
        // attack si opponent
        opponent.defence(damage);

        this.levelUp();
    }

    public void defence(int damage) {
        /* Fungsi defence ini akan melakukan pemanggilan konstruktor dan nilai kembalian yang ada di fungsi di baris-baris sebelumnya dan akan mulai untuk menghitung keterangan dari setiap serangan yang akan dilakukan. */

        // receive damage
        int defencePower = this.armor.getDefencePower();
        /*Baris diatas akan menjadi perhitungan daya tahan dengan memanggil variabel dikonstruktor dengan memanggil sintaks this yang berarti akan memanggil armor dan daya tahan. */
        int deltaDamage;

        System.out.println(this.name + " defence power = " + defencePower);
        /*Percabangan dibawah akan mengitung kerusakan yang terjadi di setiap player, jika kerusakan lebih besar dari daya tahan maka kerusakan akan terjadi, dan jika tidak, maka tidak akan terjadi perubahan kerusakan. */
        if (damage > defencePower) {
            deltaDamage = damage - defencePower;
        } else {
            deltaDamage = 0;
        }

        System.out.println("damage earned = " + deltaDamage + "\n");
        /*Baris diatas akan menghitung kerusakan yang di dapat setelah serangan terjadi. Dengan menggunakan variabel yang telah di buat sebelumnya, yaitu deltaGamage */

        // adding total damage
        this.totalDamage += deltaDamage;
        /*Baris diatas juga akan memanggil variabel konstruktor yaitu totalDamage, sehingga bisa digunakan untuk menghitung total kerusakan. */

        // check is alive
        /*Percbangan di atas akan menghitung apakah kendaraan masih bisa digunakan ata sudah rusak dengan menggunakan tipe data Boolean yaitu dengan variabel isAlive. */
        if (this.getHealth() <= 0) {
            this.isAlive = false;
            this.totalDamage = this.maxHealth();
        }

        this.display();
        /* Baris ini akan memanggil fungsi display yang akan menampilkan keterangan pertempuran antar user. */
    }

    private int getAttackPower() {
        /* Fungsi ini di set sebagai private karena sangat riskan terjadi penyalanggunaan dari user, karena attackpower sifatnya mutlak dan tidak bisa di rubah.Fungsi ini juga akan melakukan perhitungan terhadap jenis senjata dan kerusakan yang terjadi. */
        return this.baseAttack + this.level * this.incrementAttack + this.weapon.getAttack();
    }

    private void levelUp() {
        /* Fungsi ini di set sebagai private karena sangat riskan terjadi penyalanggunaan dari user, karena level sifatnya mutlak dan tidak bisa di rubah.Fungsi ini juga akan melakukan perhitungan terhadap kenaikan level yang terjadi. */

        this.level++;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
        /* Fungsi ini akan menggunaan armor sebagai variabel local yang nantinya akan bisa digunakan dalam fungsi display. */
    }

    public void setWeapon(Weapon weapon) {
        /* Fungsi ini akan menggunaan weapon sebagai variabel local yag nantinya akan bisa digunakan dalam fungsi display. */

        this.weapon = weapon;
    }

    public int maxHealth() {
        return this.baseHealth + this.level * this.incrementHealth + this.armor.getAddHealth();
    }

}

class Weapon {
    /* Methode diatas akan mengunakan variabel yang telah di set yang nantinya akan digunakan dalam fungsi display. */
    private String name;
    private int attack;

    public Weapon(String name, int attack) {
        this.name = name;
        this.attack = attack;
    }

    public int getAttack() {
        return this.attack;
    }
}

class Armor {
    /* kelas ini akan digunakan untuk membuat beberapa variabel menjadi private sehingga user tidak bisa melihat kode yang ada di dalamnya. */
    private String name;
    private int strength;
    private int health;

    public Armor(String name, int strength, int health) {
        this.name = name;
        this.strength = strength;
        this.health = health;
    }

    public int getAddHealth() {
        /* Fungsi ini membuat perhitungan kekuatan yang dimiliki user dengan mengambil kembalian nilai serta variabel yang ada di konstruktor. */
        return this.strength * 10 + this.health;
    }

    public int getDefencePower() {
        return this.strength * 2;
    }

}

public class Main {
    /* Ini adalah program utama yang akan memanggil fungsi, kelas, dan konstruktor yang telah dibuat sebagai main program. */
    public static void main(String[] args) {
        /* Disini pengembang bisa membuat simulasi input berupa nama pemain sampai dengan baju baja yang akan digunakan. */
        Player player1 = new Player("Fernando");
        Armor armor1 = new Armor("Baja", 5, 100);
        Weapon weapon1 = new Weapon("RPG", 10);
        /* Baris dibawah akan memanggil method yang telah dibuat untuk digunakan dalam fungsi display. */
        player1.setArmor(armor1);
        player1.setWeapon(weapon1);

        Player player2 = new Player("Akbar");
        Armor armor2 = new Armor("Plat Besi", 1, 40);
        Weapon weapon2 = new Weapon("Machine gun", 40);
        player2.setArmor(armor2);
        player2.setWeapon(weapon2);
        /* Baris dibawah akan menampilkan variabel yang telah dibuat oleh semua method dan konstruktor. */
        player1.display();
        player2.display();

        player1.attack(player2);
        player2.attack(player1);
        player2.attack(player1);
    }
}

