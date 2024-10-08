package com.iapurba.bookapp.mapper;

import com.iapurba.bookapp.dto.AuthorDto;
import com.iapurba.bookapp.model.entity.Author;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapper implements Mapper<Author, AuthorDto> {

    @Override
    public AuthorDto toDto(Author author) {
        if (author == null) return null;

        return AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .biography(author.getBiography())
                .build();
    }

    @Override
    public Author toEntity(AuthorDto authorDto) {
        if (authorDto == null) return null;

        Author author = new Author();
        author.setId(authorDto.getId());
        author.setName(authorDto.getName());
        author.setBiography(authorDto.getBiography());

        return author;
    }

    public List<AuthorDto> convertToDtoList(List<Author> authors) {
        return authors.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Author> convertToEntityList(List<AuthorDto> authors) {
        return authors.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
