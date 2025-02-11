package software.amazon.rds.dbcluster;

import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.amazonaws.util.CollectionUtils;

class Configuration extends BaseConfiguration {
    public Configuration() {
        super("aws-rds-dbcluster.json");
    }

    public JSONObject resourceSchemaJsonObject() {
        return new JSONObject(
                new JSONTokener(this.getClass().getClassLoader().getResourceAsStream(schemaFilename)));
    }

    public Map<String, String> resourceDefinedTags(final ResourceModel model) {
        if (CollectionUtils.isNullOrEmpty(model.getTags())) {
            return null;
        }

        return model.getTags()
                .stream()
                .collect(Collectors.toMap(Tag::getKey, Tag::getValue, (v1, v2) -> v2));
    }
}
