package com.dachan.integration.junitdemo.demo;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("fast")
@Tag("model")
class TaggingDemo {

    @Test
    @Tag("taxes")
    void testingTaxCalculation() {
    }

    @Test
    @Tag("fast")
    void testingFastTaxCalculation() {
    }

}