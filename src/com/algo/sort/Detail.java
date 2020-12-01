package com.algo.sort;

import javax.annotation.Resource;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author chowsanity
 */
@Target({METHOD})
@Retention(RUNTIME)
public @interface Detail {
    boolean showArr() default false;
}
