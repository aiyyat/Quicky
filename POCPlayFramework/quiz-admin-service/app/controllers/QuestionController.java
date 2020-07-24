package controllers;

import model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parser.QuestionBodyParser;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import repository.QuestionRepository;

import javax.inject.Inject;
import java.util.Map;
import java.util.concurrent.CompletionStage;

public class QuestionController extends Controller {
  @Inject
  QuestionRepository questionRepository;

  private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

  public Result health(Http.Request request) {
    return ok(Json.toJson(Map.of("version", "1.0", "random", Math.random())));
  }

  public CompletionStage<Result> getQuestions(Integer page) {
    return questionRepository.findAll(page).thenApply(result -> {
      return ok(Json.toJson(result));
    }).exceptionally(error -> {
      logger.error(error.getMessage(), error);
      return internalServerError(error.getMessage());
    });
  }

  public CompletionStage<Result> getQuestionById(Long id) {
    return questionRepository.findOne(id)
        .thenApply(result -> ok(Json.toJson(result)))
        .exceptionally(error -> {
          logger.error(error.getMessage(), error);
          return internalServerError(error.getMessage());
        });
  }

  @BodyParser.Of(QuestionBodyParser.class)
  public CompletionStage<Result> createQuestion(Http.Request request) {
    Question question = request.body().as(Question.class);
    return questionRepository.save(question)
        .thenApply(result -> ok(Json.toJson(result)))
        .exceptionally(error -> {
          logger.error(error.getMessage(), error);
          return internalServerError(error.getMessage());
        });
  }

  public Result deleteQuestion(Long id) {
    try {
      questionRepository.delete(id).get();
      return ok();
    } catch (Exception e) {
      return internalServerError(e.getMessage());
    }
  }

  @BodyParser.Of(QuestionBodyParser.class)
  public Result amendQuestion(Http.Request request) {
    Question question = request.body().as(Question.class);
    logger.info("Amending...{}", question);
    return ok(Json.toJson(question));
  }
}
