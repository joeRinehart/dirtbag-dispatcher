package org.github.joerinehart

import groovy.transform.CompileStatic

@CompileStatic
String hello(String name) {
    "Hello, ${name}!"
}

