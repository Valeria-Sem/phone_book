package ru.habrahabr.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.util.StringUtils;
import org.apache.commons.lang3.StringUtils;
import ru.habrahabr.entity.Contact;
import ru.habrahabr.service.ContactService;

import javax.annotation.PostConstruct;
import java.util.List;

//@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
public class MainController {

    // Инъекции Spring
    @Autowired private ContactService contactService;

    // Инъекции JavaFX
    @FXML private TableView<Contact> table;
    @FXML private TextField txtName;
    @FXML private TextField txtPhone;
    @FXML private TextField txtId;

    // Variables
    private ObservableList<Contact> data;

    /**
     * Инициализация контроллера от JavaFX.
     * Метод вызывается после того как FXML загрузчик произвел инъекции полей.
     *
     * Обратите внимание, что имя метода <b>обязательно</b> должно быть "initialize",
     * в противном случае, метод не вызовется.
     *
     * Также на этом этапе еще отсутствуют бины спринга
     * и для инициализации лучше использовать метод,
     * описанный аннотацией @PostConstruct,
     * который вызовется спрингом, после того, как им будут произведены все инъекции.
     * {@link MainController#init()}
     */
    @FXML
    public void initialize() {
        // Этап инициализации JavaFX
    }

    /**
     * На этом этапе уже произведены все возможные инъекции.
     */
  //  @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        try {
            List<Contact> contacts = contactService.findAll();
            data = FXCollections.observableArrayList(contacts);

            // Столбцы таблицы
            TableColumn<Contact, String> idColumn = new TableColumn<>("ID");
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<Contact, String> nameColumn = new TableColumn<>("Имя");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

            TableColumn<Contact, String> phoneColumn = new TableColumn<>("Телефон");
            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

            table.getColumns().setAll(idColumn, nameColumn, phoneColumn);

            // Данные таблицы
            table.setItems(data);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Метод, вызываемый при нажатии на кнопку "Добавить".
     * Привязан к кнопке в FXML файле представления.
     */
    @FXML
    public void addContact() {
        String name = txtName.getText();
        String phone = txtPhone.getText();
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(phone)) {
            return;
        }

        Contact contact = new Contact(name, phone);
        contactService.save(contact);
        data.add(contact);

        // чистим поля
        txtName.setText("");
        txtPhone.setText("");
    }

    @FXML
    public void deleteContact() {
        Long id = Long.parseLong(txtId.getText());
        if (id == null) {
            return;
        }

        Contact contact = contactService.getContactById(id);
        contactService.delete(id);
        data.remove(contact);

        // чистим поля
        txtId.setText("");
    }
}
