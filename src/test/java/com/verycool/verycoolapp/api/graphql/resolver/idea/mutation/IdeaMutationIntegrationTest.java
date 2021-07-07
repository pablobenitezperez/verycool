package com.verycool.verycoolapp.api.graphql.resolver.idea.mutation;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.verycool.verycoolapp.application.idea.IdeaService;
import com.verycool.verycoolapp.application.idea.input.CreateIdeaInput;
import com.verycool.verycoolapp.domain.category.Category;
import com.verycool.verycoolapp.domain.idea.Idea;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

//@GraphQLTest
public class IdeaMutationIntegrationTest {

    private static final String GRAPHQL_QUERY_REQUEST_PATH = "graphql/%s.graphql";
    private static final Category CATEGORY = new Category("name","description");

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    IdeaService ideaService;

    @Test
    @Disabled
    public void createIdea() throws IOException {
        Idea idea = new Idea("title", "text", CATEGORY);

        CreateIdeaInput ideaInput = new CreateIdeaInput();
        ideaInput.setText("text");
        ideaInput.setTitle("title");

        doReturn(idea).when(ideaService).create(ideaInput);

        GraphQLResponse response = graphQLTestTemplate.postForResource(format(GRAPHQL_QUERY_REQUEST_PATH, "getIdeas"));
        assertTrue(response.isOk());
    }
}
