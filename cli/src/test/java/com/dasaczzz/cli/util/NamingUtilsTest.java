package com.dasaczzz.cli.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NamingUtilsTest {

    @Test
    void ensurePascalCase_uppercasesFirstLetter() {
        assertEquals("User",     NamingUtils.ensurePascalCase("user"));
        assertEquals("BlogPost", NamingUtils.ensurePascalCase("BlogPost"));
        assertEquals("P",        NamingUtils.ensurePascalCase("p"));
    }

    @Test
    void ensurePascalCase_nullAndEmpty() {
        assertNull(NamingUtils.ensurePascalCase(null));
        assertEquals("", NamingUtils.ensurePascalCase(""));
    }

    @Test
    void lowerFirst_lowercasesFirstLetter() {
        assertEquals("user",     NamingUtils.lowerFirst("User"));
        assertEquals("blogPost", NamingUtils.lowerFirst("BlogPost"));
        assertEquals("u",        NamingUtils.lowerFirst("U"));
    }

    @Test
    void lowerFirst_nullAndEmpty() {
        assertNull(NamingUtils.lowerFirst(null));
        assertEquals("", NamingUtils.lowerFirst(""));
    }

    @Test
    void toSnakePlural_simpleWord() {
        assertEquals("users", NamingUtils.toSnakePlural("User"));
    }

    @Test
    void toSnakePlural_compoundWord() {
        assertEquals("blog_posts", NamingUtils.toSnakePlural("BlogPost"));
    }

    @Test
    void toKebabPlural_simpleWord() {
        assertEquals("users", NamingUtils.toKebabPlural("User"));
    }

    @Test
    void toKebabPlural_compoundWord() {
        assertEquals("blog-posts", NamingUtils.toKebabPlural("BlogPost"));
    }
}
