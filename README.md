#  Konsep Pemrograman Berorientasi Objek - PERSISTENCE
---
## Tutorial Membuat Persistence : 
### 1. Pilih “New – Entity Classes From Database” pada package

![image](https://github.com/user-attachments/assets/d9bc493d-8b85-4679-8180-6f920e48b264)

### 2.	Memilih Database koneksi dan Masukkan Password

![image](https://github.com/user-attachments/assets/95348d25-24a4-482e-b4ba-673da8a7de61)

### 3.	Lalu muncul tabel yang ada pada database yang sudah dipilih dan pilih “Add” agar tabel berpindah pada kotak sebelah kanan.

![image](https://github.com/user-attachments/assets/23c41bbe-b0b2-43af-8385-ddaa7922e4fc)

### 4. Pilih Next

![image](https://github.com/user-attachments/assets/afb3ee54-c0be-4c19-a588-1929b6819731)

### 5. Pilih Finish

![image](https://github.com/user-attachments/assets/d3e98605-b191-4e6e-af42-41893ef0c524)

### 6. Setelah membuat persistence, maka akan muncul package META-INF dan file Matakuliah.java

![image](https://github.com/user-attachments/assets/a309bfc5-a7a0-4723-8ec0-70156ec5481a)

## Source Code CRUD : 
### 1. Button Tambah
<pre>
  private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PertemuanDuaBelasPU");
        EntityManager em = emf.createEntityManager();

        try {
            // Validasi input
            if (tfKode.getText().isEmpty() || tfSks.getText().isEmpty() || tfNama.getText().isEmpty() || tfSmt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Isi Semua Kolom terlebih dahulu");
            } else {
                // Mulai transaksi
                em.getTransaction().begin();

                // Buat entitas Matakuliah baru dan set nilainya
                Matakuliah matakuliah = new Matakuliah();
                matakuliah.setKodeMk(tfKode.getText());
                matakuliah.setSks(tfSks.getText());
                matakuliah.setNamaMk(tfNama.getText());
                matakuliah.setSemesterAjar(tfSmt.getText());

                // Persist entitas ke database
                em.persist(matakuliah);

                // Commit transaksi
                em.getTransaction().commit();

                JOptionPane.showMessageDialog(null, "Berhasil disimpan");

                // Refresh data pada tampilan
                showTable();
            }
        } catch (Exception e) {
            // Rollback transaksi jika terjadi kesalahan
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "Gagal disimpan");
            System.out.println(e.getMessage());
        } finally {
            em.close();  // Tutup EntityManager setelah operasi
        }

// Kosongkan text field setelah penyimpanan
        tfKode.setText("");
        tfSks.setText("");
        tfNama.setText("");
        tfSmt.setText("");


    }                                         
</pre>

### 2. Button Update
<pre>
  private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PertemuanDuaBelasPU");
        EntityManager em = emf.createEntityManager();

        try {
            // Validasi input
            if (tfKode.getText().isEmpty() || tfSks.getText().isEmpty() || tfNama.getText().isEmpty() || tfSmt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Isi Semua Kolom terlebih dahulu");
            } else {
                // Mulai transaksi
                em.getTransaction().begin();

                // Cari entitas Matakuliah berdasarkan kode mata kuliah (assume KodeMK is primary key or unique)
                String kodeMK = tfKode.getText();
                Matakuliah matakuliah = em.find(Matakuliah.class, kodeMK);

                if (matakuliah != null) {
                    // Update nilai entitas Matakuliah yang ditemukan
                    matakuliah.setNamaMk(tfNama.getText());
                    matakuliah.setSks(tfSks.getText());
                    matakuliah.setSemesterAjar(tfSmt.getText());

                    // Simpan perubahan (JPA otomatis mengelola update saat transaksi commit)
                    em.getTransaction().commit();
                    JOptionPane.showMessageDialog(null, "Data berhasil diperbarui");

                    // Refresh data pada tampilan
                    showTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan untuk KodeMK: " + kodeMK);
                    em.getTransaction().rollback();
                }
            }
        } catch (Exception e) {
            // Rollback transaksi jika terjadi kesalahan
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "Gagal memperbarui data");
            System.out.println(e.getMessage());
        } finally {
            em.close();  // Tutup EntityManager setelah operasi
        }

// Kosongkan text field setelah update
        tfNama.setText("");
        tfKode.setText("");
        tfSks.setText("");
        tfSmt.setText("");

    }                                         
</pre>

### 3. Button Hapus
<pre>
  private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PertemuanDuaBelasPU");
        EntityManager em = emf.createEntityManager();

        try {
            // Validasi input
            if (tfKode.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Masukkan Kode Mata Kuliah yang akan dihapus");
            } else {
                // Mulai transaksi
                em.getTransaction().begin();

                // Cari entitas Matakuliah berdasarkan kode mata kuliah
                String kodeMK = tfKode.getText();
                Matakuliah matakuliah = em.find(Matakuliah.class, kodeMK);

                if (matakuliah != null) {
                    // Hapus entitas jika ditemukan
                    em.remove(matakuliah);

                    // Commit transaksi
                    em.getTransaction().commit();
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus");

                    // Refresh data pada tampilan
                    showTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan untuk KodeMK: " + kodeMK);
                    em.getTransaction().rollback();
                }
            }
        } catch (Exception e) {
            // Rollback transaksi jika terjadi kesalahan
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "Gagal menghapus data");
            System.out.println(e.getMessage());
        } finally {
            em.close();  // Tutup EntityManager setelah operasi
        }

// Kosongkan text field setelah penghapusan
        tfKode.setText("");
        tfNama.setText("");
        tfSks.setText("");
        tfSmt.setText("");
    }                                        
</pre>

### 4. ShowTable
<pre>
public void showTable() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PertemuanDuaBelasPU");
        EntityManager em = emf.createEntityManager();

        try {
            // Buat model tabel dengan kolom yang sesuai
            DefaultTableModel tbnmhs = new DefaultTableModel(new String[]{"KODE MK", "SKS", "NAMA MK", "SEMESTER AJAR"}, 0);

            // Menggunakan NamedQuery "Matakuliah.findAll" untuk mengambil semua data
            List<Matakuliah> mataKuliahList = em.createNamedQuery("Matakuliah.findAll", Matakuliah.class).getResultList();

            // Iterasi hasil query dan tambahkan baris ke model tabel
            for (Matakuliah matakuliah : mataKuliahList) {
                tbnmhs.addRow(new Object[]{
                    matakuliah.getKodeMk(),
                    matakuliah.getSks(),
                    matakuliah.getNamaMk(),
                    matakuliah.getSemesterAjar()
                });
            }

            // Atur model tabel ke tabel GUI
            tabel.setModel(tbnmhs);
        } catch (Exception ex) {
            Logger.getLogger(Mata_kuliah.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //     em.close();   Tutup EntityManager setelah selesai
        }
    }
<pre/>
