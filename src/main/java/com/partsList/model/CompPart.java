package com.partsList.model;

import javax.persistence.*;

@Entity(name = "part")
public class CompPart {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ISMUSTHAVE", columnDefinition = "BOOLEAN")
    private boolean isMustHave;

    @Column(name = "QUANTITY")
    private int quantity;

    public CompPart() {
    }

    public CompPart(String name, int isMustHave, int quantity) {
        this.name = name;
        this.isMustHave = isMustHave == 1;
        this.quantity = quantity;
    }

    public CompPart(String name, boolean isMustHave, int quantity) {
        this.name = name;
        this.isMustHave = isMustHave;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMustHave() {
        return isMustHave;
    }

    public void setMustHave(boolean mustHave) {
        isMustHave = mustHave;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CompPartDaoImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isMustHave=" + isMustHave +
                ", quantity=" + quantity +
                '}';
    }

}
