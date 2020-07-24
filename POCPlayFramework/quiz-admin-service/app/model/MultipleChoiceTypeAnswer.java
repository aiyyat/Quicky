package model;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static lombok.AccessLevel.NONE;

@DiscriminatorValue("c")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@Table(name = "multiple")
public class MultipleChoiceTypeAnswer extends Answer {
  @Setter(NONE)
  @OneToMany(mappedBy = "correspondingAnswer", fetch = EAGER, cascade = ALL)
  List<Choice> choiceList;

  public void setChoiceList(List<Choice> choiceList) {
    choiceList.forEach(choice -> choice.setCorrespondingAnswer(this));
    this.choiceList = choiceList;
  }

  public String toString() {
    return choiceList.toString();
  }
}
