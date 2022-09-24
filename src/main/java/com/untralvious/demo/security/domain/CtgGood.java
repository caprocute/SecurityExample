package com.untralvious.demo.security.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ctg_good", schema = "securityexample", catalog = "")
public class CtgGood {
    private long id;
    private String goodName;
    private String goodType;
    private String departId;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "good_name", nullable = true, length = 100)
    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    @Basic
    @Column(name = "good_type", nullable = true, length = 100)
    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    @Basic
    @Column(name = "depart_id", nullable = true, length = 100)
    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CtgGood ctgGood = (CtgGood) o;

        if (id != ctgGood.id) return false;
        if (goodName != null ? !goodName.equals(ctgGood.goodName) : ctgGood.goodName != null) return false;
        if (goodType != null ? !goodType.equals(ctgGood.goodType) : ctgGood.goodType != null) return false;
        if (departId != null ? !departId.equals(ctgGood.departId) : ctgGood.departId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (goodName != null ? goodName.hashCode() : 0);
        result = 31 * result + (goodType != null ? goodType.hashCode() : 0);
        result = 31 * result + (departId != null ? departId.hashCode() : 0);
        return result;
    }
}
