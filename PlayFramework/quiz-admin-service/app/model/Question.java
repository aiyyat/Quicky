package model;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@ToString
@Builder
@Entity
@Table(name = "question")
@NoArgsConstructor
@AllArgsConstructor
public class Question {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  String question;
  @OneToOne(cascade = ALL,fetch = EAGER)
  @Setter(AccessLevel.NONE)
  private Answer answer;

  public void setAnswer(Answer answer) {
    answer.setQuestion(this);
    this.answer = answer;
  }
}
