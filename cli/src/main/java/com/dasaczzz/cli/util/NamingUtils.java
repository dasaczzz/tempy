package com.dasaczzz.cli.util;

/**
 * Utilities for converting entity names to the various case forms
 * used by the scaffolded code (PascalCase, camelCase, snake_plural, kebab-plural).
 */
public final class NamingUtils {

  private NamingUtils() {
  }

  /**
   * Ensures the first character of {@code name} is uppercase (PascalCase start).
   * E.g. {@code "user"} → {@code "User"}, {@code "BlogPost"} → {@code "BlogPost"}.
   */
  public static String ensurePascalCase(String name) {
    if (name == null || name.isEmpty()) {
      return name;
    }
    return Character.toUpperCase(name.charAt(0)) + name.substring(1);
  }

  /**
   * Lowercases only the first character of {@code name}.
   * E.g. {@code "User"} → {@code "user"}, {@code "BlogPost"} → {@code "blogPost"}.
   */
  public static String lowerFirst(String name) {
    if (name == null || name.isEmpty()) {
      return name;
    }
    return Character.toLowerCase(name.charAt(0)) + name.substring(1);
  }

  /**
   * Converts a PascalCase name to snake_case and appends an "s" for a naive plural.
   * E.g. {@code "BlogPost"} → {@code "blog_posts"}, {@code "User"} → {@code "users"}.
   */
  public static String toSnakePlural(String name) {
    if (name == null || name.isEmpty()) {
      return name;
    }
    String snake = name.replaceAll("([A-Z])", "_$1").toLowerCase().replaceFirst("^_", "");
    return snake + "s";
  }

  /**
   * Converts a PascalCase name to kebab-case and appends an "s" for a naive plural.
   * E.g. {@code "BlogPost"} → {@code "blog-posts"}, {@code "User"} → {@code "users"}.
   */
  public static String toKebabPlural(String name) {
    return toSnakePlural(name).replace('_', '-');
  }

}
