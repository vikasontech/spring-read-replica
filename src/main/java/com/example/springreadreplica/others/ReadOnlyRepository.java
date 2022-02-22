package com.example.springreadreplica.others;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface ReadOnlyRepository {
}
