package com.dz.order.service;

import com.dz.order.model.BookOrder;

public interface BookOrderService {

    BookOrder placeBookOrder(BookOrder bookOrder);

    boolean checkStudentRegistered(int userId);

    boolean isBookAvailable(String bookId);

    void updateBookQuantityInDB(BookOrder bookOrder);
}
