package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.tests.vendorportaltest.model.VendorPortalTestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class JSONUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(JSONUtil.class);
    public static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T getTestData(String path, Class<T> type) {
        try (InputStream stream = ResourceLoader.getResource(path)) {
            return mapper.readValue(stream, type);
        } catch (Exception e) {
            LOGGER.error("Unable to load test data from path: {}", path, e);
        }
        return null;
    }
}
