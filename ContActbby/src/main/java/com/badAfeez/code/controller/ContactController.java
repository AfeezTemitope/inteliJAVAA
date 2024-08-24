package com.badAfeez.code.controller;

import com.badAfeez.code.DtoBBY.request.CreateContactRequest;
import com.badAfeez.code.DtoBBY.request.DeleteContactRequest;
import com.badAfeez.code.DtoBBY.request.UpdateContactRequest;
import com.badAfeez.code.DtoBBY.response.DeleteContactResponse;
import com.badAfeez.code.DtoBBY.response.UpdateContactResponse;
import com.badAfeez.code.data.models.Contacts;
import com.badAfeez.code.Exception.ContactNotFound;
import com.badAfeez.code.Exception.InvalidPhoneNumber;
import com.badAfeez.code.Exception.UserNotFound;
import com.badAfeez.code.service.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactServiceImpl contactService;

    @GetMapping
    public ResponseEntity<List<Contacts>> getAllContacts(@RequestParam String phoneNumber) {
        try {
            List<Contacts> contacts = contactService.getAllContacts(phoneNumber);
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        } catch (UserNotFound  e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Contacts> createContact(@RequestBody CreateContactRequest request) {
        try {
            Contacts contact = contactService.addContact(request);
            return new ResponseEntity<>(contact, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateContactResponse> updateContact(@RequestBody UpdateContactRequest request) {
        try {
            UpdateContactResponse response = contactService.updateContact(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ContactNotFound  e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeleteContactResponse> deleteContact(@RequestBody DeleteContactRequest request) {
        try {
            DeleteContactResponse response = contactService.deleteContact(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ContactNotFound  e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
