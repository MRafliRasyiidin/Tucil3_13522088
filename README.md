# Tugas Kecil 3 Strategi Algoritma
Program untuk menyelesaikan permainan Word Ladder

# Deskripsi Program
Word Ladder merupakan permainan untuk membuat rantai kata yang menghubungkan start word ke target word dimana setiap kata pada rantai kata hanya dapat berbeda satu huruf dari kata sebelumnya

Program ini dapat memberikan solusi untuk permainan Word Ladder dengan menggunakan 3 algoritma, yaitu Uniform Cost Search, Greedy Best First Search, dan A*

# Requirements
Pastikan java telah terinstal pada perangkat Anda
Gunakan perintah berikut pada command prompt untuk mengecek versi java yang terinstal
```
java --version
```
Program telah dites pada java versi 20.0.2. Jika terdapat masalah ketika menjalankan program, coba gunakan versi java tersebut.

# Cara Menjalankan Program
1. Masuk ke dalam folder src
```
cd src
```
2. Jalankan perintah berikut untuk menjalankan program dengan tampilan GUI
```
javac GUI.java
java GUI
```
3. Jalankan perintah berikut jika ingin menjalankan program tanpa GUI (CLI)
```
javac Main.java
java Main
```
4. Kamus yang digunakan saat ini adalah dictionary_2.txt. Jika ingin mengganti kamus yang digunakan, pindahkan kamus baru ke dalam folder src/Dictionary. Kemudian ubah directory pada file Controller.java menjadi "Dictionary/Nama_kamus_baru.txt"
![image](https://github.com/MRafliRasyiidin/Tucil3_13522088/assets/118504604/1a90cb00-f40b-44b2-abeb-fc0c0af2b71c)

# Cara Alternatif Menjalankan Program
Pada folder root, jalankan perintah berikut untuk melakukan compile
```
./compile
```
Kemudian, jalankan perintah berikut untuk menjalankan program
```
./run
```

Note: cara ini akan menjalankan program dengan tampilan GUI dan mungkin hanya berlaku pada Windows

# Cara Menggunakan Program
1. Masukkan kata awal dan kata tujuan pada kolom yang disediakan
2. Pilih metode yang ingin digunakan
3. Program akan mengeluarkan solusi yang sesuai jika input benar dan mengeluarkan peringatan jika output salah

# Author
Created by Muhamad Rafli Rasyiidin - 13522088
