package com.dasaczzz.cli.application;

import org.springframework.stereotype.Service;
import com.dasaczzz.cli.scaffolding.EntityScaffolder;

/**
 * Application-layer use case for the <em>generate entity</em> command.
 * <p>
 * Thin orchestration class that receives the raw user inputs, applies defaults,
 * and delegates to {@link EntityScaffolder}.
 */
@Service
public class GenerateEntityUseCase {

  public static final String DEFAULT_BASE_PACKAGE = "com.dasaczzz.tempy";
  public static final String DEFAULT_BACKEND_PATH = "../backend";
  private final EntityScaffolder scaffolder;

  public GenerateEntityUseCase(EntityScaffolder scaffolder) {
    this.scaffolder = scaffolder;
  }

  /**
   * Executes the entity-generation use case.
   *
   * @param entityName  PascalCase entity name provided by the user
   * @param basePackage base package override (may be {@code null} to use the default)
   * @param backendPath backend path override (may be {@code null} to use the default)
   * @return a human-readable result message
   */
  public String execute(String entityName, String basePackage, String backendPath) {
    String pkg = ( basePackage != null && !basePackage.isBlank() ) ? basePackage : DEFAULT_BASE_PACKAGE;
    String path = ( backendPath != null && !backendPath.isBlank() ) ? backendPath : DEFAULT_BACKEND_PATH;

    String error = scaffolder.scaffold(entityName, pkg, path);
    if (error != null) {
      return error;
    }

    return "Successfully generated entity '%s' under package '%s' in '%s'.".formatted(entityName, pkg, path);
  }

}
