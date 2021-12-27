package ru.habrahabr.service;

import ru.habrahabr.entity.Contact;

import java.util.List;

public interface ContactService {

    Contact save(Contact contact);

    List<Contact> findAll();

    void delete(long id);

    Contact update(Contact contact);

    Contact getContactByPhone(String phone);

    Contact getContactByName(String name);

    Contact getContactById(Long id);

}
