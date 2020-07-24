package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@Entity
@Table(name = "choice")
@NoArgsConstructor
@AllArgsConstructor
public class Choice {
  private String description;
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(name = "is_answer")
  @Getter(AccessLevel.NONE)
  private Boolean isAnswer = false;

  @JsonIgnore
  @ManyToOne(cascade = ALL)
  @JoinColumn(name = "answer_id", referencedColumnName = "id", nullable = false)
  private Answer correspondingAnswer;

  public Boolean getIsAnswer() {
    return (isAnswer != null) ? isAnswer : false;
  }
}
