package output.models;

import com.msiops.ground.either.Either;

public interface JsonParser<Data> {
  /**
   * Parse json according to response model
   * @param json the string to parse
   * @param responseModel the model that describes the response.
   * @return either a successful response (left) or an exception (right)
   */
  Either<Data, Exception> parseJson(String json, ResponseModel<Data> responseModel);
}
