/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Laura
 */
@Entity
@Table(name = "matakuliah")
@NamedQueries({
    @NamedQuery(name = "Matakuliah.findAll", query = "SELECT m FROM Matakuliah m"),
    @NamedQuery(name = "Matakuliah.findByKodeMk", query = "SELECT m FROM Matakuliah m WHERE m.kodeMk = :kodeMk"),
    @NamedQuery(name = "Matakuliah.findBySks", query = "SELECT m FROM Matakuliah m WHERE m.sks = :sks"),
    @NamedQuery(name = "Matakuliah.findByNamaMk", query = "SELECT m FROM Matakuliah m WHERE m.namaMk = :namaMk"),
    @NamedQuery(name = "Matakuliah.findBySemesterAjar", query = "SELECT m FROM Matakuliah m WHERE m.semesterAjar = :semesterAjar")})
public class Matakuliah implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "kode_mk")
    private String kodeMk;
    @Column(name = "sks")
    private String sks;
    @Column(name = "nama_mk")
    private String namaMk;
    @Column(name = "semester_ajar")
    private String semesterAjar;

    public Matakuliah() {
    }

    public Matakuliah(String kodeMk) {
        this.kodeMk = kodeMk;
    }

    public String getKodeMk() {
        return kodeMk;
    }

    public void setKodeMk(String kodeMk) {
        this.kodeMk = kodeMk;
    }

    public String getSks() {
        return sks;
    }

    public void setSks(String sks) {
        this.sks = sks;
    }

    public String getNamaMk() {
        return namaMk;
    }

    public void setNamaMk(String namaMk) {
        this.namaMk = namaMk;
    }

    public String getSemesterAjar() {
        return semesterAjar;
    }

    public void setSemesterAjar(String semesterAjar) {
        this.semesterAjar = semesterAjar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodeMk != null ? kodeMk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matakuliah)) {
            return false;
        }
        Matakuliah other = (Matakuliah) object;
        if ((this.kodeMk == null && other.kodeMk != null) || (this.kodeMk != null && !this.kodeMk.equals(other.kodeMk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uts.Matakuliah[ kodeMk=" + kodeMk + " ]";
    }
    
}
