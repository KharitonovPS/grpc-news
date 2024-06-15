### Получение сущностей по ID




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