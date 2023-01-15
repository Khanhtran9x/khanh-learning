package com.example.cmc.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "articles")
public class Article {

    @Id
    private String id;

    @MultiField(mainField = @Field(type = FieldType.Text, fielddata = true), otherFields = { @InnerField(suffix = "verbatim", type = FieldType.Keyword)})
    private String title;

    @Field(type = FieldType.Text, name = "content")
    private String content;

    @Field(type = FieldType.Text, name = "author")
    private String Author;
}
