package ru.habrahabr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.habrahabr.entity.Contact;

import java.util.List;

@Repository
//@Transactional(propagation = Propagation.MANDATORY)
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findContactByPhone(String phone);

    Contact findContactByName(String name);

    Contact findContactById(Long id);

}
