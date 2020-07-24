package parser;

import akka.util.ByteString;
import com.fasterxml.jackson.databind.JsonNode;
import model.Question;
import play.libs.F;
import play.libs.streams.Accumulator;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import java.util.concurrent.Executor;

import static play.libs.Json.fromJson;

public class QuestionBodyParser implements BodyParser<Question> {
    @Inject
    private BodyParser.Json jsonParser;
    @Inject
    private Executor executor;

    @Override
    public Accumulator<ByteString, F.Either<Result, Question>> apply(Http.RequestHeader request) {
        Accumulator<ByteString, F.Either<Result, JsonNode>> jsonAccumulator = jsonParser.apply(request);
        return jsonAccumulator.map(resultOrJson -> {
            try {
                if (resultOrJson.left.isPresent()) {
                    return F.Either.Left(resultOrJson.left.get());
                } else {
                    Question question = fromJson(resultOrJson.right.get(), Question.class);
                    return F.Either.Right(question);
                }
            } catch (Exception e) {
                return F.Either.Left(Results.badRequest(e.getMessage()));
            }
        }, executor);
    }
}
