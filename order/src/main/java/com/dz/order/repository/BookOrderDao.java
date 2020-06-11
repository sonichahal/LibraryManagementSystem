package com.dz.order.repository;

import com.dz.order.model.BookOrder;

public interface BookOrderDao {

    BookOrder saveBookOrder(BookOrder bookOrder);

    void updateBookQuantity(String bookID, Integer updatedQuantity);
}
