package com.unnathy.fieldwise;

import com.unnathy.fieldwise.entity.BaseEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FieldWiseApplication extends BaseEntity {

    public static void main(String[] args) {
        SpringApplication.run(FieldWiseApplication.class, args);
    }

}
