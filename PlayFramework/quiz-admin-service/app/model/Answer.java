package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.DiscriminatorType.CHAR;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.JOINED;

@DiscriminatorColumn(name = "type", discriminatorType = CHAR)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("a")
@Table(name = "answer")
@Inheritance(strategy = JOINED)
@Data
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = MultipleChoiceTypeAnswer.class, name = "choice"),
    @JsonSubTypes.Type(value = DescriptiveTypeAnswer.class, name = "descriptive")
})
public class Answer {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @OneToOne(mappedBy = "answer")
  @JsonIgnore
  Question question;
}
