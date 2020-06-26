package mx.com.teclo.svi.negocio.enumerable;

import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Singleton Object Mapper para deserializar un JSON
 * 
 * @author UnitisODM2
 *
 */
public enum EnumObjectMapper {

	INSTANCE;
	private final ObjectMapper mapper = new ObjectMapper();

	private EnumObjectMapper() {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public ObjectMapper getObjectMapper() {
		return mapper;
	}

	public <T> T fromJson(String json, TypeReference<T> typeRef) {
		try {
			return mapper.readValue(json, typeRef);
		} catch (IOException e) {
			return null;
		}
	}
}
