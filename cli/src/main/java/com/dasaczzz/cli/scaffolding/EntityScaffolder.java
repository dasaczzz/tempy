package com.dasaczzz.cli.scaffolding;

import com.dasaczzz.cli.io.FileWriterService;
import com.dasaczzz.cli.templates.ControllerTemplate;
import com.dasaczzz.cli.templates.ModelTemplate;
import com.dasaczzz.cli.templates.RepositoryTemplate;
import com.dasaczzz.cli.templates.ServiceTemplate;
import com.dasaczzz.cli.util.NamingUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Orchestrates the scaffolding of a new entity.
 * <p>
 * For each entity it:
 * <ol>
 *   <li>Computes destination paths via {@link EntityPaths}</li>
 *   <li>Renders each template string</li>
 *   <li>Delegates file writing to {@link FileWriterService}</li>
 * </ol>
 * If any target file already exists the method stops and returns an error message
 * without writing any files.
 */
@Component
public class EntityScaffolder {

    private final FileWriterService fileWriterService;

    public EntityScaffolder(FileWriterService fileWriterService) {
        this.fileWriterService = fileWriterService;
    }

    /**
     * Scaffolds the four entity files.
     *
     * @param entityName  PascalCase entity name (will be sanitised via {@link NamingUtils#ensurePascalCase})
     * @param basePackage base Java package for the backend (e.g. {@code "com.dasaczzz.tempy"})
     * @param backendPath filesystem path to the backend module root (e.g. {@code "../backend"})
     * @return {@code null} on success, or an error message string on failure
     */
    public String scaffold(String entityName, String basePackage, String backendPath) {
        entityName = NamingUtils.ensurePascalCase(entityName);

        EntityPaths paths = new EntityPaths(backendPath, basePackage, entityName);

        Map<Path, String> files = buildFileMap(entityName, basePackage, paths);

        // Pre-check: verify no file already exists before writing anything
        for (Path target : files.keySet()) {
            if (target.toFile().exists()) {
                return "Error: file already exists: " + target.toAbsolutePath();
            }
        }

        // Write all files
        for (Map.Entry<Path, String> entry : files.entrySet()) {
            try {
                fileWriterService.writeIfNotExists(entry.getKey(), entry.getValue());
            } catch (IOException e) {
                return "Error writing " + entry.getKey().toAbsolutePath() + ": " + e.getMessage();
            }
        }

        return null;
    }

    private Map<Path, String> buildFileMap(String entityName, String basePackage, EntityPaths paths) {
        Map<Path, String> files = new LinkedHashMap<>();
        files.put(paths.modelPath(),      ModelTemplate.generate(entityName, basePackage));
        files.put(paths.repositoryPath(), RepositoryTemplate.generate(entityName, basePackage));
        files.put(paths.servicePath(),    ServiceTemplate.generate(entityName, basePackage));
        files.put(paths.controllerPath(), ControllerTemplate.generate(entityName, basePackage));
        return files;
    }
}
