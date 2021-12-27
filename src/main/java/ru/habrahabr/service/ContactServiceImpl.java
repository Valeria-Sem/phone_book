package ru.habrahabr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.habrahabr.entity.Contact;
import ru.habrahabr.repository.ContactRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Service
//@Transactional
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;

    @Autowired
    public ContactServiceImpl(ContactRepository repository) {
        this.repository = repository;
    }

//    /**
//     * Метод добавляет парочку записей в БД после запуска приложения,
//     * чтобы не было совсем пусто.
//     *
//     * Из-за того, что подключена H2 (in-memory) БД.
//     */
//    @PostConstruct
//    public void generateTestData() {
//        save(new Contact("Иван Иванов", "+123456789", "ivan@ivan.ov"));
//        save(new Contact("Петр Петров", "+987654321", "petr@pe.tr"));
//    }

    @Override
    public Contact save(Contact contact) {
        return repository.save(contact);
    }

    @Override
    public List<Contact> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public Contact update(Contact contact) {
        return save(contact);
    }

    @Override
    public Contact getContactByPhone(String phone) {
        return repository.findContactByPhone(phone);
    }

    @Override
    public Contact getContactByName(String name) {
        return repository.findContactByName(name);
    }

    @Override
    public Contact getContactById(Long id) {
        return repository.findContactById(id);
    }
}
