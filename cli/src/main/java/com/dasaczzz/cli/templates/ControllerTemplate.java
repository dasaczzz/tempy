package com.dasaczzz.cli.templates;

import com.dasaczzz.cli.util.NamingUtils;

/**
 * Generates the source code for a basic CRUD REST controller.
 * The generated class implements {@code BaseController} and delegates to
 * the entity's service.
 */
public final class ControllerTemplate {

  private ControllerTemplate() {
  }

  public static String generate(String entityName, String basePackage) {
    String varName = NamingUtils.lowerFirst(entityName);
    String urlPath = NamingUtils.toKebabPlural(entityName);
    String serviceVar = varName + "Service";

    return """
        package %s.Controller;
        
        import %s.Lib.BaseResponse;
        import %s.Model.%sModel;
        import %s.Service.%sService;
        import jakarta.validation.Valid;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        
        import java.util.List;
        
        @RestController
        @RequestMapping("/api/%s")
        public class %sController implements BaseController<%sModel, %sModel> {
        
            @Autowired %sService %s;
        
            @Override
            @PostMapping("/")
            public ResponseEntity<BaseResponse<%sModel>> createRecord(
                    @Valid @RequestBody %sModel %s) {
                return new ResponseEntity<>(%s.createRecord(%s), HttpStatus.CREATED);
            }
        
            @Override
            @GetMapping("/")
            public ResponseEntity<BaseResponse<List<%sModel>>> getRecords() {
                return new ResponseEntity<>(%s.getRecords(), HttpStatus.OK);
            }
        
            @Override
            @GetMapping("/{id}")
            public ResponseEntity<BaseResponse<%sModel>> getRecordById(@PathVariable String id) {
                return new ResponseEntity<>(%s.getRecordById(id), HttpStatus.OK);
            }
        
            @Override
            public ResponseEntity<BaseResponse<%sModel>> deleteRecord(String id) {
                return null;
            }
        }
        """.formatted(basePackage, basePackage, basePackage, entityName, basePackage, entityName, urlPath, entityName, entityName, entityName, entityName, serviceVar, entityName, entityName, varName, serviceVar, varName, entityName, serviceVar, entityName, serviceVar, entityName);
  }

}
