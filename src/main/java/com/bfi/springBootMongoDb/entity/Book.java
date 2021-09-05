package com.bfi.springBootMongoDb.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "Book")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private String id;
    private String bookName;
    private String authorName;
}
