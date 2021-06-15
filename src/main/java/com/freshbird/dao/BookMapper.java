package com.freshbird.dao;

import com.freshbird.pojo.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {

    // 增加一本书
    int addBook(Books books);

    // 删除一本书
    int deleteBookById(@Param("bookID") int id);

    // 更新一本书
    int updateBook(Books books);

    // 根据 id 查询书
    Books queryBookById(@Param("bookID") int id);

    // 查询所有书
    List<Books> queryAllBook();

    // 根据书名查询书
    List<Books> queryBookByName(@Param("bookName") String bookName);

}
