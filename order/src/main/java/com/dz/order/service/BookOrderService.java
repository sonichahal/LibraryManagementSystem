package com.dz.order.service;

import com.dz.order.model.BookOrder;

public interface BookOrderService {

    BookOrder placeBookOrder(BookOrder bookOrder);

    boolean checkStudent(int userId);

    boolean isBookAvailable(String bookId);

    void updateBookQuantity(BookOrder bookOrder);
}
