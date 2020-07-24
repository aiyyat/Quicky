package config;

import akka.actor.ActorSystem;
import play.libs.concurrent.CustomExecutionContext;
import play.libs.concurrent.HttpExecution;

import javax.inject.Inject;
import java.util.concurrent.Executor;
import java.util.concurrent.CompletionStage;
import static java.util.concurrent.CompletableFuture.supplyAsync;
public class DatabaseExecutionContext extends CustomExecutionContext {
  @Inject
  public DatabaseExecutionContext(ActorSystem actorSystem) {
    super(actorSystem, "database.dispatcher ");
  }
}
