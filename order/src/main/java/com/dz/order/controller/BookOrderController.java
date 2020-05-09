package com.dz.order.controller;

import com.dz.order.exception.BadRequestException;
import com.dz.order.model.BookOrder;
import com.dz.order.service.BookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class BookOrderController {

    @Autowired
    private BookOrderService bookOrderService;

    @GetMapping(value = "/status")
    public String getStatus() {
        return "Order Service is up and running";
    }

    @PostMapping(value = "/placeBookOrder")
    public ResponseEntity<BookOrder> placeBookOrder(@RequestBody BookOrder bookOrder) {
        if (this.validateInputs(bookOrder)) {
             bookOrderService.placeBookOrder(bookOrder);
             this.updateBookQuantity(bookOrder);
             return new ResponseEntity<>(HttpStatus.OK);
        } else
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            throw new BadRequestException("Book can not be issued " + bookOrder.getBookName());
    }

    private void updateBookQuantity(BookOrder bookOrder) {
        bookOrderService.updateBookQuantity(bookOrder);
    }

    private boolean validateInputs(BookOrder bookOrder) {
        if (bookOrderService.checkStudent(bookOrder.getUserId())) {
            bookOrderService.isBookAvailable(bookOrder.getBookId());
            return true;
        }
        return false;
    }

}
