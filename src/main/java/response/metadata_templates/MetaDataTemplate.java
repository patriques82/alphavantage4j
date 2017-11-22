package response.metadata_templates;

import response.MetaData;

import java.util.Map;

public interface MetaDataTemplate {
  MetaData resolve(Map<String, String> metaDataResponse);
}
