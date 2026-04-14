package com.dasaczzz.cli.io;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Handles filesystem writes for the scaffolding process.
 * <p>
 * All writes are <em>write-if-not-exists</em>: attempting to write a file that
 * already exists raises an {@link IOException} rather than silently overwriting it.
 */
@Service
public class FileWriterService {

  /**
   * Writes {@code content} to {@code target}, creating parent directories as needed.
   *
   * @param target  the destination file path
   * @param content the text content to write
   * @throws IOException if the file already exists, or any I/O error occurs
   */
  public void writeIfNotExists(Path target, String content) throws IOException {
    if (Files.exists(target)) {
      throw new IOException("File already exists: " + target.toAbsolutePath());
    }
    Files.createDirectories(target.getParent());
    Files.writeString(target, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
  }

}
