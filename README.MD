### Получение сущностей по ID

```
// можем добавлять и убирать поля в запросе, не меняя dto
query newsDetails {
  newsById(id: "news-1") {
    id
    name
    pageCount
    author {
      firstName
      lastName
    }
  }
}
```

### FindAll()

```
query All {
  findAll {
    id
    name
    pageCount
    author {
      id
      firstName
      lastName
    }
  }
```

### Добавление суности

```
mutation{
//название метода и инпут парам мапится в контроллер
	  addNews(newsInput: 
    {
        //парамы dto
      name: "Celebration2",
      pageCount: 1423,
      authorId: "author-2"
    } ){
    // то что вернет запрос
    id 
    name
  } 
}
```

### update

```
mutation upd {
  updateNews(
    id: "news-3"
    newsInput: {name: "Java 4 stupids", pageCount: 123, authorId: "author-1"}
  ) {
    id
    name
    pageCount
    author {
      id
      firstName
      lastName
    }
  }
}
```