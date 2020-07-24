package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "descriptive")
@DiscriminatorValue("d")
public class DescriptiveTypeAnswer extends Answer {
  private String answer;
}
