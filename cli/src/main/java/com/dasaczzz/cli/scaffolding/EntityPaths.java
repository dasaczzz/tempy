package com.dasaczzz.cli.scaffolding;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Computes the target filesystem paths for each file that the scaffolding will generate.
 * <p>
 * Given a {@code backendPath}, {@code basePackage}, and {@code entityName}, this class
 * resolves the four destination files under
 * {@code <backendPath>/src/main/java/<package path>/}.
 */
public class EntityPaths {

  private final Path baseSourceDir;
  private final String entityName;

  /**
   * @param backendPath path to the backend module root (e.g. {@code "../backend"})
   * @param basePackage base Java package (e.g. {@code "com.dasaczzz.tempy"})
   * @param entityName  PascalCase entity name (e.g. {@code "BlogPost"})
   */
  public EntityPaths(String backendPath, String basePackage, String entityName) {
    this.entityName = entityName;
    String packageDir = basePackage.replace('.', '/');
    this.baseSourceDir = Paths.get(backendPath, "src", "main", "java", packageDir);
  }

  public Path modelPath() {
    return baseSourceDir.resolve("Model").resolve(entityName + "Model.java");
  }

  public Path repositoryPath() {
    return baseSourceDir.resolve("Repository").resolve(entityName + "Repository.java");
  }

  public Path servicePath() {
    return baseSourceDir.resolve("Service").resolve(entityName + "ServiceImp.java");
  }

  public Path controllerPath() {
    return baseSourceDir.resolve("Controller").resolve(entityName + "Controller.java");
  }

}
