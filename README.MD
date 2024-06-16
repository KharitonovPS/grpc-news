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



### Добавление суности

```mutation{
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
}```