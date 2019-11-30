package com.xyz.query.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_post_action")
public class UserPostAction {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", referencedColumnName = "id")
  private User post;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_action", referencedColumnName = "id")
  private PostAction postAction;

  @Column(name = "date")
  private LocalDateTime date;
}
