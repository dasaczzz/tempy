package com.dasaczzz.cli.templates;

import com.dasaczzz.cli.util.NamingUtils;

/**
 * Generates the source code for a JPA entity model class.
 * The generated class extends {@code BaseModel}, uses Lombok annotations,
 * and maps to a database table named after the entity (snake_plural).
 */
public final class ModelTemplate {

    private ModelTemplate() {}

    public static String generate(String entityName, String basePackage) {
        String tableName = NamingUtils.toSnakePlural(entityName);
        String idField   = "id" + entityName;

        return """
                package %s.Model;

                import jakarta.persistence.*;
                import lombok.AllArgsConstructor;
                import lombok.Data;
                import lombok.EqualsAndHashCode;
                import lombok.NoArgsConstructor;

                @EqualsAndHashCode(callSuper = false)
                @Entity
                @Table(name = "%s")
                @Data
                @AllArgsConstructor
                @NoArgsConstructor
                public class %sModel extends BaseModel {

                    @Id
                    @GeneratedValue(strategy = GenerationType.UUID)
                    private String %s;
                }
                """.formatted(basePackage, tableName, entityName, idField);
    }
}
