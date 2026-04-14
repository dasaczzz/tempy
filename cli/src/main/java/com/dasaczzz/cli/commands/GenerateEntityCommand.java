package com.dasaczzz.cli.commands;

import com.dasaczzz.cli.application.GenerateEntityUseCase;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * Spring Shell command that exposes the {@code gen entity} command.
 * <p>
 * This class is intentionally thin: all business logic is delegated to
 * {@link GenerateEntityUseCase}.
 */
@ShellComponent
public class GenerateEntityCommand {

  private final GenerateEntityUseCase useCase;

  public GenerateEntityCommand(GenerateEntityUseCase useCase) {
    this.useCase = useCase;
  }

  /**
   * Generates scaffolding files for a new entity inside the backend module.
   *
   * @param entityName  PascalCase name of the entity (e.g. {@code "BlogPost"})
   * @param basePackage base Java package of the backend (default: {@code com.dasaczzz.tempy})
   * @param backendPath relative or absolute path to the backend module root
   *                    (default: {@code ../backend})
   */
  @ShellMethod(key = "gen entity", value = "Scaffold Model/Repository/Service/Controller for a new entity")
  public String genEntity(@ShellOption(help = "Entity name in PascalCase (e.g. BlogPost)") String entityName, @ShellOption(defaultValue = GenerateEntityUseCase.DEFAULT_BASE_PACKAGE, help = "Base package of the backend (e.g. com.dasaczzz.tempy)") String basePackage, @ShellOption(defaultValue = GenerateEntityUseCase.DEFAULT_BACKEND_PATH, help = "Path to the backend module root (e.g. ../backend)") String backendPath) {

    return useCase.execute(entityName, basePackage, backendPath);
  }

}
