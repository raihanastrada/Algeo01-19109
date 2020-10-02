# Algeo01-19109
Tubes 1 Algeo 2019
Tubes 1 mahasiswa IF 2123 2020/2021 kelompok 58.
Anggota : Christian Tobing Alexandro (13519109), Raihan Astrada Fathurrahman (13519113), Muhammad Rifat Abiwardani (13519205)

Tubes 1 IF 2123 adalah membuat program yang dapat menghitung:
1. Solusi sistem persamaan linier
2. Determinan matriks
3. Matriks balikan
4. Interpolasi polinom
5. Regresi linier berganda

Perhitungan 1, 4, dan 5 dapat dilakukan melalui metode:
1. Gauss
2. Gauss-Jordan
3. Matriks balikan (x = A^-1 * b)
4. Kaidah Cramer

Input matriks dapat dilakukan melalui:
1. Input dari keyboard
2. Input filename, lalu program membaca file .txt

Output hasil dapat dilakukan melalui:
1. Print pada terminal
2. Save ke file .txt

# Struktur Repository
Folder bin berisi java byte code (.class)
Folder src berisi source code dari program java
Folder test berisi data uji
Folder doc berisi laporan

# Penggunaan
Dengan current working directory di set ke folder Algeo-19109, panggil "java src/Driver". Lalu akan muncul menu input; jika mau input melalui keyboard, input 1 dan jika mau melalui file, input 2. Setelah itu akan muncul pilihan menu aktivitas. Input 1 untuk SPL, input 2 untuk determinan, input 3 untuk matriks, input 4 untuk interpolasi polinom, input 5 untuk regresi linier berganda, dan input 6 untuk keluar dari program.

Saat input filename, semua filename diacu terhadap current working directory. Sebagai contoh, untuk menggunakan file "TC1A.txt", input "test/TC1A.txt". Untuk save output ke file, hal sama dilakukan.
