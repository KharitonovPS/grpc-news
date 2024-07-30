create table if not exists news
(
  id         bigint primary key,
  name       varchar(255),
  page_count int,
  rating     varchar(16),
  author_id  bigint references author (id)
);

create table if not exists author
(
  id        bigint primary key,
  firstName varchar(255),
  lastName  varchar(255)
);
