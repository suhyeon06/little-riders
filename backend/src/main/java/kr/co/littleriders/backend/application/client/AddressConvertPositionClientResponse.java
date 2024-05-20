package kr.co.littleriders.backend.application.client;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import kr.co.littleriders.backend.global.error.code.InputInvalidErrorCode;
import kr.co.littleriders.backend.global.error.exception.InputInvalidException;
import lombok.Getter;

import java.io.IOException;

@Getter
@JsonDeserialize(using = AddressConvertPositionClientResponse.Deserializer.class)
public class AddressConvertPositionClientResponse {


    private final String address;
    private final double latitude;
    private final double longitude;

    private AddressConvertPositionClientResponse(String address, double latitude, double longitude){
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }



    public static class Deserializer extends JsonDeserializer<AddressConvertPositionClientResponse> {
        @Override
        public AddressConvertPositionClientResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)  {
            try {
                JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);
                JsonNode addressesNode = rootNode.get("addresses");
                if (addressesNode == null || addressesNode.size() != 1) {
                    throw new IOException("Address count should be exactly 1");
                }

                JsonNode addressNode = addressesNode.get(0);

                String address = addressNode.get("roadAddress").asText();
                double latitude = addressNode.get("y").asDouble();
                double longitude = addressNode.get("x").asDouble();

                return new AddressConvertPositionClientResponse(address,latitude,longitude);
            }
            catch (Exception e){
                throw InputInvalidException.from(InputInvalidErrorCode.ADDRESS);
            }

        }
    }

}

