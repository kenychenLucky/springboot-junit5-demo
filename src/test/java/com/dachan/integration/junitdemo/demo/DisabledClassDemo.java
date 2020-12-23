package com.dachan.integration.junitdemo.demo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//@Disabled
class DisabledClassDemo {
    @Test
    void testWillBeSkipped() {
    }
    @Disabled
    @Test
    void testWillBeSkipped2() {
    }
}
