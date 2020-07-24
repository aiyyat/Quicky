package repository;

import com.google.inject.ImplementedBy;
import model.Question;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ImplementedBy(JPAQuestionRepository.class)
public interface QuestionRepository {
  public CompletionStage<Question> save(Question question);

  public CompletionStage<List<Question>> findAll(Integer page);

  public CompletableFuture<Void> delete(Long id);

  CompletionStage<Question> findOne(Long id);
}
