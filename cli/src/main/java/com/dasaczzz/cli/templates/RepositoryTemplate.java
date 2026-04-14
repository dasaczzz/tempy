package com.dasaczzz.cli.templates;

/**
 * Generates the source code for a Spring Data JPA repository interface.
 * The generated interface extends {@code JpaRepository} with a {@code String} ID type.
 */
public final class RepositoryTemplate {

  private RepositoryTemplate() {
  }

  public static String generate(String entityName, String basePackage) {
    return """
        package %s.Repository;
        
        import %s.Model.%sModel;
        import org.springframework.data.jpa.repository.JpaRepository;
        
        public interface %sRepository extends JpaRepository<%sModel, String> { }
        """.formatted(basePackage, basePackage, entityName, entityName, entityName);
  }

}
