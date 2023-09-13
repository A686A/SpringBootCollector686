
package com.example.springbootdemo.entity;

import lombok.Data;

@Data
public class Book {
    String id;
    String name;
    String master;
    Author author;
}
