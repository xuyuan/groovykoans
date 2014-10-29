/*
 * Copyright (c) 2012-2014 nadavc <https://twitter.com/nadavc>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the WTFPL, Version 2, as published by Sam Hocevar.
 * See the COPYING file for more details.
 */

package org.groovykoans.koan06
import static groovy.io.FileType.FILES

/**
 * Koan06 - More closures
 *
 * Resource list:
 *   * http://groovy.codehaus.org/groovy-jdk/java/lang/Object.html#with(groovy.lang.Closure)
 *   * http://groovy.codehaus.org/groovy-jdk/java/lang/Object.html#collect(groovy.lang.Closure)
 *   * http://groovy.codehaus.org/groovy-jdk/java/util/Collection.html
 *   * http://groovy.codehaus.org/groovy-jdk/java/io/File.html#eachFileRecurse(groovy.lang.Closure)
 *   * http://groovy.codehaus.org/groovy-jdk/java/lang/Object.html
 */
class Koan06 extends GroovyTestCase {

    void test01_WithMethod() {
        // The 'with()' method [ http://groovy.codehaus.org/groovy-jdk/java/lang/Object.html#with(groovy.lang.Closure) ]
        // allows you to access an object within a closure without explicitly referring to it.

        // This is how Java StringBuilders are used:
        StringBuilder javaStringBuilder = new StringBuilder();
        javaStringBuilder.append("roses are #FF0000\\n");
        javaStringBuilder.append("violets are #0000FF\\n");
        javaStringBuilder.append("all my base\\n")
        javaStringBuilder.append("are belong to you\\n")
        String javaResult = javaStringBuilder.toString();

        // Groovy-fy the above code, using StringBuilder and with() to get the same result in Groovy
        String groovyResult
        // ------------ START EDITING HERE ----------------------
        def groovyStringBuilder = new StringBuilder();
        groovyStringBuilder.with {
            append("roses are #FF0000\\n")
            append("violets are #0000FF\\n");
            append("all my base\\n")
            append("are belong to you\\n")
        }
        groovyResult = groovyStringBuilder.toString()

        // ------------ STOP EDITING HERE  ----------------------
        assert groovyResult == javaResult
    }

    void test02_CollectMethodOnLists() {
        // We're often required to iterate through a whole list and and perform some sort of transformation on
        // some (or all) of the items, returning a new list. Groovy has a method just for that:
        // http://groovy.codehaus.org/groovy-jdk/java/lang/Object.html#collect(groovy.lang.Closure)

        // Using collect() and a method from http://groovy.codehaus.org/groovy-jdk/java/util/Collection.html, create
        // a list of UNIQUE class types from the following list:
        def differentTypes = [1, 'String', "GString", 'a', 'Another string', 0]
        def uniqueTypes = []
        // ------------ START EDITING HERE ----------------------
        uniqueTypes = differentTypes.collect{it.class}.unique()

        // ------------ STOP EDITING HERE  ----------------------
        assert uniqueTypes == [Integer, String]
    }

    void test03_FileIteration() {
        // Groovy's File enhancements includes an iterator that walks through all files
        // http://groovy.codehaus.org/groovy-jdk/java/io/File.html#eachFileRecurse(groovy.lang.Closure)

        // Use the eachFileRecurse iterator to find the number of files that contain the string 'Lorem'
        // under the src directory
        int count = 0
        // ------------ START EDITING HERE ----------------------
        new File('src').eachFileRecurse(FILES) {
                if (it.text.contains('Lorem')) {
                    count ++
            }
        }

        // ------------ STOP EDITING HERE  ----------------------
        assert count == 3

    }

    void test04_ConcludingExercise() {
        // Using methods from Groovy object (http://groovy.codehaus.org/groovy-jdk/java/lang/Object.html) and
        // range objects, store all the prime numbers between 200 and 250 in the target variable
        def primesBetween200And250 = []
        // ------------ START EDITING HERE ----------------------
        (200..250).each { n ->
            def prime = true
            (2..(n-1)).each {
                if (n % it == 0) {
                    prime = false
                }
            }
            if (prime) {
                primesBetween200And250 += n
            }
        }

        // ------------ STOP EDITING HERE  ----------------------
        assert primesBetween200And250 == [211, 223, 227, 229, 233, 239, 241]

    }

}
