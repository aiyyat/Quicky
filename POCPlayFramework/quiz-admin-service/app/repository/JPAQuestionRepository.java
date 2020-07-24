package repository;

import com.typesafe.config.Config;
import config.DatabaseExecutionContext;
import model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Singleton
public class JPAQuestionRepository implements QuestionRepository {
  private JPAApi jpaApi;
  private DatabaseExecutionContext executionContext;
  private Config config;
  private static final Logger logger = LoggerFactory.getLogger(JPAQuestionRepository.class);

  @Inject
  public JPAQuestionRepository(JPAApi api, DatabaseExecutionContext executionContext, Config config) {
    this.jpaApi = api;
    this.executionContext = executionContext;
    this.config = config;
  }

  public CompletionStage<Question> save(Question question) {
    return CompletableFuture.supplyAsync(() -> jpaApi.withTransaction(entityManager -> {
      entityManager.persist(question);
      return question;
    }), executionContext);
  }

  @Override
  public CompletionStage<List<Question>> findAll(Integer page) {
    int pageSize = config.getInt("result.pagesize");
    return CompletableFuture.supplyAsync(() -> {
      return jpaApi.withTransaction(entityManager -> {
        return entityManager.createQuery(
            "select q " +
                "from Question q " +
                "order by q.id ")
            .setFirstResult(page * pageSize)
            .setMaxResults(pageSize)
            .getResultList();
      });
    });
  }

  @Override
  public CompletableFuture<Void> delete(Long id) {
    return CompletableFuture.supplyAsync(() -> {
      return jpaApi.withTransaction(entityManager -> {
        Question question = entityManager.find(Question.class, id);
        entityManager.remove(question);
        return null;
      });
    });
  }

  @Override
  public CompletionStage<Question> findOne(Long id) {
    return CompletableFuture.supplyAsync(() -> {
      return jpaApi.withTransaction(entityManager -> {
        return entityManager.find(Question.class, id);
      });
    });
  }
}
