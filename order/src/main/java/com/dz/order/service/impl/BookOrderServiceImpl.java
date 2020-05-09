package com.dz.order.service.impl;

import com.dz.order.dto.ResponseDTO;
import com.dz.order.exception.BadRequestException;
import com.dz.order.model.BookOrder;
import com.dz.order.repository.BookOrderDao;
import com.dz.order.service.BookOrderService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Service
@Transactional
public class BookOrderServiceImpl implements BookOrderService {

    @Autowired
    private BookOrderDao bookOrderDao;

    @Value("${library.service.url}")
    private String libraryUrl;

    @Value("${student.service.url}")
    private String studentUrl;

    private static final String BOOK_QUANTITY_API = "bookQuantity?bookId=";

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Override
    public BookOrder placeBookOrder(BookOrder bookOrder) {
        bookOrder.setOrderDate(new Date(System.currentTimeMillis()).toString());
        bookOrder.setDueDate(new Date(System.currentTimeMillis() + (10000 * 60 * 60 * 24)).toString());
        return bookOrderDao.placeOrder(bookOrder);
    }

    public void updateBookQuantity(BookOrder bookOrder) {
        try {
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
            String url = libraryUrl + "bookQuantity?bookId=" + bookOrder.getBookId();
            ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Integer.class);
            if (response.hasBody() && Objects.nonNull(response.getBody())) {
                Integer availableBookQuantity = response.getBody();
                Integer requiredBook = bookOrder.getBookQuantity();
                this.updateBook(bookOrder.getBookId(), availableBookQuantity - requiredBook);
                bookOrderDao.placeOrder(bookOrder);
            }
        } catch (Exception ex) {
            throw new BadRequestException("Problem occurred with " + ex.getMessage());
        }
    }

    @Override
    public boolean checkStudent(int userId) {
        try {
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
            String url = studentUrl + "getById?userId=" + userId;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
            if (response.hasBody())
                return true;
            else
                throw new BadRequestException("Student does not exists with userId " + userId);
        } catch (Exception ex) {
            throw new BadRequestException("Problem occurred with " + ex.getMessage());
        }
    }

    @Override
    public boolean isBookAvailable(String bookId) {
        try {
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
            String url = libraryUrl + "bookQuantity?bookId=" + bookId;
            ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Integer.class);
            if (response.hasBody()) {
                if (Objects.requireNonNull(response.getBody()) > 0)
                    return true;
            } else
                throw new BadRequestException("Book does not exists with bookId " + bookId);
        } catch (Exception ex) {
            throw new BadRequestException("Problem occurred with " + ex.getMessage());
        }
        return false;
    }

    void updateBook(String bookId, Integer updatedQuantity) {
        try {
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
            String url = libraryUrl + "getBookById?bookId=" + bookId;
            ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, List.class);
            if (Objects.nonNull(response.getBody().get(0))) {
                JSONObject book = (JSONObject) response.getBody().get(0);
                String bookID= book.get("bookId").toString();
                this.updateBookQuantity(bookID, updatedQuantity);
            }
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public void updateBookQuantity(String bookID, Integer updatedQuantity) {
        try {
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
            String url = libraryUrl + "updateById/" + bookID + "/" + updatedQuantity;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, String.class);
            if (Objects.nonNull(response.getBody())) {
                response.getBody();
            }
        } catch (Exception e) {
            throw new BadRequestException("Exception occurred with updateBook");
        }
    }
}
