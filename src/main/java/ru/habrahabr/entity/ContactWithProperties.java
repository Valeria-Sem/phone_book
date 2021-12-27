package ru.habrahabr.entity;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Класс, аналогичный классу {@link Contact} за тем исключением,
 * что поля из себя представляют JavaFX Property. Это может пригодиться.
 *
 * JPA с ним работает аналогично классу {@link Contact}.
 */
//@Entity
//@Table
//@Access(AccessType.PROPERTY)
public class ContactWithProperties implements Serializable {

    private LongProperty id = new SimpleLongProperty();

    @Column
    private StringProperty name = new SimpleStringProperty();

    @Column(unique = true)
    private StringProperty phone = new SimpleStringProperty();

    public ContactWithProperties() {
    }

    public ContactWithProperties(Long id, String name, String phone) {
        setId(id);
        setName(name);
        setPhone(phone);
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactWithProperties that = (ContactWithProperties) o;
        return id.equals(that.id) && name.equals(that.name) && phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone);
    }

    @Override
    public String toString() {
        return "ContactWithProperties{" +
                "id=" + id +
                ", name=" + name +
                ", phone=" + phone +
                '}';
    }
}
