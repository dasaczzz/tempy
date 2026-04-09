package com.dasaczzz.cli.templates;

import com.dasaczzz.cli.util.NamingUtils;

/**
 * Generates the source code for a basic CRUD service implementation.
 * The generated class implements a {@code BaseService}-based interface and
 * delegates to the entity's JPA repository.
 */
public final class ServiceTemplate {

    private ServiceTemplate() {}

    public static String generate(String entityName, String basePackage) {
        String varName     = NamingUtils.lowerFirst(entityName);
        String repoVar     = varName + "Repository";
        String serviceVar  = varName + "Service";

        return """
                package %s.Service;

                import %s.Exception.ResourceNotFound;
                import %s.Lib.BaseResponse;
                import %s.Model.%sModel;
                import %s.Repository.%sRepository;
                import org.springframework.beans.factory.annotation.Autowired;
                import org.springframework.stereotype.Service;

                import java.util.List;

                @Service
                public class %sServiceImp implements %sService {

                    @Autowired %sRepository %s;

                    @Override
                    public BaseResponse<%sModel> createRecord(%sModel record) {
                        %sModel %s = %s.save(record);
                        return BaseResponse.ok(%s);
                    }

                    @Override
                    public BaseResponse<List<%sModel>> getRecords() {
                        List<%sModel> %ss = %s.findAll();
                        return BaseResponse.ok(%ss);
                    }

                    @Override
                    public BaseResponse<%sModel> getRecordById(String id) {
                        %sModel %s = %s.findById(id)
                                .orElseThrow(() -> new ResourceNotFound(
                                        String.format("The %s with id %%s has not been found", id)));
                        return BaseResponse.ok(%s);
                    }

                    @Override
                    public BaseResponse<%sModel> deleteRecord(String id) {
                        return null;
                    }
                }
                """.formatted(
                        basePackage,
                        basePackage, basePackage, basePackage, entityName,
                        basePackage, entityName,
                        entityName, entityName,
                        entityName, repoVar,
                        entityName, entityName,
                        entityName, varName, repoVar, varName,
                        entityName, entityName, varName, repoVar, varName,
                        entityName,
                        entityName, varName, repoVar,
                        varName,
                        varName,
                        entityName
                );
    }
}
